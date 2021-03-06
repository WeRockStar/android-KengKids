package kku.freestyledev.learn;

import kku.freestyledev.ikids.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryOne extends Activity implements OnGestureListener {

	private static ImageView myImage;
	private static ImageButton btnNext;
	private static ImageButton btnBack;
	private static int[] arrImage;
	private static int indexLength;
	private static int index;
	private static TextView txtPage;
	Typeface tf;

	GestureDetector gd;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter_one);

		index = 0;
		tf = Typeface.createFromAsset(getAssets(), "ZoodHardSell.ttf");

		arrImage = new int[] { R.drawable.story_one_1, R.drawable.story_one_2,
				R.drawable.story_one_3, R.drawable.story_one_4,
				R.drawable.story_one_5, R.drawable.story_one_6,
				R.drawable.story_one_7, R.drawable.story_one_8,
				R.drawable.story_one_9, R.drawable.story_one_10,
				R.drawable.story_one_11, R.drawable.story_one_12,
				R.drawable.story_one_13, R.drawable.story_one_14,
				R.drawable.story_one_15, R.drawable.story_one_16,
				R.drawable.story_one_17, R.drawable.story_one_18,
				R.drawable.story_one_19, R.drawable.story_one_20,
				R.drawable.story_one_21 };

		// gesture
		gd = new GestureDetector(this, this);
		// Page
		txtPage = (TextView) findViewById(R.id.txtPage);
		txtPage.setTypeface(tf);
		txtPage.setText("1/" + arrImage.length);

		myImage = (ImageView) findViewById(R.id.imgStory);
		btnNext = (ImageButton) findViewById(R.id.imgBtnRight);
		btnBack = (ImageButton) findViewById(R.id.imgBtnLeft);

		btnNext.setEnabled(true);

		// Next
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextStory();
			}
		});

		// Back
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				backStory();
			}
		});
	}

	public static void nextStory() {
		btnNext.setEnabled(true);
		btnBack.setEnabled(true);

		if (index == arrImage.length - 1) {
			btnNext.setEnabled(false);
		} else {
			myImage.setImageResource(arrImage[++index]);
			txtPage.setText((index + 1) + "/" + arrImage.length);
		}
		Log.e("index : " + index, "index : " + index);
	}

	public static void backStory() {
		btnNext.setEnabled(true);
		btnBack.setEnabled(true);

		if (index == 0) {
			btnBack.setEnabled(false);
		} else {
			myImage.setImageResource(arrImage[--index]);
			txtPage.setText((index + 1) + "/" + arrImage.length);
		}

		Log.e("index : " + index, "index : " + index);

	}

	// Touch
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gd.onTouchEvent(event))
			return true;
		else
			return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {

		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		try {
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			// Left
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// Next Medthod
				nextStory();

				// Right
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// Back Medthod
				backStory();
			}

		} catch (Exception e) {
			// nothing
		}

		return true;
	}
}

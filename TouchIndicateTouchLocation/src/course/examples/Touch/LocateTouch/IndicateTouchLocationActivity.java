package course.examples.Touch.LocateTouch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class IndicateTouchLocationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
		frame.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("Motion Event start");

				int actionCode = event.getActionMasked();
				int arity = event.getPointerCount();
				for (int i = 0; i < arity; i++) {
					float x = event.getX(i);
					float y = event.getY(i);
					System.out.println("Action:" + actionCode + " Motion Event:" + event.getPointerId(i) + " x:" + x + " y:" + y);

					switch (actionCode) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_POINTER_DOWN:
					case MotionEvent.ACTION_MOVE:
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_POINTER_UP: {
						MarkerView marker = new MarkerView(
								IndicateTouchLocationActivity.this, x, y,actionCode);
						frame.addView(marker);
						break;
					}
					}
					System.out.println("Motion Event end");
				}
				return true;
			}
		});
		
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				frame.removeAllViews();
			}
		});
	}

	private class MarkerView extends View {
		float x, y;
		Paint painter = new Paint();
		int size=10, type, arity=0;

		public MarkerView(Context context, float x, float y, int type) {
			super(context);
			this.x = x;
			this.y = y;
			this.type = type;
			painter.setStyle(Style.STROKE);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			switch (type) {
			case MotionEvent.ACTION_DOWN: 
			case MotionEvent.ACTION_POINTER_DOWN: {
				arity++;
				break;
			}
			case MotionEvent.ACTION_UP: 	
			case MotionEvent.ACTION_POINTER_UP: {
				arity--;
				break;
			}
			}
			switch (arity) {
			case 1: {
				painter.setColor(Color.RED);
				break;
				}
			case 2: {
				painter.setColor(Color.YELLOW);
				break;
			}
			default: {
					painter.setColor(Color.GREEN);
				break;
			}
			}
			canvas.drawCircle(x, y, size, painter);
		}

	}

}
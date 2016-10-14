package course.examples.Graphics.ShapeDraw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ShapeDrawActivity extends Activity {
	int size = 160, spacer = 50, alpha = 127;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		RelativeLayout rl = (RelativeLayout) findViewById(R.id.main_window);
		myDrawableView magentaView = new myDrawableView(this, Color.MAGENTA);
		magentaView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
		myDrawableView cyanView = new myDrawableView(this, Color.CYAN);
		cyanView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
		ImageView spacerView = new ImageView(this);
		spacerView.setMinimumWidth(spacer);
		spacerView.setAlpha(0);
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.addView(spacerView);
		ll.addView(cyanView);
		rl.addView(ll);
		rl.addView(magentaView);
	}

	private class myDrawableView extends ImageView {
		private ShapeDrawable mDrawable;

		public myDrawableView(Context context, int color) {
			super(context);
			mDrawable = new ShapeDrawable(new OvalShape());
			mDrawable.getPaint().setColor(color);
			mDrawable.setBounds(0, 0, size, size);
			mDrawable.setAlpha(alpha);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			mDrawable.draw(canvas);
		}

	}
}
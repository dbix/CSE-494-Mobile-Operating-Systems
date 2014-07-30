package edu.asu.cse494_assignment_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class GraphView extends View {

	public static boolean BAR = false;
	public static boolean LINE = true;

	private Paint paint;
	private float[] data;
	private String[] horlabels;
	private String[] verlabels;
	private String title;
	private boolean type;

	public GraphView(Context context) {
		super(context);
		init(null, 0);
		setValues(new float[] { 0.0f }, "Graph View",
				new String[] { "Horizontal Label" },
				new String[] { "Vertical Label" }, false);
	}

	public GraphView(Context context, float[] values, String title,
			String[] horlabels, String[] verlabels, boolean type) {
		super(context);
		init(null, 0);
		setValues(values, title, horlabels, verlabels, type);
	}

	public GraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		setValues(new float[] { 0.0f }, "Graph View",
				new String[] { "Horizontal Label" },
				new String[] { "Vertical Label" }, false);
	}

	public void setValues(float[] values, String title, String[] horlabels,
			String[] verlabels, boolean type) {
		// Load attributes
		if (values == null)
			values = new float[0];
		else
			this.data = values;
		if (title == null)
			title = "";
		else
			this.title = title;
		if (horlabels == null)
			this.horlabels = new String[0];
		else
			this.horlabels = horlabels;
		if (verlabels == null)
			this.verlabels = new String[0];
		else
			this.verlabels = verlabels;
		this.type = type;
		paint = new Paint();
	}

	public void setData(float[] arr) {
		this.data = arr;
	}

	public float[] getData() {
		return this.data;
	}

	private float getMax() {
		float largest = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++)
			if (data[i] > largest)
				largest = data[i];

		// largest = 3000;
		return largest;
	}

	private float getMin() {
		float smallest = Integer.MAX_VALUE;
		for (int i = 0; i < data.length; i++)
			if (data[i] < smallest)
				smallest = data[i];

		// smallest = 0;
		return smallest;
	}
	
	/*
	 * @Override protected void onDraw(Canvas canvas) { super.onDraw(canvas); }
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		float border = 20;
		float horstart = border * 2;
		float height = getHeight();
		float width = getWidth() - 1;
		float max = getMax();
		float min = getMin();
		float diff = max - min;
		float graphheight = height - (2 * border);
		float graphwidth = width - (2 * border);

		paint.setTextAlign(Align.LEFT);
		int vers = verlabels.length - 1;

		for (int i = 0; i < verlabels.length; i++) {
			paint.setColor(Color.BLACK);
			paint.setStrokeWidth(1);
			float y = ((graphheight / vers) * i) + border;
			canvas.drawLine(horstart, y, width, y, paint);
			paint.setColor(Color.BLACK);
			canvas.drawText(verlabels[i], 0, y, paint);
		}

		int hors = horlabels.length - 1;

		for (int i = 0; i < horlabels.length; i++) {
			paint.setColor(Color.BLACK);
			float x = ((graphwidth / hors) * i) + horstart;
			paint.setStrokeWidth(1);
			canvas.drawLine(x, height - border, x, border, paint);
			paint.setTextAlign(Align.CENTER);
			if (i == horlabels.length - 1)
				paint.setTextAlign(Align.RIGHT);
			if (i == 0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.BLACK);
			canvas.drawText(horlabels[i], x, height - 4, paint);
		}

		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

		if (max != min) {
			paint.setColor(Color.BLACK);
			if (type == BAR) {
				float datalength = data.length;
				float colwidth = (width - (2 * border)) / datalength;
				for (int i = 0; i < data.length; i++) {
					float val = data[i] - min;
					float rat = val / diff;
					float h = graphheight * rat;
					float left = (i * colwidth) + horstart;
					float top = (border - h) + graphheight;
					float bottom = ((i * colwidth) + horstart) + (colwidth - 1);
					float right = height - (border - 1);
					canvas.drawRect(left, top, bottom, right, paint);
				}
			} else {
				float datalength = data.length;
				float colwidth = (width - (2 * border)) / datalength;
				float halfcol = colwidth / 2;
				float lasth = 0;
				for (int i = 0; i < data.length; i++) {
					float val = data[i] - min;
					float rat = val / diff;
					float h = graphheight * rat;
					if (i > 0) {
						paint.setColor(Color.BLACK);
					}
					paint.setStrokeWidth(4.0f);
					float left = ((i - 1) * colwidth) + (horstart + 1)
							+ halfcol;
					float top = (border - lasth) + graphheight;
					float bottom = (i * colwidth) + (horstart + 1) + halfcol;
					float right = (border - h) + graphheight;
					canvas.drawLine(left, top, bottom, right, paint);
					lasth = h;
				}
			}
		}
	}

}

package com.example.rajesh.drawing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	public static float STROKE_WIDTH = 5f;

	/** Need to track this so the dirty region can accommodate the stroke. **/
	 
	private Paint paint = new Paint();
	private Path path;
	private List<Path> paths = new ArrayList<Path>();
	private List<Paint> paints = new ArrayList<Paint>();
public static int clr;
	/**
	 * Optimizes painting by invalidating the smallest possible area.
	 */
	private float lastTouchX;
	private float lastTouchY;
 

 

	

	 

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		clr=Color.BLACK;
		
	}

	/**
	 * Erases the signature.
	 */
	public void clear() {
		for (Path path : paths) {
			path.reset();
		}
		// Repaints the entire view.
		invalidate();
	}
	public void undo() {
		//	for (Path path : paths) {
			Path pth= paths.get(paths.size()-1);
				pth.reset();
				
				paths.remove(paths.size()-1);
			//	return;
			//}
			// Repaints the entire view.
			invalidate();
		}
	@Override
	protected void onDraw(Canvas canvas) {
		int i = 0;
		for (Path path : paths) {
			canvas.drawPath(path, paints.get(i++));
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path = new Path();
			paths.add(path);

			paint = new Paint();
			paints.add(paint);
			paint.setAntiAlias(true);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);

			paint.setColor(clr);
			 
			path.moveTo(eventX, eventY);
			lastTouchX = eventX;
			lastTouchY = eventY;
			// There is no end point yet, so don't waste cycles invalidating.

			return true;

		case MotionEvent.ACTION_MOVE:
			
		case MotionEvent.ACTION_UP:

			// When the hardware tracks events faster than they are delivered,
			// the
			// event will contain a history of those skipped points.
			int historySize = event.getHistorySize();
			for (int i = 0; i < historySize; i++) {
				float historicalX = event.getHistoricalX(i);
				float historicalY = event.getHistoricalY(i);
				path.lineTo(historicalX, historicalY);
			}

			// After replaying history, connect the line to the touch point.
			path.lineTo(eventX, eventY);
			break;

		default:
			return false;
		}

		// Include half the stroke width to avoid clipping.
		invalidate();

		lastTouchX = eventX;
		lastTouchY = eventY;

		return true;
	}

}

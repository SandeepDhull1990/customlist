package com.example.androidcustomlist.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

public class CustomListView extends AdapterView<Adapter> {

	// Adapter that will act as a datasource and provide child views.
	private Adapter mAdapter;

	// Touch coordinate handling
	private int mTouchStartX;

//	To track the leftmost point of the list
	private int mListLeftStart;

	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListView(Context context) {
		super(context);
	}

	// Implement Abstract methods from Super Class - Adapter View
	@Override
	public Adapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void setAdapter(Adapter adapter) {
		this.mAdapter = adapter;
		removeAllViewsInLayout();
		requestLayout();
	}

	@Override
	public View getSelectedView() {
		throw new UnsupportedOperationException("Not Supported Yet");
	}

	@Override
	public void setSelection(int position) {
		throw new UnsupportedOperationException("Not Supported Yet");
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (this.mAdapter == null) {
			return;
		}

		if (getChildCount() == 0) {
			int position = 0;
			int rightEdge = 0;

			while ((rightEdge < getWidth()) && (position < mAdapter.getCount())) {
				View child = mAdapter.getView(position, null, this);
				addAndMesaureChild(child);
				position++;
				rightEdge = child.getWidth();
			}
		}
		positionItems();
	}

	private void addAndMesaureChild(View child) {
		LayoutParams params = child.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		addViewInLayout(child, -1, params, true);

		int height = getHeight();
		child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY | height);
	}

	private void positionItems() {
		int left = mListLeftStart;
		for (int index = 0; index < getChildCount(); index++) {
			View view = getChildAt(index);
			int width = view.getMeasuredWidth();
			int height = view.getMeasuredHeight();

			int top = getTop();
			view.layout(left, top, left + width, top + height);
			left += width;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (getChildCount() == 0) {
			// Discarding touch event if the child count is 0
			return false;
		}

		switch (event.getAction()) {
		
			case MotionEvent.ACTION_DOWN:
				mTouchStartX = (int) event.getX();
				mListLeftStart = getChildAt(0).getLeft();
				break;
	
			case MotionEvent.ACTION_MOVE:
				int scrolledDistance = (int) event.getX() - mTouchStartX;
				mListLeftStart += scrolledDistance;
				requestLayout();
//				mTouchStartX = (int) event.getX();
				break;
			
			default:
				break;
		}

		return true;
	}

}

package com.example.androidcustomlist.adapter;

import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private ArrayList<String> mStringList;
	private Context mContext;
	
	public CustomAdapter(Context context, ArrayList<String> list) {
		this.mContext = context;
		this.mStringList = list;
	}
	
	@Override
	public int getCount() {
		return this.mStringList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = new TextView(mContext);
		}
		((TextView)convertView).setText("Position = " + position);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, 200);
		convertView.setLayoutParams(params);
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		convertView.setBackgroundColor(color);
		
		return convertView;
	}

}

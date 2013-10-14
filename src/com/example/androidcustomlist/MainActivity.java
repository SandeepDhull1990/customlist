package com.example.androidcustomlist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.example.androidcustomlist.adapter.CustomAdapter;
import com.example.androidcustomlist.customview.CustomListView;

public class MainActivity extends Activity {

	private CustomListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (CustomListView)findViewById(R.id.activity_main_listView);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");

		CustomAdapter adapter = new CustomAdapter(this, list);
		mListView.setAdapter(adapter);
	}

}

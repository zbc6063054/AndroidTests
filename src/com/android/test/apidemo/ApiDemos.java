package com.android.test.apidemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ApiDemos extends ListActivity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new SimpleAdapter(this, 
				getData(null), android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{ android.R.id.text1 }));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	protected List getData(String prefix)
	{
		List<Map> list=new ArrayList<Map>();
		
		Map<String,Object> temp=new HashMap<String,Object>();
		temp.put("title", "one");
		temp.put("intent", null);
		
		list.add(temp);
		
		Intent intent=new Intent(Intent.ACTION_MAIN);
		intent.addCategory(CATEGORY_TEST);
		
		
		
		return list;
	}

	public static final String CATEGORY_TEST="com.mytest";
	
}

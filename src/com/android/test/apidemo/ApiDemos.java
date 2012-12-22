package com.android.test.apidemo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ApiDemos extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String prefix = intent.getStringExtra("com.android.test.apis.Path");

		if (prefix == null)
			prefix = "";

		setListAdapter(new SimpleAdapter(this, getData(prefix),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Map map = (Map) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		if (intent == null) {
			Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
			return;
		}
		startActivity(intent);
	}

	protected List getData(String prefix) {
		List<Map> myData = new ArrayList<Map>();

		String[] prefixPath;
		
		if (prefix == "") {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
		}

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(CATEGORY_TEST);

		PackageManager pm = getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);

		Map<String, Boolean> entries = new HashMap<String, Boolean>();

		int size = list.size();
		for (int i = 0; i < size; ++i) {
			ResolveInfo info = list.get(i);
			CharSequence labelseq = info.loadLabel(pm);
			String label = labelseq != null ? labelseq.toString()
					: info.activityInfo.name;

			if (prefix.length() == 0 || label.startsWith(prefix)) {

				String[] labelPath = label.split("/");
				String nextlabel = prefixPath == null ? labelPath[0]
						: labelPath[prefixPath.length];
				
				if ((prefixPath == null ? 0 : prefixPath.length) == labelPath.length - 1) {
					addItem(myData, nextlabel, activityIntent(
							info.activityInfo.applicationInfo.packageName,
							info.activityInfo.name));
				} else {
					if (entries.get(nextlabel) == null) {
						entries.put(nextlabel, true);
						addItem(myData, nextlabel, browserIntent(prefix == "" ? nextlabel
								: prefix + '/' + nextlabel));
					}
				}
			}
		}
		Collections.sort(myData, sDispalyNameComparator);
		
		return myData;
	}

	private static final Comparator<Map> sDispalyNameComparator=new Comparator<Map>() {
		private final Collator   collator = Collator.getInstance();
		@Override
		public int compare(Map map1, Map map2) {
			return collator.compare(map1.get("title"), map2.get("title"));
		}
	};
	
	protected Intent activityIntent(String pkg, String componentName) {
		Intent intent = new Intent();
		intent.setClassName(pkg, componentName);
		return intent;
	}

	protected Intent browserIntent(String path) {
		Intent result = new Intent(this, ApiDemos.class);
		result.putExtra("com.android.test.apis.Path", path);
		return result;
	}

	protected void addItem(List<Map> data,String label,Intent intent){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", label);
		map.put("intent", intent);
		data.add(map);
	}
	
	public static final String CATEGORY_TEST = "com.mytest";

}

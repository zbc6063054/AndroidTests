package com.android.test.apidemo;

import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Map map = (Map) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		if (intent == null) {
			Toast.makeText(this, "Intent null", Toast.LENGTH_SHORT).show();
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

				Map<String, Object> map = new HashMap<String, Object>();
				Intent intent2 = null;
				if ((prefixPath == null ? 0 : prefixPath.length) == labelPath.length - 1) {
					intent2 = new Intent();
					intent2.setClassName(
							info.activityInfo.applicationInfo.packageName,
							info.activityInfo.name);
				} else {
					if (entries.get(nextlabel) == null) {
						entries.put(nextlabel, true);
						intent2 = new Intent(this, this.getClass());
						intent2.putExtra("com.android.test.apis.Path",
								prefix == "" ? nextlabel : prefix + '/'
										+ nextlabel);
					}
				}

				map.put("title", nextlabel);
				map.put("intent", intent2);
				myData.add(map);
			}

		}

		return myData;
	}

	public static final String CATEGORY_TEST = "com.mytest";

}

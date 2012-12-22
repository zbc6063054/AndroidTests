package com.android.test.apidemo.app;


import com.android.test.apidemo.R;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorld extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.hello_world);
	}

}

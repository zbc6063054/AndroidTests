package com.android.test.apidemo.app;

import com.android.test.apidemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class TranslucentBlurActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, 
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		
		setContentView(R.layout.translucent_background);
	}

}

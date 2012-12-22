package com.android.test.apidemo.app;

import com.android.test.apidemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTitle extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.customtitle);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custome_title_1);
		
		final TextView leftText=(TextView)findViewById(R.id.left_text);
		final TextView rightText=(TextView)findViewById(R.id.right_text);
		final EditText leftTextEdit=(EditText)findViewById(R.id.left_text_edit);
		final EditText rightTextEdit=(EditText)findViewById(R.id.right_text_edit);
		
		Button leftButton=(Button)findViewById(R.id.left_text_button);
		leftButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				leftText.setText(leftTextEdit.getText().toString());
			}
		});
		
		Button rightButton=(Button)findViewById(R.id.right_text_button);
		rightButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				rightText.setText(rightTextEdit.getText().toString());
			}
		});
		
	}

}

package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class WhyActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whythisapp);
						
		TextView instructTextView = (TextView) findViewById(R.id.whyappTextview);
		instructTextView.setText(getResources().getString(R.string.whyapp_text));
		
		setTitle(getResources().getString(R.string.whyapp_title));
	}
}

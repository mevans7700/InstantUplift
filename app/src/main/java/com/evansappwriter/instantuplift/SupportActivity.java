package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SupportActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.support);
						
		TextView instructTextView = (TextView) findViewById(R.id.supportTextview);
		instructTextView.setText(getResources().getString(R.string.support_text));
		
		setTitle(getResources().getString(R.string.support_title));
	}
}

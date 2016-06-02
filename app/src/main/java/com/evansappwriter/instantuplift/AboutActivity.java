package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
						
		TextView instructTextView = (TextView) findViewById(R.id.aboutauthorTextview);
		instructTextView.setText(getResources().getString(R.string.about_text));
		
		setTitle(getResources().getString(R.string.about_title));
	}
}

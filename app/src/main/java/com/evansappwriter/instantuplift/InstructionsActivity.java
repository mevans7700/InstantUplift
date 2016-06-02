package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InstructionsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
						
		TextView instructTextView = (TextView) findViewById(R.id.instructionsTextview);
		instructTextView.setText(getResources().getString(R.string.instructions_text));
		
		setTitle(getResources().getString(R.string.instructions_title));
	}
}

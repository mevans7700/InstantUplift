package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MoodNotesActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mood_notes);
						
		TextView aboutTextView = (TextView) findViewById(R.id.moodnotesTextview);
		aboutTextView.setText(getResources().getString(R.string.moodnotes_text));
		
		setTitle(getResources().getString(R.string.moodnotes_title));
	}
}

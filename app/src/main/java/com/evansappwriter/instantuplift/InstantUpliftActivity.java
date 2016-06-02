package com.evansappwriter.instantuplift;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class InstantUpliftActivity extends ListActivity implements OnClickListener {
	public static final String UPLIFT_PREFERENCES = "UpliftPrefs";
	public static final String UPLIFT_PREFERENCES_PHRASE_ID = "NextPhrase"; 
	public static final String UPLIFT_FOCUSPHRASE = "FocusPhrase";
	public static final String UPLIFT_PLAYAUDIO = "PlayAudio";
	
	public int CURRENTBANNER = 0;
	public static final int TOTALBANNERS = 3;	
	public static final int BANNERS [] = {0x7f020000,0x7f020001,0x7f020002};
	public static final String ADSITES [] = {
		"http://www.amazon.com/John-Selby/e/B000APV284/ref=ntt_athr_dp_pel_pop_1",
		"http://www.wisdomcd.com/tappingthesource.html",
		"http://tappingdaily.org/site/"
	};
	
	public static final String UPLIFT_PUBLISHER_WEBSITE = "www.tappingdaily.org";  
			
	// A
	public static final String mInstantVideoUplifts [] = {
		"http://www.youtube.com/watch?v=1S8eNS0xAXM",  // Expand This Moment Jazz
		"http://www.youtube.com/watch?v=zb_ivycQz0M",  // Expand This Moment Meditation
		"http://www.youtube.com/watch?v=QUGjtJTkCAM",  // Mood Uplift Jazz
		"http://www.youtube.com/watch?v=LN9PLUwsNaM",  // Mood Uplift Meditation
		"http://www.youtube.com/watch?v=KL3-pwSwx1I",  // Insight Connection Jazz
		"http://www.youtube.com/watch?v=BL0RM0GS4vU",  // Insight Connection Meditation
		"http://www.youtube.com/watch?v=hKNr0uXAuJA",  // Purpose & Vision Jazz
		"http://www.youtube.com/watch?v=q5fCtDIPKrc"   // Purpose & Vision Meditation 
	};
	
	// B
	public static final String mFullProcess [] = {
		"http://www.youtube.com/watch?v=S1NpPBj8Cis",   // with guiding voice jazz music
		"http://www.youtube.com/watch?v=KECvFZ5Y9Lg",   // with guiding voice meditation music
		"http://www.youtube.com/watch?v=ehdB-85jptk",   // with music and visuals
		"http://www.youtube.com/watch?v=PtElcgUrFpM",   // quiet jazz audio
		"http://www.youtube.com/watch?v=Awz_nd_AuIc"    // meditation audio 		
	};	
	
	// C
	public static final String mQuickInspiration [] = {
		"http://www.youtube.com/watch?v=eGxhJAhq88Q",           // 12 Focus Phrases 
		"",                                                     // See mFocusPhraseImage
		"",                                                     // See mFocusPhraseImage
		"http://www.spiritualnuggets.net/selby_app_cards.cfm"   // Order Website for Cards
	};
	
	public static final int ALLFOCUSPHRASE = 12;       // Total Focus Phrases
	public static final int UPLIFT_MUSIC_1 = 0;        // Background Music
	public static final int  [] mFocusPhraseAudio = new int[] {   // Only covers all
		0x7f050000
	};
			
	public static final int [] mFocusPhraseImage = new int[] {    // List of Focus Phrase images
		0x7f020005,
		0x7f020009,
		0x7f02000a,
		0x7f02000b,
		0x7f02000c,
		0x7f02000d,
		0x7f02000e,
		0x7f02000f,
		0x7f020010,
		0x7f020006,
		0x7f020007,
		0x7f020008
	};
		
	// D	
	public static final String mSparkUpVideos [] = {
		"http://www.youtube.com/watch?v=BM6laN60IAw",  // Program Overview
		"http://www.youtube.com/watch?v=HVU97kDOjkU",  // Breathing Easy 
		"http://www.youtube.com/watch?v=Cm8gzJGgNiw",  // Emotional Healing
		"http://www.youtube.com/watch?v=zYdX7CnXo7I"   // Enjoy This Moment 
	};
	
		
	//getResources().getIdentifier("pharse_12", "raw", getPackageName())
	
	
	public void onClick(View v) {
		Uri uri = Uri.parse(ADSITES[CURRENTBANNER]);
    	Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(webIntent);		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		getMenuInflater().inflate(R.menu.options, menu);
		menu.findItem(R.id.why_option_item).setIntent(new Intent(this, WhyActivity.class));
		menu.findItem(R.id.instructions_option_item).setIntent(new Intent(this, InstructionsActivity.class));
		menu.findItem(R.id.notes_option_item).setIntent(new Intent(this, MoodNotesActivity.class));
		menu.findItem(R.id.support_option_item).setIntent(new Intent(this, SupportActivity.class));
		menu.findItem(R.id.bio_option_item).setIntent(new Intent(this, AboutActivity.class));		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.why_option_item:
		case R.id.instructions_option_item:
		case R.id.notes_option_item:
		case R.id.support_option_item:
			startActivity(item.getIntent());
			break;
		case R.id.share_option_item:
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			String subject = getResources().getString(R.string.email_subject_text);
    		String body = getResources().getString(R.string.email_body_text);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
    	    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
			emailIntent.setType("message/rfc822");
    	    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    	    break;
		}
		return true;
	}
}

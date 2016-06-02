package com.evansappwriter.instantuplift;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class PlayFocusPhraseActivity extends Activity {
	int mFocusPhrase;
	boolean mPlayAudio;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phrase);
               
        
        //startAnimating();
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		
		startAnimating();
	}
	
	private void startAnimating() {
		ImageView focusImage = (ImageView)  findViewById(R.id.ImageViewCover);
		Bundle extras = getIntent().getExtras();
		mFocusPhrase = extras.getInt(InstantUpliftActivity.UPLIFT_FOCUSPHRASE);
		mPlayAudio = extras.getBoolean(InstantUpliftActivity.UPLIFT_PLAYAUDIO);
		focusImage.setImageDrawable(getResources().getDrawable(InstantUpliftActivity.mFocusPhraseImage[mFocusPhrase]));
		Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		focusImage.startAnimation(fade);
		// Play Music 1
		if (mPlayAudio) {FocusPhraseAudio.play(this,InstantUpliftActivity.mFocusPhraseAudio[InstantUpliftActivity.UPLIFT_MUSIC_1]);}			
		
		fade.setAnimationListener(new AnimationListener() {
        	public void onAnimationEnd(Animation animation) {
        		                
        	}

			public void onAnimationRepeat(Animation animation) {
				
			}

			public void onAnimationStart(Animation animation) {
							
			}
        }); 
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (mPlayAudio) {FocusPhraseAudio.stop(this);}
		ImageView bookCover = (ImageView)findViewById(R.id.ImageViewCover);
		bookCover.clearAnimation();
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
	
	
}

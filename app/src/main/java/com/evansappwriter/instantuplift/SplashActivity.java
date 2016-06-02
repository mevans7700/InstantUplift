package com.evansappwriter.instantuplift;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        startAnimating();
    }
	
	private void startAnimating() {
		ImageView bookCover = (ImageView)  findViewById(R.id.ImageViewCover);
		Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		bookCover.startAnimation(fade);
		
		fade.setAnimationListener(new AnimationListener() {
        	public void onAnimationEnd(Animation animation) {
        		
                startActivity(new Intent(SplashActivity.this, MainMenuActivity.class));            		
                SplashActivity.this.finish();
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
		ImageView bookCover = (ImageView)  findViewById(R.id.ImageViewCover);
		bookCover.clearAnimation();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		startAnimating();
	}
}
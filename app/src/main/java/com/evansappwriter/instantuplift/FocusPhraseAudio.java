package com.evansappwriter.instantuplift;

import android.content.Context;
import android.media.MediaPlayer;

public class FocusPhraseAudio {
	private static MediaPlayer mp = null;
	
	public static void play(Context context, int resource) {
		stop(context);

	    // Start Focus Phrase only if not disabled in preferences
	    mp = MediaPlayer.create(context, resource);
	    mp.setLooping(true);
	    mp.start();
	}   

	/** Stop the Focus Phrase */
	public static void stop(Context context) { 
		if (mp != null) {
			mp.stop();
	        mp.release();
	        mp = null;
	    }
	}
}

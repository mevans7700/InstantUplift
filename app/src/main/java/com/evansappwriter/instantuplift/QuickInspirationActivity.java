package com.evansappwriter.instantuplift;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuickInspirationActivity extends InstantUpliftActivity {
	SharedPreferences mPrefSettings;
	ArrayList<DisplayItem> mMenuList;
	ListAdapter mAdapter;	
	View mBanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		//LinearLayout layout = (LinearLayout)findViewById(R.id.menu_layout);
		//layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.japanbk));
		
		TextView title = (TextView)findViewById(R.id.menu_title);
		title.setText(getResources().getString(R.string.quick_title));
		
		// set up the menu list
		mMenuList = new ArrayList<DisplayItem>();
		
		// set up the adapter
        mAdapter = new ListAdapter(this, R.layout.menu, mMenuList);

        // and set the adapter of the list view to the adapter
        setListAdapter(mAdapter);
		
		// populate the menu list
		initList();				
		
		// inform the adapter that the data set has changed, so the list will be
        // redrawn.
        mAdapter.notifyDataSetChanged();
        
        // Get Handle of adBanner
        mBanner = findViewById(R.id.adBanner);
        mBanner.setOnClickListener(this);
	}	
	
	@Override
	protected void onPause() {
		super.onPause();
		mBanner.clearAnimation();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// Set Ad Banner and start Rotation
		startAdRotation();
	}
	
	void initList() {
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.bluesunicon),
				getResources().getString(R.string.quick_menu_item_1)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.orangesunicon),
				getResources().getString(R.string.quick_menu_item_2)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.handsunicon),
				getResources().getString(R.string.quick_menu_item_3)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.leaficon),
				getResources().getString(R.string.quick_menu_item_4)));			
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
		
        switch (position) {
    	case 0:
    	case 3:
    		openBrowser(mQuickInspiration[position]);
    		break;
    	case 1:    
    		showPhrase(getRandomPhrase());   		
    		break;
    	case 2:
    		showPhrase(getNextPhrase());
    		break;    	
        }                     
    }
	
	private void showPhrase(int phrase) {
		Intent i = new Intent(this, PlayFocusPhraseActivity.class);
		i.putExtra(UPLIFT_PLAYAUDIO, true);
		i.putExtra(UPLIFT_FOCUSPHRASE, phrase); 
		startActivity(i);
	}
	
	// Open a browser on the URL 
    private void openBrowser(String Url) {
    	Uri uri = Uri.parse(Url);
    	Intent i = new Intent(Intent.ACTION_VIEW, uri);
    	startActivity(i);
    }
    
    int getRandomPhrase() {
    	Random mRandom = new Random();
    	int mPhrase = mRandom.nextInt(ALLFOCUSPHRASE);
    	return (mPhrase) ;    	
    }
    
    int getNextPhrase() {    	
    	int nextPhrase;
    	// Retrieve the shared preferences
        mPrefSettings = getSharedPreferences(UPLIFT_PREFERENCES, Context.MODE_PRIVATE); 
        int mPhrase = mPrefSettings.getInt(UPLIFT_PREFERENCES_PHRASE_ID, 1);
        if (mPhrase == ALLFOCUSPHRASE) 
        	nextPhrase = 1;
        else
        	nextPhrase = mPhrase + 1;        	
        
        // set Next Phrase
		Editor editor = mPrefSettings.edit();
        editor.putInt(UPLIFT_PREFERENCES_PHRASE_ID, nextPhrase);
        editor.commit();
        
        return mPhrase-1;
    }
    
    void startAdRotation() {		
		if (CURRENTBANNER < TOTALBANNERS-1) {
			CURRENTBANNER++;
		} else {
			CURRENTBANNER = 0;
		}
		mBanner.setBackgroundResource(BANNERS[CURRENTBANNER]);
		Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotation);
		mBanner.startAnimation(rotate);
		
		
		rotate.setAnimationListener(new AnimationListener() {
        	public void onAnimationEnd(Animation animation) {
        		mBanner.clearAnimation();
        		startAdRotation();                
        	}

			public void onAnimationRepeat(Animation animation) {
								
			}

			public void onAnimationStart(Animation animation) {
							
			}
        });		
	}
	
	private class DisplayItem {
		private Drawable image;
		private String text;
		
		public DisplayItem(Drawable image, String text) {
			super();
			this.image = image;
			this.text = text;
		}

		public Drawable getImage() {
			return image;
		}		

		public String getText() {
			return text;
		}	
	}
	
	private class ListAdapter extends ArrayAdapter<DisplayItem> {
		ArrayList<DisplayItem> items;

	    public ListAdapter(Context context, int resource,
	                    ArrayList<DisplayItem> items) {
	            super(context, resource, items);
	            this.items = items;
	    }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

	         // if we got no view, inflate one of our rows
	         if (v == null) {
	                 LayoutInflater vi = (LayoutInflater) getContext()
	                                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                 v = vi.inflate(R.layout.menu_item, null);
	         }

	         // get the current object from the list
	         DisplayItem current = items.get(position);

	         // get the views for setting data after
	         ImageView image = (ImageView) v.findViewById(R.id.menu_icon);
	         TextView item = (TextView) v.findViewById(R.id.menu_text);

	         // and set the values... 
	         image.setImageDrawable(current.getImage());
	         item.setText(current.getText());

	         // return the view
	         return v;			
		}   		
	}
}

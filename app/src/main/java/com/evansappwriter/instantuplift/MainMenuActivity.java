package com.evansappwriter.instantuplift;


import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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


public class MainMenuActivity extends InstantUpliftActivity {	
	ArrayList<DisplayItem> mMenuList;
	ListAdapter mAdapter;		
	View mBanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		//LinearLayout layout = (LinearLayout)findViewById(R.id.menu_layout);
		//layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.roadbk));
		
		TextView title = (TextView)findViewById(R.id.menu_website);
		title.setText(UPLIFT_PUBLISHER_WEBSITE);
		
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
		
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
        case 0:
        	startActivity(new Intent(this,InstantVideoUpliftsActivity.class));
        	break;
        case 1:
        	startActivity(new Intent(this,FullProcessActivity.class));
        	break;
        case 2:
        	startActivity(new Intent(this,QuickInspirationActivity.class));
        	break;
        case 3:
        	startActivity(new Intent(this,SparkUpVideosActivity.class));
        	break; 	
        }
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
	
	void initList() {
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.bluesunicon),
				getResources().getString(R.string.main_menu_item_1)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.orangesunicon),
				getResources().getString(R.string.main_menu_item_2)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.handsunicon),
				getResources().getString(R.string.main_menu_item_3)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.leaficon),
				getResources().getString(R.string.main_menu_item_4)));			
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
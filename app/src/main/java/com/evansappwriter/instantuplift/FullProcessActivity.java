package com.evansappwriter.instantuplift;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
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


public class FullProcessActivity extends InstantUpliftActivity {
	ArrayList<DisplayItem> mMenuList;
	ListAdapter mAdapter;	
	View mBanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		//LinearLayout layout = (LinearLayout)findViewById(R.id.menu_layout);
		//layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rocksbk));
		
		TextView title = (TextView)findViewById(R.id.menu_title);
		title.setText(getResources().getString(R.string.fullprocess_title));
		
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
				getResources().getString(R.string.fullprocess_menu_item_1),
				getResources().getString(R.string.fullprocess_menu_item_1a)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.orangesunicon),
				getResources().getString(R.string.fullprocess_menu_item_2),
				getResources().getString(R.string.fullprocess_menu_item_2a)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.handsunicon),
				getResources().getString(R.string.fullprocess_menu_item_3),
				getResources().getString(R.string.fullprocess_menu_item_3a)));			
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.leaficon),
				getResources().getString(R.string.fullprocess_menu_item_4),
				getResources().getString(R.string.fullprocess_menu_item_4a)));
		mMenuList.add(new DisplayItem(getResources().getDrawable(R.drawable.leaficon),
				getResources().getString(R.string.fullprocess_menu_item_5),
				getResources().getString(R.string.fullprocess_menu_item_5a)));		
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        openBrowser(mFullProcess[position]);                        
    }
		
	// Open a browser on the URL 
    private void openBrowser(String Url) {
    	Uri uri = Uri.parse(Url);
    	Intent i = new Intent(Intent.ACTION_VIEW, uri);
    	startActivity(i);
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
		private String text2;
		
		public DisplayItem(Drawable image, String text, String text2) {
			super();
			this.image = image;
			this.text = text;
			this.text2 = text2;
		}

		public Drawable getImage() {
			return image;
		}		

		public String getText() {
			return text;
		}
		
		public String getText2() {
			return text2;
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
	                 v = vi.inflate(R.layout.menu_item2, null);
	         }

	         // get the current object from the list
	         DisplayItem current = items.get(position);

	         // get the views for setting data after
	         ImageView image = (ImageView) v.findViewById(R.id.menu_icon);
	         TextView item = (TextView) v.findViewById(R.id.menu_text);
	         TextView item2 = (TextView) v.findViewById(R.id.menu_text2);

	         // and set the values... 
	         image.setImageDrawable(current.getImage());
	         item.setText(current.getText());
	         item2.setText(current.getText2());

	         // return the view
	         return v;			
		}   		
	}
}

package com.selva.viewgroupsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ViewGroupSampleActivity extends Activity {
	
	ImageView imageViewOne;
	ImageView imageViewTwo;
	ImageView imageViewThree;
	ImageView imageViewOne1;
	ImageView imageViewTwo2;
	ImageView imageViewThree3;
	ViewGroupSampleOne vgSample;
	static int nWidth;
	static int nHeight;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nWidth= displayMetrics.widthPixels;
        nHeight=displayMetrics.heightPixels;
        
        //add the views you want
        imageViewOne=new ImageView(this);
		imageViewOne.setBackgroundResource(R.drawable.icon);
		imageViewOne.setId(1);
		
		imageViewTwo=new ImageView(this);
		imageViewTwo.setBackgroundResource(R.drawable.icon1);
		imageViewTwo.setId(2);
		
		imageViewThree=new ImageView(this);
		imageViewThree.setBackgroundResource(R.drawable.icon1);
		imageViewThree.setId(3);
		
		imageViewOne1=new ImageView(this);
		imageViewOne1.setBackgroundResource(R.drawable.icon);
		imageViewOne1.setId(4);
		
		imageViewTwo2=new ImageView(this);
		imageViewTwo2.setBackgroundResource(R.drawable.icon1);
		imageViewTwo2.setId(5);
		
		imageViewThree3=new ImageView(this);
		imageViewThree3.setBackgroundResource(R.drawable.icon1);
		imageViewThree3.setId(6);
		
		//create an instance for viewgroupsampleone and add views to it
		vgSample=new ViewGroupSampleOne(this);
		vgSample.addView(imageViewOne, 0);
		vgSample.addView(imageViewTwo, 1);
		vgSample.addView(imageViewThree,2);
		vgSample.addView(imageViewOne1, 3);
		vgSample.addView(imageViewTwo2, 4);
		vgSample.addView(imageViewThree3,5);
		
		//u may set it to horizontal also
		vgSample.setOrientation(ViewGroupSampleOne.VERTICAL);
      
        setContentView(vgSample);
    }
}
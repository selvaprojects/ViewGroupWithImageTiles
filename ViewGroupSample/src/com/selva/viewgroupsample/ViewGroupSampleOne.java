package com.selva.viewgroupsample;

import android.content.Context;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//viewgroups align views by two passes, first it go through onMeasure(), and then through onLayout(),
//width and height are calculated in onMeasure(), positioning is done in onLayout() phase.
//we can customise viewgroup and override the two passes with our own implementation.
//This sample just keeps the images in tiles. we can change only the row and column numbers.
//onTouch is also overrrided

public class ViewGroupSampleOne extends ViewGroup{

public static final byte VERTICAL=0;
public static final byte HORIZONTAL=1;
private int nViewPerRow=5;
private int nViewPerColumn=5;
private int nRowNumber=0;
private int nColumnNumber=0;
private byte orientation=0;





	public ViewGroupSampleOne(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setOnTouchListener(ontouch);
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		nRowNumber=0;
		nColumnNumber=0;
		for(int i=0;i<getChildCount();i++)
		{
			
			
			if(orientation==HORIZONTAL)
			{
				

					getChildAt(i).layout(calcXFromFirstchild(nColumnNumber),(calcYFromFirstchild((nRowNumber))),calcXFromFirstchild(nColumnNumber)+getChildAt(i).getMeasuredWidth(),getChildAt(i).getMeasuredHeight()+(calcYFromFirstchild((nRowNumber))));

					
					nColumnNumber++;
					if((i+1)>=((nRowNumber+1)*nViewPerRow))
					{
						nRowNumber++;
						nColumnNumber=0;
					}
					
					
			
			
			
			}
			else
			{
				getChildAt(i).layout((calcXFromFirstchild((nColumnNumber))),calcYFromFirstchild(nRowNumber),getChildAt(i).getMeasuredWidth()+(calcXFromFirstchild((nColumnNumber))),calcYFromFirstchild(nRowNumber)+getChildAt(i).getMeasuredHeight());
				nRowNumber++;
				if((i+1)>=((nColumnNumber+1)*nViewPerColumn))
				{
					nColumnNumber++;
					nRowNumber=0;
				}
				
			}
		}
		
	}
	
	public int calcXFromFirstchild(int childNumber)
	{
		int xPos=0;   //getChildAt(0).getLeft();
		for(int i=0;i<childNumber;i++)
		{
			xPos+=getChildAt(i).getMeasuredWidth();
		}
		return xPos;
	}

	public int calcYFromFirstchild(int childNumber)
	{
		int yPos=0;   //getChildAt(0).getTop();
		for(int i=0;i<childNumber;i++)
		{
			yPos+=getChildAt(i).getMeasuredHeight();
		}
		return yPos;
	}
	public void setOrientation(byte orienatation)
	{
		orientation=orienatation;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		int nNumOfChild=getChildCount();
		int nTotalWidth=ViewGroupSampleActivity.nWidth;
		int nTotalHeight=ViewGroupSampleActivity.nHeight;
		int perChildWidth=100;
		int perChildHeight=100;
		
		if(orientation==ViewGroupSampleOne.HORIZONTAL)
		{
		perChildWidth=(int)Math.floor(nTotalWidth/nViewPerRow);
		perChildHeight=perChildWidth;
		
		}
		else
		{
		perChildHeight=(int)Math.floor(nTotalHeight/nViewPerColumn);
		perChildWidth=perChildHeight;
		}
		
		
		
		for(int i=0;i<getChildCount();i++)
		{

			int nHeight=MeasureSpec.makeMeasureSpec(perChildHeight, MeasureSpec.EXACTLY);
			int nWidth=MeasureSpec.makeMeasureSpec(perChildWidth, MeasureSpec.EXACTLY);
			getChildAt(i).measure(nWidth,nHeight);
		}
		
		setMeasuredDimension(MeasureSpec.makeMeasureSpec(nTotalWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(nTotalHeight, MeasureSpec.EXACTLY));


	}
	
OnTouchListener ontouch=new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
				
				Toast.makeText(v.getContext(), "DownX:"+event.getX()+"DownY: "+event.getY(), Toast.LENGTH_SHORT).show();
				

			}
			if(event.getAction()==MotionEvent.ACTION_UP)
			{
				Toast toast=Toast.makeText(v.getContext(), "UpX:"+event.getX()+"UpY: "+event.getY(), Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 30, 30);
				toast.show();

			}
			
			return true;
		}
	};
	

}

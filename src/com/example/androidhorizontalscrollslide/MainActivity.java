package com.example.androidhorizontalscrollslide;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	private Timer timer;
	private TimerTask timerTask;
	public int counter=0;
	HorizontalScrollView listview;
	LinearLayout mLinearLayout;
	ImageView iv1,iv2,iv3,iv4,iv5;
	public boolean flag;
	int widthScreen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		flag=false;
		mLinearLayout = (LinearLayout) findViewById(R.id.ll);
		listview = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);

		final int count = ((LinearLayout) listview.getChildAt(0))
                 .getChildCount();
        Log.d("Slider", "count:"+count);
        
        DisplayMetrics metrics = this.getResources().getDisplayMetrics(); 
		widthScreen = metrics.widthPixels;
          
		iv1 = (ImageView)findViewById(R.id.ImageView1);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(widthScreen, 300);
		iv1.setLayoutParams(layoutParams1);
		iv1.setScaleType(ScaleType.FIT_XY);
		iv2 = (ImageView)findViewById(R.id.ImageView2);
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(widthScreen, 300);
		iv2.setLayoutParams(layoutParams2);
		iv2.setScaleType(ScaleType.FIT_XY);
		iv3 = (ImageView)findViewById(R.id.ImageView3);
		LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(widthScreen, 300);
		iv3.setLayoutParams(layoutParams3);
		iv3.setScaleType(ScaleType.FIT_XY);
		iv4 = (ImageView)findViewById(R.id.ImageView4);
		LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(widthScreen, 300);
		iv4.setLayoutParams(layoutParams4);
		iv4.setScaleType(ScaleType.FIT_XY);
		iv5 = (ImageView)findViewById(R.id.ImageView5);
		LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(widthScreen, 300);
		iv5.setLayoutParams(layoutParams5);
		iv5.setScaleType(ScaleType.FIT_XY);
		
		try {
			timer = new Timer();
			timerTask = new TimerTask() {
				@Override
				public void run() {
					if(flag==false)
					{
						counter=counter+widthScreen;
						//Log.d("Slider", "counter:"+counter);
					
						if(counter>(widthScreen*5))
						{
							counter=0;
						}
						listview.smoothScrollTo(counter, 0); 
					}
				}
			};
			timer.schedule(timerTask, 1000, 4000);
		} catch (IllegalStateException e) {
			android.util.Log.i("Damn", "resume error");
		}
		
		
		listview.setOnTouchListener(new View.OnTouchListener() { 
			//outer scroll listener         
	        private float mx, my, curX, curY, downX, moveX;
	        private boolean started = false;
	        
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            curX = event.getX();
	            curY = event.getY();
	            
	            int dx = (int) (mx - curX);
	            int dy = (int) (my - curY);
	            
	            Log.d("Slider", "dx:"+curX);
	            
	            switch (event.getAction()) {
	                case MotionEvent.ACTION_MOVE:
	                    if (started) {
	                    	moveX = event.getX();
	                    	listview.scrollBy(dx, 0);
	                    } else {
	                        started = true;
	                    }
	                    mx = curX;
	                    my = curY;
	                    
	                    flag=true;
	                    
	                    break;
	                case MotionEvent.ACTION_DOWN: 
	                	downX = event.getX();
	                    started = false;
	                    flag=true;
	                    break;
	                case MotionEvent.ACTION_UP: 
	                    started = false;
	                    
	                    if(downX<moveX)
	                    {
//	    	            	Log.d("Slider", "ACTION_RIGHT:");
	                    	counter =counter- widthScreen;
	    	            	listview.smoothScrollTo(counter, 0); 
	                    }
	    	            else 
	    	            {
//	    	 	            Log.d("Slider", "ACTION_LEFT:");
	    	            	counter =counter+ widthScreen;
	    	            	listview.smoothScrollTo(counter, 0); 
	    	            }
	                    
	                    final Handler handler = new Handler();
	                    handler.postDelayed(new Runnable() {
	                        @Override
	                        public void run() {
	                            // Do something after 5s = 5000ms
	                        	flag=false;
	                        }
	                    }, 1500);
	                    
	                    
	                  
	                    
	                    break;
	            }
	            
	          
	            return true;
	        }
	    });
		
		
	}
	
	
	
	
//	 private void autoScroll() { 
//		 // Width of the screen 
//		 DisplayMetrics metrics = this.getResources().getDisplayMetrics(); 
//		 int widthScreen = metrics.widthPixels;
//		// Width of the container (LinearLayout) 
//		 int widthContainer = mLinearLayout.getWidth();
//		// Width of one child (in this case Button) 
//		 int widthChild = mLinearLayout.getChildAt(0).getWidth();
//		// Nb children which has contained in screen device
//		 int nbChildInScreen = widthScreen / widthChild;
//		// Width total of the space outside the screen / 2 (= left position) 
//		 int positionLeftWidth = (widthContainer - (widthChild * nbChildInScreen)) / 2;
//		// Auto scroll to the middle 
//		 listview.smoothScrollTo(positionLeftWidth, 0);  
//	 }
}

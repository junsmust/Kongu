package com.skt.tmapopenmapapi.ui;import com.skt.Tmap.TMapOverlayItem;import com.skt.Tmap.TMapView;import android.content.Context;import android.graphics.Bitmap;import android.graphics.Canvas;import android.os.Handler;import android.view.ViewGroup;import android.widget.LinearLayout;import android.widget.RelativeLayout;public class ImageOverlay extends TMapOverlayItem {				private Context 	mContext = null;			private TMapView 	mMapView = null;			private int mAnimationCount = 0;		private LinearLayout popupLayout = null;	private Runnable mRunnable ;		@Override	public void setImage(Bitmap bitmap) {		super.setImage(bitmap);	}		public ImageOverlay(Context context, TMapView view) 	{		this.mContext = context;		this.mMapView = view;				popupLayout = new LinearLayout(mContext);			    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);			popupLayout.setLayoutParams(params);				popupLayout.setOrientation(LinearLayout.HORIZONTAL);			}					@Override	public void draw(Canvas canvas, TMapView mapView, boolean showCallout) {		int leftX = mapView.getMapXForPoint(getLeftTopPoint().getLongitude(), getLeftTopPoint().getLatitude());		int leftY = mapView.getMapYForPoint(getLeftTopPoint().getLongitude(), getLeftTopPoint().getLatitude());					int RightX = mapView.getMapXForPoint(getRightBottomPoint().getLongitude(), getRightBottomPoint().getLatitude());		int RightY = mapView.getMapYForPoint(getRightBottomPoint().getLongitude(), getRightBottomPoint().getLatitude());						canvas.save();					int imageWidth = RightX - leftX;	}		Handler mHandler = null; 	public void startAnimation() {				mRunnable = new Runnable() {	        @Override	        public void run() {	        	if(getAnimationIcons().size() > 0)	        	{	        		if(mAnimationCount >= getAnimationIcons().size())	        			mAnimationCount = 0;	        			        		setImage(getAnimationIcons().get(mAnimationCount));	        			        		mMapView.refreshMap();	        			        		mAnimationCount++;	        			        		mHandler.postDelayed(this, getAniDuration());	        	}	        }	    };	     	    mHandler = new Handler();	 	    mHandler.post(mRunnable);	    	}	public void stopAnimation(){		if(mHandler != null){			mHandler.removeCallbacks(mRunnable);		}	}}
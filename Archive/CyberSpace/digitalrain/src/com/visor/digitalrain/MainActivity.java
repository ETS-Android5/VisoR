package com.visor.digitalrain;

import com.visor.digitalrain.R;
import com.visor.renderingpipeline.IntermediateProcessor;
import com.visor.streaming.CameraAdapter;
import com.visor.streaming.MyGLSurfaceView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity implements SurfaceTexture.OnFrameAvailableListener{

	private MyGLSurfaceView glSurfaceView;
	private CameraAdapter cameraAdapter = new CameraAdapter(this);
	//private RecordingAdapter recordingAdapter;


	private int[] images = {R.drawable.page0, R.drawable.page1, R.drawable.page2, R.drawable.page3,
			R.drawable.page4, R.drawable.page5, R.drawable.page6, R.drawable.page7, R.drawable.page8,
			R.drawable.page9};

	/* start of all overriding activity methods */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


		glSurfaceView = new MyGLSurfaceView(this, cameraAdapter, images);
		setContentView(glSurfaceView);
		Toast.makeText(this, "Loading Masks", Toast.LENGTH_LONG).show();
		
		glSurfaceView.setOnTouchListener(new OnSwipeTouchListener(this){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}

			@Override
			public void launchUpdateBox() {
				UIComponents.retrieveUpdateDialog(context, glSurfaceView).show();
			}
		});

	}

	/**
	@Override 
	public void onStop()
	{
		super.onStop();
		cameraAdapter.destroyCamera();
	}
	*/
	
	public void onPause()
	{
		super.onPause();
		cameraAdapter.destroyCamera();
	}

	@Override
	public void onResume(){
		super.onResume();
		cameraAdapter.restartCamera();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		super.onKeyLongPress(keyCode, event);
		return processAndroidButton(keyCode, event);	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		return processAndroidButton( keyCode,  event);
	}

	private boolean processAndroidButton(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			System.exit(0);
			return false;
		}
		if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			IntermediateProcessor.incrementProcess();
			return true;
		}
		else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			IntermediateProcessor.decrementProcess();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onFrameAvailable(SurfaceTexture surfaceTexture){
		glSurfaceView.requestRender();
	}

	public void setCameraSurface(SurfaceTexture cameraSurface) {
		glSurfaceView.setCameraSurface(cameraSurface);
	}
}

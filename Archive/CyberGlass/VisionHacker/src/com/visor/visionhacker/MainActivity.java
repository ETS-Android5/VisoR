package com.visor.visionhacker;

import com.visor.filters.FilterVault;
import com.visor.streaming.MyGLSurfaceView;
import com.visor.streaming.MyRenderer;
import com.visor.visionhackerOLD.R;
import java.util.HashSet;
import java.util.Set;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity implements SurfaceTexture.OnFrameAvailableListener {

	// variables for camera feed
	private boolean isCurrentlyStreaming = false;
	private MyGLSurfaceView glSurfaceView;
	private SurfaceTexture surface;
	private MyRenderer renderer;
	private CameraAdapter cameraAdapter = new CameraAdapter();

	// filter information
	private static int[] filterIndices = {0 , 0}; // left is 0, right is 1
	private String[] names = FilterVault.getAllNames();
	private int numFilters = FilterVault.getNumFilters();

	// how to display the toast upon filter switch
	private Set<Toast> bread= new HashSet<Toast>();
	private static int LEFT_FILTER = 1;
	private static int BOTH_FILTERS = 2;
	private static int RIGHT_FILTER = 3;
	private static int NO_FILTERS = -1;

	// elements of view
	private Point size = new Point();
	private AlertDialog helpDialog, settDialog;

	// customizable components
	private static float[] defaultUniformValues = {(float) 10.0, (float) 10.0};
	private static int[] settingsOptions = {1,0,0,0};
	private UIComponents builder;

	/* start of overriding activity methods */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fixScreenView();
		setContentView(R.layout.activity_main);
		getWindowManager().getDefaultDisplay().getSize(size);
		builder = new UIComponents(this, numFilters, names);

		helpDialog = builder.retrieveHelpDialog();
		helpDialog.setCanceledOnTouchOutside(true);

		settDialog = builder.retrieveSettingsDialog(settingsOptions);
		settDialog.setCanceledOnTouchOutside(true);

		builder.initialUISetup(filterIndices, helpDialog, settDialog);
		defaultUniformValues[0] = (float) (2.0/(float)size.x);
		defaultUniformValues[1] = (float) (1.0/(float)size.y);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		cameraAdapter.destroyCamera();
	}

	

	@Override
	public void onStop(){
		super.onStop();
		if(isCurrentlyStreaming){
			//cameraAdapter.destroyCamera();
			System.exit(0);
		}
		isCurrentlyStreaming = true;
	}

	@Override
	public void onResume(){
		super.onResume();
		cameraAdapter.destroyCamera();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		super.onKeyLongPress(keyCode, event);
		return processAndroidButton( keyCode,  event);	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		return processAndroidButton( keyCode,  event);
	}

	private void fixScreenView() {
		this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public void setUpCameraViews() {
		isCurrentlyStreaming = true;

		glSurfaceView = new MyGLSurfaceView(this);
		FilterVault.updateUniformValues(defaultUniformValues);
		renderer = glSurfaceView.getRenderer();
		renderer.setFilters(filterIndices);
		renderer.updateFilterVariables(settingsOptions);

		glSurfaceView.setOnTouchListener(new OnSwipeTouchListener(this, size.x){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}

			@Override
			public void onLeftScreenSwipe(int i) {
				filterIndices[0] = (filterIndices[0] + i);
				updateFilters(LEFT_FILTER);
			}

			@Override
			public void onRightScreenSwipe(int i) {
				filterIndices[1] = (filterIndices[1] + i);
				updateFilters(RIGHT_FILTER);
			}

			@Override
			public void launchUpdateBox() {

				builder.retrieveUpdateFilterDialog(filterIndices, renderer).show();
			}
		});

		replaceCurrentView(glSurfaceView);

	}

	private View myView;
	private ViewGroup vgParent;

	private void replaceCurrentView(View view) {
		myView = getWindow().getDecorView().findViewById(android.R.id.content);
		vgParent = (ViewGroup)(myView.getParent());
		vgParent.removeView(myView);
		vgParent.addView(view);
	}

	private boolean processAndroidButton(int keyCode, KeyEvent event) {
		if( settingsOptions[0]==1 && isCurrentlyStreaming){

			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
			{
				filterIndices[0]++;
				filterIndices[1] = filterIndices[0];
				updateFilters(BOTH_FILTERS);
				return true;
			}
			else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
				filterIndices[0]--;
				filterIndices[1] = filterIndices[0];
				updateFilters(BOTH_FILTERS);
				return true;
			}
			else if ( keyCode == KeyEvent.KEYCODE_MENU ) {
				builder.retrieveUpdateFilterDialog(filterIndices, renderer).show();
				return true;
			}
			else if(keyCode == KeyEvent.KEYCODE_BACK){
				endCurrentStreaming();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public void startCamera(int texture)
	{
		surface = new SurfaceTexture(texture);
		surface.setOnFrameAvailableListener(this);
		renderer.setSurface(surface);
		cameraAdapter.setupCamera(surface);
	}

	public void onFrameAvailable(SurfaceTexture surfaceTexture)
	{
		glSurfaceView.requestRender();
	}

	private void updateFilters(int whereToDisplayName){
		FilterVault.updateUniformValues(defaultUniformValues);
		fixFilterGivenIndex(0);
		fixFilterGivenIndex(1);

		renderer.updateFilters(filterIndices);

		for(Toast toast: bread){
			if(toast != null)
				toast.cancel();
		}
		bread.clear();

		if(whereToDisplayName > 0){
			if(whereToDisplayName < RIGHT_FILTER){
				bread.add(createFilterToast(0, -size.x/4));
			}
			if(whereToDisplayName > LEFT_FILTER){
				bread.add(createFilterToast(1, size.x/4));
			}
		}

	}

	private void fixFilterGivenIndex(int i){
		if(filterIndices[i] < 0)
			filterIndices[i] += numFilters;
		filterIndices[i] = filterIndices[i] % numFilters;
	}

	private Toast createFilterToast(int filterIndex, int offset){
		Toast toast= Toast.makeText(getApplicationContext(), 
				names[filterIndices[filterIndex]], Toast.LENGTH_SHORT);  
		toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, offset, 0);
		toast.show();
		return toast;
	}

	private void endCurrentStreaming() {
		cameraAdapter.destroyCamera();
		vgParent.removeView(glSurfaceView);
		isCurrentlyStreaming = false;
		vgParent.addView(myView);
	}

	private void updateFilterVariables() {

		FilterVault.updateUniformValues(defaultUniformValues);
	}

}

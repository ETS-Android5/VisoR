package com.visor.renderingpipeline;

import com.visor.model.DimensionVault;
import com.visor.recorder.RecordingAdapter;
import com.visor.streaming.CameraAdapter;
import com.visor.streaming.CameraStreaming;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer{

	private CameraStreaming cameraStreamLeft, cameraStreamRight;
	private SurfaceTexture surface;
	private CameraAdapter delegate;
	private IntermediateProcessor intermediateProcessor;
	private int[] images;
	private Context context;
	private int finalTexture;
	private RecordingAdapter recordingAdapter;
	private boolean inHeadsetMode = false;


	public MyRenderer(int[] images, CameraAdapter delegate, Context context){
		this.delegate = delegate;
		this.images = images;
		this.context = context;
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config){
		int cameraTexture = GL_Toolbox.createOESTexture();



		intermediateProcessor = new IntermediateProcessor(cameraTexture, DimensionVault.drawOrder, DimensionVault.wholeTriangleVertices, DimensionVault.fullTextureVertices);
		cameraStreamLeft = new CameraStreaming(DimensionVault.drawOrder, DimensionVault.triangleVerticesLeft, DimensionVault.textureVerticesLeft);
		cameraStreamRight = new CameraStreaming(DimensionVault.drawOrder, DimensionVault.triangleVerticesRight, DimensionVault.textureVerticesRight);

		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		intermediateProcessor.getDigitalRainAdapter().addDigitalRainImages(context, images);


		delegate.startCamera(cameraTexture);

	}

	public void onDrawFrame(GL10 unused){
		float[] rotation = {180f,1f,0f,0f};
		float[] mtx = GL_Toolbox.getMatrix(rotation);

		conductFrameDrawing(unused, mtx);


	}

	private void conductFrameDrawing(GL10 unused, float[] mtx){

		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		surface.updateTexImage();

		intermediateProcessor.process(mtx, DimensionVault.GREEN);
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);

		recordingAdapter.onDrawFrame(unused, mtx, surface);

		if(inHeadsetMode){
			cameraStreamLeft.draw(finalTexture);
			cameraStreamRight.draw(finalTexture);
		}

		recordingAdapter.drawBox();

	}

	@SuppressLint("ClickableViewAccessibility")
	public void onSurfaceChanged(GL10 unused, int width, int height){
		GLES20.glViewport(0, 0, width, height);
		finalTexture = intermediateProcessor.setDimensions(width, height);
		recordingAdapter = new RecordingAdapter(finalTexture, width, height);


	}

	public void setCameraSurface(SurfaceTexture surface){
		this.surface = surface;
	}

	public DigitalRainAdapter getDigitalRainAdapter() {
		return intermediateProcessor.getDigitalRainAdapter();
	}

	public int getRecordingTexture() {
		return finalTexture;
	}

	public void changeRecordingState(boolean status) {
		recordingAdapter.changeRecordingState(status);
	}

	public boolean isRecording() {
		return recordingAdapter.isRecording();
	}

	public boolean isInHeadsetMode(){
		return inHeadsetMode;
	}

	public void setHeadsetMode(boolean inHeadsetMode){
		this.inHeadsetMode = inHeadsetMode;
	}
}
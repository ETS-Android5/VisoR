package com.visor.renderingpipeline;

import com.visor.model.DimensionVault;
import com.visor.recorder.RecordingAdapter;
import com.visor.screenmesh.ScreenMesh;
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
	private int finalTexture, finalTextureSBS, finalFrameBufferSBS;
	private RecordingAdapter recordingAdapter;
	private boolean inHeadsetMode = true;


	public MyRenderer(int[] images, CameraAdapter delegate, Context context){
		this.delegate = delegate;
		this.images = images;
		this.context = context;
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config){
		int cameraTexture = GL_Toolbox.createOESTexture();



		intermediateProcessor = new IntermediateProcessor(cameraTexture, DimensionVault.drawOrder, DimensionVault.wholeTriangleVertices, DimensionVault.fullTextureVertices);
		
		//DimensionVault.drawOrder, DimensionVault.triangleVerticesLeft, DimensionVault.textureVerticesLeft
		//triangleBoundsLeft = [-1 0 1 -1]  right = [0 1 1 -1]
		ScreenMesh leftScreenMesh = new ScreenMesh(3,new float[]{-1f, 0f, 1f, -1f},0f);
		ScreenMesh rightScreenMesh = new ScreenMesh(9,new float[]{0f, 1f, 1f, -1f},0f);
		/*rightScreenMesh.manipulateTriangleGrid(2, 3, 0, -.04);
		rightScreenMesh.manipulateTriangleGrid(2, 4, 0, -.08);
		rightScreenMesh.manipulateTriangleGrid(2, 5, 0, -.06);
		
		rightScreenMesh.manipulateTriangleGrid(3, 2, .02, 0);
		rightScreenMesh.manipulateTriangleGrid(3, 3, .035, 0);
		rightScreenMesh.manipulateTriangleGrid(3, 4, .03, -.1);
		rightScreenMesh.manipulateTriangleGrid(3, 5, .02, -.08);
		rightScreenMesh.manipulateTriangleGrid(3, 6, .02, -.04);
		
		rightScreenMesh.manipulateTriangleGrid(4, 2, .05, 0);
		rightScreenMesh.manipulateTriangleGrid(4, 3, .05, -.06);
		rightScreenMesh.manipulateTriangleGrid(4, 4, .03, 0);
		rightScreenMesh.manipulateTriangleGrid(4, 5, .02, .04);
		rightScreenMesh.manipulateTriangleGrid(4, 6, .02, .04);
		
		rightScreenMesh.manipulateTriangleGrid(5, 2, .01, 0);
		rightScreenMesh.manipulateTriangleGrid(5, 3, .02, 0);
		rightScreenMesh.manipulateTriangleGrid(5, 4, .02, .04);
		rightScreenMesh.manipulateTriangleGrid(5, 5, 0, 0);
		rightScreenMesh.manipulateTriangleGrid(5, 6, 0, -.02);
		*/
		/*
		for(float f : leftScreenMesh.getTextureVertices()){
			System.out.print(f+" ");
		}
		System.out.println("");
		
		for(float f : leftScreenMesh.getTriangleVertices()){
			System.out.print(f+" ");
		}
		System.out.println("");
		
		for(short f : leftScreenMesh.getDrawOrder()){
			System.out.print(f+" ");
		}
		System.out.println("");
		*/
		
		
		cameraStreamLeft = new CameraStreaming(leftScreenMesh);
		cameraStreamRight = new CameraStreaming(rightScreenMesh);

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
			cameraStreamLeft.draw(finalTexture, true, mtx);
			cameraStreamRight.draw(finalTexture, false, mtx);
		}

		recordingAdapter.drawBox();

	}

	@SuppressLint("ClickableViewAccessibility")
	public void onSurfaceChanged(GL10 unused, int width, int height){
		GLES20.glViewport(0, 0, width, height);
		
		finalTexture = intermediateProcessor.setDimensions(width, height);
		
		
		finalTextureSBS = GL_Toolbox.createRegularTexture();
		finalFrameBufferSBS = GL_Toolbox.createFrameBuffer(width, height);
		
		cameraStreamLeft.handleSBSTexture(finalFrameBufferSBS, finalTextureSBS, width, height);
		cameraStreamRight.handleSBSTexture(finalFrameBufferSBS, finalTextureSBS, width, height);
		recordingAdapter = new RecordingAdapter(finalTextureSBS, width, height);


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
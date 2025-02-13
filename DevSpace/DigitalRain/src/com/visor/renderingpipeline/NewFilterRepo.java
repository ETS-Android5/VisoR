package com.visor.renderingpipeline;

public class NewFilterRepo {
	
	private static final String basics = "precision mediump float;" +
			"varying vec2 textureCoordinate;" +
			"uniform sampler2D texture1;";

	private static final String fragmentShaderCode = basics +
					"void main() {" +
					"  gl_FragColor = texture2D( texture1, textureCoordinate );\n" +
					"}";

	public static final String fs_GrayCCIR = basics +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"; 
		
	public static final String random =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 c1 = vec3(0.3427,    0.6169,    0.0403);" +
					"const vec3 c2 = vec3(0.8356,    0.0406,    0.1238);" +
					"const vec3 c3 = vec3(0.1346,    0.2178,    0.6476);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.r, 1.-tex.g, tex.ba);" +
					"}"; 
	
	public static final String permuteRBG =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.rbga);" +
					"}";
	
	public static final String permuteGRB =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.grba);" +
					"}";
	
	
	
	public static final String permuteGBR =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.gbra);" +
					"}";
	
	public static final String permuteBRG =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.brga);" +
					"}";
	
	public static final String permuteBGR =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = vec4(tex.bgra);" +
					"}";
	
	public static final String invpermuteRBG =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = 1.-vec4(tex.rbga);" +
					"}";
	
	public static final String invpermuteGRB =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = 1.-vec4(tex.grba);" +
					"}";
	
	public static final String invpermuteGBR =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = 1.-vec4(tex.gbra);" +
					"}";
	
	public static final String invpermuteBRG =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = 1.-vec4(tex.brga);" +
					"}";
	
	public static final String invpermuteBGR =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );"+
					"  gl_FragColor = 1.-vec4(tex.bgra);" +
					"}";
	

	private static final String cb_p =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec2 rcoeff = vec2(0.56667, 0.43333);" +
					"const vec2 gcoeff = vec2(0.55833, 0.44167);" +
					"const vec2 bcoeff = vec2(0.24167, 0.75833);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float r2 =  dot(tex.rg, rcoeff);" +
					"  float g2 =  dot(tex.rg, gcoeff);" +
					"  float b2 =  dot(tex.gb, bcoeff);" +
					"  gl_FragColor = vec4(r2, g2, b2, tex.a);" +
					"}";

	private static final String cb_d =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec2 rcoeff = vec2(0.625, 0.375);" +
					"const vec2 gcoeff = vec2(0.7, 0.3);" +
					"const vec2 bcoeff = vec2(0.3, 0.7);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float r2 =  dot(tex.rg, rcoeff);" +
					"  float g2 =  dot(tex.rg, gcoeff);" +
					"  float b2 =  dot(tex.gb, bcoeff);" +
					"  gl_FragColor = vec4(r2, g2, b2, tex.a);" +
					"}";

	private static final String cb_t =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec2 rcoeff = vec2(0.95, 0.05);" +
					"const vec2 gcoeff = vec2(0.43333, 0.56667);" +
					"const vec2 bcoeff = vec2(0.475, 0.525);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float r2 =  dot(tex.rg, rcoeff);" +
					"  float g2 =  dot(tex.gb, gcoeff);" +
					"  float b2 =  dot(tex.gb, bcoeff);" + 
					"  gl_FragColor = vec4(r2, g2, b2, tex.a);" +
					"}";

	private static final String cb_daltonize_p =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec2 gcoeff = vec2(-0.255, 1.255);" +
					"const vec3 bcoeff = vec3(0.30333, -0.545, 1.2417);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float g2 =  dot(tex.rg, gcoeff);" +
					"  float b2 =  dot(tex.rgb, bcoeff);" +
					"  gl_FragColor = vec4(tex.r, g2, b2, tex.a);" +
					"}"; 

	private static final String cb_daltonize_d =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec2 rcoeff = vec2(0.885, 0.115);" +
					"const vec3 bcoeff = vec3(-0.49, 0.19, 1.3);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float r2 =  dot(tex.rg, rcoeff);" +
					"  float b2 =  dot(tex.rgb, bcoeff);" + 
					"  gl_FragColor = vec4(r2, tex.g, b2, tex.a);" +
					"}"; 

	private static final String cb_daltonize_t =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 rcoeff = vec3(1.05, -0.3825, 0.3325);" +
					"const vec2 gcoeff = vec2(1.2342, -0.23417);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  float r2 =  dot(tex.rgb, rcoeff);" +
					"  float g2 =  dot(tex.gb, gcoeff);" + 
					"  gl_FragColor = vec4( r2, g2, tex.ba);" +
					"}";

	public static final String inverted =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(vec3(1.0)-tex.rgb, tex.a);" +
					"}";

	private static final String blue =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(0.0, 0.0, tex.b, tex.a);" +
					"}";

	private static final String noblue =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(tex.r, tex.g, 0.0, tex.a);" +
					"}";

	private static final String green =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(0.0, tex.g, 0.0, tex.a);" +
					"}";

	private static final String nogreen =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(tex.r, 0.0, tex.b, tex.a);" +
					"}";

	private static final String red =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(tex.r, 0.0, 0.0, tex.a);" +
					"}";

	private static final String nored =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"  gl_FragColor = vec4(0.0, tex.gba);" +
					"}";

	private static final String posterize =
					"precision mediump float;" +
					"const mediump float Quantize = 5.0;"+
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"    gl_FragColor = vec4(vec3(ivec3(Quantize*tex.rgb + vec3(0.5)))/Quantize, tex.a);"+
					"}";

	private static String sobelEdge = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 1.0;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"void main() {" +
			"mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"mediump float i00   = dot( textureColor, W);"+
			"mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;"+
			"mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;"+
			"mediump float mag = 1.0 - length(vec2(h, v));"+
			"mediump vec3 target = vec3(mag);"+
			"gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"}";

	private static String invertedSobelEdge = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 1.0;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"void main() {" +
			"mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"mediump float i00   = dot( textureColor, W);"+
			"mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;"+
			"mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;"+
			"mediump float mag = 1.0 - length(vec2(h, v));"+
			"mediump vec3 target = vec3(mag);"+
			"gl_FragColor = vec4(vec3(1,1,1)-mix(textureColor, target, intensity), 1.0);"+
			"}";

	private static String sobelCartoon = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"void main() {" +
			"mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"mediump float i00   = dot( textureColor, W);"+
			"mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;"+
			"mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;"+
			"mediump float mag = 1.0 - length(vec2(h, v));"+
			"mediump vec3 target = vec3(mag);"+
			"gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"}";


	private static String sobelCartoon2 =
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"const mediump float factor = 5.0;"+
			"void main() {" +
			"mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"mediump vec2 stp0 = vec2(factor*imageWidthFactor, 0.0);"+
			"mediump vec2 st0p = vec2(0.0, factor*imageHeightFactor);"+
			"mediump vec2 stpp = vec2(factor*imageWidthFactor, factor*imageHeightFactor);"+
			"mediump vec2 stpm = vec2(factor*imageWidthFactor, -factor*imageHeightFactor);"+
			"mediump float i00   = dot( textureColor, W);"+
			"mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;"+
			"mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;"+
			"mediump float mag = 1.0 - length(vec2(h, v));"+
			"mediump vec3 target = vec3(mag);"+
			"gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"}";


	private static String sobelPosterize = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"const mediump float MagTol = 0.25;"+
			"const mediump float Quantize = 5.0;"+
			"void main() {" +
			"  mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"  mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"  mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"  mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"  mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"  mediump float i00   = dot( textureColor, W);"+
			"  mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"  mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"  mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"  mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"  mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"  mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"  mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"  mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"  mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;"+
			"  mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;"+
			"  mediump float mag = 1.0 - length(vec2(h, v));"+
			"  mediump vec3 target = vec3(mag);"+
			"  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"  if(mag > MagTol){"+
			"    textureColor *= Quantize;"+
			"    textureColor += vec3(.5);"+
			"    ivec3 intrgb = ivec3(textureColor);"+
			"    textureColor = vec3(intrgb)/Quantize;"+
			"    gl_FragColor = vec4(textureColor, 1.);"+
			"  }"+
			"}";

	public static final String redFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(1,0,0)) < .5){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";

	public static final String greenFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(0,1,0)) < 0.7){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";

	public static final String blueFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(0,0,1)) < .7){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";

	public static final String cyanFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(0,1,1)) < .7){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";

	public static final String magentaFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(1,0,1)) < .8){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";

	public static final String yellowFlash =
					"precision mediump float;" +
					"varying vec2 textureCoordinate;" +
					"uniform sampler2D texture1;" +
					"const vec3 graycoeff = vec3(0.299, 0.587, 0.114);" +
					"void main() {" +
					"  vec4 tex = texture2D( texture1, textureCoordinate );" +
					"if(length(tex.rgb-vec3(1,1,0)) < .7){"+
					"  gl_FragColor = vec4(tex);"+
					"}"+
					"else{"+
					"  float c = dot(tex.rgb, graycoeff);" +
					"  gl_FragColor = vec4(vec3(c), tex.a);" +
					"}"+
					"}";


	private static String sobelPosterize2 = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"const mediump float MagTol = 0.3;"+
			"const mediump float Quantize = 5.0;"+
			"void main() {" +
			"  mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"  mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"  mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"  mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"  mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"  mediump float i00   = dot( textureColor, W);"+
			"  mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"  mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"  mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"  mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"  mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"  mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"  mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"  mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"  mediump float h = -im1p1 - 5.0 * i0p1 - ip1p1 + im1m1 + 5.0 * i0m1 + ip1m1;"+
			"  mediump float v = -im1m1 - 5.0 * im10 - im1p1 + ip1m1 + 5.0 * ip10 + ip1p1;"+
			"  mediump float mag = 1.0 - length(vec2(h, v));"+
			"  mediump vec3 target = vec3(mag);"+
			"  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"  if(mag > MagTol){"+
			"    textureColor *= Quantize;"+
			"    textureColor += vec3(.5);"+
			"    ivec3 intrgb = ivec3(textureColor);"+
			"    textureColor = vec3(intrgb)/Quantize;"+
			"    gl_FragColor = vec4(textureColor, 1.);"+
			"  }"
			+ "else{"
			+ "gl_FragColor = vec4(vec3(0.0), 1.);}"+
			"}";
	
	private static String sobelPosterize3 = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"const mediump float MagTol = 0.3;"+
			"const mediump float Quantize = 15.0;"+
			"const mediump float factor = 2.5;"+
			"void main() {" +
			"  mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"  mediump vec2 stp0 = vec2(factor*imageWidthFactor, 0.0);"+
			"  mediump vec2 st0p = vec2(0.0, factor*imageHeightFactor);"+
			"  mediump vec2 stpp = vec2(factor*imageWidthFactor, factor*imageHeightFactor);"+
			"  mediump vec2 stpm = vec2(factor*imageWidthFactor, -factor*imageHeightFactor);"+
			"  mediump float i00   = dot( textureColor, W);"+
			"  mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"  mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"  mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"  mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"  mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"  mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"  mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"  mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"  mediump float h = -im1p1 - 5.0 * i0p1 - ip1p1 + im1m1 + 5.0 * i0m1 + ip1m1;"+
			"  mediump float v = -im1m1 - 5.0 * im10 - im1p1 + ip1m1 + 5.0 * ip10 + ip1p1;"+
			"  mediump float mag = 1.0 - length(vec2(h, v));"+
			"  mediump vec3 target = vec3(mag);"+
			"  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"  if(mag > MagTol){"+
			"    textureColor *= Quantize;"+
			"    textureColor += vec3(.5);"+
			"    ivec3 intrgb = ivec3(textureColor);"+
			"    textureColor = vec3(intrgb)/Quantize;"+
			"    gl_FragColor = vec4(textureColor, 1.);"+
			"  }"
			+ "else{"
			+ "gl_FragColor = vec4(vec3(0.0), 1.);}"+
			"}";

	
	private static String cartoonFlash = 
			"precision mediump float;" +
			"varying mediump vec2 textureCoordinate;" +
			"uniform sampler2D texture1;" +
			"const mediump float intensity = 0.5;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"uniform mediump float irand;"+
			"uniform mediump float jrand;"+
			"uniform mediump float krand;"+
			"const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);"+
			"const mediump float MagTol = 0.1;"+
			"const mediump float Quantize = 3.0;"+
			"void main() {" +
			"  mediump vec3 textureColor = texture2D(texture1, textureCoordinate).rgb;"+
			"  mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);"+
			"  mediump vec2 st0p = vec2(0.0, imageHeightFactor);"+
			"  mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);"+
			"  mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);"+
			"  mediump float i00   = dot( textureColor, W);"+
			"  mediump float im1m1 = dot( texture2D(texture1, textureCoordinate - stpp).rgb, W);"+
			"  mediump float ip1p1 = dot( texture2D(texture1, textureCoordinate + stpp).rgb, W);"+
			"  mediump float im1p1 = dot( texture2D(texture1, textureCoordinate - stpm).rgb, W);"+
			"  mediump float ip1m1 = dot( texture2D(texture1, textureCoordinate + stpm).rgb, W);"+
			"  mediump float im10 = dot( texture2D(texture1, textureCoordinate - stp0).rgb, W);"+
			"  mediump float ip10 = dot( texture2D(texture1, textureCoordinate + stp0).rgb, W);"+
			"  mediump float i0m1 = dot( texture2D(texture1, textureCoordinate - st0p).rgb, W);"+
			"  mediump float i0p1 = dot( texture2D(texture1, textureCoordinate + st0p).rgb, W);"+
			"  mediump float h = -im1p1 - 5.0 * i0p1 - ip1p1 + im1m1 + 5.0 * i0m1 + ip1m1;"+
			"  mediump float v = -im1m1 - 5.0 * im10 - im1p1 + ip1m1 + 5.0 * ip10 + ip1p1;"+
			"  mediump float mag = 1.0 - length(vec2(h, v));"+
			"  mediump vec3 target = vec3(mag);"+
			"  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);"+
			"  if(mag > MagTol){"+
			"    textureColor *= Quantize;"+
			"    textureColor += vec3(.5);"+
			"    ivec3 intrgb = ivec3(textureColor);"+
			"    textureColor = vec3(intrgb)/Quantize;"+
			"  if(length(textureColor - vec3(irand,jrand,krand) ) < 0.3  ){"+
			"    gl_FragColor = vec4(textureColor, 1.);"+
			"    }else{"+
			"    gl_FragColor = vec4(1.);"+
			"  }}"
			+ "else{"
			+ "gl_FragColor = vec4(vec3(0.0), 1.);}"+
			"}";
	
	
	public static final String kuwahara =
			"precision mediump float;" +
			"varying vec2 textureCoordinate;"+
			"uniform sampler2D texture1;" +
			"const int radius = 2;"+
			"uniform mediump float imageWidthFactor;"+
			"uniform mediump float imageHeightFactor;"+
			"void main (void) {"+
			"  vec2 uv = textureCoordinate;"+
	    "float n = float((radius + 1) * (radius + 1));"+
	    "vec2 sizeScaling = vec2(imageWidthFactor, imageHeightFactor);"+
	    "vec3 m[4];"+
	    "vec3 s[4];"+
	    "for (int k = 0; k < 4; ++k) {"+
	    "    m[k] = vec3(0.0);"+
	    "    s[k] = vec3(0.0);"+
	    "}"+
	    "for (int j = -radius; j <= radius; ++j)  {"+
	    "    for (int i = -radius; i <= radius; ++i)  {"+
	    "        vec3 c = texture2D(texture1, uv + vec2(i,j) * sizeScaling).rgb;"+
	    "		 vec3 squared = c*c;"+
	    "		 if(j <= 0){"+
	    "           if(i <=0){"+
	    "               m[0] += c;"+
	    "               s[0] += squared;"+
	    "           }else{"+
	    "               m[1] += c;"+
	    "               s[1] += squared;"+
	    "           }"+
	    "        }"+
	    "        else{"+
	    "           if(i <=0){"+
	    "               m[3] += c;"+
	    "               s[3] += squared;"+
	    "           }else{"+
	    "               m[2] += c;"+
	    "               s[2] += squared;"+
	    "           }"+
	    "        }"+
	    "    }"+
	    "}"+
	    
	    /*"for (int j = -radius; j <= 0; ++j)  {"+
	    "    for (int i = -radius; i <= 0; ++i)  {"+
	    "        vec3 c = texture2D(texture1, uv + vec2(i,j) * sizeScaling).rgb;"+
	    "        m[0] += c;"+
	    "        s[0] += c * c;"+
	    "    }"+
	    "}"+
	    "for (int j = -radius; j <= 0; ++j)  {"+
	    "    for (int i = 0; i <= radius; ++i)  {"+
	    "        vec3 c = texture2D(texture1, uv + vec2(i,j) * sizeScaling).rgb;"+
	    "        m[1] += c;"+
	    "        s[1] += c * c;"+
	    "    }"+
	    "}"+
	    "for (int j = 0; j <= radius; ++j)  {"+
	    "    for (int i = 0; i <= radius; ++i)  {"+
	    "        vec3 c = texture2D(texture1, uv + vec2(i,j) * sizeScaling).rgb;"+
	    "        m[2] += c;"+
	    "        s[2] += c * c;"+
	    "    }"+
	    "}"+
	    "for (int j = 0; j <= radius; ++j)  {"+
	    "    for (int i = -radius; i <= 0; ++i)  {"+
	    "        vec3 c = texture2D(texture1, uv + vec2(i,j) * sizeScaling).rgb;"+
	    "        m[3] += c;"+
	    "        s[3] += c * c;"+
	    "    }"+
	    "}"+
	    */
	    "float min_sigma2 = 1e+2;"+
	    "for (int k = 0; k < 4; ++k) {"+
	    "    m[k] /= n;"+
	    "    s[k] = abs(s[k] / n - m[k] * m[k]);"+
	    "    float sigma2 = s[k].r + s[k].g + s[k].b;"+
	    "    if (sigma2 < min_sigma2) {"+
	    "        min_sigma2 = sigma2;"+
	    "        gl_FragColor = vec4(m[k], 1.0);"+
	    "    }"+
	    "}"+
	    "}";
	
	

	private static final String[] names = {"No Filter", "Grayscale", "Inverted",
		"No Filter",
		"Sobel Edge", "Inverted Sobel Edge", "Sobel Cartoon", "Posterize", "Sobel Posterize",
		"Sobel Posterize V2", "Sobel Posterize V3",
		"No Filter",
		"Protanopia Simulator","Deuteranopia Simulator", "Tritanopia Simulator",
		"Protanopia Corrected","Deuteranopia Corrected","Tritanopia Corrected",
		"Red","Green","Blue", "Cyan","Magenta","Yellow",
		"No Filter",
		"Red Flash", "Green Flash", "Blue Flash", "Cyan Flash", "Yellow Flash", "Magenta Flash",
		 "Cartoon Flash", "Random",
		 "No Filter",
		 "Permute RBG", "Permute GRB", "Permute GBR" , "Permute BRG", "Permute BGR",
		 "InvPermute RBG", "InvPermute GRB", "InvPermute GBR" , "InvPermute BRG", "InvPermute BGR"};

	public static final String[] shaders = {fragmentShaderCode, fs_GrayCCIR, inverted,
		fragmentShaderCode,
		sobelEdge, invertedSobelEdge, sobelCartoon, posterize, sobelPosterize,
		sobelPosterize2, sobelPosterize3,
		fragmentShaderCode,
		cb_p, cb_d, cb_t,
		cb_daltonize_p, cb_daltonize_d, cb_daltonize_t,
		red, green, blue, nored, nogreen, noblue,
		fragmentShaderCode,
		redFlash, greenFlash, blueFlash, cyanFlash, yellowFlash, magentaFlash,
		cartoonFlash, random,
		fragmentShaderCode,
		permuteRBG, permuteGRB, permuteGBR, permuteBRG, permuteBGR,
		invpermuteRBG, invpermuteGRB, invpermuteGBR, invpermuteBRG, invpermuteBGR};



}

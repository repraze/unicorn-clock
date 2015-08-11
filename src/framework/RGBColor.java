/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.awt.Color;

public class RGBColor{
	public final static RGBColor RED = new RGBColor(255,0,0);
	public final static RGBColor GREEN = new RGBColor(0,255,0);
	public final static RGBColor BLUE = new RGBColor(0,0,255);
	public final static RGBColor WHITE = new RGBColor(255,255,255);
	public final static RGBColor BLACK = new RGBColor(0,0,0);
	
	public int r,g,b;		
	
	public RGBColor(int r, int g, int b){
		this.r = clamp(r);
		this.g = clamp(g);
		this.b = clamp(b);
	}
	
	public RGBColor(Color color){
		int rgb = color.getRGB();
		this.r = (rgb >> 16) & 0x000000FF;
		this.g = (rgb >>8 ) & 0x000000FF;
		this.b = (rgb) & 0x000000FF;
	}
	
	public static RGBColor fromHSV(float hue, float saturation, float value){
		int h = (int)(hue * 6);
		if(h==6)h=0;
		float f = hue * 6 - h;
		float p = value * (1 - saturation);
		float q = value * (1 - f * saturation);
		float t = value * (1 - (1 - f) * saturation);
		
		switch (h) {
			case 0: return new RGBColor(clamp((int)(value*256)), clamp((int)(t*256)), clamp((int)(p*256)));
			case 1: return new RGBColor(clamp((int)(q*256)), clamp((int)(value*256)), clamp((int)(p*256)));
			case 2: return new RGBColor(clamp((int)(p*256)), clamp((int)(value*256)), clamp((int)(t*256)));
			case 3: return new RGBColor(clamp((int)(p*256)), clamp((int)(q*256)), clamp((int)(value*256)));
			case 4: return new RGBColor(clamp((int)(t*256)), clamp((int)(p*256)), clamp((int)(value*256)));
			case 5: return new RGBColor(clamp((int)(value*256)), clamp((int)(p*256)), clamp((int)(q*256)));
			default: throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
		}
	}
	
	public static Color blendColor(Color c1, Color c2, float blending){
		float inverse_blending = 1 - blending;

		float red =   c1.getRed()   * blending   +   c2.getRed()   * inverse_blending;
		float green = c1.getGreen() * blending   +   c2.getGreen() * inverse_blending;
		float blue =  c1.getBlue()  * blending   +   c2.getBlue()  * inverse_blending;
		
		return new Color(red / 255, green / 255, blue / 255);
	}
	
	public Color getColor(){
		return new Color(this.r, this.g, this.b);
	}
	
	private static int clamp(int c){
		if (c < 0) return 0;
		if (c > 255) return 255;
		return c;
	}
}
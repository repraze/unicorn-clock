/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package backgrounds;

import java.awt.Color;

import framework.Background;
import framework.RGBColor;

public class RainbowGradient extends Background {
	
	private float bganim;
	
	public RainbowGradient(){
		super();
		
		this.bganim = 0;
	}
	
	public void update(double time){
		super.update(time);
		
		this.bganim+=time/2;
		
		if(this.bganim>Math.PI*16){
			this.bganim-=Math.PI*16;
		}
	}
	
	public void render() {
		RGBColor rgbbase = new RGBColor(this.baseColor);
		float[] hsv = new float[3];
		Color.RGBtoHSB(rgbbase.r, rgbbase.g, rgbbase.b, hsv);
		
		Color c2 = RGBColor.fromHSV((hsv[0]+0.2f)%1.f, hsv[1], hsv[2]).getColor();
		
		for(int i=0;i<16;i++){
			Color cb = RGBColor.blendColor(this.baseColor,c2,((float)i)/16);
			g2d.setPaint(cb);
			g2d.drawLine(0, i, i, 0);
		}
		
	}

}

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

public class RainbowWave extends Background {
	
	private float bganim;
	
	public RainbowWave(){
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
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				float pc = (
						hsv[0]+
						(float)Math.cos((double)(i)/2)*0.06f+
						(float)Math.sin((double)(j+this.bganim)/2)*0.06f
						)%1.f;
				if (pc<0) pc += 1.f;
				Color cb = RGBColor.fromHSV(pc, hsv[1], hsv[2]).getColor();
				g2d.setPaint(cb);
				g2d.drawLine(i, j, i, j);
			}
		}
		
	}

}

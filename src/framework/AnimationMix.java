/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import framework.filter.Filter;
import framework.filter.Multiply;
import backgrounds.Uniform;

public class AnimationMix implements Animation {
	
	private Animation foreground, background;
	private boolean invert = false;
	private Filter filter;
	
	public AnimationMix(Animation foreground){
		this(foreground, null);
	}
	
	public AnimationMix(Animation foreground, Animation background){
		this(foreground, background, new Multiply());
	}
	
	public AnimationMix(Animation foreground, Animation background, Filter filter){
		this.foreground = foreground;
		this.background = background;
		this.filter = filter;
	}
	
	public void invert(boolean b){
		this.invert = b;
	}
	
	public void update(double time) {
		if(this.foreground!=null){
			this.foreground.update(time);
		}
		if(this.background!=null){
			this.background.update(time);
		}
	}

	public void render(Graphics2D g2d) {
		//uniform layer
		Background uniform = new Uniform();
		uniform.setBaseColor(Color.BLACK);
		BufferedImage black = uniform.getImage();
		
		if(this.foreground!=null){
			BufferedImage fore = new BufferedImage(8,8,BufferedImage.TYPE_INT_ARGB);
			this.foreground.render(fore.createGraphics());
			
			if(this.background!=null){
				BufferedImage back = new BufferedImage(8,8,BufferedImage.TYPE_INT_ARGB);
				this.background.render(back.createGraphics());
				
				if(this.invert){
					g2d.drawImage(this.filter.filter(fore, back), 0, 0,null);
				} else {
					g2d.drawImage(back, 0, 0,null);
					g2d.drawImage(this.filter.filter(fore, black), 0, 0,null);
				}
			}else{
				g2d.drawImage(fore, 0, 0,null);				
			}
		}
		
	}
}

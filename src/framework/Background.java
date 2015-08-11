/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.Color;

public abstract class Background implements Filterable {
	protected BufferedImage image;
	protected Graphics2D g2d;
	private boolean imageChanged = true;
	
	protected Color baseColor = Color.WHITE;
	
	public Background(){
		this.image = new BufferedImage(8,8,BufferedImage.TYPE_INT_ARGB);
		this.g2d = this.image.createGraphics();
	}
	
	public void setBaseColor(Color color){
		this.baseColor = color;
	}
	
	public void update(double time){
		imageChanged = true;
	}
	public abstract void render();
	
	public BufferedImage getImage(){
		if(this.imageChanged){
			this.render();
			this.imageChanged = false;
		}
		return this.image;
	}
}

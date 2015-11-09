/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package clocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import framework.Clock;

public class BinaryClock extends Clock {
	
	public BinaryClock(){
		super();
	}
	
	public BufferedImage drawClock(){
		BufferedImage img = new BufferedImage(8,8,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setPaint(Color.WHITE);
		
		String hs = Integer.toBinaryString(this.hours);
		String ms = Integer.toBinaryString(this.minutes);
		String ss = Integer.toBinaryString(this.seconds);
		
		//hours
		for(int i=0; i<hs.length();i++){
			int s = 8-hs.length();
			if(hs.charAt(i)=='1'){
				g2d.drawLine(s+i, 0, s+i, 0);
			}
		}
		
		//minutes
		for(int i=0; i<ms.length();i++){
			int s = 8-ms.length();
			if(ms.charAt(i)=='1'){
				g2d.drawLine(s+i, 3, s+i, 3);
			}
		}
		
		//seconds
		for(int i=0; i<ss.length();i++){
			int s = 8-ss.length();
			if(ss.charAt(i)=='1'){
				g2d.drawLine(s+i, 6, s+i, 6);
			}
		}
		
		return img;
	}
	
	public void render(Graphics2D g2d){
		BufferedImage clock = this.drawClock();
		
		g2d.drawImage(clock, 0, 0,null);
	}
}

/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package clocks;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import framework.Clock;


public class AuroraClock extends Clock {
	
	private Font font;
	
	public AuroraClock(){
		super();
		
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Pixel Millennium.ttf"));
			GraphicsEnvironment ge = 
				GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			this.font = font.deriveFont(8.f);
		} catch (Exception e) {
			e.printStackTrace();
			this.font = new Font("Serif", Font.PLAIN, 7);
		}
	}
	
	private void drawHours(int hours, Graphics2D g2d){
		g2d.setFont(font);
		String s = String.valueOf(hours);
		
        //placement and write
        if(hours<10){
	        g2d.drawString(s, 4, 6);
        }
        else if(hours<20){
        	if(hours == 11){
        		g2d.drawString("1", 1, 6);
        		g2d.drawString("1", 5, 6);
        	}else{
        		g2d.drawString(s, 1, 6);
        	}
        }
        else{
        	//write smaller 2
	        g2d.drawLine(1, 1, 1, 1);
	        g2d.drawLine(2, 1, 2, 3);
	        g2d.drawLine(1, 3, 1, 5);
	        g2d.drawLine(2, 5, 2, 5);
	        s = String.valueOf(hours%20);
	        //21 special
	        if(hours == 21){
	        	g2d.drawString(s, 5, 6);
	        }else{
	        	g2d.drawString(s, 4, 6);
	        }
        }
	}
	
	public BufferedImage drawClock(){
		BufferedImage img = new BufferedImage(8,8,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setPaint(Color.WHITE);
		
		//get and write time
		this.drawHours(this.hours, g2d);
		
		float completed = (((float)this.minutes)/60.f)*8.f;
        float left = completed-(int)(completed);
        
        //minutes
        if(completed>=4.f && completed<7.f){
        	g2d.drawLine(((int)completed)+1, 7, 7, 7);
        }
        else if(completed>1.f && completed<4.f){
        	g2d.drawLine(0, 7, ((int)completed)-1, 7);
        }
        
        //fader and seconds
        if(completed>=4.f){
        	//seconds
	        if(this.millis>=500){
	        	g2d.drawLine(0, 7, 0, 7);
	        }
	        //minutes fader
	        g2d.setPaint(new Color(255,255,255, (int)((1.f-left)*255.99f)));
	        g2d.drawLine(((int)completed), 7, ((int)completed), 7);
        }
        else{
        	//seconds
        	if(this.millis>=500){
	        	g2d.drawLine(7, 7, 7, 7);
	        }
	        //minutes fader
	        g2d.setPaint(new Color(255,255,255, (int)(left*255.99f)));
	        g2d.drawLine(((int)completed), 7, ((int)completed), 7);
        }
		
		return img;
	}
	
	public void render(Graphics2D g2d){
		BufferedImage clock = this.drawClock();
		
		g2d.drawImage(clock, 0, 0,null);
	}

}

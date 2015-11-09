/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Animator {
	private Screen screen;
	private Animation animation;
	private boolean playing;
	
	private BufferedImage img;
	private Graphics2D g2d;
	
	private double ticks = 8;
	
	private boolean testmode = false;
	
	public Animator(Animation animation){
		this.screen = new Screen();
		this.animation = animation;
		
		this.playing = false;
		
		this.img = new BufferedImage(screen.getWidth(),screen.getHeight(),BufferedImage.TYPE_INT_ARGB);
		this.g2d = img.createGraphics();
	}
	
	public void setTestMode(boolean testmode){
		this.testmode = testmode;
	}
	
	private double getTime(){
		return System.nanoTime()/1000000000D;
	}
	
	public void run(){
		double lastTime = getTime();
		double ns = 1 / ticks;
		double delta = 0;
		
		this.playing = true;
		
		JLabel lab = null;
		if(this.testmode==true){
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new BorderLayout());
			lab = new JLabel(new ImageIcon(img));
			frame.getContentPane().add(lab, BorderLayout.CENTER);
			frame.setMinimumSize(new Dimension(360,360));
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);	
		}
		
		while(this.playing){
			double now = getTime();
		    delta += (now - lastTime);
		    lastTime = now;
		    
		    //next tick
		    if(delta >= ns){
		    	this.animation.update(delta);
		    	//fill bg in black
		    	this.g2d.setPaint(Color.BLACK);
		    	this.g2d.fillRect(0, 0, 8, 8);
				this.animation.render(this.g2d);
		    	
				if(this.testmode){
					lab.setIcon(new ImageIcon(this.img.getScaledInstance(256, 256, Image.SCALE_REPLICATE)));
				}else{
					//update screen
					if(this.img!=null){
						this.screen.setImage(this.img);
					}
					this.screen.update();
				}
		    	delta-= ns;
		    }else{
		    	//sleep a bit
		    	try {
					Thread.sleep(80);
				} catch (InterruptedException e) {}
		    }
		}
		
		this.screen.end();
	}
	
	public void stop(){
		this.playing = false;
	}
	
	public boolean isPlaying(){
		return this.playing;
	}
}

/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.image.BufferedImage;

public class Screen{
	private int width = 8, height = 8;
	private int[][] pixels;
	
	private boolean screenRun = false;
	private Process process;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private static final String command = "sudo python displayer2.py";
	
	//public enum Orientation {TOP,BOTTOM,LEFT,RIGHT};
	
	public static void main(String[] args){
		Screen s = new Screen();
		RGBColor[] colors = {RGBColor.RED, RGBColor.GREEN, RGBColor.BLUE, RGBColor.WHITE};
		
		
		
		while(true){
			for(int c=0; c<colors.length; c++){
				for(int i=0; i<8; i++){
					for(int j=0; j<8; j++){
						//s.setPixel(i, j, 255, 127, 12);
						s.setPixel(i, j, colors[c]);
						s.update();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
					}
				}
			}
		}
	}
	
	public Screen(){
		//init screen updater
		try{
			this.process = Runtime.getRuntime().exec(command);
			this.writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			this.screenRun = true;
			
		}catch(Exception e){
			
		}
		
		this.pixels = new int[this.width*this.height][3];
		//set all to 0
		for(int i = 0; i<this.width*this.height; i++){
			this.pixels[i][0]=0;
			this.pixels[i][1]=0;
			this.pixels[i][2]=0;
		}
		//initial update
		this.update();
	}
	
	public void setPixel(int x, int y, RGBColor c){
		this.setPixel(x, y, c.r, c.g, c.b);
	}
	
	public void setPixel(int x, int y, int r, int g, int b){
		this.pixels[y*8+x][0]=r;
		this.pixels[y*8+x][1]=g;
		this.pixels[y*8+x][2]=b;
	}
	
	public int[] getPixel(int x, int y){
		return this.pixels[y*8+x];
	}
	
	public void setImage(BufferedImage img){
		int width = img.getHeight();
		if(width>this.width) width=this.width;
		int height = img.getHeight();
		if(height>this.height) height=this.height;
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				int rgb = img.getRGB(i, j);
				int red = (rgb >> 16) & 0x000000FF;
				int green = (rgb >>8 ) & 0x000000FF;
				int blue = (rgb) & 0x000000FF;
				
				this.setPixel(i,j,red,green,blue);
			}
		}
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public boolean update(){
		if(!this.screenRun){
			return false;
		}
		
		//compile String
		String text = "";
		for(int i = 0; i<this.width*this.height; i++){
			text += Integer.toString(this.pixels[i][0])+","+
					Integer.toString(this.pixels[i][1])+","+
					Integer.toString(this.pixels[i][2])+";";
		}
		
		try {
			this.writer.write(text+"\n");
			this.writer.flush();
			this.reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void end(){
		if(!this.screenRun){
			return;
		}
		this.screenRun = false;
		try {
			this.reader.close();
			this.writer.close();
		} catch (IOException e) {
			
		}
		
		this.process.destroy();
	}
	
	
}
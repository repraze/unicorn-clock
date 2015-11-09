package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Text implements Animation {
	private Font font;
	
	protected String text;
	protected float speed;
	
	protected float x;
	protected int width;
	
	public Text(String text){
		this.text = text;
		this.speed = 0.5f;
		this.x = 0;
		
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
		
		this.width = 0;
	}
	
	
	public void update(double time) {
		this.x += this.speed;
		if(this.x>this.width){
			this.x = 0;
		}
	}

	public void render(Graphics2D g2d) {
		if(this.width==0){
			FontMetrics metrics = g2d.getFontMetrics(this.font);
			this.width = metrics.stringWidth(this.text) + 8;
		}
		
		g2d.setPaint(Color.WHITE);
		g2d.setFont(this.font);
		g2d.drawString(this.text, 8-(int)this.x, 6);
	}

}

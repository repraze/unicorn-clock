/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework.filter;
import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Filter {
	public static int[] RGBsplit(int rgb){
		int r = (rgb >> 16) & 0x000000FF;
		int g = (rgb >>8 ) & 0x000000FF;
		int b = (rgb) & 0x000000FF;
		int a = (rgb >> 24) & 0x000000FF;
		
		return new int[]{r,g,b,a};
	}
	
	protected static BufferedImage execute(BufferedImage image, BufferedImage mask, FilterOperation filter){
		BufferedImage blend = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		for(int j=0; j<blend.getHeight(); j++){
			for(int i=0; i<blend.getWidth(); i++){
				int rgb = image.getRGB(i, j);
				int mrgb = mask.getRGB(i, j);
				
				int[] ic = RGBsplit(rgb);
				int[] mc = RGBsplit(mrgb);
				
				int[] oc = filter.calc(ic, mc);
				
				blend.setRGB(i, j, new Color(oc[0],oc[1],oc[2],oc[3]).getRGB());
			}
		}
		
		return blend;
	}
	
	public abstract BufferedImage filter(BufferedImage image, BufferedImage mask);
}

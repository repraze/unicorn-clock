/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Filter {
	public static int[] RGBsplit(int rgb){
		int r = (rgb >> 16) & 0x000000FF;
		int g = (rgb >>8 ) & 0x000000FF;
		int b = (rgb) & 0x000000FF;
		int a = (rgb >> 24) & 0x000000FF;
		
		return new int[]{r,g,b,a};
	}
	
	private interface FilterOperation{
		public int[] calc(int imageRGB[], int maskRGB[]);
	}
	
	private static BufferedImage execute(BufferedImage image, BufferedImage mask, FilterOperation filter){
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
	
	public static BufferedImage multiply(BufferedImage image, BufferedImage mask){
		return execute(image, mask, new FilterOperation(){
			public int[] calc(int[] imageRGB, int[] maskRGB) {
				int[] blendRGB = new int[4];
				for(int i=0; i<4; i++){
					blendRGB[i] = (imageRGB[i]*maskRGB[i])/255;
				}
				return blendRGB;
			}
		});
	}
	
	public static BufferedImage divide(BufferedImage image, BufferedImage mask){
		return execute(image, mask, new FilterOperation(){
			public int[] calc(int[] imageRGB, int[] maskRGB) {
				int[] blendRGB = new int[4];
				for(int i=0; i<4; i++){
					blendRGB[i] = (256*imageRGB[i])/(maskRGB[i]+1);
					blendRGB[i] = Math.min(blendRGB[i], 255);
					blendRGB[i] = Math.max(blendRGB[i], 0);
				}
				return blendRGB;
			}
		});
	}
}

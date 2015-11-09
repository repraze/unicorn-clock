/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework.filter;

import java.awt.image.BufferedImage;

public class Divide extends Filter {
	
	public BufferedImage filter(BufferedImage image, BufferedImage mask) {
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

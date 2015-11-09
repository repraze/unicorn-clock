/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework.filter;

import java.awt.image.BufferedImage;

public class Multiply extends Filter {
	
	public BufferedImage filter(BufferedImage image, BufferedImage mask) {
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
	
}

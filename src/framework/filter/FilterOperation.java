/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework.filter;

public interface FilterOperation{
	public int[] calc(int imageRGB[], int maskRGB[]);
}

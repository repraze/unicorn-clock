/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package backgrounds;

import framework.Background;

public class Uniform extends Background {
	
	public Uniform(){
		super();
	}
	
	public void update(double time){
		super.update(time);
	}
	
	public void render() {
		g2d.setPaint(this.baseColor);
		g2d.fillRect(0, 0, 8, 8);
	}

}

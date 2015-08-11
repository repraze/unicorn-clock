/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.awt.Graphics2D;


public interface Animation {
	public abstract void update(double time);
	public abstract void render(Graphics2D g2d);
}

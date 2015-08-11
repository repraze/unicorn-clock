/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

import backgrounds.RainbowWave;
import clockfaces.AuroraClock;
import framework.Animator;
import framework.Clock;


public class UnicornClock {
	public static void main(String[] args){
		// - create a new clock
		Clock clock = new AuroraClock(new RainbowWave());
		
		// - set the test mode of the clock 
		//clock.setTestMode(true);
		
		// - reduce light emitted by the clock if supported
		//clock.setNightMode(true);
		
		// - creates new animator with the clock as an animation
		Animator animator = new Animator(clock);
		
		// - set the animator into testmode to be displayed and tested onto a computer, comment out to use on the Pi!
		animator.setTestMode(true);
		
		// - runs the animation
		animator.run();
	}
}



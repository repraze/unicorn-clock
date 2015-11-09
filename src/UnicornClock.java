/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

import backgrounds.RainbowGradient;
import backgrounds.RainbowWave;
import clocks.AuroraClock;
import clocks.BinaryClock;
import clocks.EmptyClock;
import framework.AnimationMix;
import framework.Animator;
import framework.Background;
import framework.Clock;
import framework.Text;


public class UnicornClock {
	public static void main(String[] args){
		// - create a new clock
		Clock clock = new EmptyClock();
		//Clock clock = new AuroraClock(new RainbowWave());
		Background bg = new RainbowGradient();
		clock.SetColorizer(bg);
		
		// - set the test mode of the clock 
		clock.setTestMode(true);
		
		// - creates new animator with the clock as an animation
		// mix to change how the filter is used
		//AnimationMix mix = new AnimationMix(clock, bg);
		AnimationMix mix = new AnimationMix(new Text("UnicornClock is Awesome!"), new AnimationMix(clock, bg));
		mix.invert(true);
		Animator animator = new Animator(mix);
		
		// - set the animator into testmode to be displayed and tested onto a computer, comment out to use on the Pi!
		animator.setTestMode(true);
		
		// - runs the animation
		animator.run();
	}
}



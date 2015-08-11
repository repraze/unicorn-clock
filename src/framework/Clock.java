/*!
* Unicorn Clock
* https://github.com/repraze/unicorn-clock
*
* Copyright 2015, - Thomas Dubosc (http://repraze.com)
* Released under the MIT license
*/

package framework;
import java.util.Calendar;


public abstract class Clock implements Animation {
	
	protected int hours, minutes, seconds, millis;
	private boolean testmode = false;
	protected boolean nightmode = false;
	
	public Clock(){
		this.hours	 = 0;
		this.minutes = 0;
		this.seconds = 0;
		this.millis	 = 0;
	}
	
	public void setTestMode(boolean testmode){
		this.testmode = testmode;
	}
	
	public void setNightMode(boolean nightmode){
		this.nightmode = nightmode;
	}

	public void update(double time) {
		Calendar calendar = Calendar.getInstance();
		if(testmode){
			this.seconds = calendar.get(Calendar.SECOND);
			this.millis	= calendar.get(Calendar.MILLISECOND);
			this.minutes++;
			if(this.minutes==60){
				this.minutes=0;
				this.hours++;
				if(this.hours==24){
					this.hours=0;
				}
			}
			return;
		}
		this.hours 	 = calendar.get(Calendar.HOUR_OF_DAY);
		this.minutes = calendar.get(Calendar.MINUTE);
		this.seconds = calendar.get(Calendar.SECOND);
		this.millis	 = calendar.get(Calendar.MILLISECOND);
	}
}

# unicorn-clock

> **PLEASE NOTE:** This project is still in early stage. In the future additional clocks will be added as well as extra online features.

### Creating a beautiful clock

Creating a colorful clock running on a Raspberry Pi with a Unicorn Hat.

![Unicorn Clock](https://raw.githubusercontent.com/repraze/unicorn-clock/master/images/pi1-small.jpg)

## What you need

- A Raspberry Pi 2, B+, and A+
- A [Unicorn HAT](http://shop.pimoroni.com/products/unicorn-hat)
- A good power supply for your Pi (2A)
- Optionally a case and a difuser for the LED matrix. (the Pibow cases fit perfectly)

![Clock Details](https://github.com/repraze/unicorn-clock/blob/master/images/pi2-small.jpg)

## Getting Started
- Instal the [Unicorn Hat Python Library](https://github.com/pimoroni/unicorn-hat)
- Download/clone this project and place the source files (/src/) onto your pi
- You will need Java and Python to run this project
- Compile and run the clock
```bash
javac UnicornClock.java
...
java UnicornClock
```

Be sure not to run the clock in test mode on your pi or else nothing will appear:
(UnicornClock.java)
```java
...
// - set the animator into testmode to be displayed and tested onto a computer, comment out to use on the Pi!
animator.setTestMode(true);
...
```

## License

Copyright 2015, Thomas Dubosc  
This content is released under the MIT license 

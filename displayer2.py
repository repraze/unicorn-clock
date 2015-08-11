#!/usr/bin/env python

#!
# Unicorn Clock
# https://github.com/repraze/unicorn-clock
#
# Copyright 2015, - Thomas Dubosc (http://repraze.com)
# Released under the MIT license
#

import sys
import unicornhat as UH
import time

UH.brightness(0.05)
UH.rotation(180)

def getData():
	data = sys.stdin.readline().rstrip("\n").split(";")
	
	return data

while True:
	rgbm = getData()
	if rgbm:
		for y in range(8):
			for x in range(8):
				try:
					rgb=rgbm[y*8+x].split(",")
					UH.set_pixel(x,y,int(rgb[0]),int(rgb[1]),int(rgb[2]))
					#UH.set_pixel(x,y,255,0,0)
				except ValueError:
					pass
				except IndexError:
					pass
		UH.show()
	
	sys.stdout.write('DONE\n')
	sys.stdout.flush()
	#time.sleep(0.04)

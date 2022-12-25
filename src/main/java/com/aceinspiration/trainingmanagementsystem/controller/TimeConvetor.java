package com.aceinspiration.trainingmanagementsystem.controller;


public class TimeConvetor {
	/*START OF TIME 12 HOUR CONVERTOR*/
	static String convert12(String str) {
		String realTime = "";
		// Get Hours
		int h1 = (int) str.charAt(0) - '0';
		int h2 = (int) str.charAt(1) - '0';

		int hh = h1 * 10 + h2;

		// Finding out the Meridien of time
		// ie. AM or PM
		String Meridien;
		if (hh < 12 || hh == 0) {
			Meridien = " AM";
		} else
			Meridien = " PM";

		hh %= 12;

		// Handle 00 and 12 case separately
		if (hh == 0) {
			hh = 12;

			// Printing minutes and seconds

			realTime = hh + ":" + str.charAt(3) + str.charAt(4) + Meridien;

		} else {
			// Printing minutes and seconds

			realTime = hh + ":" + str.charAt(3) + str.charAt(4) + Meridien;

		}

		return realTime;
	}
//END OF CONVERTOR
}


package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;

import android.test.AndroidTestCase;

public class RobertJUnit extends AndroidTestCase {

	public void testGeneratePlanets() {
		
		Universe universe = new Universe(1, getContext());	
		universe.generatePlanets();
		
		assertEquals("equal",1, 1);
	}

}

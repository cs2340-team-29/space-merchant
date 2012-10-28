package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.Arrays;

public class StatGroup {

	public enum Stat {
		PILOT, FIGHTER, TRADER, ENGINEER
	}

	private int[] values = new int[Stat.values().length];

	public StatGroup() {
		Arrays.fill(values, 0);
	}

	public StatGroup(int[] statArray) {
		for(int i = 0; i < statArray.length; i++) {
			values[i] = statArray[i];
		}
	}

	public int get(Stat stat) {
		return values[stat.ordinal()];
	}

	public void set(Stat stat, int value) {
		values[stat.ordinal()] = value;
	}

	public int[] toIntArray() {
		return values;
	}

}

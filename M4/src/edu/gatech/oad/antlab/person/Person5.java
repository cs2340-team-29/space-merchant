package edu.gatech.oad.antlab.person;

/**
 * A simple class for person 5 returns their name and a modified string
 * 
 * @author Bob
 * @version 1.1
 */
public class Person5 {
	/** Holds the persons real name */
	private String name;

	/**
	 * The constructor, takes in the persons name
	 * 
	 * @param pname
	 *            the person's real name
	 */
	public Person5(String pname) {
		name = pname;
	}

	/**
	 * This method should take the string input and return its characters
	 * rotated 3 positions. given "gtg123b" it should return "123bgtg".
	 * 
	 * @param input
	 *            the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		// Person 5 put your implementation here
		char swap[] = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			int pos = i - 3;
			if (pos >= input.length()) {
				pos = pos % input.length();
			} else if(pos < 0) {
				pos = input.length() + pos;
			}
			swap[pos] = input.charAt(i);

		}
		return new String(swap);
	}

	/**
	 * Return a string rep of this object that varies with an input string
	 * 
	 * @param input
	 *            the varying string
	 * @return the string representing the object
	 */
	public String toString(String input) {
		return name + calc(input);
	}

}

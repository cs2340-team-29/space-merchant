package edu.gatech.cs2340.group29.spacemerchant.model;

public class Difficulty {

	protected final int BEGINNER = 0;
	protected final int EASY = 1;
	protected final int NORMAL = 2;
	protected final int HARD = 3;
	protected final int IMPOSSIBLE = 4;
	private int difficulty;
	private int icon;
	
	public Difficulty() {
		this.difficulty = NORMAL;
		this.icon = android.R.drawable.ic_dialog_alert;
	}
	
	public Difficulty(int difficulty_level) {
		this.difficulty = difficulty_level;
		this.icon = android.R.drawable.ic_dialog_alert;
	}
	
	public Difficulty(int difficulty_level, int drawable) {
		this.difficulty = difficulty_level;
		this.icon = drawable;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
	
}

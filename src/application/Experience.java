package application;

public class Experience {
	private double maxExp = 1.0;
	private double expGain = 0.2;
	private double currentExp = 0;
	private double spareExp = 0;
	private int level = 1;
	
	public double getCurrentExp() {
		return this.currentExp;
	}
	
	public double getMaxExp() {
		return this.maxExp;
	}
	
	public double getExpGain() {
		return this.expGain;
	}
	
	public double getSpareExp() {
		return this.spareExp;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void gainExp() {
		currentExp += expGain;
		if (isAbleToLevelUp())
			levelUp();
	}
	
	private void levelUp() {
		spareExp = currentExp - maxExp;
		currentExp = 0 + spareExp;
		maxExp += 1.0;
		level += 1;
		if (maxExp % 5 == 0) {
			expGain += 0.1;
		}
	}
	
	private boolean isAbleToLevelUp() {
		return currentExp > maxExp;
	}
}

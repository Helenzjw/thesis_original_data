package model;

public class Decision {
	private int server = 0;
	private int channel = 0;
	private double power = 0;
	private double dataRate = 0;
	private double benefit = 0;
	private double cost = 0;
	
	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public Decision() {
		
	}
	
	public Decision(int server, int channel) {
		this.server = server;
		this.channel = channel;
	}
	
	public Decision(Decision decision) {
		this.server = decision.getServer();
		this.channel = decision.getChannel();
		this.power = decision.getPower();
		this.dataRate = decision.getDataRate();
		this.benefit = decision.getBenefit();
		this.cost = decision.getCost();
	}
	
	public static boolean isUnallocated(Decision decision) {
		boolean isUnallocated = true;
		if(decision.getServer() != -1 && decision.getChannel() != -1) {
			isUnallocated = false;
		}
		return isUnallocated;
	}
	
	public static boolean isSame(Decision decision1, Decision decision2) {
		boolean isSame = true;
		if(decision1.getServer() != decision2.getServer()  || decision1.getChannel() != decision2.getChannel()) {
			isSame = false;
		}
		return isSame;
	}

	/**
	 * @return the power
	 */
	public double getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(double power) {
		this.power = power;
	}

	/**
	 * @return the dataRate
	 */
	public double getDataRate() {
		return dataRate;
	}

	/**
	 * @param dataRate the dataRate to set
	 */
	public void setDataRate(double dataRate) {
		this.dataRate = dataRate;
	}

	/**
	 * @return the benefit
	 */
	public double getBenefit() {
		return benefit;
	}

	/**
	 * @param benefit the benefit to set
	 */
	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

}

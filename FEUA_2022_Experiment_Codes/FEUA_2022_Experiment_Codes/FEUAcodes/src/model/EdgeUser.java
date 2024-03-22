package model;
/**
 * 
 */

import java.util.ArrayList;

/**
 * @author helen
 *
 */
public class EdgeUser {
	
    private double[] resource=new double[ConstNum.dResource];
	private ArrayList<Integer> coveredserver=new ArrayList<Integer>();
	private Location location = new Location();
	private double benefit = 0;
	private double cost = 0;
	private ArrayList<Integer> coveredBaseStation=new ArrayList<Integer>();
	private double power = 0;
	private double dataRate = 0;
	private double h2Random = 0; // for NOMA
	
	public double[] getResource(){
		return resource;
	}
	
	public void setResource(double res[]){
		resource=res;
	}
	
	public ArrayList<Integer> getCoveredserver() {
		return this.coveredserver;
	}
	
	public void setCoveredserver(ArrayList<Integer> coveredserver) {
		this.coveredserver=coveredserver;
	}
	
	public EdgeUser() {
		
	}
	
	public EdgeUser(EdgeUser u) {
		this.location = u.getLocation();
		this.coveredserver = u.getCoveredserver();
		this.resource = u.getResource();
		this.cost = u.getCost();
		this.benefit = u.getBenefit();
		this.coveredBaseStation = u.getCoveredBaseStation();
		this.power = u.getPower();
		this.dataRate = u.getDataRate();
		this.h2Random = u.getH2Random();
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the Cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param Cost the Cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * 
	 * @param user cost for allocated user
	 * @return cost
	 */
	public static double getCostCausedByUser(PhysicalMachine server,int nUser) {
		double cost = 0;
		cost = server.getEnergyCost()/nUser;
		return cost;
	}
	
	/**
	 * @param user cost for unallocated users
	 * @return
	 */
	public static double getCostCausedByUser() { // for unallocated user
		double cost = 0;
		cost = ConstNum.penalty * ConstNum.peakEnergyConsumption;
		return cost;
	}
	

	public static void resetUserSet(ArrayList<EdgeUser> users) {
		for(int i=0;i<users.size();i++) {
			users.get(i).setCost(0);
			users.get(i).setBenefit(0);
			users.get(i).setPower(0);
			users.get(i).setDataRate(0);
		}
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}
	
	/**
	 * @param user benefit for allocated user
	 * @return
	 */
	public static double getBenefitOfUser(int nUser, double dataRate, PhysicalMachine server) { // for decision != -1 (0 in the paper)
		double benefit = 0;
		benefit =  dataRate * nUser/server.getEnergyCost();
		benefit =  dataRate * nUser/5000;
		benefit =  dataRate/5000;
		return benefit;
	}
	
	public static double getBenefitOfUserfeua(int nUser, double dataRate, PhysicalMachine server, int i, ArrayList<EdgeUser> users, ArrayList<PhysicalMachine> machines, double nusedserver) { // for decision != -1 (0 in the paper)
		double benefit = 0;
		benefit = 1/users.size() + (dataRate/ConstNum.Rmax) + ((nusedserver/users.size())/machines.size());
		return benefit;
	}
	
//	public static double getBenefitOfUsertpds(int nUser, double dataRate, PhysicalMachine server) { // for decision != -1 (0 in the paper)
//		double benefit = 0;
//		//benefit =  dataRate * nUser/server.getEnergyCost();
//		benefit = (1 + Math.log(nUser + 2) * 0.01);
//		return benefit;
//	}
	
	public static double getBenefitOfUsertpds(int nUser, EdgeUser user, double dataRate, PhysicalMachine server) { // for decision != -1 (0 in the paper)
		double benefit = 0;
		//benefit =  dataRate * nUser/server.getEnergyCost();
		benefit =  0;
		for(int i =0;i<ConstNum.dResource; i++) {
			benefit += user.getResource()[i];
		}
	//	benefit *= station.getCoff();
		benefit *= 1*dataRate;
		
		return benefit;
	}
	
	/**
	 * 
	 * @param user benefit for unallocated
	 * @return
	 */
	public static double getBenefitOfUser() {// for unallocated user
		return 0;
	}

	public static double getSumOfResource(EdgeUser user) {
		double sumOfResource = 0;
		for(int i=0;i<ConstNum.dResource;i++) {
			sumOfResource+=user.getResource()[i];
		}
		return sumOfResource;
	}

	public ArrayList<Integer> getCoveredBaseStation() {
		return coveredBaseStation;
	}

	public void setCoveredBaseStation(ArrayList<Integer> coveredBaseStation) {
		this.coveredBaseStation = coveredBaseStation;
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
	 * @return the h2Random
	 */
	public double getH2Random() {
		return h2Random;
	}

	/**
	 * @param h2Random the h2Random to set
	 */
	public void setH2Random(double h2Random) {
		this.h2Random = h2Random;
	}



}

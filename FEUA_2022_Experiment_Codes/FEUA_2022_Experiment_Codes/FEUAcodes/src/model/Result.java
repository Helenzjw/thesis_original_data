package model;

import java.util.ArrayList;

public class Result {
	private double energyCost = 0;
	private double nAlloUsers = 0;
	private ArrayList<Decision> decisions = new ArrayList<>();
	private int iterTimes = 0;
	private double timeConsumption = 0;
	private double systemCost = 0;
	private double nUsedServers = 0;
	private double dataRate = 0;
	private double fairness = 0;
	/**
	 * @return the energyCost
	 */
	public double getEnergyCost() {
		return energyCost;
	}
	/**
	 * @param energyCost the energyCost to set
	 */
	public void setEnergyCost(double energyCost) {
		this.energyCost = energyCost;
	}
	/**
	 * @return the nAlloUsers
	 */
	public double getnAlloUsers() {
		return nAlloUsers;
	}
	/**
	 * @param nAlloUsers the nAlloUsers to set
	 */
	public void setnAlloUsers(double nAlloUsers) {
		this.nAlloUsers = nAlloUsers;
	}
	/**
	 * @return the decisions
	 */
	public ArrayList<Decision> getDecisions() {
		return decisions;
	}
	/**
	 * @param decisions the decisions to set
	 */
	public void setDecisions(ArrayList<Decision> decisions) {
		for(int i=0;i<decisions.size();i++) {
			this.decisions.add(new Decision(decisions.get(i)));
		}
	}
	
	/**
	 * @return the iterTimes
	 */
	public int getIterTimes() {
		return iterTimes;
	}
	/**
	 * @param iterTimes the iterTimes to set
	 */
	public void setIterTimes(int iterTimes) {
		this.iterTimes = iterTimes;
	}
	public double getTimeConsumption() {
		return timeConsumption;
	}
	public void setTimeConsumption(double timeConsumption) {
		this.timeConsumption = timeConsumption;
	}
	
	public Result() {
		
	}
	
	public Result(Result r) {
		this.energyCost = r.getEnergyCost();
		this.nAlloUsers = r.getnAlloUsers();
		this.decisions = r.getDecisions();
		this.iterTimes = r.getIterTimes();
		this.timeConsumption = r.getTimeConsumption();
		this.systemCost = r.getSystemCost();
		this.nUsedServers = r.getnUsedServers();
		this.dataRate = r.getDataRate();
		this.fairness = r.getfairness();
	}
	
	public Result(double energyCost, double nAllu,ArrayList<Decision> decisions,int iterTimes, double timeConsumption,double systemCost, double nUsedServers) {
		this.energyCost = energyCost;
		this.nAlloUsers = nAllu;
		this.decisions = decisions;
		this.iterTimes = iterTimes;
		this.timeConsumption = timeConsumption;
		this.systemCost = systemCost;
		this.nUsedServers = nUsedServers;
	}
	
	public static double getNumofAlloUsers(ArrayList<Decision> decisions) {
		double num = 0;
		for(int i=0;i<decisions.size();i++) {
			if(!Decision.isUnallocated(decisions.get(i))) {
				num++;
			}
		}
		return num/decisions.size();
	}
	
	public static double getOverallEnergyCost(ArrayList<PhysicalMachine>servers) {
		double overallEnergyCost = 0;
		for(int i=0;i<servers.size();i++) {
			if(servers.get(i).getnUsers()!=0) {
				overallEnergyCost +=servers.get(i).getEnergyCost();
			}
		}
		return overallEnergyCost;
	}
	
	
	public double getSystemCost() {
		return systemCost;
	}
	public void setSystemCost(double systemCost) {
		this.systemCost = systemCost;
	}
	
	/**
	 * 
	 * @param System Cost caused by each user, including the penalty of the unallocated users
	 * @return
	 */
	public static double getOverallSystemCost(ArrayList<PhysicalMachine>servers,ArrayList<EdgeUser>users,Result result){
		double overallCost = 0;
		for(int i=0;i<users.size();i++) {
			if(Decision.isUnallocated(result.getDecisions().get(i))) {
				users.get(i).setCost(EdgeUser.getCostCausedByUser());
			}else {
				users.get(i).setCost(EdgeUser.getCostCausedByUser(servers.get(result.getDecisions().get(i).getServer()), servers.get(result.getDecisions().get(i).getServer()).getnUsers()));
			}
			overallCost +=users.get(i).getCost();
		}
		return overallCost;
	}
	
	/**
	 * 
	 * @param System Cost caused by each user, including the penalty of the unallocated users 202209
	 * @return
	 */
	public static double getOverallSystemCostfeua(ArrayList<PhysicalMachine>servers,ArrayList<EdgeUser>users,Result result){
		double overallCost = 0;
		double num = 0;
		for(int i=0;i<users.size();i++) {
			if(!Decision.isUnallocated(result.decisions.get(i))) {
				num++;
			}
		}
		double nServers = 0;
		for(int i=0;i<servers.size();i++) {
			if(servers.get(i).getnUsers()>0) {
				nServers++;
			}
		}
		
		double fairness = 0;
		double mean = 0;
		double denominator = 0;
		
		mean = result.getOverallDataRate_result(result.getDecisions());
		
		for(int j=0;j<users.size();j++) {
			denominator += Math.pow(result.getDecisions().get(j).getDataRate(),2);
		}
		
		//fairness = Math.pow(mean, 2)/(denominator*users.size());
		fairness = (result.getOverallDataRate(result.getDecisions())/ConstNum.Rmax)*(Math.pow(mean, 2)/(denominator*users.size()));
		
		overallCost = (1 - (num/users.size()))/3 + (1 - fairness)/3 + (nServers/servers.size())/3;
		
		return overallCost;
	}
	
	
	public double getnUsedServers() {
		return nUsedServers;
	}
	public void setnUsedServers(double nUsedServers) {
		this.nUsedServers = nUsedServers;
	}
	
	/**
	 * @param the number of used edge servers
	 */
	public static double getNUsedServers(ArrayList<PhysicalMachine> servers) {
		double nServers = 0;
		for(int i=0;i<servers.size();i++) {
			if(servers.get(i).getnUsers()>0) {
				nServers++;
			}
		}
		return nServers/servers.size();
	}
	
	public static double getOverallDataRate(ArrayList<Decision> decisions) {
		double dataRate = 0;
		for(int i=0;i<decisions.size();i++) {
			dataRate += decisions.get(i).getDataRate();
		}
		return dataRate/decisions.size();
	}
	
	public static double getOverallDataRate_result(ArrayList<Decision> decisions) {
		double dataRate = 0;
		for(int i=0;i<decisions.size();i++) {
			dataRate += decisions.get(i).getDataRate();
		}
		return dataRate;
	}
	
	/**
	 * @param the value of fairness among all users' data rates
	 */
	public static double getoverallfairness (ArrayList<PhysicalMachine>servers,ArrayList<EdgeUser>users,Result result) {
		double fairness = 0;
		double mean = 0;
		double denominator = 0;
		
		mean = result.getOverallDataRate_result(result.getDecisions());
		
		for(int j=0;j<users.size();j++) {
			denominator += Math.pow(result.getDecisions().get(j).getDataRate(),2);
		}
		
		//fairness = Math.pow(mean, 2)/(denominator*users.size());
		fairness = (result.getOverallDataRate(result.getDecisions())/ConstNum.Rmax)*(Math.pow(mean, 2)/(denominator*users.size()));

		return fairness;
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
	 * @return the fairness
	 */
	public double getfairness() {
		return fairness;
	}
	
	/**
	 * @param set fairness
	 */
	public void setfairness(double fairness) {
		this.fairness = fairness;
	}

}

package model;

import java.util.ArrayList;

//TODO modify base station model 

public class BaseStation {
	private ArrayList<Integer> physicamMachines = new ArrayList<Integer>();
	private int nUser = 0;
	private double[] channelPower = new double[ConstNum.nChannel];
	private double[] channelUsedPower = new double[ConstNum.nChannel];
	private ArrayList<ArrayList<Integer>> channleUsers = new ArrayList<ArrayList<Integer>>();
	private Location location = new Location();
	private double radius = 0;
	private double energyConsumption = 0;
	private double coff = 0;
	
	
	public int getnUser() {
		return nUser;
	}
	public void setnUser(int nUser) {
		this.nUser = nUser;
	}
	
	public BaseStation() {
		
	}
	
	public BaseStation(BaseStation baseStation) {
		
		this.nUser = baseStation.getnUser();
		this.channelPower = baseStation.getChannelPower();
		this.channelUsedPower = baseStation.getChannelUsedPower();
		this.channleUsers = baseStation.getChannleUsers();
		this.location = baseStation.getLocation();
		this.radius = baseStation.getRadius();
		this.energyConsumption = baseStation.getEnergyConsumption();
		this.coff = baseStation.getCoff();
	}
	
	public static ArrayList<Integer> getCoveredBaseStations (EdgeUser user, ArrayList<BaseStation> baseStations){
		ArrayList<Integer> coveredBaseStations = new ArrayList<Integer>();
		for(int j=0;j<baseStations.size();j++) {
			if(isCoveredByBaseStation(baseStations.get(j), user)) {
				coveredBaseStations.add(new Integer(j));
			}
		}
		return coveredBaseStations;
	}
	
	public static boolean isCoveredByBaseStation(BaseStation baseStation, EdgeUser user) {
		boolean isCovered = false;
		
		for(int i=0;i<baseStation.getPhysicamMachines().size();i++) {
			for(int j=0;j<user.getCoveredserver().size();j++) {
				if(user.getCoveredserver().get(j).intValue() == baseStation.getPhysicamMachines().get(i).intValue()) {
					isCovered = true;
					break;
				}
			}
			if(isCovered == true) {
				break;
			}
		}
		
		return isCovered;
	}
	
	public static void getEnergy(ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines) {
		for(int i=0;i<stations.size();i++) {
			for(int j=0;j<stations.get(i).getPhysicamMachines().size();j++) {
				stations.get(i).setEnergyConsumption(stations.get(i).getEnergyConsumption() + machines.get(stations.get(i).getPhysicamMachines().get(j)).getEnergyCost());
			}
		}
	}
	
	
	public static void resetBaseStations(ArrayList<BaseStation> baseStations) {
		for(int i=0;i<baseStations.size();i++) {
			baseStations.get(i).setnUser(0);
			double [] usedPower = new double[ConstNum.nChannel];
			baseStations.get(i).setChannelUsedPower(usedPower);
			for(int j=0;j<ConstNum.nChannel;j++) {
				baseStations.get(i).getChannleUsers().get(j).clear();
			}
		}
	}
	
	public void addUsedPower(BaseStation baseStation, int channelIndex, double power) {
		double [] usedPower = new double[ConstNum.nChannel];
		for(int i=0;i<ConstNum.nChannel;i++) {
			usedPower[i] = baseStation.getChannelUsedPower()[i]; 
		}
		usedPower[channelIndex] += power;
		baseStation.setChannelUsedPower(usedPower);
	}
	
	public void removeUsedPower(BaseStation baseStation, int channelIndex, double power) {
		double [] usedPower = new double[ConstNum.nChannel];
		for(int i=0;i<ConstNum.nChannel;i++) {
			usedPower[i] = baseStation.getChannelUsedPower()[i]; 
		}
		usedPower[channelIndex] -= power;
		baseStation.setChannelUsedPower(usedPower);
	}
	
	public void addChannelUser(BaseStation baseStation, int channelIndex, int userIndex) {
		baseStation.getChannleUsers().get(channelIndex).add(new Integer(userIndex));
	}
	
	public void removeChannleUser(BaseStation baseStation, int channelIndex, int userIndex) {
		int index = -1;
		for(int i=0;i<baseStation.getChannleUsers().get(channelIndex).size();i++) {
			if(baseStation.getChannleUsers().get(channelIndex).get(i).intValue() == userIndex) {
				index = i;
				break;
			}
		}
		baseStation.getChannleUsers().get(channelIndex).remove(index);
	}
	
	public static double getRemainingPower (BaseStation station, int channelIndex) {
		double remainingPower = 0;
		remainingPower = station.getChannelPower()[channelIndex] - station.getChannelUsedPower()[channelIndex]; 
		return remainingPower;
	}
	
	/**
	 * @return the channelPower
	 */
	public double[] getChannelPower() {
		return channelPower;
	}
	/**
	 * @param channelPower the channelPower to set
	 */
	public void setChannelPower(double[] channelPower) {
		this.channelPower = channelPower;
	}
	/**
	 * @return the channelUsedPower
	 */
	public double[] getChannelUsedPower() {
		return channelUsedPower;
	}
	/**
	 * @param channelUsedPower the channelUsedPower to set
	 */
	public void setChannelUsedPower(double[] channelUsedPower) {
		this.channelUsedPower = channelUsedPower;
	}
	/**
	 * @return the channleUsers
	 */
	public ArrayList<ArrayList<Integer>> getChannleUsers() {
		return channleUsers;
	}
	/**
	 * @param channleUsers the channleUsers to set
	 */
	public void setChannleUsers(ArrayList<ArrayList<Integer>> channleUsers) {
		this.channleUsers = channleUsers;
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
	 * @return the physicamMachines
	 */
	public ArrayList<Integer> getPhysicamMachines() {
		return physicamMachines;
	}
	/**
	 * @param physicamMachines the physicamMachines to set
	 */
	public void setPhysicamMachines(ArrayList<Integer> physicamMachines) {
		this.physicamMachines = physicamMachines;
	}
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * @return the energyConsumption
	 */
	public double getEnergyConsumption() {
		return energyConsumption;
	}
	/**
	 * @param energyConsumption the energyConsumption to set
	 */
	public void setEnergyConsumption(double energyConsumption) {
		this.energyConsumption = energyConsumption;
	}
	public double getCoff() {
		return coff;
	}
	public void setCoff(double coff) {
		this.coff = coff;
	}

}
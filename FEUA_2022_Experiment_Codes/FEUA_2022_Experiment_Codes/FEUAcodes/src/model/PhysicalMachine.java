package model;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author helen
 *
 */
public class PhysicalMachine {
	private int attachedBSID = 0;
	private double[] capacity=new double[ConstNum.dResource];
	private double energyCost = 0;
	private int nUsers = 0;
	private double[] usedCapacity=new double[ConstNum.dResource];
	private ArrayList<Integer> users = new ArrayList<Integer>();
	
	public PhysicalMachine(){
		
	}
	
	public PhysicalMachine(PhysicalMachine s) {
		this.attachedBSID = s.getAttachedBSID();
		this.capacity = s.getCapacity();
		this.energyCost = s.getEnergyCost();
		this.nUsers = s.getnUsers();
		this.usedCapacity = s.getUsedCapacity();
		this.setUsers(s.getUsers());
	}

	
	
	public double[] getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(double[] capacity) {
		this.capacity=capacity;
	}

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
	 * @return the usedCapacity
	 */
	public double[] getUsedCapacity() {
		return usedCapacity;
	}

	/**
	 * @param usedCapacity the usedCapacity to set
	 */
	public void setUsedCapacity(double[] usedCapacity) {
		this.usedCapacity = usedCapacity;
	}
	
	/**
	 * @return the nUsers
	 */
	public int getnUsers() {
		return nUsers;
	}

	/**
	 * @param nUsers the nUsers to set
	 */
	public void setnUsers(int nUsers) {
		this.nUsers = nUsers;
	}

	
	
	public static void resetServerSet(ArrayList<PhysicalMachine> servers) {
		for(int i=0;i<servers.size();i++) {
			servers.get(i).setnUsers(0);
			double[] usedCapacity = new double[ConstNum.dResource];
			servers.get(i).setUsedCapacity(usedCapacity);
		
			servers.get(i).getUsers().clear();
		}
	}
	
	public static boolean remainingCapacitiesCheck(PhysicalMachine server,EdgeUser user) {
		boolean isAva = true;
		for(int i=0;i<ConstNum.dResource;i++) {
			if(server.getCapacity()[i]<server.getUsedCapacity()[i]+user.getResource()[i]) {
				isAva = false;
				break;
			}
		}
		return isAva;
	}
	
	public static void addUsedCapacities(PhysicalMachine server,EdgeUser user) {
		double[] usedCapacities = new double[ConstNum.dResource];
		for(int i=0;i<ConstNum.dResource;i++) {
			usedCapacities[i] = server.getUsedCapacity()[i] + user.getResource()[i];  
		}
		server.setUsedCapacity(usedCapacities);
	}
	
	public static void removeUsedCapacities(PhysicalMachine server,EdgeUser user) {
		double[] usedCapacities = new double[ConstNum.dResource];
		for(int i=0;i<ConstNum.dResource;i++) {
			usedCapacities[i] = server.getUsedCapacity()[i] - user.getResource()[i];  
		}
		server.setUsedCapacity(usedCapacities);
	}
	
	
	public static void addUser(PhysicalMachine server, int userIndex) {
		server.getUsers().add(new Integer(userIndex));
	}
	
	public static void removeUser(PhysicalMachine server, int userIndex) {
		int index = -1;
		for(int i=0;i<server.getUsers().size();i++) {
			if(server.getUsers().get(i).intValue() == userIndex) {
				index = i;
				break;
			}
		}
		server.getUsers().remove(index);
	}
	
	/**
	 * @return the users
	 */
	public ArrayList<Integer> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<Integer> users) {
		this.users = users;
	}

	/**
	 * @return the attachedBSID
	 */
	public int getAttachedBSID() {
		return attachedBSID;
	}

	/**
	 * @param attachedBSID the attachedBSID to set
	 */
	public void setAttachedBSID(int attachedBSID) {
		this.attachedBSID = attachedBSID;
	}



}

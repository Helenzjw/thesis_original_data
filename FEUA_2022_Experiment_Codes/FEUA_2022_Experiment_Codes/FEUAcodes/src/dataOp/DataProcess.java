package dataOp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.*;


public class DataProcess {
	
	public static ArrayList<EdgeUser> GetUsers(String filep){
		ArrayList<EdgeUser> users = new ArrayList<>();
		Random rad = new Random();
		
		System.out.println(filep);
		
		File file = new File(Thread.currentThread().getContextClassLoader().getResource(filep).getFile());
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			reader.readLine();
			String line = null;
			int i=0;
			while ((line = reader.readLine())!= null) {
				users.add(new EdgeUser());
				
				String item[]=line.split(",");

				double[] loc=new double[2];
				loc[0]=Double.parseDouble(item[0]);
				loc[1]=Double.parseDouble(item[1]);
				users.get(i).setLocation(new Location(loc));
				double [] QoS = new double[ConstNum.dResource];
				for(int j=0;j<QoS.length;j++) {
					QoS[j] = 1;
				}
				users.get(i).setResource(QoS);
				users.get(i).setCost(0);
				users.get(i).setBenefit(0);
				users.get(i).setDataRate(0);
				users.get(i).setPower(0);
				double h2Random = rad.nextGaussian();
				users.get(i).setH2Random(h2Random);
				
				i++;
			  }
			  reader.close();
			} catch (Exception e) {
		      e.printStackTrace(); 
		    }
		return users;
	}
	
	public static ArrayList<BaseStation> GetStations(String filep){
		ArrayList<BaseStation> stations = new ArrayList<>();
		File file = new File(Thread.currentThread().getContextClassLoader().getResource(filep).getFile());
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			reader.readLine();
			String line = null;
			int i=0;
			while ((line = reader.readLine())!= null) {
				stations.add(new BaseStation());
				
				String item[]=line.split(",");

				double[] loc=new double[2];
				loc[0]=Double.parseDouble(item[0]);
				loc[1]=Double.parseDouble(item[1]);
				stations.get(i).setLocation(new Location(loc));
				double radius = Double.parseDouble(item[2]);
				stations.get(i).setRadius(radius);
				
				//channel power
				double[] power = new double[ConstNum.nChannel];
				for(int j=0;j<ConstNum.nChannel;j++) {
					power[j] = ConstNum.power;
				}
				stations.get(i).setChannelPower(power);
				
				//channel user
				for(int j=0;j<ConstNum.nChannel;j++) {
					stations.get(i).getChannleUsers().add(new ArrayList<Integer>());
				}
				
				i++;
			  }
			  reader.close();
			} catch (Exception e) {
		      e.printStackTrace();
		    }
		
		return stations;
	}
	
	public static ArrayList<PhysicalMachine> getPhysicalMachiness(int num, int mean, Random rad){
		ArrayList<PhysicalMachine> machines = new ArrayList<PhysicalMachine>();
		
		for(int i=0;i<num;i++) {
			machines.add(new PhysicalMachine());
		}
		
		GetServerCapacity(machines, mean, rad);
		
		return machines;
	}
	
	/**
	 * @see generate physical machines based on subStations, substation is generated based on a given number
	 * @param servers
	 * @param serversNum
	 * @param rad
	 * @return
	 */
	public static ArrayList<BaseStation> getSubStations(ArrayList<BaseStation> servers,int serversNum,Random rad){
		ArrayList<BaseStation> eSubSetServers = new ArrayList<>();
		int[] indexSet = new int[serversNum];
		Arrays.fill(indexSet, -1);
		for(int i=0;i<serversNum;i++) {
			int index = 0;
			while(true) {
				index = rad.nextInt(servers.size());
				boolean mark = false;
				for(int j=0;j<indexSet.length;j++) {
					if(index == indexSet[j]) {
						mark = true;
						break;
					}
				}
				if(mark == false) {
					break;
				}
			}
			indexSet[i]=index;
			eSubSetServers.add(new BaseStation(servers.get(index)));
		}
		
		return eSubSetServers;
	}
	
	/**
	 * @see get submachines based on Base stations
	 * @param rad
	 * @param stations
	 * @param mean
	 * @return
	 */
	public static ArrayList<PhysicalMachine> getSubMachines (Random rad, ArrayList<BaseStation> stations, int mean){
		ArrayList<PhysicalMachine> subMachines = new ArrayList<PhysicalMachine>();
		int sumMachines = 0;
		for(int i=0;i<stations.size();i++) {
			int nMachines = rad.nextInt(ConstNum.nPhysicalMachines);
			for(int j=0;j<nMachines;j++) {
				subMachines.add(new PhysicalMachine());
				subMachines.get(sumMachines).setAttachedBSID(i);
				stations.get(i).getPhysicamMachines().add(new Integer(sumMachines));
				sumMachines ++;
			}
		}
		
		GetServerCapacity(subMachines, mean, rad);
		
		return subMachines;
	}
	
	
	/**
	 * @see get Sub base stations based on physical machines
	 * @param rad
	 * @param stations
	 * @param machines
	 * @return
	 */
	public static ArrayList<BaseStation> getSuBaseStations(Random rad, ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines){
		ArrayList<BaseStation> subBaseStations = new ArrayList<BaseStation>();
		int nStations = 0;
		int machinesIndex = 0;
		int stationIndex = rad.nextInt(stations.size());
		int nPhysicalMachines = rad.nextInt(ConstNum.nPhysicalMachines);
		
		subBaseStations.add(new BaseStation(stations.get(stationIndex)));
		for(int i=0;i<nPhysicalMachines && machinesIndex < machines.size();i++) {
			
			subBaseStations.get(nStations).getPhysicamMachines().add(new Integer(machinesIndex));
			machines.get(machinesIndex).setAttachedBSID(nStations);
			machinesIndex ++;
		}
		
		nStations ++;
		while(true) {
			
			stationIndex = rad.nextInt(stations.size());
			
//			// find the connected base station 
//			while(true) {
//				stationIndex = rad.nextInt(stations.size());
//				Boolean isConnected = false;
//				for(int i=0;i<subBaseStations.size();i++) {
//					if(Location.GetDist(stations.get(stationIndex).getLocation(), subBaseStations.get(i).getLocation()) < (stations.get(stationIndex).getRadius() + subBaseStations.get(i).getRadius())) {
//						isConnected = true;
//						break;
//					}
//				}
//				if(isConnected == true) {
//
//					break;
//				}
//			}
			 
			 nPhysicalMachines = rad.nextInt(ConstNum.nPhysicalMachines);
			
			subBaseStations.add(new BaseStation(stations.get(stationIndex)));
			for(int i=0;i<nPhysicalMachines && machinesIndex < machines.size();i++) {
				
				subBaseStations.get(nStations).getPhysicamMachines().add(new Integer(machinesIndex));
				machines.get(machinesIndex).setAttachedBSID(nStations);
				machinesIndex ++;
			}
			
			nStations ++;
			
			if(machinesIndex == machines.size()) {
				break;
			}
		}
		
		return subBaseStations;
	}
	
//	public static ArrayList<EdgeUser> GetSubUsers(ArrayList<EdgeUser>users,int userNum,Random rad) {
//		ArrayList<EdgeUser> eSubSetUsers = new ArrayList<>();
//		
//		ArrayList<Integer> indexSet = new ArrayList<>();
//		
//		for(int i=0;i<userNum;i++) {
//			int index = 0;
//			while(true) {
//				index = rad.nextInt(users.size());
//				boolean mark = false;
//				for(int j=0;j<indexSet.size();j++) {
//					if(index == indexSet.get(j)) {
//						mark = true;
//						break;
//					}
//				}
//				if(mark == false) {
//					break;
//				}
//			}
//			indexSet.add(new Integer(index));
//			eSubSetUsers.add(new EdgeUser(users.get(index)));
//		}
//		
//		return eSubSetUsers;
//	}
	
	//generate users after generating base stations
	public static ArrayList<EdgeUser> GetSubUsers(ArrayList<EdgeUser>users,int userNum, ArrayList<BaseStation> suBaseStations, Random rad) {
		ArrayList<EdgeUser> eSubSetUsers = new ArrayList<>();
		
		ArrayList<Integer> indexSet = new ArrayList<>();
		
		for(int i=0;i<userNum;i++) {
			int index = 0;
			while(true) {
				index = rad.nextInt(users.size());
				boolean mark1 = false;
				boolean mark2 = false;
				for(int j=0;j<indexSet.size();j++) {
					if(index == indexSet.get(j)) {
						mark1 = true;
						break;
					}
				}
				if(mark1 == false) {
					int times =0;
					for(int k=0;k<suBaseStations.size();k++) {
						if(Location.GetDist(users.get(index).getLocation(), suBaseStations.get(k).getLocation())<suBaseStations.get(k).getRadius()) {
							times++;
						}
						// user need to be covered by at least two edge servers
						if(times > 1) {
							mark2 = true;
							break;
						}
					}
				}
				if(mark1 == false && mark2 == true) {
					break;
				}
			}
			indexSet.add(new Integer(index));
			eSubSetUsers.add(new EdgeUser(users.get(index)));
		}
		
		return eSubSetUsers;
	}
	
	
	public static void GetServerCapacity(ArrayList<PhysicalMachine> servers,int mean, Random rad) {
		//capacity
		for(int i=0;i<servers.size();i++) {
			double[] capacity = new double[ConstNum.dResource];
			for(int j=0;j<ConstNum.dResource;j++) {
				while(true) {
					capacity[j] = rad.nextGaussian()+mean;
					if(capacity[j]>0) {
						break;
					}
				}
				 
			}
			servers.get(i).setCapacity(capacity);
			
			double radValue = 0;
			while(true) {
				radValue = rad.nextGaussian();
				if(radValue<1 && radValue > -1) {
					break;
				}
			}
			
			//energy consumption
			servers.get(i).setEnergyCost((ConstNum.peakEnergyConsumption-ConstNum.idleEnergyConsumption)/2 * radValue + (ConstNum.peakEnergyConsumption + ConstNum.idleEnergyConsumption)/2);

		}
		
	}
	
	public static void GetNeighbourServers(ArrayList<PhysicalMachine> servers, ArrayList<BaseStation> stations, ArrayList<EdgeUser>users) {
		for(int i=0;i<users.size();i++) {
			users.get(i).getCoveredserver().clear();
			for(int j=0;j<servers.size();j++) {
				//System.out.println("Lat: lng: " + users.get(i).getLocation().getLat() + "-" + users.get(i).getLocation().getLng());
				//System.out.println("Distance: " + Location.GetDist(users.get(i).getLocation(), stations.get(servers.get(j).getAttachedBSID()).getLocation()) + " Radius " + stations.get(servers.get(j).getAttachedBSID()).getRadius());
				if(Location.GetDist(users.get(i).getLocation(), stations.get(servers.get(j).getAttachedBSID()).getLocation()) <= stations.get(servers.get(j).getAttachedBSID()).getRadius()) {
					users.get(i).getCoveredserver().add(new Integer(j));
				}
			}
		}
	}
	
}

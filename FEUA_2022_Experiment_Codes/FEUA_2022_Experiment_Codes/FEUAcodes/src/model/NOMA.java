package model;

import java.util.ArrayList;

//TODO: data rate update

public class NOMA {
	private double minPower = Double.POSITIVE_INFINITY;
	

	/**
	 * @return the minPower
	 */
	public double getMinPower() {
		return minPower;
	}

	/**
	 * @param minPower the minPower to set
	 */
	public void setMinPower(double minPower) {
		this.minPower = minPower;
	}
	
	
	public static double getPower(double dataRate, double interference, ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines, EdgeUser user, int stationIndex, int channelIndex) {
		double power = 0;
		double dist = Location.GetDist(stations.get(stationIndex).getLocation(), user.getLocation());
		//double hRandom = rad.nextGaussian();
		
		double hRandom = user.getH2Random(); 
		double h2Pow = Math.pow(dist, ConstNum.pathLossAlphaNOMA);
		double h2 = hRandom * hRandom * ConstNum.frequencyDependceNOMA*h2Pow;
		double I = h2 * NOMA.getInterCellInterference(stations, machines, user, stationIndex);
		double powZhiShu = dataRate/stations.get(stationIndex).getChannelPower()[channelIndex];
		power = (Math.pow(2, powZhiShu) - 1) * (h2*interference + I + ConstNum.nosieNOMA) / h2;
		return power;
	}
	
	public static double GetLog2(double x) {
		double log2 = 0;
		log2 = Math.log(x)/Math.log(2);
		return log2;
	}
	
	public static double getDataRate(double power, double inteference, ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines, EdgeUser user, int stationIndex, int channelIndex) {
		double dataRate = 0;
		double dist = Location.GetDist(stations.get(stationIndex).getLocation(), user.getLocation());
		//double hRandom = rad.nextGaussian();
		double hRandom = user.getH2Random();
		double h2 = hRandom * hRandom * ConstNum.frequencyDependceNOMA*Math.pow(dist, ConstNum.pathLossAlphaNOMA);
		double I = h2 * NOMA.getInterCellInterference(stations, machines, user, stationIndex);
	//	dataRate = stations.get(stationIndex).getChannelPower()[channelIndex] * NOMA.GetLog2(1 + (h2*power)/(h2*inteference + I + ConstNum.nosieNOMA));
		dataRate = ConstNum.B * stations.get(stationIndex).getChannelPower()[channelIndex] * NOMA.GetLog2(1 + (h2*power)/(h2*inteference + I + ConstNum.nosieNOMA));
		
		if(dataRate > ConstNum.Rmax) {
			//System.out.println("Too Large Data Rate: " + dataRate);
			dataRate = ConstNum.Rmax;
		}
		//normalized:
		//dataRate = dataRate/ConstNum.Rmax;
		
		return dataRate;
	}
	
	public static double getInterference(ArrayList<EdgeUser> users, int userIndex, BaseStation station, int channelIndex) {
		double interference = 0;
		for(int i=0;i<station.getChannleUsers().get(channelIndex).size();i++) {
			if(station.getChannleUsers().get(channelIndex).get(i) == userIndex) {
				break;
			}else{
				interference += users.get(station.getChannleUsers().get(channelIndex).get(i)).getPower();
			}
		}
		return interference;
	}
	
	public static double getInterCellInterference(ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines, EdgeUser user, int stationIndex) {
		double I = 0;
		for(int i=0;i<user.getCoveredserver().size();i++) {
			if(stationIndex == machines.get(user.getCoveredserver().get(i)).getAttachedBSID()) {
				continue;
			}
			for(int j=0;j<ConstNum.nChannel;j++) {
				I += stations.get(machines.get(user.getCoveredserver().get(i)).getAttachedBSID()).getChannelPower()[j];
			}
		}
		return I;
	}
	
	public static double getInterCellInterferenceOfBaseStation(ArrayList<BaseStation> baseStations, EdgeUser user, int baseStationIndex) {
		double I = 0;
		for(int i=0;i<user.getCoveredBaseStation().size();i++) {
			if(baseStationIndex == user.getCoveredBaseStation().get(i)) {
				continue;
			}
			for(int j=0;j<ConstNum.nChannel;j++) {
				I += baseStations.get(user.getCoveredBaseStation().get(i)).getChannelPower()[j];
			}
		}
		return I;
	}
	
	public static void updateDataRate(ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> machines, ArrayList<EdgeUser> users, int serverIndex, int channelIndex) {
		for(int i=0;i<stations.get(serverIndex).getChannleUsers().get(channelIndex).size();i++) {
			int userIndex = stations.get(serverIndex).getChannleUsers().get(channelIndex).get(i);
			double interference = NOMA.getInterference(users, userIndex, stations.get(serverIndex), channelIndex);
			double dataRate = NOMA.getDataRate(users.get(userIndex).getPower(), interference, stations, machines, users.get(userIndex), serverIndex, channelIndex);
			
			users.get(userIndex).setDataRate(dataRate);
		}
	}

}

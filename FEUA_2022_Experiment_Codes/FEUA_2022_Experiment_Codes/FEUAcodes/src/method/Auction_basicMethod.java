package method;

import java.util.ArrayList;
import java.util.Random;

import model.BaseStation;
import model.ConstNum;
import model.Decision;
import model.PhysicalMachine;
import model.EdgeUser;
import model.NOMA;
import model.Result;

public class Auction_basicMethod {
	public static Result getBest (ArrayList<PhysicalMachine> servers, ArrayList<BaseStation> stations, ArrayList<EdgeUser> users) {
		Random rad = new Random();
		//System.out.println("Auction_Basic Start: userNum: "+ users.size() +", serverNum: "+ servers.size());
		
		double timeStart = System.currentTimeMillis();
		
		Result result = new Result();
		for(int j=0;j<users.size();j++) {
			result.getDecisions().add(new Decision(-1,-1));
		}
		
		//double deltaW = 0;
		
		result = getAlgorithm3Infocom19(rad, users, stations, servers);
		
		//update the overall energy cost and the number of allocated users
		//the number of allocated users
		double totalUsers = Result.getNumofAlloUsers(result.getDecisions());
		result.setnAlloUsers(totalUsers);
		//the overall energy cost of server
		double overallEnergyCost = Result.getOverallEnergyCost(servers);
		result.setEnergyCost(overallEnergyCost);
		//the overall system cost of each user
		//double overallSystemCost = Result.getOverallSystemCost(servers, users, result);
		double overallSystemCost = Result.getOverallSystemCostfeua(servers, users, result);
		result.setSystemCost(overallSystemCost);
		//the number of used edge servers
		double nServer = Result.getNUsedServers(servers);
		result.setnUsedServers(nServer);
				
		//overall data rate
		double overallDataRate = Result.getOverallDataRate(result.getDecisions());
		result.setDataRate(overallDataRate);
		
		//overall fairness
		double overallfairness = Result.getoverallfairness(servers, users, result);
		result.setfairness(overallfairness);
				
		//calculate the time consumption
		double timeEnd = System.currentTimeMillis();
		result.setTimeConsumption(timeEnd-timeStart);
		
		
		return result;
	}
	
	
	
	public static double getAlgorithm1Infocom19(Random rad, ArrayList<EdgeUser> users, ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> servers, Result tempResult, int[] baseStationDecision) {
		double cost = 0;
		// allocate the users to the base station with the minimum energy consumption
		
		//reset users and servers
		
		PhysicalMachine.resetServerSet(servers);
		EdgeUser.resetUserSet(users);
		
		for(int i=0;i<users.size();i++) {
			//the minimum energy consumption
			
			double miniEnergy = Double.MAX_VALUE;
			Decision minDecision = new Decision(-1,-1);
			for(int j=0;j<users.get(i).getCoveredserver().size();j++) {
				if(baseStationDecision[users.get(i).getCoveredserver().get(j)] == 0) {
					continue;
				}else {
					if(PhysicalMachine.remainingCapacitiesCheck(servers.get(users.get(i).getCoveredserver().get(j)), users.get(i))) {
						double energy = servers.get(users.get(i).getCoveredserver().get(j)).getEnergyCost();
						if(energy < miniEnergy) {
							miniEnergy = energy;
							int channel =rad.nextInt(ConstNum.nChannel);
							minDecision.setChannel(channel);
							minDecision.setServer(users.get(i).getCoveredserver().get(j));
						}
					}
					
				}
			}
			
			if(!Decision.isUnallocated(minDecision)) {
				
				//power //TODO
				double power = 0;
				//power = NOMA.getPower(rad, ConstNum.Rmin, servers.get(minDecision.getServer()).getChannelPower()[minDecision.getChannel()], servers, users.get(i), minDecision.getServer(), minDecision.getChannel());

				power = (ConstNum.Pmax + ConstNum.Pmin)/2;
				
				double inteference = 0;
				inteference = stations.get(servers.get(minDecision.getServer()).getAttachedBSID()).getChannelPower()[minDecision.getChannel()];
				
				// data rate
				double dataRate = NOMA.getDataRate(power, inteference, stations, servers, users.get(i), servers.get(minDecision.getServer()).getAttachedBSID(), minDecision.getChannel()); 
				
				if(dataRate > ConstNum.Rmin) {
					// update the used resources
					PhysicalMachine.addUsedCapacities(servers.get(minDecision.getServer()), users.get(i));
					// update the number of allocated User of base station
					servers.get(minDecision.getServer()).setnUsers(servers.get(minDecision.getServer()).getnUsers()+1);
				
					//update the used power
					stations.get(servers.get(minDecision.getServer()).getAttachedBSID()).addUsedPower(stations.get(servers.get(minDecision.getServer()).getAttachedBSID()), minDecision.getChannel(), minDecision.getPower());
					//update the channelUser
					stations.get(servers.get(minDecision.getServer()).getAttachedBSID()).addChannelUser(stations.get(servers.get(minDecision.getServer()).getAttachedBSID()), minDecision.getChannel(), i);
					
					
					// set power and data rate
					minDecision.setDataRate(dataRate);
					minDecision.setPower(power);
				}else {
					minDecision.setServer(-1);
					minDecision.setChannel(-1);
				}
				
				
			}
			// update the decision of result
			tempResult.getDecisions().set(i, new Decision(minDecision));
			}
		
		//update the overall energy cost and the number of allocated users
		//the number of allocated users
		double totalUsers = Result.getNumofAlloUsers(tempResult.getDecisions());
		tempResult.setnAlloUsers(totalUsers);
		//the overall energy cost of server
		double overallEnergyCost = Result.getOverallEnergyCost(servers);
		tempResult.setEnergyCost(overallEnergyCost);
		//the overall system cost of each user
		double overallSystemCost = Result.getOverallSystemCost(servers, users, tempResult);
		tempResult.setSystemCost(overallSystemCost);
		//the number of used edge servers
		double nServer = Result.getNUsedServers(servers);
		tempResult.setnUsedServers(nServer);
		//overall data rate
		double overallDataRate = Result.getOverallDataRate(tempResult.getDecisions());
		tempResult.setDataRate(overallDataRate);
		
		cost = -1 * overallSystemCost - overallEnergyCost;
		
		return cost;
	}
	
	public static Result getAlgorithm3Infocom19(Random rad, ArrayList<EdgeUser> users, ArrayList<BaseStation> stations, ArrayList<PhysicalMachine> servers) {
		double maxCost = Double.NEGATIVE_INFINITY;
		Result result = new Result();
		for(int j=0;j<users.size();j++) {
			result.getDecisions().add(new Decision(-1,-1));
		}
		
		ArrayList<PhysicalMachine> tempServers = new ArrayList<PhysicalMachine>();
		ArrayList<EdgeUser> tempUsers = new ArrayList<EdgeUser>();
		ArrayList<BaseStation> tempStations = new ArrayList<BaseStation>();
		
		for(int i=0;i<servers.size();i++) {
			tempServers.add(new PhysicalMachine(servers.get(i)));
		}
		
		for(int i=0;i<users.size();i++) {
			tempUsers.add(new EdgeUser(users.get(i)));
		}

		for(int i=0;i<stations.size();i++) {
			tempStations.add(new BaseStation(stations.get(i)));
		}
		
		// case 1: Descending of energy consumption
		//sort the base stations
		sortBaseStationsDescendingByEnergy(stations,servers);
		int[] edgeServerDecision = new int[servers.size()];
		for(int i=0;i<servers.size();i++) {
			edgeServerDecision[i] = 1;
		}
		
		for(int i=0;i<stations.size();) {
			Result tempResult = new Result();
			for(int j=0;j<users.size();j++) {
				tempResult.getDecisions().add(new Decision(-1,-1));
			}
			double cost = 0;
			cost = getAlgorithm1Infocom19(rad, tempUsers, tempStations, tempServers, tempResult, edgeServerDecision);
			if(cost>maxCost) {
				maxCost = cost;
				result = new Result(tempResult);
				
				for(int j=0;j<servers.size();j++) {
					servers.get(j).setnUsers(tempServers.get(j).getnUsers());
					double[] usedCapacity = new double[ConstNum.dResource];
					for(int k=0;k<ConstNum.dResource;k++) {
						usedCapacity[k] = tempServers.get(j).getUsedCapacity()[k]; 
					}
					servers.get(j).setUsedCapacity(usedCapacity);
				}
				
				for(int j=0;j<users.size();j++) {
					users.get(j).setBenefit(tempUsers.get(j).getBenefit());
					users.get(j).setCost(tempUsers.get(j).getCost());
				}
				
			}
			

			i++;
			if(i>=stations.size()) {
				continue;
			}
			for(int j=0;j<stations.get(i).getPhysicamMachines().size();j++) {
				edgeServerDecision[stations.get(i).getPhysicamMachines().get(j)]= 0;
			}
			
		}
		
		//other three cases are not applicable in our scenarios
		
		return result;
	}
	
	public static void sortBaseStationsDescendingByEnergy(ArrayList<BaseStation> baseStations, ArrayList<PhysicalMachine> machines) {
		//BaseStation.getEnergy(baseStations, machines);
		for(int i=0;i<baseStations.size();i++) {
			for(int j=i+1;j<baseStations.size();j++) {
				if(baseStations.get(i).getEnergyConsumption()<baseStations.get(j).getEnergyConsumption()) {
					BaseStation tempBaseStation = new BaseStation(baseStations.get(i));
					baseStations.set(i, new BaseStation(baseStations.get(j)));
					baseStations.set(j,new BaseStation(tempBaseStation));
				}
			}
		}
	}

}

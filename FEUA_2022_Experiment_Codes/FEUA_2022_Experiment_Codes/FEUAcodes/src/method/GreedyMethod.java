package method;

import java.util.ArrayList;

import model.*;

public class GreedyMethod {
	public static Result GetBest(ArrayList<PhysicalMachine> servers, ArrayList<BaseStation> stations, ArrayList<EdgeUser> users) {//most remaining capacity and most data rate
		Result result = new Result();
		
		double timeStart = System.currentTimeMillis();

		//System.out.println("Greedy Start: userNum: "+ users.size() +", serverNum: "+ servers.size());
		
		// initialize the result
		for(int i=0;i<users.size();i++) {
			result.getDecisions().add(new Decision(-1,-1));
		}
		
		// initialize users' cost and benefit
		for(int i=0;i<users.size();i++) {
			users.get(i).setBenefit(EdgeUser.getBenefitOfUser());
			users.get(i).setCost(EdgeUser.getCostCausedByUser());
		}
		
		// allocate users to the edge server with maximum remaining capacities
		
		for(int i=0;i<users.size();i++) {
			
			//allocate each user
			double maxRemainingCapacities = Double.MIN_VALUE;
			double minRemainingCapacities = Double.MAX_VALUE;
			Decision maxRemaingDecision = new Decision(-1,-1);
			Decision minRemaingDecision = new Decision(-1,-1);
			for(int j=0;j<users.get(i).getCoveredserver().size();j++) {
				int serverIndex = users.get(i).getCoveredserver().get(j);
				for(int k=0;k<ConstNum.nChannel;k++) {
					if(PhysicalMachine.remainingCapacitiesCheck(servers.get(serverIndex), users.get(i))) {//can be allocated
						double remainingCapacities = 0;
						for(int l=0;l<ConstNum.dResource;l++) {
							remainingCapacities+=servers.get(serverIndex).getCapacity()[l]-servers.get(serverIndex).getUsedCapacity()[l];
						}
						
						//power //TODO
						double power = 0;
						//power = NOMA.getPower(rad, ConstNum.Rmin, servers.get(serverIndex).getChannelPower()[k], servers, users.get(i), serverIndex, k);

						power = (ConstNum.Pmax + ConstNum.Pmin)/2;
						
						double inteference = 0;
						inteference = stations.get(servers.get(serverIndex).getAttachedBSID()).getChannelPower()[k];
						
						// data rate
						double dataRate = NOMA.getDataRate(power, inteference, stations, servers, users.get(i), servers.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID(), k); 
						
						remainingCapacities += dataRate;
						
						//find server with maximized remaining capacity
						if(remainingCapacities>maxRemainingCapacities && dataRate > ConstNum.Rmin) {
							maxRemainingCapacities = remainingCapacities;
							maxRemaingDecision.setServer(serverIndex);
							maxRemaingDecision.setChannel(k);
							// calculate power
							maxRemaingDecision.setPower(power);
							//calculate data rate
							maxRemaingDecision.setDataRate(dataRate);
						}
						
//						//find server with minimized remaining capacity
//						if(remainingCapacities<minRemainingCapacities && dataRate < ConstNum.Rmax) {
//							minRemainingCapacities = remainingCapacities;
//							maxRemaingDecision.setServer(serverIndex);
//							maxRemaingDecision.setChannel(k);
//							// calculate power
//							maxRemaingDecision.setPower(power);
//							//calculate data rate
//							maxRemaingDecision.setDataRate(dataRate);
//						}
					}
				}
				
			}
			if(!Decision.isUnallocated(maxRemaingDecision)) {
				//update server's nUser
				servers.get(maxRemaingDecision.getServer()).setnUsers(servers.get(maxRemaingDecision.getServer()).getnUsers()+1);
				//update the server's used capacities
				PhysicalMachine.addUsedCapacities(servers.get(maxRemaingDecision.getServer()), users.get(i));
				//update result: number of allocated users
				result.setnAlloUsers(result.getnAlloUsers()+1);
				
				//update the used power
				stations.get(servers.get(maxRemaingDecision.getServer()).getAttachedBSID()).addUsedPower(stations.get(servers.get(maxRemaingDecision.getServer()).getAttachedBSID()), maxRemaingDecision.getChannel(), maxRemaingDecision.getPower());
				//update the channelUser
				stations.get(servers.get(maxRemaingDecision.getServer()).getAttachedBSID()).addChannelUser(stations.get(servers.get(maxRemaingDecision.getServer()).getAttachedBSID()), maxRemaingDecision.getChannel(), i);
				
			}else {
				maxRemaingDecision.setChannel(-1);
				maxRemaingDecision.setServer(-1);
			}
			//update result:decision
			result.getDecisions().set(i, new Decision(maxRemaingDecision));
			
		}
		
		//update the overall energy cost, the number of allocated users and the time consumption
		//the number of allocated users
		double totalUsers = Result.getNumofAlloUsers(result.getDecisions());
		result.setnAlloUsers(totalUsers);
		//the overallComponentHelper energy cost
		double overallEnergyCost = Result.getOverallEnergyCost(servers);
		result.setEnergyCost(overallEnergyCost);
		//calculate the time consumption
		double timeEnd = System.currentTimeMillis();
		result.setTimeConsumption(timeEnd-timeStart);
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
		
		return result;
	}

}

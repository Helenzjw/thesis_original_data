package method;

import java.util.ArrayList;
import java.util.Random;

import model.*;

public class FEUAGame {
	public static Result GetBest(ArrayList<PhysicalMachine> machines, ArrayList<BaseStation> stations, ArrayList<EdgeUser> users) {
		
		Result result = new Result();
		Random rad = new Random();
		
		//System.out.println("Game Start: userNum: "+ users.size() +", serverNum: "+ machines.size());
		//System.out.println("Game Start: userNum: "+ users.size() +", stations: " + stations.size());
		
		// initialize result
		for(int i=0;i<users.size();i++) {
			result.getDecisions().add(new Decision(-1,-1));
		}
		
		// initialize users' cost and benefit
		for(int i=0;i<users.size();i++) {
			users.get(i).setBenefit(EdgeUser.getBenefitOfUser());
			users.get(i).setCost(EdgeUser.getCostCausedByUser());
		}
		
		while(true) {
			ArrayList<Decision> decisionArrayList = getDecisionStrategyPhase1(machines, stations, users, result);
			//System.out.println("Decision ready");
			int winner = getWinner(decisionArrayList, rad);
			
			//if(winner != -1) {
				//System.out.println("Winner ready " + winner + " Decision: " + decisionArrayList.size() + " : " + decisionArrayList.get(winner).getServer() + "," + decisionArrayList.get(winner).getChannel() +" previous decision: " + result.getDecisions().get(winner).getServer() + " , " + result.getDecisions().get(winner).getChannel());
				
			//}
			
			if(winner == -1) {
				break;
			}else {
				int decisionOfWinner_0_server = result.getDecisions().get(winner).getServer(); //the previous server
				int decisionOfWinner_0_channel = result.getDecisions().get(winner).getChannel(); //the previous server
				
				int decisionOfWinner_1_server = decisionArrayList.get(winner).getServer(); //the new server
				int decisionOfWinner_1_channel = decisionArrayList.get(winner).getChannel(); //the new server
				
				if(decisionOfWinner_0_channel == decisionOfWinner_1_channel && decisionOfWinner_0_server == decisionOfWinner_1_server) {
					continue;
				}
				
				//update result
				result.getDecisions().set(winner, new Decision(decisionArrayList.get(winner)));
				result.setIterTimes(result.getIterTimes()+1);
				
				//update the server information
				// previous decision
				if(!Decision.isUnallocated(new Decision(decisionOfWinner_0_server,decisionOfWinner_0_channel))) {
					//released the used resources of server decisionOfWinner_0
					PhysicalMachine.removeUsedCapacities(machines.get(decisionOfWinner_0_server), users.get(winner));
					
					// reset the nUser of server decisionOfWinner_0
					machines.get(decisionOfWinner_0_server).setnUsers(machines.get(decisionOfWinner_0_server).getnUsers()-1);
					
					//released the used power
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).removeUsedPower(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()), decisionOfWinner_0_channel, users.get(winner).getPower());
					
					// reset the user of server decisionOfWinner_0 on channel decisionOfWinner_0_channel
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).removeChannleUser(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()), decisionOfWinner_0_channel, winner);
					
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).setnUser(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).getnUser() - 1);
					//update data rate caused by the remove of user
					NOMA.updateDataRate(stations, machines, users, machines.get(decisionOfWinner_1_server).getAttachedBSID(), decisionOfWinner_1_channel);
				}
				//System.out.println("winner: " + winner +" Decision:" + decisionOfWinner_1_server +" , " + decisionOfWinner_1_channel);
				
				//new decision
				//update the used resources of server decisionOfWinner_1
				PhysicalMachine.addUsedCapacities(machines.get(decisionOfWinner_1_server),users.get(winner));
				//update the nUser of server decisionOfWinner_1
				machines.get(decisionOfWinner_1_server).setnUsers(machines.get(decisionOfWinner_1_server).getnUsers()+1);
				//update the used power
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).addUsedPower(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()), decisionOfWinner_1_channel, decisionArrayList.get(winner).getPower());
				//update the channelUser
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).addChannelUser(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()), decisionOfWinner_1_channel, winner);
				
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).setnUser(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).getnUser() + 1);
				
				//update winner's benefit and cost
				users.get(winner).setBenefit(decisionArrayList.get(winner).getBenefit());
				// cost
				users.get(winner).setCost(decisionArrayList.get(winner).getCost());
				//data rate and power
				users.get(winner).setPower(decisionArrayList.get(winner).getPower());
				users.get(winner).setDataRate(decisionArrayList.get(winner).getDataRate());
				
				
				//System.out.println("P1: Winner: " + winner +" Decision: " + decisionOfWinner_1_server + "," + decisionOfWinner_1_channel + " Benefit: " + users.get(winner).getBenefit() + ", user data rate: " + users.get(winner).getDataRate() + ", user cost: " + users.get(winner).getCost() + ", channel nuser: " + servers.get(decisionOfWinner_1_server).getChannleUsers().get(decisionOfWinner_1_channel).size() + ", server nuser: " + servers.get(decisionOfWinner_1_server).getnUsers());
			}
		}
		
		//update the overall energy cost and the number of allocated users
		//the number of allocated users
		double totalUsersPhase1 = Result.getNumofAlloUsers(result.getDecisions());
		result.setnAlloUsers(totalUsersPhase1);
		//the overall energy cost of server
		double overallEnergyCostPhase1 = Result.getOverallEnergyCost(machines);
		result.setEnergyCost(overallEnergyCostPhase1);
		//the overall system cost of each user
		//double overallSystemCostPhase1 = Result.getOverallSystemCost(machines, users, result);
		double overallSystemCostPhase1 = Result.getOverallSystemCostfeua(machines, users, result);
		result.setSystemCost(overallSystemCostPhase1);
		//the number of used edge servers
		double nServerPhase1 = Result.getNUsedServers(machines);
		result.setnUsedServers(nServerPhase1);
		
		//overall data rate
		double overallDataRate1 = Result.getOverallDataRate(result.getDecisions());
		result.setDataRate(overallDataRate1);
		
		//overall fairness
		double overallfairness1 = Result.getoverallfairness(machines, users, result);
		result.setfairness(overallfairness1);
		
		//System.out.println("Phase 1: total users: " + totalUsersPhase1 + " energy consumption: " +overallEnergyCostPhase1 + " system cost: " + overallSystemCostPhase1 +" used servers: " + nServerPhase1 +" overall data rate: " + overallDataRate1);		
		
		// Phase 2
		
		while(true) {
			ArrayList<Decision> decisionArrayList = getDecisionStrategyPhase2(machines, stations, users, result);
			int winner = getWinner(decisionArrayList, rad);
			if(winner==-1) {
				break;
			}else {
				int decisionOfWinner_0_server = result.getDecisions().get(winner).getServer(); //the previous server
				int decisionOfWinner_0_channel = result.getDecisions().get(winner).getChannel(); //the previous server
				
				int decisionOfWinner_1_server = decisionArrayList.get(winner).getServer(); //the new server
				int decisionOfWinner_1_channel = decisionArrayList.get(winner).getChannel(); //the new server
				
				//update result
				result.getDecisions().set(winner, new Decision(decisionArrayList.get(winner)));
				result.setIterTimes(result.getIterTimes()+1);
				
				//update the server information
				// previous decision
				if(!Decision.isUnallocated(new Decision(decisionOfWinner_0_server,decisionOfWinner_0_channel))) {
					//released the used resources of server decisionOfWinner_0
					PhysicalMachine.removeUsedCapacities(machines.get(decisionOfWinner_0_server), users.get(winner));
					
					// reset the nUser of server decisionOfWinner_0
					machines.get(decisionOfWinner_0_server).setnUsers(machines.get(decisionOfWinner_0_server).getnUsers()-1);
					
					//released the used power
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).removeUsedPower(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()), decisionOfWinner_0_channel, users.get(winner).getPower());
					
					// reset the user of server decisionOfWinner_0 on channel decisionOfWinner_0_channel
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).removeChannleUser(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()), decisionOfWinner_0_channel, winner);
					
					stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).setnUser(stations.get(machines.get(decisionOfWinner_0_server).getAttachedBSID()).getnUser() - 1);
					//update data rate caused by the remove of user
					NOMA.updateDataRate(stations, machines, users, machines.get(decisionOfWinner_1_server).getAttachedBSID(), decisionOfWinner_1_channel);
				
				
				}
				
				//new decision
				//update the used resources of server decisionOfWinner_1
				PhysicalMachine.addUsedCapacities(machines.get(decisionOfWinner_1_server),users.get(winner));
				//update the nUser of server decisionOfWinner_1
				machines.get(decisionOfWinner_1_server).setnUsers(machines.get(decisionOfWinner_1_server).getnUsers()+1);
				//update the used power
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).addUsedPower(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()), decisionOfWinner_1_channel, decisionArrayList.get(winner).getPower());
				//update the channelUser
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).addChannelUser(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()), decisionOfWinner_1_channel, winner);
				
				stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).setnUser(stations.get(machines.get(decisionOfWinner_1_server).getAttachedBSID()).getnUser() + 1);
				
				//update winner's benefit and cost
				users.get(winner).setBenefit(decisionArrayList.get(winner).getBenefit());
				// cost
				users.get(winner).setCost(decisionArrayList.get(winner).getCost());
				//data rate and power
				users.get(winner).setPower(decisionArrayList.get(winner).getPower());
				users.get(winner).setDataRate(decisionArrayList.get(winner).getDataRate());
				
				//System.out.println("P2: Winner: " + winner +" Decision: " + decisionOfWinner_1_server + "," + decisionOfWinner_1_channel + " Benefit: " + users.get(winner).getBenefit() + ", user data rate: " + users.get(winner).getDataRate() + ", user cost: " + users.get(winner).getCost() + ", channel nuser: " + servers.get(decisionOfWinner_1_server).getChannleUsers().get(decisionOfWinner_1_channel).size() + ", server nuser: " + servers.get(decisionOfWinner_1_server).getnUsers());
					
			}
		}
		
		//update the overall energy cost and the number of allocated users
		//the number of allocated users
		double totalUsersPhase2 = Result.getNumofAlloUsers(result.getDecisions());
		result.setnAlloUsers(totalUsersPhase2);
		//the overall energy cost of server
		double overallEnergyCostPhase2 = Result.getOverallEnergyCost(machines);
		result.setEnergyCost(overallEnergyCostPhase2);
		//the overall system cost of each user
		//double overallSystemCostPhase2 = Result.getOverallSystemCost(machines, users, result);
		double overallSystemCostPhase2 = Result.getOverallSystemCostfeua(machines, users, result);
		result.setSystemCost(overallSystemCostPhase2);
		//the number of used edge servers
		double nServerPhase2 = Result.getNUsedServers(machines);
		
		result.setnUsedServers(nServerPhase2);
		
		//overall data rate
		double overallDataRate2 = Result.getOverallDataRate(result.getDecisions());
		result.setDataRate(overallDataRate2);

		//overall fairness
		double overallfairness2 = Result.getoverallfairness(machines, users, result);
		result.setfairness(overallfairness2);
		
		//System.out.println("Phase 2: total users: " + totalUsersPhase2 + " energy consumption: " +overallEnergyCostPhase2 + " system cost: " + overallSystemCostPhase2 +" used servers: " + nServerPhase2 + " overall data rate: " + overallDataRate2);
			
		
		return result;
	}
	
	public static ArrayList<Decision> getDecisionStrategyPhase1(ArrayList<PhysicalMachine> machines, ArrayList<BaseStation> stations, ArrayList<EdgeUser>users,Result result){
		ArrayList<Decision> delta = new ArrayList<>();
		for(int i=0;i<users.size();i++) {
			delta.add(new Decision(-1,-1));
		}
		double timeConsumptionPerInter = 0;
		for(int i=0;i<users.size();i++) {
			double timeStart = System.currentTimeMillis();
			
			Decision maxBenefitDecision = new Decision(-1,-1);
			double maxBenefit = 0;
			
			if(Decision.isUnallocated(result.getDecisions().get(i))) {
				maxBenefit = 0;
			}else {
				maxBenefit = EdgeUser.getBenefitOfUser(machines.get(result.getDecisions().get(i).getServer()).getnUsers(), users.get(i).getDataRate(), machines.get(result.getDecisions().get(i).getServer()));
				//maxBenefit = EdgeUser.getBenefitOfUserfeua(machines.get(result.getDecisions().get(i).getServer()).getnUsers(), users.get(i).getDataRate(), machines.get(result.getDecisions().get(i).getServer()), i, users, machines, result.getnUsedServers());
			}
			users.get(i).setBenefit(maxBenefit);
			
			for(int j=0;j<users.get(i).getCoveredserver().size();j++) {
				for(int k=0;k<ConstNum.nChannel;k++) {
					if(Decision.isSame(result.getDecisions().get(i), new Decision(users.get(i).getCoveredserver().get(j),k))) {
						continue;
					}
					if(PhysicalMachine.remainingCapacitiesCheck(machines.get(users.get(i).getCoveredserver().get(j)), users.get(i))) {
						
						//power 
						// calculate the power min based on the Rmin (way 1)
						//double power = 0;
						//power = NOMA.getPower(rad, ConstNum.Rmin, servers.get(users.get(i).getCoveredserver().get(j)).getChannelPower()[k], servers, users.get(i), users.get(i).getCoveredserver().get(j), k);
						
						//if(power > servers.get(users.get(i).getCoveredserver().get(j)).getChannelPower()[k] || power < 0) {
							//continue;
						//}
						//way 2: calaculate the power min before the game
						double power = ConstNum.Pmin;
						
						double interference = 0;
						interference = NOMA.getInterference(users,i, stations.get(machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID()), k);
						
						// data rate
						//System.out.println("Machine Size: " + machines.size() + " Station Size: " + stations.size() + " Station ID " +  machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID());
						double dataRate = NOMA.getDataRate(power, interference, stations, machines,  users.get(i), machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID(), k); 
						
						// calculate the benefit and cost
						double benefit = 0;
						double cost = 0;
						
						if(dataRate < ConstNum.Rmin) {
							continue;
						}
						
						if(users.get(i).getCoveredserver().get(j) == result.getDecisions().get(i).getServer()) {
							 benefit = EdgeUser.getBenefitOfUser(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers(), dataRate, machines.get(users.get(i).getCoveredserver().get(j)));
							 //benefit = EdgeUser.getBenefitOfUserfeua(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers(), dataRate, machines.get(users.get(i).getCoveredserver().get(j)), i, users, machines, result.getnUsedServers());
									
						}else {
							 benefit = EdgeUser.getBenefitOfUser(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers()+1, dataRate, machines.get(users.get(i).getCoveredserver().get(j)));
							 //benefit = EdgeUser.getBenefitOfUserfeua(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers()+1, dataRate, machines.get(users.get(i).getCoveredserver().get(j)), i, users, machines, result.getnUsedServers());
						}
						
						
						if(maxBenefit<benefit) {
							maxBenefit = benefit;
							maxBenefitDecision.setServer(users.get(i).getCoveredserver().get(j));
							maxBenefitDecision.setChannel(k);
							maxBenefitDecision.setDataRate(dataRate);
							maxBenefitDecision.setPower(power);
							maxBenefitDecision.setBenefit(benefit);
							maxBenefitDecision.setCost(cost);
							
							if(Decision.isSame(maxBenefitDecision, result.getDecisions().get(i)) || Decision.isUnallocated(maxBenefitDecision)) {
								maxBenefitDecision.setServer(-1);
								maxBenefitDecision.setChannel(-1);

								maxBenefitDecision.setDataRate(0);
								maxBenefitDecision.setPower(0);

								maxBenefitDecision.setBenefit(0);
								maxBenefitDecision.setCost(0);
							}	
						}
					}
				}
			}
			

			
			//update user_i's update request
			delta.set(i, new Decision(maxBenefitDecision));
			
			double timeEnd = System.currentTimeMillis();
			//Find the maximum time consumption
			if((timeEnd-timeStart)>timeConsumptionPerInter) {
				timeConsumptionPerInter = timeEnd-timeStart;
			}
		}

		//update the time consumption
		result.setTimeConsumption(timeConsumptionPerInter+result.getTimeConsumption());
		return delta;
	}
	
	public static ArrayList<Decision> getDecisionStrategyPhase2(ArrayList<PhysicalMachine> machines, ArrayList<BaseStation> stations, ArrayList<EdgeUser>users,Result result){
		ArrayList<Decision> delta = new ArrayList<>();
		for(int i=0;i<users.size();i++) {
			delta.add(new Decision(-1,-1));
		}
		double timeConsumptionPerInter = 0;
		for(int i=0;i<users.size();i++) {
			double timeStart = System.currentTimeMillis();
			
			Decision maxBenefitDecision = new Decision(-1,-1);

			double maxBenefit = 0;
			
			if(Decision.isUnallocated(result.getDecisions().get(i))) {
				maxBenefit = 0;
			}else {
				maxBenefit = EdgeUser.getBenefitOfUser(machines.get(result.getDecisions().get(i).getServer()).getnUsers(), users.get(i).getDataRate(), machines.get(result.getDecisions().get(i).getServer()));
				//maxBenefit = EdgeUser.getBenefitOfUserfeua(machines.get(result.getDecisions().get(i).getServer()).getnUsers(), users.get(i).getDataRate(), machines.get(result.getDecisions().get(i).getServer()), i, users, machines, result.getnUsedServers());
			}
			users.get(i).setBenefit(maxBenefit);
			
			
			for(int j=0;j<users.get(i).getCoveredserver().size();j++) {
				for(int k=0;k<ConstNum.nChannel;k++) {
					if(Decision.isSame(result.getDecisions().get(i), new Decision(users.get(i).getCoveredserver().get(j),k))) {
						continue;
					}
					if(PhysicalMachine.remainingCapacitiesCheck(machines.get(users.get(i).getCoveredserver().get(j)), users.get(i))) {
						
						//power 
						
						//way 1: calculate the power max based on the Rmax
						//double power = 0;
						//power = NOMA.getPower(rad, ConstNum.Rmax, servers.get(users.get(i).getCoveredserver().get(j)).getChannelPower()[k], servers, users.get(i), users.get(i).getCoveredserver().get(j), k);
						//double remaingPower = EdgeServer.getRemainingPower(servers.get(users.get(i).getCoveredserver().get(j)), k);
						//if(remaingPower < power) {
							//power = remaingPower;
						//}
						
						//if(power > servers.get(users.get(i).getCoveredserver().get(j)).getChannelPower()[k] || power < 0) {
							//continue;
						//}
						
						//way 2: calculate the power_max before the game
						double power = ConstNum.Pmax;
						double remaingPower = BaseStation.getRemainingPower(stations.get(machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID()), k);
						if(remaingPower < power) {
							power = remaingPower;
						}
						
						double inteference = 0;
						inteference = NOMA.getInterference( users, i, stations.get(machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID()), k);
						
						// data rate
						double dataRate = NOMA.getDataRate(power, inteference, stations, machines, users.get(i), machines.get(users.get(i).getCoveredserver().get(j)).getAttachedBSID(), k); 
						
						if(dataRate < ConstNum.Rmin) {
							continue;
						}
						
						// calculate the benefit and cost
						double benefit = 0;
						double cost = 0;
						
						if(users.get(i).getCoveredserver().get(j) == result.getDecisions().get(i).getServer()) {
							 benefit = EdgeUser.getBenefitOfUser(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers(), dataRate, machines.get(users.get(i).getCoveredserver().get(j)));	
						}else {
							 benefit = EdgeUser.getBenefitOfUser(machines.get(users.get(i).getCoveredserver().get(j)).getnUsers()+1, dataRate, machines.get(users.get(i).getCoveredserver().get(j)));	
						}
						
						if(maxBenefit<benefit) {
							maxBenefit = benefit;
							maxBenefitDecision.setServer(users.get(i).getCoveredserver().get(j));
							maxBenefitDecision.setChannel(k);
							maxBenefitDecision.setDataRate(dataRate);
							maxBenefitDecision.setPower(power);
							maxBenefitDecision.setBenefit(benefit);
							maxBenefitDecision.setCost(cost);
							
							if(Decision.isSame(maxBenefitDecision, result.getDecisions().get(i)) || Decision.isUnallocated(maxBenefitDecision)) {
								maxBenefitDecision.setServer(-1);
								maxBenefitDecision.setChannel(-1);

								maxBenefitDecision.setDataRate(0);
								maxBenefitDecision.setPower(0);

								maxBenefitDecision.setBenefit(0);
								maxBenefitDecision.setCost(0);
							}	
						}
					}
				}
			}
			

			
			//update user_i's update request
			delta.set(i, new Decision(maxBenefitDecision));
			
			double timeEnd = System.currentTimeMillis();
			//Find the maximum time consumption
			if((timeEnd-timeStart)>timeConsumptionPerInter) {
				timeConsumptionPerInter = timeEnd-timeStart;
			}
		}

		//update the time consumption
		result.setTimeConsumption(timeConsumptionPerInter+result.getTimeConsumption());
		return delta;
	}
	
	public static int getWinner(ArrayList<Decision> decisionArrayList,Random rad) { // return the userIndex who can be updated
		int decision = -1;
		
		boolean flag = false;
		for(int i=0;i<decisionArrayList.size();i++) {
			if(!Decision.isUnallocated(decisionArrayList.get(i))) {
				flag = true;
				break;
			}
		}
		if(flag == false) {
			decision = -1;
		}else {
			//update the decision
			while(true) {
				int index = rad.nextInt(decisionArrayList.size());
				if(!Decision.isUnallocated(decisionArrayList.get(index))) {
					decision = index;
					break;
				}
			}
		}
		
		return decision;
	}
}

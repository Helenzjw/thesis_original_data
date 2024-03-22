package method;

import java.util.ArrayList;
import java.util.Random;

import model.*;

public class RandomMethod {
	public static Result GetBest(ArrayList<PhysicalMachine> servers, ArrayList<BaseStation> stations, ArrayList<EdgeUser> users) {
		Result result = new Result();
		Random rad = new Random();
		double timeStart = System.currentTimeMillis();

		//System.out.println("Random Start: userNum: "+ users.size() +", serverNum: "+ servers.size());
		
		// initialize the result
		for(int i=0;i<users.size();i++) {
			result.getDecisions().add(new Decision(-1,-1));
		}
				
		// initialize users' cost and benefit
		for(int i=0;i<users.size();i++) {
			users.get(i).setBenefit(EdgeUser.getBenefitOfUser());
			users.get(i).setCost(EdgeUser.getCostCausedByUser());
		}
				
		// allocate users to a random edge server
		for(int i=0;i<users.size();i++) {
			
			if(users.get(i).getCoveredserver().size()==0) {
				result.getDecisions().set(i, new Decision(-1,-1));
				continue;
			}
			//allocate each user
			int coveredServerIndex = rad.nextInt(users.get(i).getCoveredserver().size());
			int coveredChannelIndex = rad.nextInt(ConstNum.nChannel);
			Decision decision = new Decision(-1,-1);
			if(PhysicalMachine.remainingCapacitiesCheck(servers.get(users.get(i).getCoveredserver().get(coveredServerIndex)), users.get(i))) {// can be allocated
				
				//power //TODO
				double power = 0;
				//power = NOMA.getPower(rad, ConstNum.Rmin, servers.get(coveredServerIndex).getChannelPower()[coveredChannelIndex], servers, users.get(i), coveredServerIndex, coveredChannelIndex);
				
				power = (ConstNum.Pmax + ConstNum.Pmin)/2;
				
				double inteference = 0;
				inteference = stations.get(servers.get(coveredServerIndex).getAttachedBSID()).getChannelPower()[coveredChannelIndex];
				
				// data rate
				double dataRate = NOMA.getDataRate(power, inteference, stations, servers, users.get(i), servers.get(users.get(i).getCoveredserver().get(coveredServerIndex)).getAttachedBSID(), coveredChannelIndex); 
				
				if(dataRate>ConstNum.Rmin) {
					decision.setServer(users.get(i).getCoveredserver().get(coveredServerIndex));
					decision.setChannel(coveredChannelIndex);
					//update server's nUser
					servers.get(decision.getServer()).setnUsers(servers.get(decision.getServer()).getnUsers()+1);
					//update the server's used capacities
					PhysicalMachine.addUsedCapacities(servers.get(decision.getServer()), users.get(i));
					//update result: number of allocated users
					result.setnAlloUsers(result.getnAlloUsers()+1);
					
					//update the used power
					 stations.get(servers.get(decision.getServer()).getAttachedBSID()).addUsedPower(stations.get(servers.get(decision.getServer()).getAttachedBSID()), decision.getChannel(), decision.getPower());
					//update the channelUser
					 stations.get(servers.get(decision.getServer()).getAttachedBSID()).addChannelUser(stations.get(servers.get(decision.getServer()).getAttachedBSID()), decision.getChannel(), i);
					
					
					// set power and data rate
					decision.setDataRate(dataRate);
					decision.setPower(power);
				}else {
					decision.setChannel(-1);
					decision.setServer(-1);
				}
				
			}	
			//update result:decision
			result.getDecisions().set(i, decision);
		}
		
		//update the overall energy cost, the number of allocated users and the time consumption
		//the number of allocated users
		double totalUsers = Result.getNumofAlloUsers(result.getDecisions());
		result.setnAlloUsers(totalUsers);
		//the overallComponentHelper energy cost
		double overallEnergyCost = Result.getOverallEnergyCost(servers);
		result.setEnergyCost(overallEnergyCost);
		//the time consumption
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

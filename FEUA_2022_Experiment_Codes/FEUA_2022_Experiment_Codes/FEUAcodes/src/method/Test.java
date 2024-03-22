package method;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dataOp.DataProcess;
import model.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		//smallScale();
		//largeScale();
		//smallScaleoptimal();
		smallScale_nooptimal();
	}
	
	public static void smallScale() {
		ArrayList<EdgeUser> users=new ArrayList<EdgeUser>();
		ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
		//Matro
		String userpString = "User.csv";
		String serverpString = "Server.csv";
		
		//CBD
		//String userpString = "melb_cbd_user.csv";
		//String serverpString = "melb_cbd_station.csv";
		//large scale
		//detail result
		String optimalResultCSV = "src/data/smalloptimalResult.csv";
		String EUAGameResultCSV = "src/data/smallEUAGameResult.csv";
		String MEDAGameResultCSV = "src/data/smallMEDAGameResult.csv";
		String TPDSGameResultCSV = "src/data/smallTPDSGameResult.csv";
		String greedyResultCSV = "src/data/smallGreedyResult.csv";
		String randomResultCSV = "src/data/smallRandomResult.csv";
		String auctionBasicResultCSV = "src/data/smallAuctionBasicResult.csv";
		String auctionExtendResultCSV = "src/data/smallAuctionExtendResult.csv";
		//overall result
		String overallResutCSV = "src/data/smallOverallResult.csv";
		String overallResutTXT= "src/data/smallOverallResult.txt";
		
		users = DataProcess.GetUsers(userpString);
		stations = DataProcess.GetStations(serverpString);
		
		Random rad = new Random();
		try {	
			FileWriter optimalWriter = new FileWriter(optimalResultCSV);
			FileWriter EUAGameWriter = new FileWriter(EUAGameResultCSV);
			FileWriter MEDAGameWriter = new FileWriter(MEDAGameResultCSV);
			FileWriter TPDSGameWriter = new FileWriter(TPDSGameResultCSV);
			FileWriter greedyWriter = new FileWriter(greedyResultCSV);
			FileWriter randomWriter = new FileWriter(randomResultCSV);
			FileWriter auctionBasicWriter = new FileWriter(auctionBasicResultCSV);
			FileWriter auctionExtendWriter = new FileWriter(auctionExtendResultCSV);
			
			FileWriter overallResultCSVWriter = new FileWriter(overallResutCSV);
			FileWriter overallResultTXTWriter = new FileWriter(overallResutTXT);
			
			String[] setStrings = {"server set, 2，4，6，8，10，12","user set, 5，15，20，25，30,35","capacity set, 1，2，3，4，5，6"};
			
			String[] titleStrings ={"nUsedServer", "energyConsumption", "nAllocatedUser", "systemCost", "dataRate", "timeConsumption", "iteration", "fairness"};
			
			String[] optimalResutStrings = {"optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal"};
			String[] gameResutStrings = {"Game", "Game", "Game", "Game", "Game", "Game", "Game", "Game"};
			String[] MEDAgameResutStrings = {"MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame"};
			String[] TPDSgameResutStrings = {"TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame"};
			String[] greedyResultStrings = {"greedy", "greedy", "greedy", "greedy", "greedy", "greedy", "greedy"};
			String[] randomResultStrings = {"random", "random", "random", "random", "random", "random", "random"};
			String[] auctionBasicResultStrings = {"auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic"};
			String[] auctionExtendResultStrings = {"auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend"};
			
			//Set #1. the change number of edge server
			optimalWriter.write("Set#1,the change number of edge server\r\n");
			EUAGameWriter.write("Set#1,the change number of edge server\r\n");
			greedyWriter.write("Set#1,the change number of edge server\r\n");
			randomWriter.write("Set#1,the change number of edge server\r\n");
			auctionBasicWriter.write("Set#1,the change number of edge server\r\n");
			auctionExtendWriter.write("Set#1,the change number of edge server\r\n");
				
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int serversNum = (int) (2 + 2*i);
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {					
					//test 2: generate physical machines based on base stations
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, serversNum, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nLargeCapacity4Set12);
					
					//test 1: generate base stations based on physical machines
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(serversNum, ConstNum.nSmallCapacity4Set12, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13, suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					//System.out.println(subUsers.size());
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalResult = new Result();
					optimalResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalResult.getIterTimes();
					optimalTotalTime += optimalResult.getTimeConsumption();
					optimalTotalSystemCost += optimalResult.getSystemCost();
					optimalTotalNumUsedServers += optimalResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalResult.getDataRate();
					optimalTotalOverallFairness += optimalResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
									
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();
				
					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				optimalWriter.write("Optimal:, Servers set, " + serversNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + 
						optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", "
								+ "SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " 
						+optimalTotalOverallDataRate/ConstNum.nTimes +", Overallfairness, " +optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
						
						optimalWriter.flush();
						System.out.println("Optimal:, Servers set, " + serversNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " 
						+ optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", "
								+ "SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " 
						+optimalTotalOverallDataRate/ConstNum.nTimes +", OverallFairness, " +optimalTotalOverallFairness/ConstNum.nTimes);
				
				EUAGameWriter.write("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes);
			
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;		
						
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
				
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			// initialize
			optimalResutStrings[0] = "Optimal";
			optimalResutStrings[1] = "Optimal";
			optimalResutStrings[2] = "Optimal";
			optimalResutStrings[3] = "Optimal";
			optimalResutStrings[4] = "Optimal";
			optimalResutStrings[5] = "Optimal";
			optimalResutStrings[6] = "Optimal";
			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";
			
			//Set #2. the change number of mobile users
			optimalWriter.write("Set#2,the change number of mobile user\r\n");
			EUAGameWriter.write("Set#2,the change number of mobile user\r\n");
			MEDAGameWriter.write("Set#2,the change number of mobile user\r\n");
			TPDSGameWriter.write("Set#2,the change number of mobile user\r\n");
			greedyWriter.write("Set#2,the change number of mobile user\r\n");
			randomWriter.write("Set#2,the change number of mobile user\r\n");
			auctionBasicWriter.write("Set#2,the change number of mobile user\r\n");
			auctionExtendWriter.write("Set#2,the change number of mobile user\r\n");
			
			// TODO User test
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int userNum = 5 + (5*i);
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//test 2
					ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nSmallServers4Set23, rad);
					ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nSmallCapacity4Set12);		
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, userNum,suBaseStations, rad);
					
					//ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nLargeServers4Set23, ConstNum.nLargeCapacity4Set12, rad);
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalGameResult = new Result();
					optimalGameResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalGameResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalGameResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalGameResult.getIterTimes();
					optimalTotalTime += optimalGameResult.getTimeConsumption();
					optimalTotalSystemCost += optimalGameResult.getSystemCost();
					optimalTotalNumUsedServers += optimalGameResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalGameResult.getDataRate();
					optimalTotalOverallFairness += optimalGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				optimalWriter.write("Optimal:, User set, " + userNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
				optimalWriter.flush();
				System.out.println("Optimal:, User set, " + userNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes);		
				
				EUAGameWriter.write("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
			
				greedyWriter.write("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;	
				
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			//initilize
			optimalResutStrings[0] = "Optimal";
			optimalResutStrings[1] = "Optimal";
			optimalResutStrings[2] = "Optimal";
			optimalResutStrings[3] = "Optimal";
			optimalResutStrings[4] = "Optimal";
			optimalResutStrings[5] = "Optimal";
			optimalResutStrings[6] = "Optimal";
			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";	
			
			//Set #3. the change of available capacity
			optimalWriter.write("Set#3,the change of available capacity\r\n");
			EUAGameWriter.write("Set#3,the change of available capacity\r\n");
			MEDAGameWriter.write("Set#3,the change of available capacity\r\n");
			greedyWriter.write("Set#3,the change of available capacity\r\n");
			randomWriter.write("Set#3,the change of available capacity\r\n");
			auctionBasicWriter.write("Set#3,the change of available capacity\r\n");
			auctionExtendWriter.write("Set#3,the change of available capacity\r\n");
			
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int capacityNum = 1+1*i;
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nLargeServers4Set23, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, capacityNum);
						
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nSmallServers4Set23, capacityNum, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);				
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalGameResult = new Result();
					optimalGameResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalGameResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalGameResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalGameResult.getIterTimes();
					optimalTotalTime += optimalGameResult.getTimeConsumption();
					optimalTotalSystemCost += optimalGameResult.getSystemCost();
					optimalTotalNumUsedServers += optimalGameResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalGameResult.getDataRate();
					optimalTotalOverallFairness += optimalGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					suBaseStations.clear();
					subUsers.clear();
				}		
				
				optimalWriter.write("Optimal:, Capacity set, " + capacityNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
				optimalWriter.flush();
				System.out.println("Optimal:, Capacity set, " + capacityNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes);
	
				EUAGameWriter.write("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;	
				
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}
			

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
		
			optimalWriter.close();
			EUAGameWriter.close();
			MEDAGameWriter.close();
			TPDSGameWriter.close();
			greedyWriter.close();
			randomWriter.close();
			auctionBasicWriter.close();
			auctionExtendWriter.close();
			
			overallResultCSVWriter.close();
			overallResultTXTWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void largeScale() {
		ArrayList<EdgeUser> users=new ArrayList<EdgeUser>();
		ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
		//Matro
		String userpString = "User.csv";
		String serverpString = "Server.csv";
		
		//CBD
		//String userpString = "melb_cbd_user.csv";
		//String serverpString = "melb_cbd_station.csv";
		//large scale
		//detail result
		//String optimalResultCSV = "src/data.Matro/smalloptimalResult.csv";
		String EUAGameResultCSV = "src/data/Matro/largeEUAGameResult.csv";
		String MEDAGameResultCSV = "src/data/Matro/largeMEDAGameResult.csv";
		String TPDSGameResultCSV = "src/data/Matro/largeTPDSGameResult.csv";
		String greedyResultCSV = "src/data/Matro/largeGreedyResult.csv";
		String randomResultCSV = "src/data/Matro/largeRandomResult.csv";
		String auctionBasicResultCSV = "src/data/Matro/largeAuctionBasicResult.csv";
		String auctionExtendResultCSV = "src/data/Matro/largeAuctionExtendResult.csv";
		//overall result
		String overallResutCSV = "src/data/Matro/largeOverallResult.csv";
		String overallResutTXT= "src/data/Matro/largeOverallResult.txt";
		
		users = DataProcess.GetUsers(userpString);
		stations = DataProcess.GetStations(serverpString);
		
		Random rad = new Random();
		try {
			//FileWriter optimalWriter = new FileWriter(optimalResultCSV);
			FileWriter EUAGameWriter = new FileWriter(EUAGameResultCSV);
			FileWriter MEDAGameWriter = new FileWriter(MEDAGameResultCSV);
			FileWriter TPDSGameWriter = new FileWriter(TPDSGameResultCSV);
			FileWriter greedyWriter = new FileWriter(greedyResultCSV);
			FileWriter randomWriter = new FileWriter(randomResultCSV);
			FileWriter auctionBasicWriter = new FileWriter(auctionBasicResultCSV);
			FileWriter auctionExtendWriter = new FileWriter(auctionExtendResultCSV);
			
			FileWriter overallResultCSVWriter = new FileWriter(overallResutCSV);
			FileWriter overallResultTXTWriter = new FileWriter(overallResutTXT);
			
			String[] setStrings = {"server set, 50, 100, 150, 200, 250, 300, 350, 400","user set, 16, 32, 64, 128, 256, 512, 1024, 2048","capacity set, 10, 20, 30, 40, 50, 60, 70, 80"};
			
			String[] titleStrings ={"nUsedServerRatio", "energyConsumption", "nAllocatedUserRatio", "systemCost", "dataRate", "timeConsumption", "iteration", "fairness"};
			
			String[] optimalResutStrings = {"optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal"};
			String[] gameResutStrings = {"Game", "Game", "Game", "Game", "Game", "Game", "Game", "Game"};
			String[] MEDAgameResutStrings = {"MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame"};
			String[] TPDSgameResutStrings = {"TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame"};
			String[] greedyResultStrings = {"greedy", "greedy", "greedy", "greedy", "greedy", "greedy", "greedy"};
			String[] randomResultStrings = {"random", "random", "random", "random", "random", "random", "random"};
			String[] auctionBasicResultStrings = {"auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic"};
			String[] auctionExtendResultStrings = {"auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend"};
			
			//Set #1. the change number of edge server
			//optimalWriter.write("Set#1,the change number of edge server\r\n");
			EUAGameWriter.write("Set#1,the change number of edge server\r\n");
			MEDAGameWriter.write("Set#1,the change number of edge server\r\n");
			TPDSGameWriter.write("Set#1,the change number of edge server\r\n");
			greedyWriter.write("Set#1,the change number of edge server\r\n");
			randomWriter.write("Set#1,the change number of edge server\r\n");
			auctionBasicWriter.write("Set#1,the change number of edge server\r\n");
			auctionExtendWriter.write("Set#1,the change number of edge server\r\n");
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				int serversNum = (int) (50 + 50*i);
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {					
					//test 2: generate physical machines based on base stations
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, serversNum, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nLargeCapacity4Set12);
					
					//test 1: generate base stations based on physical machines
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(serversNum, ConstNum.nLargeCapacity4Set12, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nLargeUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					//System.out.println(subUsers.size());
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();

					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				EUAGameWriter.write("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
				
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			// initialize

			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";
			

			//Set #2. the change number of mobile users
			EUAGameWriter.write("Set#2,the change number of mobile user\r\n");
			MEDAGameWriter.write("Set#2,the change number of mobile user\r\n");
			TPDSGameWriter.write("Set#2,the change number of mobile user\r\n");
			greedyWriter.write("Set#2,the change number of mobile user\r\n");
			randomWriter.write("Set#2,the change number of mobile user\r\n");
			auctionBasicWriter.write("Set#2,the change number of mobile user\r\n");
			auctionExtendWriter.write("Set#2,the change number of mobile user\r\n");
			
			// TODO User test
			for(int i=0;i<4;i++) {
				
				System.out.println();
				
				int userNum = (int) (16 * Math.pow(2, i) );
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//test 2
					ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nLargeServers4Set23, rad);
					ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nLargeCapacity4Set12);		
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, userNum,suBaseStations, rad);
					
					//ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nLargeServers4Set23, ConstNum.nLargeCapacity4Set12, rad);
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				EUAGameWriter.write("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
			
				greedyWriter.write("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			//initilize

			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";	
			
			//Set #3. the change of available capacity
			EUAGameWriter.write("Set#3,the change of available capacity\r\n");
			MEDAGameWriter.write("Set#3,the change of available capacity\r\n");
			greedyWriter.write("Set#3,the change of available capacity\r\n");
			randomWriter.write("Set#3,the change of available capacity\r\n");
			auctionBasicWriter.write("Set#3,the change of available capacity\r\n");
			auctionExtendWriter.write("Set#3,the change of available capacity\r\n");
			
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int capacityNum = 10+10*i;
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nLargeServers4Set23, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, capacityNum);
						
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nLargeServers4Set23, capacityNum, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nLargeUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);				
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					suBaseStations.clear();
					subUsers.clear();
				}		
				
				EUAGameWriter.write("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}
			

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			EUAGameWriter.close();
			MEDAGameWriter.close();
			TPDSGameWriter.close();
			greedyWriter.close();
			randomWriter.close();
			auctionBasicWriter.close();
			auctionExtendWriter.close();
			
			overallResultCSVWriter.close();
			overallResultTXTWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void smallScaleoptimal() {
		ArrayList<EdgeUser> users=new ArrayList<EdgeUser>();
		ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
		//Matro
		//String userpString = "User.csv";
		//String serverpString = "Server.csv";
		
		//CBD
		String userpString = "melb_cbd_user.csv";
		String serverpString = "melb_cbd_station.csv";
		//large scale
		//detail result
		String optimalResultCSV = "src/data/smalloptimalResult.csv";
		
		//overall result
		String overallResutCSV = "src/data/smallOverallResult.csv";
		String overallResutTXT= "src/data/smallOverallResult.txt";
		
		users = DataProcess.GetUsers(userpString);
		stations = DataProcess.GetStations(serverpString);
		
		Random rad = new Random();
		try {	
			FileWriter optimalWriter = new FileWriter(optimalResultCSV);
			
			FileWriter overallResultCSVWriter = new FileWriter(overallResutCSV);
			FileWriter overallResultTXTWriter = new FileWriter(overallResutTXT);
			
			String[] setStrings = {"server set, 2，4，6，8，10，12,14,16","user set, 5，15，20，25，30,35,40","capacity set, 1，2，3，4，5，6,7,8"};
			
			String[] titleStrings ={"nUsedServer", "energyConsumption", "nAllocatedUser", "systemCost", "dataRate", "timeConsumption", "iteration", "fairness"};
			
			String[] optimalResutStrings = {"optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal"};
			
			//Set #1. the change number of edge server
			optimalWriter.write("Set#1,the change number of edge server\r\n");
				
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int serversNum = (int) (2 + 2*i);
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {					
					//test 2: generate physical machines based on base stations
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, serversNum, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nLargeCapacity4Set12);
					
					//test 1: generate base stations based on physical machines
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(serversNum, ConstNum.nSmallCapacity4Set12, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13, suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					//System.out.println(subUsers.size());
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalResult = new Result();
					optimalResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalResult.getIterTimes();
					optimalTotalTime += optimalResult.getTimeConsumption();
					optimalTotalSystemCost += optimalResult.getSystemCost();
					optimalTotalNumUsedServers += optimalResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalResult.getDataRate();
					optimalTotalOverallFairness += optimalResult.getfairness();
	
					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				optimalWriter.write("Optimal:, Servers set, " + serversNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + 
						optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", "
								+ "SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " 
						+optimalTotalOverallDataRate/ConstNum.nTimes +", Overallfairness, " +optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
						
						optimalWriter.flush();
						System.out.println("Optimal:, Servers set, " + serversNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " 
						+ optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", "
								+ "SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " 
						+optimalTotalOverallDataRate/ConstNum.nTimes +", OverallFairness, " +optimalTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;				
			}
	
			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			
			// initialize
			optimalResutStrings[0] = "Optimal";
			optimalResutStrings[1] = "Optimal";
			optimalResutStrings[2] = "Optimal";
			optimalResutStrings[3] = "Optimal";
			optimalResutStrings[4] = "Optimal";
			optimalResutStrings[5] = "Optimal";
			optimalResutStrings[6] = "Optimal";
			
			//Set #2. the change number of mobile users
			optimalWriter.write("Set#2,the change number of mobile user\r\n");
			
			// TODO User test
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int userNum = 5 + (5*i);
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//test 2
					ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nSmallServers4Set23, rad);
					ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nSmallCapacity4Set12);		
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, userNum,suBaseStations, rad);
					
					//ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nLargeServers4Set23, ConstNum.nLargeCapacity4Set12, rad);
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalGameResult = new Result();
					optimalGameResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalGameResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalGameResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalGameResult.getIterTimes();
					optimalTotalTime += optimalGameResult.getTimeConsumption();
					optimalTotalSystemCost += optimalGameResult.getSystemCost();
					optimalTotalNumUsedServers += optimalGameResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalGameResult.getDataRate();
					optimalTotalOverallFairness += optimalGameResult.getfairness();
					
					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				optimalWriter.write("Optimal:, User set, " + userNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
				optimalWriter.flush();
				System.out.println("Optimal:, User set, " + userNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes);		
				
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;	
				
			}
	
			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[7] +"\r\n");
			
			//initilize
			optimalResutStrings[0] = "Optimal";
			optimalResutStrings[1] = "Optimal";
			optimalResutStrings[2] = "Optimal";
			optimalResutStrings[3] = "Optimal";
			optimalResutStrings[4] = "Optimal";
			optimalResutStrings[5] = "Optimal";
			optimalResutStrings[6] = "Optimal";
		
			//Set #3. the change of available capacity
			optimalWriter.write("Set#3,the change of available capacity\r\n");
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int capacityNum = 1+1*i;
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nLargeServers4Set23, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, capacityNum);
						
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nSmallServers4Set23, capacityNum, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);				
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result optimalGameResult = new Result();
					optimalGameResult = OptimalMethod.GetBest(subServers, subUsers);
					optimalTotalEnergyConsumption += optimalGameResult.getEnergyCost();
					optimalTotalNumAllocatedUsers += optimalGameResult.getnAlloUsers();
					optimalTotalDecisionIteration += optimalGameResult.getIterTimes();
					optimalTotalTime += optimalGameResult.getTimeConsumption();
					optimalTotalSystemCost += optimalGameResult.getSystemCost();
					optimalTotalNumUsedServers += optimalGameResult.getnUsedServers();
					optimalTotalOverallDataRate += optimalGameResult.getDataRate();
					optimalTotalOverallFairness += optimalGameResult.getfairness();
					
					subServers.clear();
					suBaseStations.clear();
					subUsers.clear();
				}		
				
				optimalWriter.write("Optimal:, Capacity set, " + capacityNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes +",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes +"\r\n");
				optimalWriter.flush();
				System.out.println("Optimal:, Capacity set, " + capacityNum +", Energy Consumption, " + optimalTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + optimalTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + optimalTotalDecisionIteration/ConstNum.nTimes+",Time," + optimalTotalTime/(ConstNum.nTimes) +", SystemCost, " + optimalTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + optimalTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +optimalTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + optimalTotalOverallFairness/ConstNum.nTimes);
		
				//overall result
				optimalResutStrings[0] += ", " + optimalTotalNumUsedServers/ConstNum.nTimes;
				optimalResutStrings[1] += ", " + optimalTotalEnergyConsumption/ConstNum.nTimes;
				optimalResutStrings[2] += ", " + optimalTotalNumAllocatedUsers/ConstNum.nTimes;
				optimalResutStrings[3] += ", " + optimalTotalSystemCost/ConstNum.nTimes;
				optimalResutStrings[4] += ", " + optimalTotalOverallDataRate/ConstNum.nTimes;
				optimalResutStrings[5] += ", " + optimalTotalTime/ConstNum.nTimes;
				optimalResutStrings[6] += ", " + optimalTotalOverallFairness/ConstNum.nTimes;	
		
			}
			
	
			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(optimalResutStrings[7] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(optimalResutStrings[7] +"\r\n");
		
			optimalWriter.close();
			
			overallResultCSVWriter.close();
			overallResultTXTWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void smallScale_nooptimal() {
		ArrayList<EdgeUser> users=new ArrayList<EdgeUser>();
		ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
		//Matro
		//String userpString = "User.csv";
		//String serverpString = "Server.csv";
		
		//CBD
		String userpString = "melb_cbd_user.csv";
		String serverpString = "melb_cbd_station.csv";
		//large scale
		//detail result
		String optimalResultCSV = "src/data/smalloptimalResult.csv";
		String EUAGameResultCSV = "src/data/smallEUAGameResult.csv";
		String MEDAGameResultCSV = "src/data/smallMEDAGameResult.csv";
		String TPDSGameResultCSV = "src/data/smallTPDSGameResult.csv";
		String greedyResultCSV = "src/data/smallGreedyResult.csv";
		String randomResultCSV = "src/data/smallRandomResult.csv";
		String auctionBasicResultCSV = "src/data/smallAuctionBasicResult.csv";
		String auctionExtendResultCSV = "src/data/smallAuctionExtendResult.csv";
		//overall result
		String overallResutCSV = "src/data/smallOverallResult.csv";
		String overallResutTXT= "src/data/smallOverallResult.txt";
		
		users = DataProcess.GetUsers(userpString);
		stations = DataProcess.GetStations(serverpString);
		
		Random rad = new Random();
		try {
			FileWriter optimalWriter = new FileWriter(optimalResultCSV);
			FileWriter EUAGameWriter = new FileWriter(EUAGameResultCSV);
			FileWriter MEDAGameWriter = new FileWriter(MEDAGameResultCSV);
			FileWriter TPDSGameWriter = new FileWriter(TPDSGameResultCSV);
			FileWriter greedyWriter = new FileWriter(greedyResultCSV);
			FileWriter randomWriter = new FileWriter(randomResultCSV);
			FileWriter auctionBasicWriter = new FileWriter(auctionBasicResultCSV);
			FileWriter auctionExtendWriter = new FileWriter(auctionExtendResultCSV);
			
			FileWriter overallResultCSVWriter = new FileWriter(overallResutCSV);
			FileWriter overallResultTXTWriter = new FileWriter(overallResutTXT);
			
			String[] setStrings = {"server set, 2, 4, 6, 8, 10, 12, 14, 16","user set, 5, 10, 15, 20, 25, 30, 35, 40","capacity set, 1, 2, 3, 4, 5, 6, 7, 8"};
			
			String[] titleStrings ={"nUsedServerRatio", "energyConsumption", "nAllocatedUserRatio", "systemCost", "dataRate", "timeConsumption", "iteration", "fairness"};
			
			String[] optimalResutStrings = {"optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal", "optimal"};
			String[] gameResutStrings = {"Game", "Game", "Game", "Game", "Game", "Game", "Game", "Game"};
			String[] MEDAgameResutStrings = {"MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame", "MEDAGame"};
			String[] TPDSgameResutStrings = {"TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame", "TPDSGame"};
			String[] greedyResultStrings = {"greedy", "greedy", "greedy", "greedy", "greedy", "greedy", "greedy"};
			String[] randomResultStrings = {"random", "random", "random", "random", "random", "random", "random"};
			String[] auctionBasicResultStrings = {"auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic", "auctionBasic"};
			String[] auctionExtendResultStrings = {"auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend", "auctionExtend"};
			
			//Set #1. the change number of edge server
			//optimalWriter.write("Set#1,the change number of edge server\r\n");
			EUAGameWriter.write("Set#1,the change number of edge server\r\n");
			MEDAGameWriter.write("Set#1,the change number of edge server\r\n");
			TPDSGameWriter.write("Set#1,the change number of edge server\r\n");
			greedyWriter.write("Set#1,the change number of edge server\r\n");
			randomWriter.write("Set#1,the change number of edge server\r\n");
			auctionBasicWriter.write("Set#1,the change number of edge server\r\n");
			auctionExtendWriter.write("Set#1,the change number of edge server\r\n");
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				int serversNum = (int) (2 + 2*i);
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {					
					//test 2: generate physical machines based on base stations
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, serversNum, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nLargeCapacity4Set12);
					
					//test 1: generate base stations based on physical machines
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(serversNum, ConstNum.nSmallCapacity4Set12, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					//System.out.println(subUsers.size());
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();

					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				EUAGameWriter.write("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Servers set, " + serversNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Servers set, " + serversNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Servers set, " + serversNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Servers set, " + serversNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Servers set, " + serversNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes +", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Servers set, " + serversNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes+"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Servers set, " + serversNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + auctionExtendTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
				
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[0] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[0] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			// initialize

			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";
			

			//Set #2. the change number of mobile users
			EUAGameWriter.write("Set#2,the change number of mobile user\r\n");
			MEDAGameWriter.write("Set#2,the change number of mobile user\r\n");
			TPDSGameWriter.write("Set#2,the change number of mobile user\r\n");
			greedyWriter.write("Set#2,the change number of mobile user\r\n");
			randomWriter.write("Set#2,the change number of mobile user\r\n");
			auctionBasicWriter.write("Set#2,the change number of mobile user\r\n");
			auctionExtendWriter.write("Set#2,the change number of mobile user\r\n");
			
			// TODO User test
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int userNum = 5 + 5*i;
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//test 2
					ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nSmallServers4Set23, rad);
					ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, ConstNum.nSmallCapacity4Set12);		
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, userNum,suBaseStations, rad);
					
					//ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nLargeServers4Set23, ConstNum.nLargeCapacity4Set12, rad);
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					subUsers.clear();
					suBaseStations.clear();
				}
				
				EUAGameWriter.write("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, User set, " + userNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, User set, " + userNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, User set, " + userNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " + TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
			
				greedyWriter.write("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, User set, " + userNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, User set, " + userNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, User set, " + userNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, User set, " + userNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[1] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[1] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			//initilize

			gameResutStrings[0] = "Game";
			gameResutStrings[1] = "Game";
			gameResutStrings[2] = "Game";
			gameResutStrings[3] = "Game";
			gameResutStrings[4] = "Game";
			gameResutStrings[5] = "Game";
			gameResutStrings[6] = "Game";
			gameResutStrings[7] = "Game";
			MEDAgameResutStrings[0] = "MEDAGame";
			MEDAgameResutStrings[1] = "MEDAGame";
			MEDAgameResutStrings[2] = "MEDAGame";
			MEDAgameResutStrings[3] = "MEDAGame";
			MEDAgameResutStrings[4] = "MEDAGame";
			MEDAgameResutStrings[5] = "MEDAGame";
			MEDAgameResutStrings[6] = "MEDAGame";
			MEDAgameResutStrings[7] = "MEDAGame";
			TPDSgameResutStrings[0] = "TPDSGame";
			TPDSgameResutStrings[1] = "TPDSGame";
			TPDSgameResutStrings[2] = "TPDSGame";
			TPDSgameResutStrings[3] = "TPDSGame";
			TPDSgameResutStrings[4] = "TPDSGame";
			TPDSgameResutStrings[5] = "TPDSGame";
			TPDSgameResutStrings[6] = "TPDSGame";
			TPDSgameResutStrings[7] = "TPDSGame";
			greedyResultStrings[0] = "greedy";
			greedyResultStrings[1] = "greedy";
			greedyResultStrings[2] = "greedy";
			greedyResultStrings[3] = "greedy";
			greedyResultStrings[4] = "greedy";
			greedyResultStrings[5] = "greedy";
			greedyResultStrings[6] = "greedy";
			randomResultStrings[0] = "random";
			randomResultStrings[1] = "random";
			randomResultStrings[2] = "random";
			randomResultStrings[3] = "random";
			randomResultStrings[4] = "random";
			randomResultStrings[5] = "random";
			randomResultStrings[6] = "random";
			auctionBasicResultStrings[0] = "auctionBasic";
			auctionBasicResultStrings[1] = "auctionBasic";
			auctionBasicResultStrings[2] = "auctionBasic";
			auctionBasicResultStrings[3] = "auctionBasic";
			auctionBasicResultStrings[4] = "auctionBasic";
			auctionBasicResultStrings[5] = "auctionBasic";
			auctionBasicResultStrings[6] = "auctionBasic";
			auctionExtendResultStrings[0] = "auctionExtend";
			auctionExtendResultStrings[1] = "auctionExtend";
			auctionExtendResultStrings[2] = "auctionExtend";
			auctionExtendResultStrings[3] = "auctionExtend";
			auctionExtendResultStrings[4] = "auctionExtend";
			auctionExtendResultStrings[5] = "auctionExtend";
			auctionExtendResultStrings[6] = "auctionExtend";	
			
			//Set #3. the change of available capacity
			EUAGameWriter.write("Set#3,the change of available capacity\r\n");
			MEDAGameWriter.write("Set#3,the change of available capacity\r\n");
			greedyWriter.write("Set#3,the change of available capacity\r\n");
			randomWriter.write("Set#3,the change of available capacity\r\n");
			auctionBasicWriter.write("Set#3,the change of available capacity\r\n");
			auctionExtendWriter.write("Set#3,the change of available capacity\r\n");
			
			
			for(int i=0;i<8;i++) {
				
				System.out.println();
				
				int capacityNum = 1+1*i;
				
				double optimalTotalEnergyConsumption = 0;
				double optimalTotalNumAllocatedUsers = 0;
				int optimalTotalDecisionIteration = 0;
				double optimalTotalTime = 0;
				double optimalTotalSystemCost = 0;
				double optimalTotalNumUsedServers = 0;
				double optimalTotalOverallDataRate = 0;
				double optimalTotalOverallFairness = 0;
				
				double EUAGameTotalEnergyConsumption = 0;
				double EUAGameTotalNumAllocatedUsers = 0;
				int EUAGameTotalDecisionIteration = 0;
				double EUAGameTotalTime = 0;
				double EUAGameTotalSystemCost = 0;
				double EUAGameTotalNumUsedServers = 0;
				double EUAGameTotalOverallDataRate = 0;
				double EUAGameTotalOverallFairness = 0;
				
				double MEDAGameTotalEnergyConsumption = 0;
				double MEDAGameTotalNumAllocatedUsers = 0;
				int MEDAGameTotalDecisionIteration = 0;
				double MEDAGameTotalTime = 0;
				double MEDAGameTotalSystemCost = 0;
				double MEDAGameTotalNumUsedServers = 0;
				double MEDAGameTotalOverallDataRate = 0;
				double MEDAGameTotalOverallFairness = 0;
				
				double TPDSGameTotalEnergyConsumption = 0;
				double TPDSGameTotalNumAllocatedUsers = 0;
				int TPDSGameTotalDecisionIteration = 0;
				double TPDSGameTotalTime = 0;
				double TPDSGameTotalSystemCost = 0;
				double TPDSGameTotalNumUsedServers = 0;
				double TPDSGameTotalOverallDataRate = 0;
				double TPDSGameTotalOverallFairness = 0;
				
				double greedyTotalEnergyConsumption = 0;
				double greedyTotalNumAllocatedUsers = 0;
				int greedyTotalDecisionIteration = 0;
				double greedyTotalTime = 0;
				double greedyTotalSystemCost = 0;
				double greedyTotalNumUsedServers = 0;
				double greedyTotalOverallDataRate = 0;
				double greedyTotalOverallFairness = 0;
				
				double randomTotalEnergyConsumption = 0;
				double randomTotalNumAllocatedUsers = 0;
				int randomTotalDecisionIteration = 0;
				double randomTotalTime = 0;
				double randomTotalSystemCost = 0;
				double randomTotalNumUsedServers = 0;
				double randomTotalOverallDataRate = 0;
				double randomTotalOverallFairness = 0;
				
				double auctionBasicTotalEnergyConsumption = 0;
				double auctionBasicTotalNumAllocatedUsers = 0;
				int auctionBasicTotalDecisionIteration = 0;
				double auctionBasicTotalTime = 0;
				double auctionBasicTotalSystemCost = 0;
				double auctionBasicTotalNumUsedServers = 0;
				double auctionBasicTotalOverallDataRate = 0;
				double auctionBasicTotalOverallFairness = 0;
				
				double auctionExtendTotalEnergyConsumption = 0;
				double auctionExtendTotalNumAllocatedUsers = 0;
				int auctionExtendTotalDecisionIteration = 0;
				double auctionExtendTotalTime = 0;
				double auctionExtendTotalSystemCost = 0;
				double auctionExtendTotalNumUsedServers = 0;
				double auctionExtendTotalOverallDataRate = 0;
				double auctionExtendTotalOverallFairness = 0;		
				
				for(int iter=0;iter<ConstNum.nTimes;iter++) {										
					//ArrayList<BaseStation> suBaseStations = DataProcess.getSubStations(stations, ConstNum.nLargeServers4Set23, rad);
					//ArrayList<PhysicalMachine> subServers = DataProcess.getSubMachines(rad, suBaseStations, capacityNum);
						
					ArrayList<PhysicalMachine> subServers = DataProcess.getPhysicalMachiness(ConstNum.nSmallServers4Set23, capacityNum, rad);
					ArrayList<BaseStation> suBaseStations = DataProcess.getSuBaseStations(rad, stations, subServers);
					ArrayList<EdgeUser> subUsers = DataProcess.GetSubUsers(users, ConstNum.nSmallUsers4Set13,suBaseStations, rad);
					DataProcess.GetNeighbourServers(subServers, suBaseStations, subUsers);				
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result EUAGameResult = new Result();
					EUAGameResult = FEUAGame.GetBest(subServers, suBaseStations, subUsers);
					EUAGameTotalEnergyConsumption += EUAGameResult.getEnergyCost();
					EUAGameTotalNumAllocatedUsers += EUAGameResult.getnAlloUsers();
					EUAGameTotalDecisionIteration += EUAGameResult.getIterTimes();
					EUAGameTotalTime += EUAGameResult.getTimeConsumption();
					EUAGameTotalSystemCost += EUAGameResult.getSystemCost();
					EUAGameTotalNumUsedServers += EUAGameResult.getnUsedServers();
					EUAGameTotalOverallDataRate += EUAGameResult.getDataRate();
					EUAGameTotalOverallFairness += EUAGameResult.getfairness();
								
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result MEDAGameResult = new Result();
					MEDAGameResult = MEDAGame.GetBest(subServers, suBaseStations, subUsers);
					MEDAGameTotalEnergyConsumption += MEDAGameResult.getEnergyCost();
					MEDAGameTotalNumAllocatedUsers += MEDAGameResult.getnAlloUsers();
					MEDAGameTotalDecisionIteration += MEDAGameResult.getIterTimes();
					MEDAGameTotalTime += MEDAGameResult.getTimeConsumption();
					MEDAGameTotalSystemCost += MEDAGameResult.getSystemCost();
					MEDAGameTotalNumUsedServers += MEDAGameResult.getnUsedServers();
					MEDAGameTotalOverallDataRate += MEDAGameResult.getDataRate();
					MEDAGameTotalOverallFairness += MEDAGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result TPDSGameResult = new Result();
					TPDSGameResult = TPDSGame.GetBest(subServers, suBaseStations, subUsers);
					TPDSGameTotalEnergyConsumption += TPDSGameResult.getEnergyCost();
					TPDSGameTotalNumAllocatedUsers += TPDSGameResult.getnAlloUsers();
					TPDSGameTotalDecisionIteration += TPDSGameResult.getIterTimes();
					TPDSGameTotalTime += TPDSGameResult.getTimeConsumption();
					TPDSGameTotalSystemCost += TPDSGameResult.getSystemCost();
					TPDSGameTotalNumUsedServers += TPDSGameResult.getnUsedServers();
					TPDSGameTotalOverallDataRate += TPDSGameResult.getDataRate();
					TPDSGameTotalOverallFairness += TPDSGameResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result greedyResult = new Result();
					greedyResult = GreedyMethod.GetBest(subServers, suBaseStations, subUsers);
					greedyTotalEnergyConsumption += greedyResult.getEnergyCost();
					greedyTotalNumAllocatedUsers += greedyResult.getnAlloUsers();
					greedyTotalDecisionIteration += greedyResult.getIterTimes();
					greedyTotalTime += greedyResult.getTimeConsumption();
					greedyTotalSystemCost += greedyResult.getSystemCost();
					greedyTotalNumUsedServers += greedyResult.getnUsedServers();
					greedyTotalOverallDataRate += greedyResult.getDataRate();
					greedyTotalOverallFairness += greedyResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result randomResult = new Result();
					randomResult = RandomMethod.GetBest(subServers, suBaseStations, subUsers);
					randomTotalEnergyConsumption += randomResult.getEnergyCost();
					randomTotalNumAllocatedUsers += randomResult.getnAlloUsers();
					randomTotalDecisionIteration += randomResult.getIterTimes();
					randomTotalTime += randomResult.getTimeConsumption();
					randomTotalSystemCost += randomResult.getSystemCost();
					randomTotalNumUsedServers += randomResult.getnUsedServers();
					randomTotalOverallDataRate += randomResult.getDataRate();
					randomTotalOverallFairness += randomResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionBasicResult = new Result();
					auctionBasicResult = Auction_basicMethod.getBest(subServers, suBaseStations, subUsers);
					auctionBasicTotalEnergyConsumption += auctionBasicResult.getEnergyCost();
					auctionBasicTotalNumAllocatedUsers += auctionBasicResult.getnAlloUsers();
					auctionBasicTotalDecisionIteration += auctionBasicResult.getIterTimes();
					auctionBasicTotalTime += auctionBasicResult.getTimeConsumption();
					auctionBasicTotalSystemCost += auctionBasicResult.getSystemCost();
					auctionBasicTotalNumUsedServers += auctionBasicResult.getnUsedServers();
					auctionBasicTotalOverallDataRate += auctionBasicResult.getDataRate();
					auctionBasicTotalOverallFairness += auctionBasicResult.getfairness();
					
					PhysicalMachine.resetServerSet(subServers);
					BaseStation.resetBaseStations(suBaseStations);
					EdgeUser.resetUserSet(subUsers);
					Result auctionExtendResult = new Result();
					auctionExtendResult = Auction_extendMethod.getBest(subServers, suBaseStations, subUsers);
					auctionExtendTotalEnergyConsumption += auctionExtendResult.getEnergyCost();
					auctionExtendTotalNumAllocatedUsers += auctionExtendResult.getnAlloUsers();
					auctionExtendTotalDecisionIteration += auctionExtendResult.getIterTimes();
					auctionExtendTotalTime += auctionExtendResult.getTimeConsumption();
					auctionExtendTotalSystemCost += auctionExtendResult.getSystemCost();
					auctionExtendTotalNumUsedServers += auctionExtendResult.getnUsedServers();
					auctionExtendTotalOverallDataRate += auctionExtendResult.getDataRate();
					auctionExtendTotalOverallFairness += auctionExtendResult.getfairness();				

					subServers.clear();
					suBaseStations.clear();
					subUsers.clear();
				}		
				
				EUAGameWriter.write("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				EUAGameWriter.flush();
				System.out.println("EUAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + EUAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + EUAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + EUAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + EUAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + EUAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +EUAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + EUAGameTotalOverallFairness/ConstNum.nTimes);
				
				MEDAGameWriter.write("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes +",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				MEDAGameWriter.flush();
				System.out.println("MEDAGame:, Capacity set, " + capacityNum +", Energy Consumption, " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + MEDAGameTotalDecisionIteration/ConstNum.nTimes+",Time," + MEDAGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + MEDAGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + MEDAGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +MEDAGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + MEDAGameTotalOverallFairness/ConstNum.nTimes);
				
				TPDSGameWriter.write("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes +",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes +"\r\n");
				TPDSGameWriter.flush();
				System.out.println("TPDSGame:, Capacity set, " + capacityNum +", Energy Consumption, " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + TPDSGameTotalDecisionIteration/ConstNum.nTimes+",Time," + TPDSGameTotalTime/(ConstNum.nTimes) +", SystemCost, " + TPDSGameTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + TPDSGameTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +TPDSGameTotalOverallDataRate/ConstNum.nTimes +", Fairness, " + TPDSGameTotalOverallFairness/ConstNum.nTimes);
				
				greedyWriter.write("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes +"\r\n");
				greedyWriter.flush();
				System.out.println("Greedy:, Capacity set, " + capacityNum +", Energy Consumption, " + greedyTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + greedyTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + greedyTotalDecisionIteration/ConstNum.nTimes +",Time," + greedyTotalTime/ConstNum.nTimes +", SystemCost, " + greedyTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + greedyTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +greedyTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +greedyTotalOverallFairness/ConstNum.nTimes);
				
				randomWriter.write("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes +"\r\n");
				randomWriter.flush();
				System.out.println("Random:, Capacity set, " + capacityNum +", Energy Consumption, " + randomTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + randomTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + randomTotalDecisionIteration/ConstNum.nTimes +",Time," + randomTotalTime/ConstNum.nTimes +", SystemCost, " + randomTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + randomTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +randomTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +randomTotalOverallFairness/ConstNum.nTimes);
				
				auctionBasicWriter.write("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionBasicWriter.flush();
				System.out.println("Auction_basic:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionBasicTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionBasicTotalTime/ConstNum.nTimes +", SystemCost, " + auctionBasicTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionBasicTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionBasicTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionBasicTotalOverallFairness/ConstNum.nTimes);
				
				auctionExtendWriter.write("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes +"\r\n");
				auctionExtendWriter.flush();
				System.out.println("Auction_extend:, Capacity set, " + capacityNum +", Energy Consumption, " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes +", User_allo_ratio, " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes+",DecisionIter," + auctionExtendTotalDecisionIteration/ConstNum.nTimes +",Time," + auctionExtendTotalTime/ConstNum.nTimes +", SystemCost, " + auctionExtendTotalSystemCost/ConstNum.nTimes +", Server_ratio, " + auctionExtendTotalNumUsedServers/ConstNum.nTimes +", OverallDataRate, " +auctionExtendTotalOverallDataRate/ConstNum.nTimes + ", Fairness, " +auctionExtendTotalOverallFairness/ConstNum.nTimes);
				
				//overall result
				gameResutStrings[0] += ", " + EUAGameTotalNumUsedServers/ConstNum.nTimes;
				gameResutStrings[1] += ", " + EUAGameTotalEnergyConsumption/ConstNum.nTimes;
				gameResutStrings[2] += ", " + EUAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				gameResutStrings[3] += ", " + EUAGameTotalSystemCost/ConstNum.nTimes;
				gameResutStrings[4] += ", " + EUAGameTotalOverallDataRate/ConstNum.nTimes;
				gameResutStrings[5] += ", " + EUAGameTotalTime/ConstNum.nTimes;
				gameResutStrings[6] += ", " + EUAGameTotalDecisionIteration/ConstNum.nTimes;
				gameResutStrings[7] += ", " + EUAGameTotalOverallFairness/ConstNum.nTimes;
				
				MEDAgameResutStrings[0] += ", " + MEDAGameTotalNumUsedServers/ConstNum.nTimes;
				MEDAgameResutStrings[1] += ", " + MEDAGameTotalEnergyConsumption/ConstNum.nTimes;
				MEDAgameResutStrings[2] += ", " + MEDAGameTotalNumAllocatedUsers/ConstNum.nTimes;
				MEDAgameResutStrings[3] += ", " + MEDAGameTotalSystemCost/ConstNum.nTimes;
				MEDAgameResutStrings[4] += ", " + MEDAGameTotalOverallDataRate/ConstNum.nTimes;
				MEDAgameResutStrings[5] += ", " + MEDAGameTotalTime/ConstNum.nTimes;
				MEDAgameResutStrings[6] += ", " + MEDAGameTotalDecisionIteration/ConstNum.nTimes;
				MEDAgameResutStrings[7] += ", " + MEDAGameTotalOverallFairness/ConstNum.nTimes;
				
				TPDSgameResutStrings[0] += ", " + TPDSGameTotalNumUsedServers/ConstNum.nTimes;
				TPDSgameResutStrings[1] += ", " + TPDSGameTotalEnergyConsumption/ConstNum.nTimes;
				TPDSgameResutStrings[2] += ", " + TPDSGameTotalNumAllocatedUsers/ConstNum.nTimes;
				TPDSgameResutStrings[3] += ", " + TPDSGameTotalSystemCost/ConstNum.nTimes;
				TPDSgameResutStrings[4] += ", " + TPDSGameTotalOverallDataRate/ConstNum.nTimes;
				TPDSgameResutStrings[5] += ", " + TPDSGameTotalTime/ConstNum.nTimes;
				TPDSgameResutStrings[6] += ", " + TPDSGameTotalDecisionIteration/ConstNum.nTimes;
				TPDSgameResutStrings[7] += ", " + TPDSGameTotalOverallFairness/ConstNum.nTimes;
				
				greedyResultStrings[0] += ", " + greedyTotalNumUsedServers/ConstNum.nTimes;
				greedyResultStrings[1] += ", " + greedyTotalEnergyConsumption/ConstNum.nTimes;
				greedyResultStrings[2] += ", " + greedyTotalNumAllocatedUsers/ConstNum.nTimes;
				greedyResultStrings[3] += ", " + greedyTotalSystemCost/ConstNum.nTimes;
				greedyResultStrings[4] += ", " + greedyTotalOverallDataRate/ConstNum.nTimes;
				greedyResultStrings[5] += ", " + greedyTotalTime/ConstNum.nTimes;
				greedyResultStrings[6] += ", " + greedyTotalOverallFairness/ConstNum.nTimes;
				
				randomResultStrings[0] += ", " + randomTotalNumUsedServers/ConstNum.nTimes;
				randomResultStrings[1] += ", " + randomTotalEnergyConsumption/ConstNum.nTimes;
				randomResultStrings[2] += ", " + randomTotalNumAllocatedUsers/ConstNum.nTimes;
				randomResultStrings[3] += ", " + randomTotalSystemCost/ConstNum.nTimes;
				randomResultStrings[4] += ", " + randomTotalOverallDataRate/ConstNum.nTimes;
				randomResultStrings[5] += ", " + randomTotalTime/ConstNum.nTimes;
				randomResultStrings[6] += ", " + randomTotalOverallFairness/ConstNum.nTimes;
				
				auctionBasicResultStrings[0] += ", " + auctionBasicTotalNumUsedServers/ConstNum.nTimes;
				auctionBasicResultStrings[1] += ", " + auctionBasicTotalEnergyConsumption/ConstNum.nTimes;
				auctionBasicResultStrings[2] += ", " + auctionBasicTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionBasicResultStrings[3] += ", " + auctionBasicTotalSystemCost/ConstNum.nTimes;
				auctionBasicResultStrings[4] += ", " + auctionBasicTotalOverallDataRate/ConstNum.nTimes;
				auctionBasicResultStrings[5] += ", " + auctionBasicTotalTime/ConstNum.nTimes;
				auctionBasicResultStrings[6] += ", " + auctionBasicTotalOverallFairness/ConstNum.nTimes;
				
				auctionExtendResultStrings[0] += ", " + auctionExtendTotalNumUsedServers/ConstNum.nTimes;
				auctionExtendResultStrings[1] += ", " + auctionExtendTotalEnergyConsumption/ConstNum.nTimes;
				auctionExtendResultStrings[2] += ", " + auctionExtendTotalNumAllocatedUsers/ConstNum.nTimes;
				auctionExtendResultStrings[3] += ", " + auctionExtendTotalSystemCost/ConstNum.nTimes;
				auctionExtendResultStrings[4] += ", " + auctionExtendTotalOverallDataRate/ConstNum.nTimes;
				auctionExtendResultStrings[5] += ", " + auctionExtendTotalTime/ConstNum.nTimes;
				auctionExtendResultStrings[6] += ", " + auctionExtendTotalOverallFairness/ConstNum.nTimes;
			}
			

			// overall result save
			//number of used edge server
			overallResultCSVWriter.write(titleStrings[0] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[0] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[0] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[0] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[0] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[0] +"\r\n");
			
			// energy consumption
			overallResultCSVWriter.write(titleStrings[1] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[1] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[1] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[1] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[1] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[1] +"\r\n");
			
			//number of allocated user
			overallResultCSVWriter.write(titleStrings[2] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[2] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[2] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[2] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[2] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[2] +"\r\n");
			
			//overall system cost
			overallResultCSVWriter.write(titleStrings[3] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[3] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[3] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[3] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[3] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[3] +"\r\n");
			
			//overall data rate
			overallResultCSVWriter.write(titleStrings[4] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[4] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[4] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[4] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[4] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[4] +"\r\n");
			
			//time consumption
			overallResultCSVWriter.write(titleStrings[5] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[5] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[5] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[5] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[5] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[5] +"\r\n");
			
			//iteration
			overallResultCSVWriter.write(titleStrings[6] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[6] +"\r\n");
					
			overallResultTXTWriter.write(titleStrings[6] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[6] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[6] +"\r\n");
			
			//fairness
			overallResultCSVWriter.write(titleStrings[7] +"\r\n");
			overallResultCSVWriter.write(setStrings[2] + "\r\n");
			overallResultCSVWriter.write(gameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultCSVWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultCSVWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultCSVWriter.write(randomResultStrings[6] +"\r\n");
			
			overallResultTXTWriter.write(titleStrings[7] +"\r\n");
			overallResultTXTWriter.write(setStrings[2] + "\r\n");
			overallResultTXTWriter.write(gameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(MEDAgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(TPDSgameResutStrings[7] +"\r\n");
			overallResultTXTWriter.write(auctionBasicResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(auctionExtendResultStrings[6] + "\r\n");
			overallResultTXTWriter.write(greedyResultStrings[6] +"\r\n");
			overallResultTXTWriter.write(randomResultStrings[6] +"\r\n");
			
			EUAGameWriter.close();
			MEDAGameWriter.close();
			TPDSGameWriter.close();
			greedyWriter.close();
			randomWriter.close();
			auctionBasicWriter.close();
			auctionExtendWriter.close();
			
			overallResultCSVWriter.close();
			overallResultTXTWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}


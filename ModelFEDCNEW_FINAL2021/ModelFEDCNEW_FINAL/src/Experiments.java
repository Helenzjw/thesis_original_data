import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.EADModel;
import models.GreedyConnectionModel;
import models.GreedyCoveredUsersModel;
//import models.NearOptimalModel;
import models.HGAModel;
import models.HModel;
import models.RandomModel;
import models.LiuICWSModel;
import models.NearOptimalModel;

import models.NearFCModel;//return fairness
import models.NearFairCostRatioModel; //returen fairness cost
import models.NearBenefitCostRatioModel; //return benefit cost
import models.NearBenefitModel; //return benefit

import objectives.EdgeServer;
import objectives.User;
import tool.RandomGraphGenerator;
import tool.RandomServersGenerator;
import tool.RandomUserListGenerator;

public class Experiments {

	private static EADModel mEADModel;
	private static RandomModel mRandomModel;
	private static GreedyCoveredUsersModel mGreedyCoveredUsersModel;
	private static GreedyConnectionModel mGreedyConnectionModel;
	private static LiuICWSModel mLiuICWSModel;
	private static NearOptimalModel mNearOptimalModel;
	private static HGAModel mHGAModel;
	private static HModel mHModel;
	
	private static NearFCModel mNearFCModel;
	private static NearFairCostRatioModel mNearFairCostRatioModel ;
	private static NearBenefitModel mNearBenefitModel ;
	private static NearBenefitCostRatioModel mNearBenefitCostRatioModel;

	private static List<Double> mEADUserList = new ArrayList<>();
	private static List<Double> mEADCostList = new ArrayList<>();
	private static List<Double> mEADfairnessdegreeList = new ArrayList<>();
	private static List<Double> mEADfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mEADallbenefitList = new ArrayList<>();
	private static List<Double> mEADbenefitefficiencyList = new ArrayList<>();	
	private static List<Double> mEADExecutionTimeList = new ArrayList<>();

	private static List<Double> mRandomUserList = new ArrayList<>();
	private static List<Double> mRandomCostList = new ArrayList<>();
	private static List<Double> mRandomfairnessdegreeList = new ArrayList<>();
	private static List<Double> mRandomfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mRandomlbenefitList = new ArrayList<>();
	private static List<Double> mRandombenefitefficiencyList = new ArrayList<>();
	private static List<Double> mRandomExecutionTimeList = new ArrayList<>();

	private static List<Double> mGreedyCoveredUsersUserList = new ArrayList<>();
	private static List<Double> mGreedyCoveredCostList = new ArrayList<>();
	private static List<Double> mGreedyCoveredfairnessdegreeList = new ArrayList<>();
	private static List<Double> mGreedyCoveredfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mGreedyCoveredallbenefitList = new ArrayList<>();
	private static List<Double> mGreedybenefitefficiencyList = new ArrayList<>();
	private static List<Double> mGreedyCoveredUsersExecutionTimeList = new ArrayList<>();

	private static List<Double> mGreedyConnectionUserList = new ArrayList<>();
	private static List<Double> mGreedyConnectionCostList = new ArrayList<>();
	private static List<Double> mGreedyConnectionfairnessdegreeList = new ArrayList<>();
	private static List<Double> mGreedyConnectionfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mGreedyConnectionallbenefitList = new ArrayList<>();
	private static List<Double> mGreedyConnectionbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mGreedyConnectionExecutionTimeList = new ArrayList<>();

	private static List<Double> mHGAUserList = new ArrayList<>();
	private static List<Double> mHGACostList = new ArrayList<>();
	private static List<Double> mHGAfairnessdegreeList = new ArrayList<>();
	private static List<Double> mHGAfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mHGAallbenefitList = new ArrayList<>();
	private static List<Double> mHGAbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mHGAExecutionTimeList = new ArrayList<>();

	private static List<Double> mLiuICWSUserList = new ArrayList<>();
	private static List<Double> mLiuICWSCostList = new ArrayList<>();
	private static List<Double> mLiuICWSfairnessdegreeList = new ArrayList<>();
	private static List<Double> mLiuICWSfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mLiuICWSallbenefitList = new ArrayList<>();
	private static List<Double> mLiuICWSbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mLiuICWSExecutionTimeList = new ArrayList<>();

	private static List<Double> mNearOptimalUserList = new ArrayList<>();
	private static List<Double> mNearOptimalCostList = new ArrayList<>();
	private static List<Double> mNearOptimalfairnessdegreeList = new ArrayList<>();
	private static List<Double> mNearOptimalfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mNearOptimalallbenefitList = new ArrayList<>();
	private static List<Double> mNearOptimalbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mNearOptimalExecutionTimeList = new ArrayList<>();
	
	private static List<Double> mHMUserList = new ArrayList<>();
	private static List<Double> mHMCostList = new ArrayList<>();
	private static List<Double> mHMfairnessdegreeList = new ArrayList<>();
	private static List<Double> mHMfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mHMallbenefitList = new ArrayList<>();
	private static List<Double> mHMbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mHMExecutionTimeList = new ArrayList<>();
	
	private static List<Double> mNearFCUserList = new ArrayList<>();
	private static List<Double> mNearFCCostList = new ArrayList<>();
	private static List<Double> mNearFCfairnessdegreeList = new ArrayList<>();
	private static List<Double> mNearFCfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mNearFCallbenefitList = new ArrayList<>();
	private static List<Double> mNearFCbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mNearFCExecutionTimeList = new ArrayList<>();
	
	private static List<Double> mNearFairCostRatioUserList = new ArrayList<>();
	private static List<Double> mNearFairCostRatioCostList = new ArrayList<>();
	private static List<Double> mNearFairCostRatiofairnessdegreeList = new ArrayList<>();
	private static List<Double> mNearFairCostRatiofairnessefficiencyList = new ArrayList<>();
	private static List<Double> mNearFairCostRatioallbenefitList = new ArrayList<>();
	private static List<Double> mNearFairCostRatiobenefitefficiencyList = new ArrayList<>();
	private static List<Double> mNearFairCostRatioExecutionTimeList = new ArrayList<>();

	private static List<Double> mNearBenefitCostRatioUserList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatioCostList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatiofairnessdegreeList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatiofairnessefficiencyList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatioallbenefitList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatiobenefitefficiencyList = new ArrayList<>();
	private static List<Double> mNearBenefitCostRatioExecutionTimeList = new ArrayList<>();

	private static List<Double> mNearBenefitUserList = new ArrayList<>();
	private static List<Double> mNearBenefitCostList = new ArrayList<>();
	private static List<Double> mNearBenefitfairnessdegreeList = new ArrayList<>();
	private static List<Double> mNearBenefitfairnessefficiencyList = new ArrayList<>();
	private static List<Double> mNearBenefitallbenefitList = new ArrayList<>();
	private static List<Double> mNearBenefitbenefitefficiencyList = new ArrayList<>();
	private static List<Double> mNearBenefitExecutionTimeList = new ArrayList<>();
	private static List<String> mLines = new ArrayList<>();

	public static void main(String[] args) {

		//runSyntheticExperimentEdgeServerNumber();
		//runSyntheticExperimentDensity();
		//runSyntheticExperimentfairnessNumber();
		
		//runRealWorldExperimentEdgeServerNumber();
		runRealWorldExperimentDensity();
		//runRealWorldExperimentfairnessNumber();
		//runRealWorldExperimentUserNumber();
	//	runRealWorldExperimentConnectionNumber();
		
		// runSyntheticExperimentBudgetNumber();
		// runEAD();

		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");

			Path file = Paths.get("Synthetic - results-" + sdf.format(cal.getTime()) + ".txt");
			// Path file = Paths.get("Real world data - results-" +
			// sdf.format(cal.getTime()) + ".txt");

			Files.write(file, mLines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//ÓÃ»§µÄ
	private static boolean isConnected(List<Integer> servers, int server, int[][] adjacencyMatrix) {
		for (int s : servers) {
			if (adjacencyMatrix[s][server] == 1)
				return true;
		}
		return false;
	}
	//2 hops
	private static boolean istwohopsConnected(List<Integer> servers, int server, int[][] distanceMatrix) {
		for (int s : servers) {
			if (distanceMatrix[s][server] == 2 || distanceMatrix[server][s] == 2)
				return true;
		}

		return false;
	}
	//3 hops
	private static boolean isthreehopsConnected(List<Integer> servers, int server, int[][] distanceMatrix) {
		for (int s : servers) {
			if (distanceMatrix[s][server] == 3 || distanceMatrix[server][s] == 3)
				return true;
		}
		return false;
	}
	
	private static void setupSyntheticExperiments(int serversNumber, double fairness_index, int totalUsersNumber,
			double density) {
		RandomGraphGenerator graphGenerator = new RandomGraphGenerator(serversNumber, density);
		graphGenerator.createRandomGraph();
		int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
		int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();

		RandomServersGenerator serversGenerator = new RandomServersGenerator();
		RandomUserListGenerator userGenerator = new RandomUserListGenerator();
		int networkArea = serversNumber * 2;

		List<EdgeServer> servers = serversGenerator.generateEdgeServers(serversNumber, networkArea);
		List<User> users = userGenerator.generateUsers(serversNumber, totalUsersNumber, networkArea, servers);

		int[][] userCovered = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i)) {
					userCovered[i][j] = 1;
				} else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix)) {
					userCovered[i][j] = 1;
				} else {
					userCovered[i][j] = 0;
				}
			}
		}

		int[][] userbenefitICWS = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i)) {
					userbenefitICWS[i][j] = 1;
					// change 1 to 0 and use this in the liuICWSmodel to calculate the benefit_2021
				} else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix)) {
					userbenefitICWS[i][j] = 0;
				} else {
					userbenefitICWS[i][j] = 0;
				}
			}
		}

		int[][] userBenefits = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i))
					userBenefits[i][j] = 2;
				else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
					userBenefits[i][j] = 1;
				else
					userBenefits[i][j] = 0;
			}
		} 
		
		/*int[][] userBenefits = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i))
					userBenefits[i][j] = 3;
				else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
					userBenefits[i][j] = 2;
				else if(isConnected(users.get(j).getnearedgeserverlistintwohops(users.get(j).nearEdgeServers, i, adjacencyMatrix), 
						i, adjacencyMatrix)  
						)//µ÷ÓÃÒ»¸öÐÂµÄº¯Êý£¬°Ñuser jµÄÁÚ½Óserver list ´«µÝ£¬·µ»ØÓëÕâ¸öserver listÀïÈÎÒâÒ»¸öserverÓÐÁ¬½ÓµÄserver list£» È»ºóÔÙµ÷ÓÃboolean
					userBenefits[i][j] = 1;
				else
					userBenefits[i][j] = 0;
			}
		}*/

		for (EdgeServer server1 : servers) {
			server1.usersInOneHop.addAll(server1.servedUsers);
			for (EdgeServer server2 : servers) {
				if (adjacencyMatrix[server1.id][server2.id] == 1 || adjacencyMatrix[server2.id][server1.id] == 1) {
					for (int user : server2.servedUsers) {
						if (!server1.usersInOneHop.contains(user))
							server1.usersInOneHop.add(user);
					}
				}
			}
		}
		mEADModel = new EADModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
		mRandomModel = new RandomModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits,
				users);
		mGreedyCoveredUsersModel = new GreedyCoveredUsersModel(serversNumber, fairness_index, adjacencyMatrix,
				userCovered, userBenefits, users, servers);
		mGreedyConnectionModel = new GreedyConnectionModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users);
		mNearOptimalModel = new NearOptimalModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
		mLiuICWSModel = new LiuICWSModel(serversNumber, fairness_index, adjacencyMatrix, userbenefitICWS, userBenefits,
				users, servers);
		mHGAModel = new HGAModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
		mHModel = new HModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
	}
	
	private static void setupRealWorldDataExperiments(int serversNumber, double fairness_index, int totalUsersNumber, 
			double density, int connection, List<EdgeServer> servers_ten, List<User> users_100, int[][] adjacencyMatrix, int[][] distanceMatrics) {
//		//生成一个n个server 的网络，然后再选择用户；
//	//	RandomGraphGenerator graphGenerator = new RandomGraphGenerator(serversNumber, density);
//		RandomGraphGenerator graphGenerator = new RandomGraphGenerator(30, density);
//		
//		//基于上面的矩阵，create一个连接density为density的图
//		graphGenerator.createRandomGraph();
//		
//		//接收random图的adjacency矩阵值
//		int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
//		
//		//得到random图的distance矩阵值
//		int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();
//
		RandomServersGenerator serversGenerator = new RandomServersGenerator();
	
	//	RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
//0820_2021
		//从全部server中选取n个server
		List<EdgeServer> servers;
		servers = servers_ten;


//		List<EdgeServer> servers = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
		//新建两个server list server_ten server_morethanten, 传过来的servers_ten是10个server，
		//如果只需要十个server那就server = servers_ten,如果需要更多： 那就random生成n-10个server 加上前面10个
		
		
		
//		if(serversNumber <= 10) {
//			servers = servers_ten;
//			//users_original = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers);
//		} else {	
//			List<EdgeServer> server_morethanten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber-10);
//            //servers = new ArrayList<>(servers_ten);
//			servers = servers_ten;
//			servers.addAll(server_morethanten);
//			//users_original = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers);
//		}
		
		
		
	//	RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
	//	List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers);
		//test print
		//	System.out.println(servers);
		//	System.out.println(users);
		//根据 生成的server list  传到user list generator里生成一个 
		List<User> users = users_100;

	//	List<User> users = users_original;
		int[][] userCovered = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i)) {
					userCovered[i][j] = 1;
				} else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix)) {
					userCovered[i][j] = 1;
				} else {
					userCovered[i][j] = 0;
				}
			}
		}
		
//		//test print
//		for (int i = 0; i < serversNumber; i++) {
//			for (int j = 0; j < users.size(); j++) {
//				System.out.println(userCovered[i][j]);
//			}
//		}

//		int[][] userbenefitICWS = new int[serversNumber][users.size()];
//
//		for (int i = 0; i < serversNumber; i++) {
//			for (int j = 0; j < users.size(); j++) {
//				if (users.get(j).nearEdgeServers.contains(i)) {
//					userbenefitICWS[i][j] = 1;
//					// change 1 to 0 and use this in the liuICWSmodel to calculate the benefit_2021
//				} else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix)) {
//					userbenefitICWS[i][j] = 0;
//				} else {
//					userbenefitICWS[i][j] = 0;
//				}
//			}
//		}
		
		int[][] userbenefitICWS = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i)) {
					userbenefitICWS[i][j] = 1;
					// change 1 to 0 and use this in the liuICWSmodel to calculate the benefit_2021
				} else {
					userbenefitICWS[i][j] = 0;
				}
			}
		}
		
		
		int[][] userBenefits = new int[serversNumber][users.size()];
		
		if (connection == 1) {
			//int[][] userBenefits = new int[serversNumber][users.size()];

			for (int i = 0; i < serversNumber; i++) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).nearEdgeServers.contains(i))
						userBenefits[i][j] = 2;
					else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
						userBenefits[i][j] = 1;
					else
						userBenefits[i][j] = 0;
				}
			}		
		} else if (connection == 2) {
			//int[][] userBenefits = new int[serversNumber][users.size()];

			for (int i = 0; i < serversNumber; i++) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).nearEdgeServers.contains(i))
						userBenefits[i][j] = 3;
					else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
						userBenefits[i][j] = 2;
					else if(istwohopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
							)
						userBenefits[i][j] = 1;
					else
						userBenefits[i][j] = 0;
				}
			} 		
		} else if(connection == 3) {
		//	int[][] userBenefits = new int[serversNumber][users.size()];

			for (int i = 0; i < serversNumber; i++) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).nearEdgeServers.contains(i))
						userBenefits[i][j] = 4;
					else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
						userBenefits[i][j] = 3;
					else if(istwohopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
							)
						userBenefits[i][j] = 2;
					else if(isthreehopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
							)
						userBenefits[i][j] = 1;
					else
						userBenefits[i][j] = 0;
				}
			} 
		}
	/*	int[][] userBenefits = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i))
					userBenefits[i][j] = 2;
				else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
					userBenefits[i][j] = 1;
				else
					userBenefits[i][j] = 0;
			}
		} */
		
	/*	int[][] userBenefits = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i))
					userBenefits[i][j] = 3;
				else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
					userBenefits[i][j] = 2;
				else if(istwohopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
						)
					userBenefits[i][j] = 1;
				else
					userBenefits[i][j] = 0;
			}
		}  */
		
	/*	int[][] userBenefits = new int[serversNumber][users.size()];

		for (int i = 0; i < serversNumber; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).nearEdgeServers.contains(i))
					userBenefits[i][j] = 4;
				else if (isConnected(users.get(j).nearEdgeServers, i, adjacencyMatrix))
					userBenefits[i][j] = 3;
				else if(istwohopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
						)
					userBenefits[i][j] = 2;
				else if(isthreehopsConnected(users.get(j).nearEdgeServers, i, distanceMatrics)  
						)
					userBenefits[i][j] = 1;
				else
					userBenefits[i][j] = 0;
			}
		} */


		for (EdgeServer server1 : servers) {
			server1.usersInOneHop.addAll(server1.servedUsers);
			for (EdgeServer server2 : servers) {
				if (adjacencyMatrix[server1.id][server2.id] == 1 || adjacencyMatrix[server2.id][server1.id] == 1) {
					for (int user : server2.servedUsers) {
						if (!server1.usersInOneHop.contains(user))
							server1.usersInOneHop.add(user);
					}
				}
			}
		}
		
		mEADModel = new EADModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
		mRandomModel = new RandomModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits,
				users);
		mGreedyCoveredUsersModel = new GreedyCoveredUsersModel(serversNumber, fairness_index, adjacencyMatrix,
				userCovered, userBenefits, users, servers);
		mGreedyConnectionModel = new GreedyConnectionModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users);
		mNearOptimalModel = new NearOptimalModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
		mLiuICWSModel = new LiuICWSModel(serversNumber, fairness_index, adjacencyMatrix, userbenefitICWS, userBenefits,
				users, servers);
		mHGAModel = new HGAModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
		mHModel = new HModel(serversNumber, fairness_index, adjacencyMatrix, userCovered, userBenefits, users,
				servers);
		//only fairness-cost ratio optimal
		mNearFCModel = new NearFCModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
		
		mNearFairCostRatioModel = new NearFairCostRatioModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
		mNearBenefitCostRatioModel = new NearBenefitCostRatioModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
		mNearBenefitModel = new NearBenefitModel(serversNumber, fairness_index, adjacencyMatrix, userCovered,
				userBenefits, users, servers);
	}
	

	private static void runSyntheticExperimentEdgeServerNumber() {
		// in this set, random userNumber, same density, same budget, different edge
		// server number
		// server

		int serversNumber = 10;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 100;
		double density = 1;

		for (int i = 0; i < 5; i++) {
			clearLists();

			for (int j = 0; j < 80; j++) {
				setupSyntheticExperiments(serversNumber, fairness_index, totalUsersNumber, density);
				runEAD();
				runRandom();
				runGreedyCoveredUsers();
				runGreedyConnection();
				runLiuICWS();
				runNearOptimal();				

				//int budget = runNearOptimal();

				//mHGAModel.setBudget(budget);

				//runHGA();

				// printResults("ss");
			}
			printResults("Set 1 S---" + serversNumber + " Edge Servers---" + fairness_index + " fairness index---"
					+ totalUsersNumber + " Users");

			serversNumber = serversNumber + 5;
		}
	}

	private static void runSyntheticExperimentfairnessNumber() {
		// in this set, random userNumber, same density, same edge server number,
		// different budget,
		// budget

		int serversNumber = 30;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.80;
		int totalUsersNumber = 300;
		double density = 1;

		for (int i = 0; i < 4; i++) {
			clearLists();

			for (int j = 0; j < 80; j++) {
				setupSyntheticExperiments(serversNumber, fairness_index, totalUsersNumber, density);
				runEAD();
				runRandom();
				runGreedyCoveredUsers();
				runGreedyConnection();
				runLiuICWS();
				//runHGA();
				runNearOptimal();
			}
			printResults("Set 2 S---" + serversNumber + " Edge Servers---" + fairness_index + " Budget---" + totalUsersNumber
					+ "  Users");

			fairness_index = fairness_index + 0.05;
		}
	}

	private static void runSyntheticExperimentDensity() {
		// in this set, random userNumber, same edge server number, same budget,
		// different density
		// density

		int serversNumber = 300;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 1000;
		double density = 1;

		for (int i = 0; i < 5; i++) {
			clearLists();

			for (int j = 0; j < 80; j++) {
				setupSyntheticExperiments(serversNumber, fairness_index, totalUsersNumber, density);
				//runEAD();
				runRandom();
				runGreedyCoveredUsers();
				runGreedyConnection();
				runLiuICWS();
				runNearOptimal();
				//runHGA();
			}

			printResults("Set 3 S---" + "Edge Density = " + density + "---" + serversNumber + " Edge Servers---" + fairness_index
					+ " Budget---" + totalUsersNumber + " Users");
			density = density + 0.5;
		}
	}

	private static void runRealWorldExperimentEdgeServerNumber() {
		// in this set, random userNumber, same density, same budget, different edge
		// server number
		// server

	
		
		int serversNumber = 10;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 200;
		double density = 1;
		int connection = 1;

		//生成一个n个server 的网络，然后再选择用户；
		//	RandomGraphGenerator graphGenerator = new RandomGraphGenerator(serversNumber, density);
			RandomGraphGenerator graphGenerator = new RandomGraphGenerator(30, density);
			
			//基于上面的矩阵，create一个连接density为density的图
			graphGenerator.createRandomGraph();
			
			//接收random图的adjacency矩阵值
			int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
			
			//得到random图的distance矩阵值
			int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();
//		RandomServersGenerator serversGenerator = new RandomServersGenerator();
//		//这里生成10个server，和80个用户
//		List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
//		RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
//
//	    List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);
//
//		
		for (int i = 0; i < 5; i++) {
			clearLists();

			for (int j = 0; j < 10; j++) {
				RandomServersGenerator serversGenerator = new RandomServersGenerator();
				//这里生成10个server，和80个用户
				List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
				RandomUserListGenerator userListGenerator = new RandomUserListGenerator();

			    List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);

				//这里把生成的10个server和对应的80个用户传到函数里，如果只有10个server需要生成，那就不会再有新的server， 如果需要多于10个server，那就在setup函数里面随机选择多的server，用户保持不变
				setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection, servers_ten, users, adjacencyMatrix, distanceMatrics);
				//runEAD();
				runRandom();
				//runGreedyCoveredUsers();
				//runGreedyConnection();
				//runLiuICWS();
				runNearOptimal();
				runNearFC();
				//runNearFairCostRatio();
				runNearBenefitCostRatio();
				//runNearBenefit();
				runHGA();
				

				//int budget = runNearOptimal();

//	         	mHGAModel.setBudget(budget);

     			//runHGA();
				// printResults("ss");
			}
			printResults("Set 1 R---" + serversNumber + " Edge Servers---" + fairness_index + " fairness index---"
					+ totalUsersNumber + " Users");

			serversNumber = serversNumber + 5;
		}
	}

//	private static void runRealWorldExperimentfairnessNumber() {
//		// in this set, random userNumber, same density, same edge server number,
//		// different budget,
//		// budget
//
//		int serversNumber = 30;
//		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
//		double fairness_index = 0.80;
//		int totalUsersNumber = 100;
//		double density = 1;
//		int connection = 1;
//		//这里生成10个server，和80个用户
//		RandomServersGenerator serversGenerator = new RandomServersGenerator();
//		RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
//		List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
//		List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);
//
//		for (int i = 0; i < 4; i++) {
//			clearLists();
//
//			for (int j = 0; j < 80; j++) {
//				//setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection);
//				setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection, servers_ten, users);
//				runEAD();
//				runRandom();
//				runGreedyCoveredUsers();
//				runGreedyConnection();
//				runLiuICWS();
//				//runHGA();
//				runNearOptimal();
//			}
//			printResults("Set 2 R---" + serversNumber + " Edge Servers---" + fairness_index + " Budget---" + totalUsersNumber
//					+ "  Users");
//
//			fairness_index = fairness_index + 0.05;
//		}
//	}
//
	private static void runRealWorldExperimentDensity() {
		// in this set, random userNumber, same edge server number, same budget,
		// different density
		// density

		int serversNumber = 30;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 100;
		double density = 0.2;
		int connection = 1;
		
		//这里生成10个server，和80个用户
		RandomServersGenerator serversGenerator = new RandomServersGenerator();
		RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
		List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(30);
		List<User> users = userListGenerator.generateUserListFromRealWorldData(30, totalUsersNumber, servers_ten);

		for (int i = 0; i < 5; i++) {
			clearLists();

			for (int j = 0; j < 8; j++) {
			    //生成一个n个server 的网络，然后再选择用户；
			    //RandomGraphGenerator graphGenerator = new RandomGraphGenerator(serversNumber, density);
				RandomGraphGenerator graphGenerator = new RandomGraphGenerator(30, density);
				
				//基于上面的矩阵，create一个连接density为density的图
				graphGenerator.createRandomGraph();
				
				//接收random图的adjacency矩阵值
				int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
				
				//得到random图的distance矩阵值
				int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();
				//setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection);
				setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection, servers_ten, users, adjacencyMatrix, distanceMatrics);
				//runEAD();
				//runRandom();
				runGreedyCoveredUsers();
				//runGreedyConnection();
				//runLiuICWS();
				runNearOptimal();
				//runNearFC();
				//runNearFairCostRatio();
				runNearBenefitCostRatio();
				//runNearBenefit();
				//runHGA();
			}

			printResults("Set 3 R---" + "Edge Density = " + density + "---" + serversNumber + " Edge Servers---" + fairness_index
					+ " Budget---" + totalUsersNumber + " Users");
			density = density + 0.2;
		}
	}	
//	
	private static void runRealWorldExperimentUserNumber() {
		// in this set, random userNumber, same edge server number, same budget,
		// different density
		// density

		int serversNumber = 30;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 100;
		double density = 1;
		int connection = 1;
		
		RandomGraphGenerator graphGenerator = new RandomGraphGenerator(30, density);
		
		//基于上面的矩阵，create一个连接density为density的图
		graphGenerator.createRandomGraph();
		
		//接收random图的adjacency矩阵值
		int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
		
		//得到random图的distance矩阵值
		int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();
		//这里生成10个server，和80个用户
		
		//List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);
	
		for (int i = 0; i < 5; i++) {
			clearLists();

			for (int j = 0; j < 10; j++) {
				RandomServersGenerator serversGenerator = new RandomServersGenerator();
				RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
				List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
				List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);
				 //生成一个n个server 的网络，然后再选择用户；
			    //RandomGraphGenerator graphGenerator = new RandomGraphGenerator(serversNumber, density);
				//setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection);
				setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection, servers_ten, users, adjacencyMatrix, distanceMatrics);
				//runEAD();
				runRandom();
				//runGreedyCoveredUsers();
				//runGreedyConnection();
				//runLiuICWS();
				runNearOptimal();
				runHGA();
				runNearFC();
				//runNearFairCostRatio();
				runNearBenefitCostRatio();
				//runNearBenefit();
			}

			printResults("Set 3 R---" + "Edge Density = " + density + "---" + serversNumber + " Edge Servers---" + fairness_index
					+ " Budget---" + totalUsersNumber + " Users");
			totalUsersNumber = totalUsersNumber + 20;
		}
	}
	
	private static void runRealWorldExperimentConnectionNumber() {
		// in this set, random userNumber, same edge server number, same budget,
		// different density
		// density

		int serversNumber = 30;
		// ÕâÀïfairness indexµÄºâÁ¿Ö¸±ê×îºÃÊÇ0.75-0.95
		double fairness_index = 0.70;
		int totalUsersNumber = 100;
		double density = 1;
		int connection = 1;

		
		//这里生成10个server，和80个用户
				RandomServersGenerator serversGenerator = new RandomServersGenerator();
				RandomUserListGenerator userListGenerator = new RandomUserListGenerator();
				List<EdgeServer> servers_ten = serversGenerator.generateEdgeServerListFromRealWorldData(serversNumber);
				List<User> users = userListGenerator.generateUserListFromRealWorldData(serversNumber, totalUsersNumber, servers_ten);
				RandomGraphGenerator graphGenerator = new RandomGraphGenerator(30, density);
				
				//基于上面的矩阵，create一个连接density为density的图
				graphGenerator.createRandomGraph();
				
				//接收random图的adjacency矩阵值
				int[][] adjacencyMatrix = graphGenerator.getRandomGraphAdjacencyMatrix();
				
				//得到random图的distance矩阵值
				int[][] distanceMatrics = graphGenerator.getRandomGraphDistanceMatrix();
		for (int i = 0; i < 3; i++) {
			clearLists();

			for (int j = 0; j < 80; j++) {
				//setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection);
				setupRealWorldDataExperiments(serversNumber, fairness_index, totalUsersNumber, density, connection, servers_ten, users, adjacencyMatrix, distanceMatrics);
				//runEAD();
				runRandom();
				runGreedyCoveredUsers();
				runGreedyConnection();
				//runLiuICWS();
				runNearOptimal();
				//runHGA();
				runNearFC();
				runNearFairCostRatio();
				runNearBenefitCostRatio();
				runNearBenefit();
			}

			printResults("Set 3 R---" + "Edge Density = " + density + "---" + serversNumber + " Edge Servers---" + fairness_index
					+ " Budget---" + totalUsersNumber + " Users" + connection + "Connection");
			connection = connection + 1;
		}
	}
	
	private static void printResult(String name, List<Double> allUsersList, List<Double> costList,
			List<Double> fairnessdegreeList, List<Double> fairnessefficiencyList, List<Double> benefitList, List<Double> averagebenefitefficiencylist, List<Double> timeList) {
		double averageUsers = 0;
		double averageCost = 0;
		double averagefairnessdegree = 0;
		double averagefairnessefficiency = 0;
		double averagebenefits = 0;
		double averagebenefitefficiency = 0;
		double averageExecutionTime = 0;

		int size = costList.size();

		for (int i = 0; i < size; i++) {
			averageUsers = averageUsers + allUsersList.get(i);
			averageCost = averageCost + costList.get(i);
			averagefairnessdegree = averagefairnessdegree + fairnessdegreeList.get(i);
			averagefairnessefficiency = averagefairnessefficiency + fairnessefficiencyList.get(i);
			averagebenefits = averagebenefits + benefitList.get(i);
			averagebenefitefficiency = averagebenefitefficiency + averagebenefitefficiencylist.get(i); 
			averageExecutionTime = averageExecutionTime + timeList.get(i);
		}

		averageUsers = averageUsers / size;
		averageCost = averageCost / size;
		averagefairnessdegree = averagefairnessdegree / size;
		averagefairnessefficiency = averagefairnessefficiency / size;
		averagebenefits = averagebenefits / size;
		averagebenefitefficiency = averagebenefitefficiency / size;
		averageExecutionTime = averageExecutionTime / size;

		System.out.println(name);

		System.out.println("averageUsers = " + averageUsers);
		System.out.println("averageCost = " + averageCost);
		System.out.println("averagefairnessdegree = " + averagefairnessdegree);
		System.out.println("averagefairnessefficiency = " + averagefairnessefficiency);
		System.out.println("average_ALL_benefits = " + averagebenefits);
		System.out.println("average_benefitefficiency = " + averagebenefitefficiency);
		System.out.println("averageExecutionTime = " + averageExecutionTime);
		System.out.println();

		mLines.add(name);
		mLines.add("averageUsers = " + averageUsers);
		mLines.add("averageCost = " + averageCost);
		mLines.add("averagefairnessdegree = " + averagefairnessdegree);
		mLines.add("averagefairnessefficiency = " + averagefairnessefficiency);
		mLines.add("average_ALL_benefits = " + averagebenefits);
		mLines.add("average_benefitefficiency = " + averagebenefitefficiency);
		mLines.add("averageExecutionTime = " + averageExecutionTime);
		mLines.add("");
	}

	private static void printResults(String setName) {
		System.out.println("-----------------------  " + setName + "  ----------------------");
		System.out.println();

		mLines.add("-----------------------  " + setName + "  ----------------------");
		mLines.add("");
		printResult("EAD Model", mEADUserList, mEADCostList, mEADfairnessdegreeList, mEADfairnessefficiencyList, mEADallbenefitList, mEADbenefitefficiencyList,
				mEADExecutionTimeList);
		printResult("NearOptimal Model", mNearOptimalUserList, mNearOptimalCostList, mNearOptimalfairnessdegreeList, 
				mNearOptimalfairnessefficiencyList, mNearOptimalallbenefitList, mNearOptimalbenefitefficiencyList, mNearOptimalExecutionTimeList);
		printResult("Greedy Connection Model", mGreedyConnectionUserList, mGreedyConnectionCostList,
				mGreedyConnectionfairnessdegreeList, mGreedyConnectionfairnessefficiencyList, mGreedyConnectionallbenefitList, mGreedyConnectionbenefitefficiencyList,
				mGreedyConnectionExecutionTimeList);
		printResult("Greedy Covered Users Model", mGreedyCoveredUsersUserList, mGreedyCoveredCostList,
				mGreedyCoveredfairnessdegreeList, mGreedyCoveredfairnessefficiencyList, mGreedyCoveredallbenefitList, mGreedybenefitefficiencyList, 
				mGreedyCoveredUsersExecutionTimeList);
		printResult("LiuICWS Model", mLiuICWSUserList, mLiuICWSCostList, mLiuICWSfairnessdegreeList,
				mLiuICWSfairnessefficiencyList, mLiuICWSallbenefitList, mLiuICWSbenefitefficiencyList, mLiuICWSExecutionTimeList);
		printResult("Random Model", mRandomUserList, mRandomCostList, mRandomfairnessdegreeList,
				mRandomfairnessefficiencyList, mRandomlbenefitList, mRandombenefitefficiencyList,  mRandomExecutionTimeList);
		printResult("HGA Model", mHGAUserList, mHGACostList, mHGAfairnessdegreeList, mHGAfairnessefficiencyList, mHGAallbenefitList, mHGAbenefitefficiencyList,
				mHGAExecutionTimeList);
		
		printResult("NearFC Model", mNearFCUserList, mNearFCCostList, mNearFCfairnessdegreeList, mNearFCfairnessefficiencyList, 
				mNearFCallbenefitList, mNearFCbenefitefficiencyList, mNearFCExecutionTimeList);
		printResult("NearFairCostRatio Model", mNearFairCostRatioUserList, mNearFairCostRatioCostList, mNearFairCostRatiofairnessdegreeList, 
				mNearFairCostRatiofairnessefficiencyList, 
				mNearFairCostRatioallbenefitList, mNearFairCostRatiobenefitefficiencyList, mNearFairCostRatioExecutionTimeList);
		printResult("NearBenefitCostRatio Model", mNearBenefitCostRatioUserList, mNearBenefitCostRatioCostList, mNearBenefitCostRatiofairnessdegreeList, 
				mNearBenefitCostRatiofairnessefficiencyList, 
				mNearBenefitCostRatioallbenefitList, mNearBenefitCostRatiobenefitefficiencyList, mNearBenefitCostRatioExecutionTimeList);
		printResult("NearBenefit Model", mNearBenefitUserList, mNearBenefitCostList, mNearBenefitfairnessdegreeList, 
				mNearBenefitfairnessefficiencyList, 
				mNearBenefitallbenefitList, mNearBenefitbenefitefficiencyList, mNearBenefitExecutionTimeList);
//		printResult("EAD Model", mEADUserList, mEADfairnessefficiencyList, mEADfairnessdegreeList, mEADallbenefitList, mEADbenefitefficiencyList, mEADCostList,
//				mEADExecutionTimeList);
//		printResult("NearOptimal Model", mNearOptimalUserList, mNearOptimalfairnessefficiencyList,  mNearOptimalfairnessdegreeList, 
//				 mNearOptimalallbenefitList, mNearOptimalbenefitefficiencyList, mNearOptimalCostList, mNearOptimalExecutionTimeList);
//		printResult("Greedy Connection Model", mGreedyConnectionUserList, mGreedyConnectionfairnessefficiencyList, 
//				mGreedyConnectionfairnessdegreeList, mGreedyConnectionallbenefitList, mGreedyConnectionbenefitefficiencyList, mGreedyConnectionCostList,
//				mGreedyConnectionExecutionTimeList);
//		printResult("Greedy Covered Users Model", mGreedyCoveredUsersUserList, mGreedyCoveredfairnessefficiencyList,
//				mGreedyCoveredfairnessdegreeList, mGreedyCoveredallbenefitList, mGreedybenefitefficiencyList, mGreedyCoveredCostList, 
//				mGreedyCoveredUsersExecutionTimeList);
//		printResult("LiuICWS Model", mLiuICWSUserList,  mLiuICWSfairnessdegreeList,
//				mLiuICWSfairnessefficiencyList, mLiuICWSallbenefitList, mLiuICWSbenefitefficiencyList, mLiuICWSCostList,mLiuICWSExecutionTimeList);
//		printResult("Random Model", mRandomUserList, mRandomCostList, mRandomfairnessdegreeList,
//				mRandomfairnessefficiencyList, mRandomlbenefitList, mRandombenefitefficiencyList,  mRandomExecutionTimeList);
////		printResult("HGA Model", mHGAUserList, mHGACostList, mHGAfairnessdegreeList, mHGAfairnessefficiencyList, mHGAallbenefitList,
////				mHGAExecutionTimeList);
	}

	private static void clearLists() {
		mEADUserList.clear();
		mEADCostList.clear();
		mEADExecutionTimeList.clear();
		mEADfairnessdegreeList.clear();
		mEADallbenefitList.clear();
		mEADbenefitefficiencyList.clear();
		mEADfairnessefficiencyList.clear();

		mRandomUserList.clear();
		mRandomCostList.clear();
		mRandomExecutionTimeList.clear();
		mRandomfairnessdegreeList.clear();
		mRandomfairnessefficiencyList.clear();
		mRandomlbenefitList.clear();

		mGreedyCoveredUsersUserList.clear();
		mGreedyCoveredCostList.clear();
		mGreedyCoveredUsersExecutionTimeList.clear();
		mGreedyCoveredfairnessdegreeList.clear();
		mGreedyCoveredfairnessefficiencyList.clear();
		mGreedybenefitefficiencyList.clear();
		mGreedyCoveredallbenefitList.clear();

		mGreedyConnectionUserList.clear();
		mGreedyConnectionCostList.clear();
		mGreedyConnectionExecutionTimeList.clear();
		mGreedyConnectionfairnessdegreeList.clear();
		mGreedyConnectionfairnessefficiencyList.clear();
		mGreedyConnectionbenefitefficiencyList.clear();
		mGreedyConnectionallbenefitList.clear();


		mNearOptimalUserList.clear();
		mNearOptimalCostList.clear();
		mNearOptimalExecutionTimeList.clear();
		mNearOptimalfairnessdegreeList.clear();
		mNearOptimalallbenefitList.clear();
		mNearOptimalbenefitefficiencyList.clear();
		mNearOptimalfairnessefficiencyList.clear();

		mLiuICWSUserList.clear();
		mLiuICWSCostList.clear();
		mLiuICWSExecutionTimeList.clear();
		mLiuICWSfairnessdegreeList.clear();
		mLiuICWSallbenefitList.clear();
		mLiuICWSbenefitefficiencyList.clear();
		mLiuICWSfairnessefficiencyList.clear();

		mHGAUserList.clear();
		mHGACostList.clear();
		mHGAExecutionTimeList.clear();
		mHGAfairnessdegreeList.clear();
		mHGAallbenefitList.clear();
		mHGAbenefitefficiencyList.clear();
		mHGAfairnessefficiencyList.clear();
		
		mNearFCUserList.clear();
		mNearFCCostList.clear();
		mNearFCExecutionTimeList.clear();
		mNearFCfairnessdegreeList.clear();
		mNearFCallbenefitList.clear();
		mNearFCbenefitefficiencyList.clear();
		mNearFCfairnessefficiencyList.clear();
		
		mNearFairCostRatioUserList.clear();
		mNearFairCostRatioCostList.clear();
		mNearFairCostRatioExecutionTimeList.clear();
		mNearFairCostRatiofairnessdegreeList.clear();
		mNearFairCostRatioallbenefitList.clear();
		mNearFairCostRatiobenefitefficiencyList.clear();
		mNearFairCostRatiofairnessefficiencyList.clear();
		
		mNearBenefitCostRatioUserList.clear();
		mNearBenefitCostRatioCostList.clear();
		mNearBenefitCostRatioExecutionTimeList.clear();
		mNearBenefitCostRatiofairnessdegreeList.clear();
		mNearBenefitCostRatioallbenefitList.clear();
		mNearBenefitCostRatiobenefitefficiencyList.clear();
		mNearBenefitCostRatiofairnessefficiencyList.clear();
		
		mNearBenefitUserList.clear();
		mNearBenefitCostList.clear();
		mNearBenefitExecutionTimeList.clear();
		mNearBenefitfairnessdegreeList.clear();
		mNearBenefitallbenefitList.clear();
		mNearBenefitbenefitefficiencyList.clear();
		mNearBenefitfairnessefficiencyList.clear();
	}

	private static void runEAD() {
		Instant start = Instant.now();
		mEADModel.runnEAD();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mEADUserList.add(mEADModel.getAllUsers());
		mEADCostList.add(mEADModel.getcost());
		mEADfairnessdegreeList.add(mEADModel.getfairness_degree());
		mEADfairnessefficiencyList.add(mEADModel.getfairness_efficiency());
		mEADallbenefitList.add(mEADModel.getallbenefits());
		mEADbenefitefficiencyList.add(mEADModel.getbenefit_efficiency());
		mEADExecutionTimeList.add(duration);
	}

	private static int runNearOptimal() {
		
		int budget = 0;
		
		Instant start = Instant.now();
		mNearOptimalModel.runNearOptimal();
		mHModel.runHM();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		if (mNearOptimalModel.getfairness_efficiency() > mHModel.getfairness_efficiency()) {
			mNearOptimalUserList.add(mNearOptimalModel.getAllUsers());
			mNearOptimalCostList.add(mNearOptimalModel.getcost());
			mNearOptimalfairnessdegreeList.add(mNearOptimalModel.getfairness_degree());
			mNearOptimalfairnessefficiencyList.add(mNearOptimalModel.getfairness_efficiency());
			mNearOptimalallbenefitList.add(mNearOptimalModel.getallbenefits());
			mNearOptimalbenefitefficiencyList.add(mNearOptimalModel.getbenefit_efficiency());
			mNearOptimalExecutionTimeList.add(duration);
			budget = (int) mNearOptimalModel.getcost();
		} else {
			mNearOptimalUserList.add(mHModel.getAllUsers());
			mNearOptimalCostList.add(mHModel.getcost());
			mNearOptimalfairnessdegreeList.add(mHModel.getfairness_degree());
			mNearOptimalfairnessefficiencyList.add(mHModel.getfairness_efficiency());
			mNearOptimalallbenefitList.add(mHModel.getallbenefits());
			mNearOptimalbenefitefficiencyList.add(mHModel.getbenefit_efficiency());
			mNearOptimalExecutionTimeList.add(duration);
			budget = (int) mHModel.getcost();
		}
		
		return budget;
	}
	

	private static void runHGA() {
		Instant start = Instant.now();
	//	mHGAModel.runHGA();
	//	mHModel.runHM();
		mNearOptimalModel.runNearOptimal();
		mNearBenefitCostRatioModel.runNearBenefitCostRatio();
		//mNearOptimalModel.runNearOptimal();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
//		mHGAUserList.add(mHModel.getAllUsers());
//		mHGACostList.add(mHModel.getcost());
//		mHGAfairnessdegreeList.add(mHModel.getfairness_degree());
//		mHGAfairnessefficiencyList.add(mHModel.getfairness_efficiency());
//		mHGAallbenefitList.add(mHModel.getallbenefits());
//		mHGAbenefitefficiencyList.add(mHModel.getbenefit_efficiency());
//		mHGAExecutionTimeList.add(duration);
		if (mNearOptimalModel.getfairness_efficiency() > mNearBenefitCostRatioModel.getfairness_efficiency()) {
			mHGAUserList.add(mNearOptimalModel.getAllUsers());
			mHGACostList.add(mNearOptimalModel.getcost());
			mHGAfairnessdegreeList.add(mNearOptimalModel.getfairness_degree());
			mHGAfairnessefficiencyList.add(mNearOptimalModel.getfairness_efficiency());
			mHGAallbenefitList.add(mNearOptimalModel.getallbenefits());
			mHGAbenefitefficiencyList.add(mNearOptimalModel.getbenefit_efficiency());
			mHGAExecutionTimeList.add(duration);
		} else {
			mHGAUserList.add(mNearBenefitCostRatioModel.getAllUsers());
			mHGACostList.add(mNearBenefitCostRatioModel.getcost());
			mHGAfairnessdegreeList.add(mNearBenefitCostRatioModel.getfairness_degree());
			mHGAfairnessefficiencyList.add(mNearBenefitCostRatioModel.getfairness_efficiency());
			mHGAallbenefitList.add(mNearBenefitCostRatioModel.getallbenefits());
			mHGAbenefitefficiencyList.add(mNearBenefitCostRatioModel.getbenefit_efficiency());
			mHGAExecutionTimeList.add(duration);
		}
	}
	
//	private static void runNearOptimal() {
//		Instant start = Instant.now();
//		mHGAModel.runHGA();
//		Instant end = Instant.now();
//		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
//		// System.out.println(duration);
//		mNearOptimalUserList.add(mHGAModel.getAllUsers());
//		mNearOptimalCostList.add(mHGAModel.getcost());
//		mNearOptimalfairnessdegreeList.add(mHGAModel.getfairness_degree());
//		mNearOptimalfairnessefficiencyList.add(mHGAModel.getfairness_efficiency());
//		mNearOptimalallbenefitList.add(mHGAModel.getallbenefits());
//		mNearOptimalExecutionTimeList.add(duration);
//	}

	private static void runGreedyConnection() {
		// System.out.println("------- Greedy Connections -------");
		Instant start = Instant.now();
		mGreedyConnectionModel.runGreedyConnection();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mGreedyConnectionUserList.add(mGreedyConnectionModel.getAllUsers());
		mGreedyConnectionCostList.add(mGreedyConnectionModel.getcost());
		mGreedyConnectionfairnessdegreeList.add(mGreedyConnectionModel.getfairness_degree());
		mGreedyConnectionfairnessefficiencyList.add(mGreedyConnectionModel.getfairness_efficiency());
		mGreedyConnectionallbenefitList.add(mGreedyConnectionModel.getallbenefits());
		mGreedyConnectionbenefitefficiencyList.add(mGreedyConnectionModel.getbenefit_efficiency());
		mGreedyConnectionExecutionTimeList.add(duration);
	}

	private static void runGreedyCoveredUsers() {
		Instant start = Instant.now();
		mGreedyCoveredUsersModel.runGreedyCoveredUsers();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mGreedyCoveredUsersUserList.add(mGreedyCoveredUsersModel.getAllUsers());
		mGreedyCoveredCostList.add(mGreedyCoveredUsersModel.getcost());
		mGreedyCoveredfairnessdegreeList.add(mGreedyCoveredUsersModel.getfairness_degree());
		mGreedyCoveredfairnessefficiencyList.add(mGreedyCoveredUsersModel.getfairness_efficiency());
		mGreedyCoveredallbenefitList.add(mGreedyCoveredUsersModel.getallbenefits());
		mGreedybenefitefficiencyList.add(mGreedyCoveredUsersModel.getbenefit_efficiency());
		mGreedyCoveredUsersExecutionTimeList.add(duration);
	}

	private static void runLiuICWS() {
		Instant start = Instant.now();
		mLiuICWSModel.runLiuICWS();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mLiuICWSUserList.add(mLiuICWSModel.getAllUsers());
		mLiuICWSCostList.add(mLiuICWSModel.getcost());
		mLiuICWSfairnessdegreeList.add(mLiuICWSModel.getfairness_degree());
		mLiuICWSfairnessefficiencyList.add(mLiuICWSModel.getfairness_efficiency());
		mLiuICWSallbenefitList.add(mLiuICWSModel.getallbenefits());
		mLiuICWSbenefitefficiencyList.add(mLiuICWSModel.getbenefit_efficiency());
		mLiuICWSExecutionTimeList.add(duration);
	}

	private static void runRandom() {
		Instant start = Instant.now();
		mRandomModel.runRandom();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mRandomUserList.add(mRandomModel.getAllUsers());
		mRandomCostList.add(mRandomModel.getcost());
		mRandomfairnessdegreeList.add(mRandomModel.getfairness_degree());
		mRandomfairnessefficiencyList.add(mRandomModel.getfairness_efficiency());
		mRandomlbenefitList.add(mRandomModel.getallbenefits());
		mRandombenefitefficiencyList.add(mRandomModel.getbenefit_efficiency());
		mRandomExecutionTimeList.add(duration);
	}
	
	private static void runNearFC() {
		Instant start = Instant.now();
		mNearFCModel.runNearFC();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mNearFCUserList.add(mNearFCModel.getAllUsers());
		mNearFCCostList.add(mNearFCModel.getcost());
		mNearFCfairnessdegreeList.add(mNearFCModel.getfairness_degree());
		mNearFCfairnessefficiencyList.add(mNearFCModel.getfairness_efficiency());
		mNearFCallbenefitList.add(mNearFCModel.getallbenefits());
		mNearFCbenefitefficiencyList.add(mNearFCModel.getbenefit_efficiency());
	 	mNearFCExecutionTimeList.add(duration);
   }
	
	private static void runNearFairCostRatio() {
		Instant start = Instant.now();
		mNearFairCostRatioModel.runNearFairCostRatio();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mNearFairCostRatioUserList.add(mNearFairCostRatioModel.getAllUsers());
		mNearFairCostRatioCostList.add(mNearFairCostRatioModel.getcost());
		mNearFairCostRatiofairnessdegreeList.add(mNearFairCostRatioModel.getfairness_degree());
		mNearFairCostRatiofairnessefficiencyList.add(mNearFairCostRatioModel.getfairness_efficiency());
		mNearFairCostRatioallbenefitList.add(mNearFairCostRatioModel.getallbenefits());
		mNearFairCostRatiobenefitefficiencyList.add(mNearFairCostRatioModel.getbenefit_efficiency());
	 	mNearFairCostRatioExecutionTimeList.add(duration);
   }
	
//	private static void runNearBenefitCostRatio() {
//		Instant start = Instant.now();
//		mNearBenefitCostRatioModel.runNearBenefitCostRatio();
//		Instant end = Instant.now();
//		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
//		// System.out.println(duration);
//		mNearBenefitCostRatioUserList.add(mNearBenefitCostRatioModel.getAllUsers());
//		mNearBenefitCostRatioCostList.add(mNearBenefitCostRatioModel.getcost());
//		mNearBenefitCostRatiofairnessdegreeList.add(mNearBenefitCostRatioModel.getfairness_degree());
//		mNearBenefitCostRatiofairnessefficiencyList.add(mNearBenefitCostRatioModel.getfairness_efficiency());
//		mNearBenefitCostRatioallbenefitList.add(mNearBenefitCostRatioModel.getallbenefits());
//		mNearBenefitCostRatiobenefitefficiencyList.add(mNearBenefitCostRatioModel.getbenefit_efficiency());
//	 	mNearBenefitCostRatioExecutionTimeList.add(duration);
//   }
//	
	private static void runNearBenefitCostRatio() {
		
		Instant start = Instant.now();
		mNearOptimalModel.runNearOptimal();
		mHModel.runHM();
		mGreedyCoveredUsersModel.runGreedyCoveredUsers();

		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		if ( (mNearOptimalModel.getfairness_efficiency() > mHModel.getfairness_efficiency()) &&  
				(mNearOptimalModel.getfairness_efficiency() > mGreedyCoveredUsersModel.getfairness_efficiency()) ) {	
			mNearOptimalUserList.add(mNearOptimalModel.getAllUsers());
			mNearOptimalCostList.add(mNearOptimalModel.getcost());
			mNearOptimalfairnessdegreeList.add(mNearOptimalModel.getfairness_degree());
			mNearOptimalfairnessefficiencyList.add(mNearOptimalModel.getfairness_efficiency());
			mNearOptimalallbenefitList.add(mNearOptimalModel.getallbenefits());
			mNearOptimalbenefitefficiencyList.add(mNearOptimalModel.getbenefit_efficiency());
			System.out.println("select 1");
		} else if ( (mHModel.getfairness_efficiency()  >  mNearOptimalModel.getfairness_efficiency() ) &&  
				(mHModel.getfairness_efficiency() > mGreedyCoveredUsersModel.getfairness_efficiency()) 
				) {
			mNearOptimalUserList.add(mHModel.getAllUsers());
			mNearOptimalCostList.add(mHModel.getcost());
			mNearOptimalfairnessdegreeList.add(mHModel.getfairness_degree());
			mNearOptimalfairnessefficiencyList.add(mHModel.getfairness_efficiency());
			mNearOptimalallbenefitList.add(mHModel.getallbenefits());
			mNearOptimalbenefitefficiencyList.add(mHModel.getbenefit_efficiency());
			mNearOptimalExecutionTimeList.add(duration);
			System.out.println("select 2");
		} else {
			mGreedyCoveredUsersUserList.add(mGreedyCoveredUsersModel.getAllUsers());
			mGreedyCoveredCostList.add(mGreedyCoveredUsersModel.getcost());
			mGreedyCoveredfairnessdegreeList.add(mGreedyCoveredUsersModel.getfairness_degree());
			mGreedyCoveredfairnessefficiencyList.add(mGreedyCoveredUsersModel.getfairness_efficiency());
			mGreedyCoveredallbenefitList.add(mGreedyCoveredUsersModel.getallbenefits());
			mGreedybenefitefficiencyList.add(mGreedyCoveredUsersModel.getbenefit_efficiency());
			mGreedyCoveredUsersExecutionTimeList.add(duration);
			System.out.println("select 3");
			
		}
		
   }
	
	
	private static void runNearBenefit() {
		Instant start = Instant.now();
		mNearBenefitModel.runNearBenefit();
		Instant end = Instant.now();
		double duration = (double) (Duration.between(start, end).toMillis()) / 1000;
		// System.out.println(duration);
		mNearBenefitUserList.add(mNearBenefitModel.getAllUsers());
		mNearBenefitCostList.add(mNearBenefitModel.getcost());
		mNearBenefitfairnessdegreeList.add(mNearBenefitModel.getfairness_degree());
		mNearBenefitfairnessefficiencyList.add(mNearBenefitModel.getfairness_efficiency());
		mNearBenefitallbenefitList.add(mNearBenefitModel.getallbenefits());
		mNearBenefitbenefitefficiencyList.add(mNearBenefitModel.getbenefit_efficiency());
	 	mNearBenefitExecutionTimeList.add(duration);
   }
}

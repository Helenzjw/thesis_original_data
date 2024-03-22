package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objectives.User;
import tool.RandomNodeGenerator;

public class RandomModel {
	
	private double mAllUsers;
	private double mCost;
	private double mbenefitefficiency;
	private double mAllBenefits;
	private double mAllBenefit_square;

	private int mServersNumber;
	private int mBudget;
	private double mFairness_index;
	private int[][] mAdjacencyMatrix;
	private int[][] mUserCovered; 
	private int[][] mUserBenefits;
	private List<User> mUsers;// All Users
	
	//2021
	//private double mfairnessindex;
	private double mfairnessdegree; //make the original value = 0
	private double mfairness_efficiency;
    private double mfairness_degree;
	//no need to add another variable for caching cost, we can use the 
	
	private List<Integer> mValidUserList; //Covered Users by selected servers
	
	private List<Integer> mSelectedServerList;
	private List<Integer> mBenefitList;
	//private List<Integer> mUserList;
	
	public RandomModel(int serversNumber, double fairness_index, int[][] adjacencyMatrix, int[][] userCovered, int[][] userBenefits, List<User> users) {
		mServersNumber = serversNumber;
		mFairness_index = fairness_index;
		mAdjacencyMatrix = adjacencyMatrix;
		mUserCovered = userCovered;
		mUsers = users;
		
		
		mUserBenefits = userBenefits;

		mValidUserList = new ArrayList<>();
		mSelectedServerList = new ArrayList<>();
		//mUserList = new ArrayList<>();
		mBenefitList = new ArrayList<>();
		
	}
	
	private boolean canServerAccessSelectedServers(int server) {
		for (int ss : mSelectedServerList) {
			if (ss == server) return true;
			//if (mAdjacencyMatrix[ss][server] == 1 || mAdjacencyMatrix[server][ss] == 1) return true;
		}
		
		return false;
	}
	
	public void runRandom() {
		// randomly select first server
		RandomNodeGenerator randomGenerator = new RandomNodeGenerator();
		int randomServer;
		//randomServer = randomGenerator.getRandomNode(mServersNumber);
		
		//while (mValidUserList.size() < mUsers.size()) 
		//2021 while the fairness degree < fairness index
		//这里设置初始的fairness degree是零 然后和输入的fairness index比较
		double fairness_degree = 0;
		
		//这里给fairness初始化值为零 和private为零有区别吗..为什么循环走不出来？
		mfairnessdegree = 0;
		//开始循环，计算fairness degree的值
		//while (mfairnessdegree <= mFairness_index && mValidUserList.size() < mUsers.size()) {
		while (mValidUserList.size() < mUsers.size()) {
			randomServer = randomGenerator.getRandomNode(mServersNumber);
			//System.out.println(randomServer);
			if (canServerAccessSelectedServers(randomServer)) continue;
			
			//this is the number of caching cost
			mSelectedServerList.add(randomServer);
			
			//这里是把直接被random选择的server cover的用户添加到valid user list里面
			for (User user : mUsers) {
				if (user.nearEdgeServers.contains(randomServer) && !mValidUserList.contains(user.id)) {
					mValidUserList.add(user.id);
				}
			}
	       //这里的判断如何设置循环 without budget
		   //这里这个循环是要把选择的random server的邻接server cover的user也放到valid user list里面
		   //这里为什么要加循环？ 为什么是m_servernumber
			for (int i = 0; i < mServersNumber; i++) {
				if (mAdjacencyMatrix[i][randomServer] == 1 || mAdjacencyMatrix[randomServer][i] == 1) {
					// mConnectionsMap.remove(i);
					for (User user : mUsers) {
						if (user.nearEdgeServers.contains(i) && !mValidUserList.contains(user.id)) {
							mValidUserList.add(user.id);
						}
					}
				}
			}
			
		
		}
		//这里计算一下全部的cost和全部的benefit和全部cover用户的数量
		mCost = mSelectedServerList.size();
		mAllBenefits = calculateBenefits();//全部用户benefit之和
		mAllUsers = mValidUserList.size(); //计算fairness degree的分母
		mAllBenefit_square = calculate_single_Benefits_square(); //fairness degree的分母
		
		//现在计算fairness degree    这里fairness_degree和 m fairness degree一样吧...
		//fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);		
		mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);	
		
		//计算fairness efficiency
		//mfairness_efficiency = fairness_degree / mCost;		
		mfairness_efficiency = mfairnessdegree / mCost;
		//mAllUsers = mValidUserList.size();
		
/*		//循环里面已经计算过了，这里还需要额外计算吗？
		//现在计算fairness degree
		mfairness_degree = fairness_degree;		
		
		//计算fairness efficiency
		mfairness_efficiency = fairness_degree / mCost;		
		
		mAllUsers = mValidUserList.size();
*/
		
	
	}
	
	/*		while (mfairnessdegree <= mfairnessindex) {
				int m = mServersNumber - 1;
				int j = m +(int)(Math.random()*(0+1-m));
				//System.out.println(j);
				if (mAdjacencyMatrix[j][randomServer] == 1 || mAdjacencyMatrix[randomServer][j] == 1) {
					// mConnectionsMap.remove(i);
					for (User user : mUsers) {
						if (user.nearEdgeServers.contains(j) && !mValidUserList.contains(user.id)) {
							mValidUserList.add(user.id);
						}
					}
				}
			}
	*/
/*			while (mSelectedServerList.size() <= mBudget) {
			randomServer = randomGenerator.getRandomNode(mServersNumber);
			//System.out.println(randomServer);
			if (canServerAccessSelectedServers(randomServer)) continue;
			
			//this is the number of caching cost
			mSelectedServerList.add(randomServer);
			
			for (User user : mUsers) {
				if (user.nearEdgeServers.contains(randomServer) && !mValidUserList.contains(user.id)) {
					mValidUserList.add(user.id);
				}
			}
	
			for (int i = 0; i < mBudget; i++) {
				int m = mServersNumber - 1;
				int j = m +(int)(Math.random()*(0+1-m));
				//System.out.println(j);
				if (mAdjacencyMatrix[j][randomServer] == 1 || mAdjacencyMatrix[randomServer][j] == 1) {
					// mConnectionsMap.remove(i);
					for (User user : mUsers) {
						if (user.nearEdgeServers.contains(j) && !mValidUserList.contains(user.id)) {
							mValidUserList.add(user.id);
						}
					}
				}
			}
		}
*/
	
	//这个函数计算了全部用户全部的benefit的和 然后返回了全部的benefit
	private double calculateBenefits() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;
		
		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server)) benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit) mBenefitList.set(user.id, benefit);
			}
		}
		
		//每一个benefit都是一个用户的benefit，然后benefits是全部用户的benefit相加
		for (int benefit : mBenefitList) benefits = benefits + benefit;
		
		return benefits;
	}
	
	//接下来应该计算每个用户的benefit，然后把benefit存在数组里，最后循环每个数字的平方和相加作为fairness计算的分母
	private double calculate_single_Benefits_square() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;
		
		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server)) benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit) mBenefitList.set(user.id, benefit);
			}
		}
		
		//这里计算benefits是指每一个用户的benefit的平方和,这样的话可以返回benefits
		for (int benefit : mBenefitList) benefits = benefits + (benefit * benefit);
		
		return benefits;
	}
	
	public double getAllUsers() {
		return mAllUsers;
	}
	
	public double getcost() {
		return mCost;
	}
	
	public double getfairness_degree() {
		return mfairnessdegree;
	}
	
	public double getfairness_efficiency() {
		return mfairness_efficiency;
	}
	
	public double getallbenefits() {
		return mAllBenefits = mAllBenefits/mValidUserList.size();
	}
	
	public double getbenefit_efficiency() {
		return mbenefitefficiency = mAllBenefits/mCost;
	}
}

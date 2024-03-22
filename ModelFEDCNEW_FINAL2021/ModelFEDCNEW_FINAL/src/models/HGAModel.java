package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectives.EdgeServer;
import objectives.User;

public class HGAModel {

	private double mAllUsers;

	private int mServersNumber;
	private int mBudget;
	private int[][] mAdjacencyMatrix;
	private int[][] mUserCovered;
	private List<User> mUsers;
	List<EdgeServer> mServers;

	private int budget;

	// 2021
	private double mCost;
	private double mbenefitefficiency;
	private double mAllBenefits;
	private double mAllBenefit_square;
	private int[][] mUserBenefits;

	private double mFairness_index;
	private double mfairnessdegree; // make the original value = 0
	private double mfairness_efficiency;
	private List<Integer> mBenefitList;
	//

	private Map<Integer, Integer> mCoveredUsersMap;
	private List<Integer> mValidUserList;
	private List<Integer> mSelectedServerList;

	public void setBudget(int b) {
		budget = b;
	}

	public HGAModel(int serversNumber, double fairness_index, int[][] adjacencyMatrix, int[][] userCovered,
			int[][] userBenefits, List<User> users, List<EdgeServer> servers) {
		mServersNumber = serversNumber;
		mAdjacencyMatrix = adjacencyMatrix;
		mUserCovered = userCovered;
		mUsers = users;
		mServers = servers;

		// 2021
		mFairness_index = fairness_index;
		mUserBenefits = userBenefits;
		mBenefitList = new ArrayList<>();
		//

		mValidUserList = new ArrayList<>();
		mSelectedServerList = new ArrayList<>();
		mCoveredUsersMap = new HashMap<>();// ?
	}

	public void runHGA() {

		for (int i = 0; i < mUsers.size(); i++)
			mBenefitList.add(0);

		mAllBenefits = 0;

		int currentBuedget = budget;

		while (currentBuedget > 0) {
//			updateNewUsersCoveredByServerList();
			int selected = bubbleServerWithMaximumIncreasingUncoveredUsers();

			if (selected != -1) {
				mSelectedServerList.add(selected);
				if (!mServers.get(selected).getCoveredUsersWithinOneHopNotInTheList(mValidUserList).isEmpty()) {
					mValidUserList.addAll(mServers.get(selected).getCoveredUsersWithinOneHopNotInTheList(mValidUserList));
				}
			}
			currentBuedget--;
	//20210223		System.out.println(currentBuedget);
		}

		// 2021
		// �������һ��ȫ����cost��ȫ����benefit��ȫ��cover�û�������
		mCost = budget;
		mAllBenefits = calculateBenefits();// ȫ���û�benefit֮��
		mAllUsers = mValidUserList.size(); // ����fairness degree�ķ�ĸ
		mAllBenefit_square = calculate_single_Benefits_square(); // fairness degree�ķ�ĸ

		// ���ڼ���fairness degree ����fairness_degree�� m fairness degreeһ����...
		// fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() *
		// mAllBenefit_square);

		mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);

		// ����fairness efficiency
		// mfairness_efficiency = fairness_degree / mCost;
		mfairness_efficiency = mfairnessdegree / mCost;
		//

//					System.out.println(mfairnessdegree + " --- " + mfairness_efficiency);

		for (int serverId : mSelectedServerList) {
			for (int userId : mServers.get(serverId).servedUsers) {
				mBenefitList.set(userId, 2);
			}
			for (int userId : mServers.get(serverId).usersInOneHop) {
				if (mBenefitList.get(userId) < 1)
					mBenefitList.set(userId, 1);
			}
		}

		//20210223
	/*	for (int i : mSelectedServerList)
			System.out.print(i + ", ");

		System.out.println();
	*/
		// System.out.print("Benefits list: ");
		for (int benefits : mBenefitList) {
			mAllBenefits = mAllBenefits + benefits;
			// System.out.print(benefits + " ");
		}
		// System.out.println();
//		//20210816
//		mAllBenefits = mAllBenefits/mValidUserList.size();
//		mCost = mSelectedServerList.size();
		// mBenefitPerReplica = mAllBenefits / mCost;
	}

	private int bubbleServerWithMaximumIncreasingUncoveredUsers() {
		int id = -1;
		int cu = 0;
		int dcu = 0;
		for (EdgeServer server : mServers) {
			List<Integer> nlist = new ArrayList<>();

			if (server.getCoveredUsersWithinOneHopNotInTheList(nlist).size() >= cu
					|| (server.getCoveredUsersWithinOneHopNotInTheList(nlist).size() == cu
							&& server.getDirectlyCoveredUsersNotInTheList(nlist).size() >= dcu)) {
				cu = server.getCoveredUsersWithinOneHopNotInTheList(nlist).size();
				dcu = server.getDirectlyCoveredUsersNotInTheList(nlist).size();
				id = server.id;
			}
		}

		return id;
	}

	// 2021
	// �������������ȫ���û�ȫ����benefit�ĺ� Ȼ�󷵻���ȫ����benefit
	private double calculateBenefits() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;

		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server))
					benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit)
					mBenefitList.set(user.id, benefit);
			}
		}

		// ÿһ��benefit����һ���û���benefit��Ȼ��benefits��ȫ���û���benefit���
		for (int benefit : mBenefitList)
			benefits = benefits + benefit;

		return benefits;
	}

	// ������Ӧ�ü���ÿ���û���benefit��Ȼ���benefit������������ѭ��ÿ�����ֵ�ƽ���������Ϊfairness����ķ�ĸ
	private double calculate_single_Benefits_square() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;

		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server))
					benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit)
					mBenefitList.set(user.id, benefit);
			}
		}

		// �������benefits��ָÿһ���û���benefit��ƽ����,�����Ļ����Է���benefits
		for (int benefit : mBenefitList)
			benefits = benefits + (benefit * benefit);

		return benefits;
	}
	//

	private int selectServerWithMaximumCoveredUsers() {
		int maximumCoveredUsers = -1;
		int serverWithMaximumCoveredUsers = -1;
		for (int server : mCoveredUsersMap.keySet()) {
			int coveredUsers = mCoveredUsersMap.get(server);
			if (coveredUsers > maximumCoveredUsers) {
				maximumCoveredUsers = coveredUsers;
				serverWithMaximumCoveredUsers = server;
			}
		}

		mCoveredUsersMap.remove(serverWithMaximumCoveredUsers);

		mSelectedServerList.add(serverWithMaximumCoveredUsers);

		for (User user : mUsers) {
			if (user.nearEdgeServers.contains(serverWithMaximumCoveredUsers) && !mValidUserList.contains(user.id)) {
				mValidUserList.add(user.id);
			}
		}

		for (int i = 0; i < mServersNumber; i++) {
			if (mAdjacencyMatrix[i][serverWithMaximumCoveredUsers] == 1
					|| mAdjacencyMatrix[serverWithMaximumCoveredUsers][i] == 1) {
				for (User user : mUsers) {
					if (user.nearEdgeServers.contains(i) && !mValidUserList.contains(user.id)) {
						mValidUserList.add(user.id);
					}
				}
			}
		}

		return serverWithMaximumCoveredUsers;
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

/*
 * public void runNearOptimal() {
 * 
 * for (int i = 0; i < mServersNumber; i++) { mCoveredUsersMap.put(i,
 * mServers.get(i).servedUsers.size());//What is this line mean? }
 * 
 * while (mSelectedServerList.size() <= mBudget) {
 * selectServerWithMaximumCoveredUsers(); }
 * 
 * mAllUsers = mValidUserList.size(); }
 * 
 * private int selectServerWithMaximumCoveredUsers() { int maximumCoveredUsers =
 * -1; int serverWithMaximumCoveredUsers = -1; for (int server :
 * mCoveredUsersMap.keySet()) { int coveredUsers = mCoveredUsersMap.get(server);
 * if (coveredUsers > maximumCoveredUsers) { maximumCoveredUsers = coveredUsers;
 * serverWithMaximumCoveredUsers = server; } }
 * 
 * mCoveredUsersMap.remove(serverWithMaximumCoveredUsers);
 * 
 * mSelectedServerList.add(serverWithMaximumCoveredUsers);
 * 
 * for (User user : mUsers) { if
 * (user.nearEdgeServers.contains(serverWithMaximumCoveredUsers) &&
 * !mValidUserList.contains(user.id)) { mValidUserList.add(user.id); } }
 * 
 * for (int i = 0; i < mServersNumber; i++) { if
 * (mAdjacencyMatrix[i][serverWithMaximumCoveredUsers] == 1 ||
 * mAdjacencyMatrix[serverWithMaximumCoveredUsers][i] == 1) { for (User user :
 * mUsers) { if (user.nearEdgeServers.contains(i) &&
 * !mValidUserList.contains(user.id)) { mValidUserList.add(user.id); } } } }
 * 
 * return serverWithMaximumCoveredUsers; }
 */

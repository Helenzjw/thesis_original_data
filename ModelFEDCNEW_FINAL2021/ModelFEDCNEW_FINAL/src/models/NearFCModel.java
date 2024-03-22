package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectives.EdgeServer;
import objectives.User;

public class NearFCModel {
	private double mAllUsers;

	private int mServersNumber;
	private int mBudget;
	private int[][] mAdjacencyMatrix;
	private int[][] mUserCovered;
	private List<User> mUsers;
	List<EdgeServer> mServers;

	// 2021
	private double mCost = 0;
	private double mbenefitefficiency;
	private double mAllBenefits;
	private double mAllBenefit_square;
	private int[][] mUserBenefits;

	private double mFairness_index;
	private double mfairnessdegree; // make the original value = 0
	private double mfairness_efficiency;
	private List<Integer> mBenefitList;
	private List<Integer> mServerfairnesslist;
	//

	private Map<Integer, Integer> mCoveredUsersMap;
	private List<Integer> mValidUserList;
	private List<Integer> mSelectedServerList;

	// 2021
	private List<Integer> bubbleValidUserList;
	private List<Integer> bubbleSelectServerList;

	public NearFCModel(int serversNumber, double fairness_index, int[][] adjacencyMatrix, int[][] userCovered,
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

	public void runNearFC() {

		for (int i = 0; i < mUsers.size(); i++)
			mBenefitList.add(0);

		mAllBenefits = 0;

		while (mValidUserList.size() < mUsers.size()) {
//			updateNewUsersCoveredByServerList();
			bubbleServerWithMaximumIncreasingUncoveredUsers();
		}
		
		// 2021
					// ÕâÀï¼ÆËãÒ»ÏÂÈ«²¿µÄcostºÍÈ«²¿µÄbenefitºÍÈ«²¿coverÓÃ»§µÄÊýÁ¿
					mCost = mSelectedServerList.size();
					mAllBenefits = calculateBenefits();// È«²¿ÓÃ»§benefitÖ®ºÍ
					mAllUsers = mValidUserList.size(); // ¼ÆËãfairness degreeµÄ·ÖÄ¸
					mAllBenefit_square = calculate_single_Benefits_square(); // fairness degreeµÄ·ÖÄ¸

					// ÏÖÔÚ¼ÆËãfairness degree ÕâÀïfairness_degreeºÍ m fairness degreeÒ»Ñù°É...
					// fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() *
					// mAllBenefit_square);
					mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);
//					//20210816
//					mAllBenefits = mAllBenefits/mValidUserList.size();
					// ¼ÆËãfairness efficiency
					// mfairness_efficiency = fairness_degree / mCost;
					mfairness_efficiency = mfairnessdegree / mCost;

		mAllUsers = mValidUserList.size(); // ¼ÆËãfairness degreeµÄ·ÖÄ¸
	}


	private int bubbleServerWithMaximumIncreasingUncoveredUsers() {
		int id = -1;
		double cu = 0.00;

		for (EdgeServer server : mServers) {
			if (mSelectedServerList.contains(server.id))
				continue;

			double newcu = server.getnewfairnessWithinOneHopNotInTheListAndDirectCovered(mValidUserList, mSelectedServerList,
					server.id, mUserBenefits, mUsers);
			if (newcu > cu)
			{
				cu = newcu; // Ò»ÌøÄÚÐÂÔö¼ÓµÄÓÃ»§
				// dcu = server.getDirectlyCoveredUsersNotInTheList(mValidUserList).size(); //
				id = server.id;
			}
		}
		mSelectedServerList.add(id);
		//这里是选好server 之后 把多覆盖到的用户添加到valid用户的列表里
				for (User user : mUsers) {
					if (user.nearEdgeServers.contains(id) && !mValidUserList.contains(user.id)) {
						mValidUserList.add(user.id);
					}
				}

				//这里是把选择到的server 的邻接server能cover 到的新的用户 也添加到选择道德用户列表里
				for (int i = 0; i < mServersNumber; i++) {
					if (mAdjacencyMatrix[i][id] == 1 || mAdjacencyMatrix[id][i] == 1) {
						// mConnectionsMap.remove(i);
						for (User user : mUsers) {
							if (user.nearEdgeServers.contains(i) && !mValidUserList.contains(user.id)) {
								mValidUserList.add(user.id);
							}
						}
					}
				}
		
		return id;

	}

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

		// Ã¿Ò»¸öbenefit¶¼ÊÇÒ»¸öÓÃ»§µÄbenefit£¬È»ºóbenefitsÊÇÈ«²¿ÓÃ»§µÄbenefitÏà¼Ó
		for (int benefit : mBenefitList)
			benefits = benefits + benefit;

		return benefits;
	}

	// ½ÓÏÂÀ´Ó¦¸Ã¼ÆËãÃ¿¸öÓÃ»§µÄbenefit£¬È»ºó°Ñbenefit´æÔÚÊý×éÀï£¬×îºóÑ­»·Ã¿¸öÊý×ÖµÄÆ½·½ºÍÏà¼Ó×÷Îªfairness¼ÆËãµÄ·ÖÄ¸
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

		// ÕâÀï¼ÆËãbenefitsÊÇÖ¸Ã¿Ò»¸öÓÃ»§µÄbenefitµÄÆ½·½ºÍ,ÕâÑùµÄ»°¿ÉÒÔ·µ»Øbenefits
		for (int benefit : mBenefitList)
			benefits = benefits + (benefit * benefit);

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
		//return mbenefitefficiency = (mAllBenefits/mValidUserList.size())/mCost;
		return mbenefitefficiency = mAllBenefits/mCost;
	}
}

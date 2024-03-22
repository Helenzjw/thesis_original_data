package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectives.EdgeServer;
import objectives.User;

public class NearOptimalModel {

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

	public NearOptimalModel(int serversNumber, double fairness_index, int[][] adjacencyMatrix, int[][] userCovered,
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

	public void runNearOptimal() {

		for (int i = 0; i < mUsers.size(); i++)
			mBenefitList.add(0);

		mAllBenefits = 0;

		while (mValidUserList.size() < mUsers.size()) {
//			updateNewUsersCoveredByServerList();
			bubbleServerWithMaximumIncreasingUncoveredUsers();

			// ÕâÀïÑ¡ÔñÁË¼ÆËã³öfairness efficiency×î´óµÄÄÇ¸öserver µÄ id
			
			//
			//mValidUserList.addAll(mServers.get(selected).getCoveredUsersWithinOneHopNotInTheList(mValidUserList));
			//mValidUserList.addAll(mServers.get(selected).getDirectlyCoveredUsersNotInTheList(mValidUserList));

			
			//
			// for(int i = 0; i < mServers.size();i++) {
			// if (mServers.get(i).id == selected) {
			// mServers.remove(i);
			// i--;
			// }
			// }

//			System.out.println(mfairnessdegree + " --- " + mfairness_efficiency);

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
		//20210223
	/*	for (int i : mSelectedServerList)
			System.out.print(i + ", ");

		System.out.println();
	 */
		// for (int serverId : mSelectedServerList) {
		// for (int userId : mServers.get(serverId).servedUsers) {
		// mBenefitList.set(userId, 2);
		// }
		// for (int userId : mServers.get(serverId).usersInOneHop) {
		// if (mBenefitList.get(userId) < 1) mBenefitList.set(userId, 1);
		// }
		// }

		// System.out.print("Benefits list: ");
		// for (int benefits : mBenefitList) {
		// mAllBenefits = mAllBenefits + benefits;
		// //System.out.print(benefits + " ");
		// }
		// System.out.println();

		// mCost = mSelectedServerList.size();
		// mBenefitPerReplica = mAllBenefits / mCost;
		mAllUsers = mValidUserList.size(); // ¼ÆËãfairness degreeµÄ·ÖÄ¸
	}

//	private boolean hasIncreaseInFairnessDegree() {
//		
//		//TODO: calculate fairness degree
//		double currentFDegree = 0;
//		
//		for (EdgeServer server : mServers) {
//			if (mSelectedServerList.contains(server.id)) continue;
//			
//			mSelectedServerList.add(server.id);
//			//TODO: calculate fairness degree
//			double newFDegree = 0;
//			
//			if (newFDegree > currentFDegree) return true;
//		}
//		
//		return false;
//	}

	private int bubbleServerWithMaximumIncreasingUncoveredUsers() {
		int id = -1;
		double cu = 0.00;
		/*
		 * double dcu = 0.00; double alluserbenefit = 0; double alluserbenefitsquare =
		 * 0; double allselectedservercost = 0; double fairness = 0; double
		 * fairnessefficiency = 0; double bubblefe = 0; List<Integer>
		 * bubbleValidUserList = mValidUserList; bubbleSelectServerList =
		 * mSelectedServerList;
		 */

		// mServerfairnesslist = new ArrayList<>();
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

//	if (server.getCoveredUsersWithinOneHopNotInTheList(mValidUserList).size() > cu ||
//			(server.getCoveredUsersWithinOneHopNotInTheList(mValidUserList).size() == cu
//					&& server.getDirectlyCoveredUsersNotInTheList(mValidUserList).size() > dcu)
//			) {
//		cu = server.getCoveredUsersWithinOneHopNotInTheList(mValidUserList).size();
//		dcu = server.getDirectlyCoveredUsersNotInTheList(mValidUserList).size();
//		id = server.id;
//	}
// }

//		mAllBenefits = 0;

// mSelectedServerList.add(server);
// mValidUserList.addAll(mServers.get(server).getCoveredUsersWithinOneHopNotInTheList(mValidUserList));
// 2021
// ÕâÀï¼ÆËãÒ»ÏÂÈ«²¿µÄcostºÍÈ«²¿µÄbenefitºÍÈ«²¿coverÓÃ»§µÄÊýÁ¿

// ¶ÔÓÚÃ¿¸öÔÚserver listÀïµÄserver£¬Òª¼ÆËãÑ¡ÔñÕâ¸öserverÖ®ºó£¬¶ÔÓÚËùÓÐm-validuserlistÀï
// Ã»ÓÐ°üÀ¨µÄuser¼ÓÈëµ½ÐÂ½¨µÄuserlistÀï¡£ È»ºó·µ»ØÕâ¸öuser list¡£
// ÐÂµÄnear optimal·½·¨°ÑÓÃ»§·ÖÎªÁ½×é Ò»×éÊÇÐÂÔö¼ÓµÄÒ»ÌøµÄÓÃ»§£¬Ò»×éÊÇ0ÌøµÄÓÃ»§
// edge serverÀïÃæº¯Êý·µ»ØµÄÓ¦¸ÃÖ±½ÓÊÇ Ñ¡ÔñÄ³¸öserverÖ®ºó£¬ µ÷ÓÃµÄº¯ÊýÀïÒª¼ÆËãÈ«²¿µÄcoverµÄÓÃ»§µÄlist£¬È»ºó¼ÆËãbenefit
// list£¨ÐÂ½¨Ò»¸ö£©£¬µ÷ÓÃ¶îÍâÁ½¸öº¯Êý·Ö±ð¼ÆËãbenefitµÄºÍ ºÍ benefitÆ½·½µÄºÍ¡£
// ·µ»ØµÄÖµ ÔÚ º¯ÊýÀï¼ÆËãÑ¡ÔñÕâ¸öserverÖ®ºóµÄfairness
// µÄÖµ£¬ÕâÑùµÄ»°¾Í²»ÓÃ°ÑÓÃ»§·ÖÎªÁ½²¿·ÖÁË....·µ»ØµÄÊ±ºò¡£Ö»ÐèÒªÑ¡ÔñÁËÕâ¸öserverÖ®ºóµÄserver listÊµÏÖµÄfairness×î´ó£¬¾ÍÓÃÕâ¸öid
// ÕâÑùÐèÒª´«µÝÁ½¸ölist£¬Ò»¸öÊÇmValidUserList£¬Ò»¸öÊÇmSelectedServerList
//		mCost = mSelectedServerList.size();
//		mAllBenefits = calculateBenefits();//È«²¿ÓÃ»§benefitÖ®ºÍ
//		mAllUsers = mValidUserList.size(); //¼ÆËãfairness degreeµÄ·ÖÄ¸
//		mAllBenefit_square = calculate_single_Benefits_square(); //fairness degreeµÄ·ÖÄ¸
//		
//		//ÏÖÔÚ¼ÆËãfairness degree    ÕâÀïfairness_degreeºÍ m fairness degreeÒ»Ñù°É...
//		//fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);		
//		mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);	
//		
//		//¼ÆËãfairness efficiency
//		//mfairness_efficiency = fairness_degree / mCost;		
//		mfairness_efficiency = mfairnessdegree / mCost;
//		//	

//Õâ¸öº¯Êý¼ÆËãÁËÈ«²¿ÓÃ»§È«²¿µÄbenefitµÄºÍ È»ºó·µ»ØÁËÈ«²¿µÄbenefit

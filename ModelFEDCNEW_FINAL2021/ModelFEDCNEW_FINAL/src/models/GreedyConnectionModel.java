package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectives.User;

public class GreedyConnectionModel {

	private int mServersNumber;
	private int mBudget;
	//2021
	private double mCost;
	private double mbenefitefficiency;
	private double mAllBenefits;
	private double mAllBenefit_square;
	private int[][] mUserBenefits;
	
	private double mFairness_index;
	private double mfairnessdegree; //make the original value = 0
	private double mfairness_efficiency;
	private List<Integer> mBenefitList;
	//
	private int[][] mAdjacencyMatrix;
	private int[][] mUserCovered;
	private List<User> mUsers;
	
	private double mAllUsers;

	private List<Integer> mValidUserList;

	private Map<Integer, Integer> mConnectionsMap;
	private List<Integer> mSelectedServerList;

	public GreedyConnectionModel(int serversNumber, double fairness_index, int[][] adjacencyMatrix, int[][] userCovered, int[][] userBenefits, List<User> users) {
		mServersNumber = serversNumber;
		mAdjacencyMatrix = adjacencyMatrix;
		mUserCovered = userCovered;
		mUsers = users;
		mValidUserList = new ArrayList<>();
		mSelectedServerList = new ArrayList<>();
		mConnectionsMap = new HashMap<>();
		
		//2021
		mFairness_index = fairness_index;
		mUserBenefits = userBenefits;
		mBenefitList = new ArrayList<>();
		//
	}

	public void runGreedyConnection() {
		for (int i = 0; i < mServersNumber; i++) {
			int connection = 0;
			for (int c : mAdjacencyMatrix[i])
				connection = connection + c;
			mConnectionsMap.put(i, connection);
		}

		//2021
		//�����fairness��ʼ��ֵΪ�� ��privateΪ����������..Ϊʲôѭ���߲�������
		mfairnessdegree = 0;
		//
		
		/*while (mSelectedServerList.size() <= mBudget) {
			selectServerWithMaximumConnections();
		}
		*/
		
		while (mValidUserList.size() < mUsers.size()) {
			selectServerWithMaximumConnections();
			
			
			//
		}	
		//2021
		//�������һ��ȫ����cost��ȫ����benefit��ȫ��cover�û�������
		mCost = mSelectedServerList.size();
		mAllBenefits = calculateBenefits();//ȫ���û�benefit֮��
		mAllUsers = mValidUserList.size(); //����fairness degree�ķ�ĸ
		mAllBenefit_square = calculate_single_Benefits_square(); //fairness degree�ķ�ĸ
		
		//���ڼ���fairness degree    ����fairness_degree�� m fairness degreeһ����...
		//fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);		
		mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);	
		
		//����fairness efficiency
		//mfairness_efficiency = fairness_degree / mCost;		
		mfairness_efficiency = mfairnessdegree / mCost;

		//mAllUsers = mValidUserList.size();
	}
	/*
	private double calculateUsers() {

		mValidUserList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mValidUserList.add(0);
		}

		double validusers = 0;

		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int validuser = 1;
				if (mSelectedServerList.contains(server))
					validuser = mUserCovered[server][user.id];
				if (mValidUserList.get(user.id) < validuser)
					mValidUserList.set(user.id, validuser);
			}
		}

		for (int validuser : mValidUserList)
			validusers = validusers + validuser;

		return validusers;
	}*/

	private int selectServerWithMaximumConnections() { 
		int maximumConnections = 0;
		int serverWithMaximumConnections = -1;
		for (int server : mConnectionsMap.keySet()) {
			int connections = mConnectionsMap.get(server);
			if (connections > maximumConnections) {
				maximumConnections = connections;
				serverWithMaximumConnections = server;
			}
		}
		mConnectionsMap.remove(serverWithMaximumConnections);
		mSelectedServerList.add(serverWithMaximumConnections);
		
		for (User user : mUsers) {
			if (user.nearEdgeServers.contains(serverWithMaximumConnections) && !mValidUserList.contains(user.id)) {
				mValidUserList.add(user.id);
			}
		}

		for (int i = 0; i < mServersNumber; i++) {
			if (mAdjacencyMatrix[i][serverWithMaximumConnections] == 1 || mAdjacencyMatrix[serverWithMaximumConnections][i] == 1) {
				// mConnectionsMap.remove(i);
				for (User user : mUsers) {
					if (user.nearEdgeServers.contains(i) && !mValidUserList.contains(user.id)) {
						mValidUserList.add(user.id);
					}
				}
			}
		}

		return serverWithMaximumConnections;
	}

	//2021
	//�������������ȫ���û�ȫ����benefit�ĺ� Ȼ�󷵻���ȫ����benefit
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
		
		//ÿһ��benefit����һ���û���benefit��Ȼ��benefits��ȫ���û���benefit���
		for (int benefit : mBenefitList) benefits = benefits + benefit;
		
		return benefits;
	}
	
	//������Ӧ�ü���ÿ���û���benefit��Ȼ���benefit������������ѭ��ÿ�����ֵ�ƽ���������Ϊfairness����ķ�ĸ
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
		
		//�������benefits��ָÿһ���û���benefit��ƽ����,�����Ļ����Է���benefits
		for (int benefit : mBenefitList) benefits = benefits + (benefit * benefit);
		
		return benefits;
	}
	//
	
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

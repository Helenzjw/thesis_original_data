package objectives;

import java.util.ArrayList;
import java.util.List;

public class EdgeServer {
	public double lat;
	public double lng;
	public double radius;
	public int id;
	public List<Integer> servedUsers = new ArrayList<>();
	public double fromArea;
	public double toArea;
	public List<Integer> usersInOneHop = new ArrayList<>();
	private List<Integer> benefitlist = new ArrayList<>(); //�û���id��benefit ��һ��list---ʲô�ã���
	private int[][] mUserserver_Benefits;//�û���server id֮���benefit�ļ���Ķ�ά����
	private List<User> mAllMobileUser;
	private List<Integer> mSelectedServerL;
	
	private double alluserbenefit;
	private double alluserbenefitsquare;
	private double allselectedservercost;
	private double fairness;
	private double fairnessefficiency;
	private double benefitefficiency;
	
	public List<Integer> getCoveredUsersWithinOneHopNotInTheList(List<Integer> list) {
		List<Integer> users = new ArrayList<>();
		
		for (int id : usersInOneHop) {
			if (!list.contains(id)) users.add(id);//�����list��validuser list
		}
		
		return users;
		//���������һ�����ܷ�����µ��û��������������Ļ��������¼����ÿ���û���benefit����1.
	}
	
	public List<Integer> getDirectlyCoveredUsersNotInTheList(List<Integer> list) {
		List<Integer> users = new ArrayList<>();
		
		for (int id : servedUsers) {
			if (!list.contains(id)) users.add(id);
		}
		
		return users;
		//�������ֱ�Ӹ��ǵ��µ��û��������������Ļ��������¼����ÿ���û���benefit����2.
	}
	
	public double getnewfairnessWithinOneHopNotInTheListAndDirectCovered(List<Integer> validusers, List<Integer> servers, int serverID, 
		int[][]userserverbenefit, List<User> mAllUsers) {
		List<Integer> users = new ArrayList<>();
		List<Integer> coveredusers = new ArrayList<>();
		coveredusers = validusers;
		//mAllUser.clear();
		//mSelectedServerL.clear();
		mUserserver_Benefits = userserverbenefit;
		mAllMobileUser = mAllUsers; //ȫ�����û� �����ǻ���û�����ǣ�Ϊ�˼���fairness
		mSelectedServerL = servers;
		
		for (int id : usersInOneHop) {
			if (!coveredusers.contains(id)) users.add(id);
		}
		
		for (int id : servedUsers) {
			if (!coveredusers.contains(id)) users.add(id);
		}
	
		for (int id : coveredusers) {
			if (!users.contains(id)) users.add(id); //�����е�valid user ȫ�����뵽user��list�����users ���list����ȫ��cover���û�
		}
		
		mSelectedServerL.add(serverID); //����ѱ���ѭ���е�server���뵽select server list �Ȼ�󷽱�����������benefit����һ��ѭ����ʱ��select server list �������ǲ�Ӱ���
		alluserbenefit = calculateBenefits();
		alluserbenefitsquare = calculate_single_Benefits_square();
		allselectedservercost = mSelectedServerL.size();
		fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
		fairnessefficiency = fairness / allselectedservercost;
		
		mSelectedServerL.remove(mSelectedServerL.size()-1);
		//return fairnessefficiency;
		return fairness;
		//���������һ�����ܷ�����µ��û��������������Ļ��������¼����ÿ���û���benefit����1.
	}
	
	//20210908
	public double getnewbenefitWithinOneHopNotInTheListAndDirectCovered(List<Integer> validusers, List<Integer> servers, int serverID, 
			int[][]userserverbenefit, List<User> mAllUsers) {
			List<Integer> users = new ArrayList<>();
			List<Integer> coveredusers = new ArrayList<>();
			coveredusers = validusers;
			//mAllUser.clear();
			//mSelectedServerL.clear();
			mUserserver_Benefits = userserverbenefit;
			mAllMobileUser = mAllUsers; //ȫ�����û� �����ǻ���û�����ǣ�Ϊ�˼���fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //�����е�valid user ȫ�����뵽user��list�����users ���list����ȫ��cover���û�
			}
			
			mSelectedServerL.add(serverID); //����ѱ���ѭ���е�server���뵽select server list �Ȼ�󷽱�����������benefit����һ��ѭ����ʱ��select server list �������ǲ�Ӱ���
			alluserbenefit = calculateBenefits();
			alluserbenefitsquare = calculate_single_Benefits_square();
			allselectedservercost = mSelectedServerL.size();
			fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
			fairnessefficiency = fairness / allselectedservercost;
			
			mSelectedServerL.remove(mSelectedServerL.size()-1);
			//return fairnessefficiency;
			return alluserbenefit;
			//���������һ�����ܷ�����µ��û��������������Ļ��������¼����ÿ���û���benefit����1.
		}
	
	//20210908
	public double getnewbenefitcostratioWithinOneHopNotInTheListAndDirectCovered(List<Integer> validusers, List<Integer> servers, int serverID, 
			int[][]userserverbenefit, List<User> mAllUsers, int cost) {
			List<Integer> users = new ArrayList<>();
			List<Integer> coveredusers = new ArrayList<>();
			coveredusers = validusers;
			//mAllUser.clear();
			//mSelectedServerL.clear();
			mUserserver_Benefits = userserverbenefit;
			mAllMobileUser = mAllUsers; //ȫ�����û� �����ǻ���û�����ǣ�Ϊ�˼���fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //�����е�valid user ȫ�����뵽user��list�����users ���list����ȫ��cover���û�
			}
			
			mSelectedServerL.add(serverID); //����ѱ���ѭ���е�server���뵽select server list �Ȼ�󷽱�����������benefit����һ��ѭ����ʱ��select server list �������ǲ�Ӱ���
			alluserbenefit = calculateBenefits();
			alluserbenefitsquare = calculate_single_Benefits_square();
			allselectedservercost = mSelectedServerL.size();
			fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
			fairnessefficiency = fairness / allselectedservercost;
			benefitefficiency = alluserbenefit / allselectedservercost;
			mSelectedServerL.remove(mSelectedServerL.size()-1);
			//return fairnessefficiency;
			return benefitefficiency;
			//���������һ�����ܷ�����µ��û��������������Ļ��������¼����ÿ���û���benefit����1.
		}
	
	//20210908
	public double getnewfairnesscostratioWithinOneHopNotInTheListAndDirectCovered(List<Integer> validusers, List<Integer> servers, int serverID, 
			int[][]userserverbenefit, List<User> mAllUsers, int cost) {
			List<Integer> users = new ArrayList<>();
			List<Integer> coveredusers = new ArrayList<>();
			coveredusers = validusers;
			//mAllUser.clear();
			//mSelectedServerL.clear();
			mUserserver_Benefits = userserverbenefit;
			mAllMobileUser = mAllUsers; //ȫ�����û� �����ǻ���û�����ǣ�Ϊ�˼���fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //�����е�valid user ȫ�����뵽user��list�����users ���list����ȫ��cover���û�
			}
			
			mSelectedServerL.add(serverID); //����ѱ���ѭ���е�server���뵽select server list �Ȼ�󷽱�����������benefit����һ��ѭ����ʱ��select server list �������ǲ�Ӱ���
			alluserbenefit = calculateBenefits();
			alluserbenefitsquare = calculate_single_Benefits_square();
			//allselectedservercost = mSelectedServerL.size();
			allselectedservercost = cost + 1;
			fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
			fairnessefficiency = fairness / allselectedservercost;
			benefitefficiency = alluserbenefit / allselectedservercost;
			mSelectedServerL.remove(mSelectedServerL.size()-1);
			//return fairnessefficiency;
			return fairnessefficiency;
			//���������һ�����ܷ�����µ��û��������������Ļ��������¼����ÿ���û���benefit����1.
		}
	
	
	private double calculateBenefits() {
		//�����benefit list��ֵ ����Ϊȫ���û��ĳ���
		benefitlist.clear();
		for (int i = 0; i < mAllMobileUser.size(); i++) {
			benefitlist.add(0);
		}

		double benefits = 0;
		
		for (User user : mAllMobileUser) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerL.contains(server)) benefit = mUserserver_Benefits[server][user.id];
				if (benefitlist.get(user.id) < benefit) benefitlist.set(user.id, benefit);
			}
		}
		
		//ÿһ��benefit����һ���û���benefit��Ȼ��benefits��ȫ���û���benefit���
		for (int benefit : benefitlist) benefits = benefits + benefit;
		
		return benefits;
	}
	
	//������Ӧ�ü���ÿ���û���benefit��Ȼ���benefit������������ѭ��ÿ�����ֵ�ƽ���������Ϊfairness����ķ�ĸ
	private double calculate_single_Benefits_square() {
		benefitlist.clear();
		for (int i = 0; i < mAllMobileUser.size(); i++) {
			benefitlist.add(0);
		}

		double benefits = 0;
		
		for (User user : mAllMobileUser) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerL.contains(server)) benefit = mUserserver_Benefits[server][user.id];
				if (benefitlist.get(user.id) < benefit) benefitlist.set(user.id, benefit);
			}
		}
		
		//�������benefits��ָÿһ���û���benefit��ƽ����,�����Ļ����Է���benefits
		for (int benefit : benefitlist) benefits = benefits + (benefit * benefit);
		
		return benefits;
	}
	
/*	public double calculatefairnessefficiencybyselectthisserver(double a) {
		double fairnessefficiency;
		
		for (int id : servedUsers) {
			if (!list.contains(id)) users.add(id);
		}
		return fairnessefficiency;
	}
	*/
}

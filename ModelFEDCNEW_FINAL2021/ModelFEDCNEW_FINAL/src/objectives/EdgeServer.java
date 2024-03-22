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
	private List<Integer> benefitlist = new ArrayList<>(); //用户的id和benefit 的一个list---什么用？？
	private int[][] mUserserver_Benefits;//用户和server id之间的benefit的计算的二维数组
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
			if (!list.contains(id)) users.add(id);//这里的list是validuser list
		}
		
		return users;
		//这里加入了一条内能服务的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是1.
	}
	
	public List<Integer> getDirectlyCoveredUsersNotInTheList(List<Integer> list) {
		List<Integer> users = new ArrayList<>();
		
		for (int id : servedUsers) {
			if (!list.contains(id)) users.add(id);
		}
		
		return users;
		//这里加入直接覆盖的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是2.
	}
	
	public double getnewfairnessWithinOneHopNotInTheListAndDirectCovered(List<Integer> validusers, List<Integer> servers, int serverID, 
		int[][]userserverbenefit, List<User> mAllUsers) {
		List<Integer> users = new ArrayList<>();
		List<Integer> coveredusers = new ArrayList<>();
		coveredusers = validusers;
		//mAllUser.clear();
		//mSelectedServerL.clear();
		mUserserver_Benefits = userserverbenefit;
		mAllMobileUser = mAllUsers; //全部的用户 被覆盖或者没被覆盖，为了计算fairness
		mSelectedServerL = servers;
		
		for (int id : usersInOneHop) {
			if (!coveredusers.contains(id)) users.add(id);
		}
		
		for (int id : servedUsers) {
			if (!coveredusers.contains(id)) users.add(id);
		}
	
		for (int id : coveredusers) {
			if (!users.contains(id)) users.add(id); //把已有的valid user 全部加入到user的list里，这样users 这个list就是全部cover的用户
		}
		
		mSelectedServerL.add(serverID); //这里把本次循环中的server加入到select server list 里，然后方便计算接下来的benefit。下一次循环的时候select server list 传过来是不影响的
		alluserbenefit = calculateBenefits();
		alluserbenefitsquare = calculate_single_Benefits_square();
		allselectedservercost = mSelectedServerL.size();
		fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
		fairnessefficiency = fairness / allselectedservercost;
		
		mSelectedServerL.remove(mSelectedServerL.size()-1);
		//return fairnessefficiency;
		return fairness;
		//这里加入了一条内能服务的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是1.
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
			mAllMobileUser = mAllUsers; //全部的用户 被覆盖或者没被覆盖，为了计算fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //把已有的valid user 全部加入到user的list里，这样users 这个list就是全部cover的用户
			}
			
			mSelectedServerL.add(serverID); //这里把本次循环中的server加入到select server list 里，然后方便计算接下来的benefit。下一次循环的时候select server list 传过来是不影响的
			alluserbenefit = calculateBenefits();
			alluserbenefitsquare = calculate_single_Benefits_square();
			allselectedservercost = mSelectedServerL.size();
			fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
			fairnessefficiency = fairness / allselectedservercost;
			
			mSelectedServerL.remove(mSelectedServerL.size()-1);
			//return fairnessefficiency;
			return alluserbenefit;
			//这里加入了一条内能服务的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是1.
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
			mAllMobileUser = mAllUsers; //全部的用户 被覆盖或者没被覆盖，为了计算fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //把已有的valid user 全部加入到user的list里，这样users 这个list就是全部cover的用户
			}
			
			mSelectedServerL.add(serverID); //这里把本次循环中的server加入到select server list 里，然后方便计算接下来的benefit。下一次循环的时候select server list 传过来是不影响的
			alluserbenefit = calculateBenefits();
			alluserbenefitsquare = calculate_single_Benefits_square();
			allselectedservercost = mSelectedServerL.size();
			fairness = (alluserbenefit * alluserbenefit) / (mAllMobileUser.size() * alluserbenefitsquare);
			fairnessefficiency = fairness / allselectedservercost;
			benefitefficiency = alluserbenefit / allselectedservercost;
			mSelectedServerL.remove(mSelectedServerL.size()-1);
			//return fairnessefficiency;
			return benefitefficiency;
			//这里加入了一条内能服务的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是1.
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
			mAllMobileUser = mAllUsers; //全部的用户 被覆盖或者没被覆盖，为了计算fairness
			mSelectedServerL = servers;
			
			for (int id : usersInOneHop) {
				if (!coveredusers.contains(id)) users.add(id);
			}
			
			for (int id : servedUsers) {
				if (!coveredusers.contains(id)) users.add(id);
			}
		
			for (int id : coveredusers) {
				if (!users.contains(id)) users.add(id); //把已有的valid user 全部加入到user的list里，这样users 这个list就是全部cover的用户
			}
			
			mSelectedServerL.add(serverID); //这里把本次循环中的server加入到select server list 里，然后方便计算接下来的benefit。下一次循环的时候select server list 传过来是不影响的
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
			//这里加入了一条内能服务的新的用户的数量，这样的话对这里新加入的每个用户的benefit就是1.
		}
	
	
	private double calculateBenefits() {
		//给这个benefit list赋值 长度为全部用户的长度
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
		
		//每一个benefit都是一个用户的benefit，然后benefits是全部用户的benefit相加
		for (int benefit : benefitlist) benefits = benefits + benefit;
		
		return benefits;
	}
	
	//接下来应该计算每个用户的benefit，然后把benefit存在数组里，最后循环每个数字的平方和相加作为fairness计算的分母
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
		
		//这里计算benefits是指每一个用户的benefit的平方和,这样的话可以返回benefits
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

package objectives;

import java.util.ArrayList;
import java.util.List;

public class User {
	public double location;
	public double lat;
	public double lng;
	public int id;
	public List<Integer> nearEdgeServers = new ArrayList<>();
	public List<Integer> getnearedgeserverlistintwohops(List<Integer> nearEdgeServers2, int server, int[][] adjacencyMatrix) {
		//nearedgeserver2是某个用户j的邻接server list； server是循环中某个server i的id  
		//通过循环判断如果这个server i和我们这个用户j的邻接server 中任何一个server 由连接，就可以把这个server 加入到两条的server集合中；然后返回
		// TODO Auto-generated method stub
        List<Integer> newedgeservers = new ArrayList<>();
		
		for (int s : nearEdgeServers2) {
			if (adjacencyMatrix[s][server] == 1);{
				newedgeservers.add(server);
			}
		}
		return newedgeservers;
		//return null;
	}
}

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
		//nearedgeserver2��ĳ���û�j���ڽ�server list�� server��ѭ����ĳ��server i��id  
		//ͨ��ѭ���ж�������server i����������û�j���ڽ�server ���κ�һ��server �����ӣ��Ϳ��԰����server ���뵽������server�����У�Ȼ�󷵻�
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

package method;

import java.util.ArrayList;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloMultiCriterionExpr;
import ilog.concert.IloNumExpr;
import ilog.cp.IloCP;
import model.*;

public class OptimalMethod {
	public static Result GetBest(ArrayList<PhysicalMachine> servers,ArrayList<EdgeUser> users) {
		Result result = new Result();
		double timeStart = System.currentTimeMillis();
		
		System.out.println("Optimal Start: userNum: "+ users.size() +", serverNum: "+ servers.size());
		
		//user-server covered matrix
		int[][] isCovered = new int[users.size()][servers.size()];
		for(int i=0;i<users.size();i++) {
			for(int j=0;j<users.get(i).getCoveredserver().size();j++) {
				isCovered[i][users.get(i).getCoveredserver().get(j)] = 1;
			}
		}
		
		//user resources request matrix
		double[][] request = new double[ConstNum.dResource][users.size()];
		for(int i=0;i<users.size();i++) {
			for(int j=0;j<ConstNum.dResource;j++) {
				request[j][i] = users.get(i).getResource()[j];
			}
		}
		
		//server maximum capacities
		double[][] capacities = new double[ConstNum.dResource][servers.size()];
		for(int i=0;i<servers.size();i++) {
			for(int j=0;j<ConstNum.dResource;j++) {
				capacities[j][i] = servers.get(i).getCapacity()[j];
			}
		}
		
		//server cost
		double[] serverCost = new double[servers.size()];
		for(int i=0;i<servers.size();i++) {
			serverCost[i]=servers.get(i).getEnergyCost();
		}
		
		//CP part
		try {
			
			  IloCP cp = new IloCP();
			  
			  //variables
			  ArrayList<IloIntVar[]> selection = new ArrayList<>();
			  for(int i=0;i<servers.size();i++) {
				  IloIntVar[] e = cp.intVarArray(users.size(), 0, 1);
				  selection.add(e);
			  }
			 
			  //objective
			  
			  //maximize number of allocated users
			  IloNumExpr[] nUserExprs = new IloNumExpr[servers.size()];
			  
			  for(int i=0;i<servers.size();i++) {
				  nUserExprs[i]= cp.sum(selection.get(i));  
			  }
			  IloNumExpr totalNUserExpr = cp.sum(nUserExprs);
			 
			  //minimize the server cost
			  IloNumExpr[] serverCostExprs = new IloNumExpr[servers.size()];
			  for(int i=0;i<servers.size();i++) {
				  serverCostExprs[i]= cp.prod(serverCost[i], nUserExprs[i]); 
			  }
			  IloNumExpr totalEnergyCostExpr = cp.sum(serverCostExprs);
			  
			  //constraints
			  for(int i=0;i<servers.size();i++) { // capacities constraint
				  for(int j=0;j<ConstNum.dResource;j++) {
					 IloNumExpr usedResourcesExpr = cp.scalProd(request[j],selection.get(i));
					  cp.addLe(usedResourcesExpr, capacities[j][i]);
				  }
			  }
			  
			  for(int i=0;i<users.size();i++) { // one user can be allocated only once
				  IloNumExpr userDecisioNumExpr = selection.get(0)[i];
				  for(int j=1;j<servers.size();j++) {
					  userDecisioNumExpr = cp.sum(userDecisioNumExpr, selection.get(j)[i]);
				  }
				  cp.addLe(userDecisioNumExpr, 1);
			  }

			  IloNumExpr[] exprs = new IloNumExpr[2];
			  exprs[0] = totalNUserExpr;
			  exprs[1] = cp.prod(-1.0, totalEnergyCostExpr);
			  IloMultiCriterionExpr moExpr = cp.staticLex(exprs);

			  // cp.add(cp.maximize(moExpr));
			   cp.add(cp.maximize(exprs[0]));

//			  IloNumExpr[] exprs = new IloNumExpr[2];
//			  exprs[0] = totalNUserExpr;
//			  exprs[1] = cp.prod(-1.0, totalEnergyCostExpr);
//			  IloMultiCriterionExpr moExpr = cp.staticLex(exprs);
//
//			  cp.add(cp.maximize(exprs[0]));
			  
			  cp.setOut(null);
			
			  //Solve
			  //cplex.exportModel("kSubGraphModel.lp");
			  if (cp.solve()) {
				  ArrayList<Decision> decisionArrayList = new ArrayList<Decision>();
				  for(int i=0;i<users.size();i++) {
					  decisionArrayList.add(new Decision(-1,-1)); //initialization
				  }
				  
				  for(int i=0;i<servers.size();i++) { //server
					  for(int j=0;j<users.size();j++) { //user
						  if((int)cp.getValue(selection.get(i)[j])!=0) {
							  decisionArrayList.set(j, new Decision(i,i));
						  }
					  }
				  }
				 result.setDecisions(decisionArrayList);

				//calculate the nUser of each server
					for(int i=0;i<servers.size();i++) {
						int nUser = 0;
						for(int j=0;j<users.size();j++) {
							if(result.getDecisions().get(j).getServer()==i) {
								nUser++;
							}
						}
						servers.get(i).setnUsers(nUser);
					}
					
					//update the overall energy cost, the number of allocated users and the time consumption
					//the number of allocated users
					double totalUsers = Result.getNumofAlloUsers(result.getDecisions());
					result.setnAlloUsers(totalUsers);
					//the overallComponentHelper energy cost
					double overallEnergyCost = Result.getOverallEnergyCost(servers);
					result.setEnergyCost(overallEnergyCost);
					//result.setEnergyCost(50);
					//calculate the time consumption
					double timeEnd = System.currentTimeMillis();
					result.setTimeConsumption(timeEnd-timeStart);
					//the overall system cost of each user
					//double overallSystemCost = Result.getOverallSystemCost(servers, users, result);
					double overallSystemCost = Result.getOverallSystemCostfeua(servers, users, result);
					result.setSystemCost(overallSystemCost);
					//the number of used edge servers
					double nServer = Result.getNUsedServers(servers);
					result.setnUsedServers(nServer);
		             
		       }else {
		        	  System.out.println("No solution");
		       }
			  cp.end();    
		
		} catch (IloException exc) {
			// TODO: handle exception
			exc.printStackTrace();
		}
		
		
		return result;
	}
	
	

}

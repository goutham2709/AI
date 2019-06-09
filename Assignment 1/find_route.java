/**@author Goutham Munagala
*UTA ID: 1001565060
*/

import java.io.*;
import java.util.*;

public class find_route {
	public static void main (String[] args){

		String input_file = args[0]; //inputs
		String start = args [1]; //start state
		String goal = args[2]; //goal state
		ArrayList<String> list = new ArrayList<String>(); //array list of strings
		try{ //try to read from a input text file
			FileReader readfile = new FileReader(input_file);
			BufferedReader bufferedReader = new BufferedReader(readfile);
			String line; //read strings line by line
			while((line = bufferedReader.readLine().toString()).equals("END OF INPUT") == false){
				list.add(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to find input file");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		node_properties parent_node = new node_properties(null,0,0,start); //node propoerties
		ArrayList<node_properties> fringe_array = new ArrayList<node_properties>();
		fringe_array.add(parent_node);
		node_properties goal_node = null;
		ArrayList<String> closed_array = new ArrayList<String>();
		int i=0;
		while(!(fringe_array.isEmpty()) && goal_node == null) {
			i++;
			node_properties currentnode = fringe_array.remove(0); //removing the first element
			if(currentnode.present_state.equals(goal)) //reached goal node
			{
				goal_node = currentnode;
			}
			else{
				if(closed_array.contains(currentnode.present_state)) {}
				else{
					closed_array.add(currentnode.present_state);
					for(String temp:list)
					{
						if(temp.contains(currentnode.present_state)){
							StringTokenizer tokenstring = new StringTokenizer(temp, " ");
							String cityA = tokenstring.nextToken();
							String cityB = tokenstring.nextToken();
							float cost = (float)Integer.parseInt(tokenstring.nextToken());
							if(currentnode.present_state.equals(cityA))
							{
								node_properties city_node = new node_properties(currentnode, currentnode.depth+1, currentnode.path_cost+cost, cityB);
								fringe_array.add(city_node); // add the successors of current node to the fringe
							}
							else{
								node_properties city_node = new node_properties(currentnode, currentnode.depth + 1, currentnode.path_cost + cost, cityA);
								fringe_array.add(city_node);
							}

						}

					}
					Collections.sort(fringe_array);
				}
			}
		}
		if(goal_node == null) {
			System.out.println("nodes expanded: \ndistance: infinity \nroute: \n none"); //output if no route found
		}
		else {
			goal_node.child = null;
			System.out.println("nodes expanded: " + +i + "\ndistance: " + goal_node.path_cost + "\nroute: ");
			while(goal_node.parent!=null){
				goal_node.parent.child = goal_node;
				goal_node = goal_node.parent;
			}
			while(goal_node.child!=null){
				System.out.println(goal_node.present_state + " to " + goal_node.child.present_state + " " + (goal_node.child.path_cost - goal_node.path_cost));
				goal_node = goal_node.child; //output of the nodes visited
			}
		}

	}
}
/*Goutham Munagala
UTA ID:1001565060
*/
//node properties
public class node_properties implements Comparable<node_properties>{
	node_properties parent;
	int depth;
	float path_cost;
	String present_state;
	node_properties child;
	public node_properties(node_properties parent, int depth, float path_cost, String present_state)//nodes initializing
	{
		this.parent = parent;
		this.depth = depth;
		this.path_cost = path_cost;
		this.present_state = present_state;
	}
	@Override
	public int compareTo(node_properties args0){
		return Float.compare(this.path_cost,args0.path_cost); //compare
	}
}
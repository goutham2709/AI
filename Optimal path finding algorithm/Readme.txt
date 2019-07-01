Goutham Munagala
1001565060
Java

1. Initial input_file, state state and goal state are declared.
2. Input_file is read using bufferedreader and stored in the form of array list.
3.  path_cost, depth and the present_state of a node is created in a separate java file.
4. The nodes are added to the fringe.
5. First element in the fringe is removed and added it to the currentnode and goal. The successor nodes are added to the fringe.
6. Cost is calculated and the loop finds the goal state.
7. The path is retraced from goal state to the state state and the cost is displayed.

Instructions:

javac find_route.java
java find_route input_file.txt source destination

References:
http://www.benchresources.net/sorting-arraylist-using-comparable-and-comparator-in-java/
https://www.javatpoint.com/java-arraylist

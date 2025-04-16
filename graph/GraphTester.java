package Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphTester {

	private static DiGraph graph;
	
	//helper function - print every node's value in a node list

	public static void printNodesValues(List<GraphNode> nodeList) {

		System.out.print("Nodes are: ");

		if (nodeList == null) System.out.print("N/A");
		else nodeList.forEach((n) -> System.out.printf("%s ", n.getValue()));
		
		System.out.println(); // new line
		
	}
	
	public static void printPath(List<GraphNode> path) {

		System.out.println("Path is:");

		if (path == null) {

			System.out.println("---- No Path Found ----");

		} else {

			List<String> pathStr = new ArrayList<String>();
			path.forEach((node) -> pathStr.add(node.getValue()));
			System.out.println(String.join(" -> ", pathStr));

		}

	}
	
	public static void main(String[] argv) {
		
		graph = new DiGraphImpl();
		
		// create/add nodes

		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");
		GraphNode nodeC = new GraphNode("C");
		GraphNode nodeD = new GraphNode("D");
		GraphNode nodeE = new GraphNode("E");
		GraphNode nodeF = new GraphNode("F");
		GraphNode nodeG = new GraphNode("G");
		GraphNode nodeH = new GraphNode("H");

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		graph.addNode(nodeG);
		graph.addNode(nodeH);
		
		// add edges
	
		graph.addEdge(nodeA, nodeB, 5);
		graph.addEdge(nodeB, nodeC, 5);
		graph.addEdge(nodeC, nodeD, 1);
		graph.addEdge(nodeE, nodeF, 1);
		graph.addEdge(nodeF, nodeA, 1);
		graph.addEdge(nodeC, nodeF, 2);
		graph.addEdge(nodeD, nodeB, 15);
		graph.addEdge(nodeG, nodeC, 5);
		graph.addEdge(nodeG, nodeE, 8);
		
		// describe

		printNodesValues(graph.getNodes());
		graph.getNodes().forEach(n -> n.printNeighbors());
		
		// test reachability

		System.out.print("F is reachable to E: ");
		System.out.println(graph.nodeIsReachable(nodeF, nodeE)); //false
		System.out.print("F is reachable to D: ");
		System.out.println(graph.nodeIsReachable(nodeF, nodeD)); //true
		
		// test hasCycles

		System.out.print("Graph has cycles: ");
		System.out.println(graph.hasCycles());
		
		// test fewest hops

		System.out.println("Fewest hop from G to B is: " + graph.fewestHops(nodeG, nodeB));
		
		// test shortest path
		
		System.out.println("Shortest from G to B is: " + graph.shortestPath(nodeG, nodeB)); 

	}
	
}
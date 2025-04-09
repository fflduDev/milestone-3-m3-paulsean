package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;
import java.util.Queue;

public class DiGraphImpl implements DiGraph {

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {

		return this.nodeList.add(node);

	}

	@Override
	public Boolean removeNode(GraphNode node) {

		return this.nodeList.remove(node);

	}

	@Override
	public GraphNode getNode(String nodeValue) {

		return this.nodeList.stream().filter((node) -> node.getValue().equals(nodeValue)).findFirst().orElse(null);

	}

	@Override
	public List<GraphNode> getNodes() {

		return this.nodeList;

	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {

		node.setValue(newNodeValue);
		return true;

	}

	@Override
	public String getNodeValue(GraphNode node) {

		return node.getValue();

	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {

		return fromNode.addNeighbor(toNode, weight);

	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {

		return fromNode.removeNeighbor(toNode);

	}
	
	public Boolean addEdgeStr(String fromNodeValue, String toNodeValue, Integer weight) {

		GraphNode fromNode = this.getNode(fromNodeValue);
		GraphNode toNode = this.getNode(toNodeValue);

		if (fromNode == null || toNode == null) return false;

		return this.addEdge(fromNode, toNode, weight);

	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {

		if (fromNode.getDistanceToNeighbor(toNode) == null) {

			return false;

		}

		fromNode.getPaths().put(toNode, newWeight);

		return true;

	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {

		return fromNode.getDistanceToNeighbor(toNode);

	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {

		return node.getNeighbors();

	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {

		return fromNode.getNeighbors().contains(toNode);
		
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {

		Queue<GraphNode> queue = new LinkedList<>();
		List<GraphNode> visited = new ArrayList<>();

		queue.add(fromNode); // Start with the initial 
		queue.add(toNode); // Make sure to add

		while (!queue.isEmpty()) {

			GraphNode current = queue.poll();

			if (current == toNode) {

				return true;

			}

			for (GraphNode neighbour : current.getNeighbors()) {

				if (!visited.contains(neighbour)) {

					visited.add(neighbour);
					queue.add(neighbour);

				}

			}

		}

		return false;

	}

	@Override
	public Boolean hasCycles() {

		for (GraphNode node : nodeList) {

			if (this.nodeIsReachable(node, node)) return true;
			
		}
		
		return false;

	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

}
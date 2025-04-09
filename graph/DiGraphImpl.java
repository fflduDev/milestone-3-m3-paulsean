package Graph;

import java.util.ArrayList;
import java.util.List;
 

public class DiGraphImpl implements DiGraph {

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {

		return nodeList.add(node);

	}

	@Override
	public Boolean removeNode(GraphNode node) {

		return nodeList.remove(node);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasCycles() {
		// TODO Auto-generated method stub
		return null;
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
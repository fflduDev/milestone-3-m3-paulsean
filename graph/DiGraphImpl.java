package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	public Boolean nodeIsReachable(GraphNode targetFromNode, GraphNode targetToNode) {

		Queue<GraphNode> graphNodeQueue = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();
		
		// Start from the targetFromNode

		graphNodeQueue.add(targetFromNode);
		visitedNodes.add(targetFromNode);

		while (!graphNodeQueue.isEmpty()) {

			GraphNode currentNode = graphNodeQueue.poll();

			// Check if the current node is the targetToNode

			if (currentNode.equals(targetToNode)) {

				return true;

			}

			// Get all neighbors and enqueue if not already visited

			for (GraphNode neighbor : currentNode.getNeighbors()) {

				if (!visitedNodes.contains(neighbor)) {

					graphNodeQueue.add(neighbor);
					visitedNodes.add(neighbor);

				}

			}

		}
		
		return false; // Target node is not reachable

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

		Queue<GraphNode> graphNodeQueue = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();

		Map<GraphNode, Integer> hopsCount = new HashMap<>();

		// init collections

		graphNodeQueue.add(fromNode);
		visitedNodes.add(fromNode);
		hopsCount.put(fromNode, 0);

		while (!graphNodeQueue.isEmpty()) {

			GraphNode currentNode = graphNodeQueue.poll();
			int currentHops = hopsCount.get(currentNode);

			// check if successful 

			if (currentNode.equals(toNode)) {

				return currentHops;

			}

			// if not, add more nodes to the queue

			for (GraphNode neighbor : currentNode.getNeighbors()) {

				if (!visitedNodes.contains(neighbor)) {

					graphNodeQueue.add(neighbor);
					visitedNodes.add(neighbor);
					hopsCount.put(neighbor, currentHops + 1);

				}

			}

		}

		return -1;

	}
	
	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {

		Queue<GraphNode> stack = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();
		Map<GraphNode, Integer> pathWeights = new HashMap<>();

		stack.add(fromNode);
		visitedNodes.add(fromNode);
		pathWeights.put(fromNode, 0);

		int shortestPathWeight = -1;

		while (!stack.isEmpty()) {

			GraphNode currentNode = stack.poll();
			int currentWeight = pathWeights.get(currentNode);

			// check if successful 

			if (currentNode.equals(toNode)) {

				if (shortestPathWeight == -1 || currentWeight < shortestPathWeight) {

					shortestPathWeight = currentWeight;

				}

			} else {

				// if not, add more nodes to the queue

				for (GraphNode neighbor : currentNode.getNeighbors()) {

					int neighborWeight = currentNode.getDistanceToNeighbor(neighbor);

					if (!visitedNodes.contains(neighbor)) {

						stack.add(neighbor);
						visitedNodes.add(neighbor);

						pathWeights.put(neighbor, currentWeight + neighborWeight);

					} else if (pathWeights.get(neighbor) > currentWeight + neighborWeight) {

						pathWeights.put(neighbor, currentWeight + neighborWeight);

					}

				}

			}

		}
		
		return shortestPathWeight;

	}

}
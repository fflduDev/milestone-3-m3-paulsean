package Tree.Chart;

import java.util.ArrayList;
import java.util.List;

import Tree.Structs.Employee;
import Tree.Structs.TreeNode;

public class OrgChartImpl implements OrgChart {

	private List<TreeNode<Employee>> nodes = new ArrayList<>();

	// Utility Methods... streams are *very* useful here

	private TreeNode<Employee> findNode(Employee employeeToFind) {

		return this.nodes.stream().filter(node -> node.getData().equals(employeeToFind)).findFirst().orElse(null);

	}
	
	private TreeNode<Employee> findParent(Employee reportingEmployee) {

		return this.nodes.stream().filter(node -> node.getChildren().stream().anyMatch(child -> child.getData().equals(reportingEmployee))).findFirst().orElse(null);
		
	}

	@Override
	public void addRoot(Employee e) throws Exception {

		if (this.nodes.isEmpty()) {

			TreeNode<Employee> root = new TreeNode<>(e);
			this.nodes.add(root);

		} else {

			throw new Exception("Org chart already has a root.");
		
		}
		
	}

	@Override
	public void clear() {

		this.nodes.clear();
		
	}
	
	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {

		// Creating the new node to be added 

		TreeNode<Employee> newPersonNode = new TreeNode<Employee>(newPerson);

		// Attempting to "find" the manager 

		TreeNode<Employee> managerNode = this.findNode(manager);
		
		if (managerNode != null) {

			managerNode.addChild(newPersonNode);

		} else {

			// Add the manager if they do not already exist

			TreeNode<Employee> newManagerNode = new TreeNode<>(manager);
			this.nodes.add(newManagerNode);

			newManagerNode.addChild(newPersonNode);
			
		}
		
	}

	@Override
	public void removeEmployee(Employee firedPerson) {

		// we need to now find the parent of the fired person 

		TreeNode<Employee> firedPersonManager = this.findParent(firedPerson);

		if (firedPersonManager == null) {

			return; // this shouldn't happen, but for general type/runtime safety we should keep this

		}

		firedPersonManager.removeChild(firedPerson); // bye bye
		
	}
	
	@Override
	public void showOrgChartBreadthFirst() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showOrgChartDepthFirst() {
		// TODO Auto-generated method stub

	}
	
}
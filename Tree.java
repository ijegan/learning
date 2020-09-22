package com.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

	Node root = null;
	Node currentNode = null;

	public static void main(String[] args) {

		String[] topics = new String[] { "ITERGO_D_E_Topic", "ITERGO_D_F_Topic", "ITERGO_D_A4_Topic",
				"ITERGO_D_A5_Topic", "ITERGO_D_A1_T1_Topic", "ITERGO_D_A1_T2_Topic", "ITERGO_D_A2_T1_Topic",
				"ITERGO_D_A2_T2_Topic", "ITERGO_D_A3_T1_Topic", "ITERGO_D_A3_T2_Topic", "ITERGO_D_B4_Topic",
				"ITERGO_D_B5_Topic", "ITERGO_D_B1_T1_Topic", "ITERGO_D_B1_T2_Topic", "ITERGO_D_B2_T1_Topic",
				"ITERGO_D_B2_T2_Topic", "ITERGO_D_B3_T1_Topic", "ITERGO_D_B3_T2_Topic", "ITERGO_D_C4_Topic",
				"ITERGO_D_C5_Topic", "ITERGO_D_C1_T1_Topic", "ITERGO_D_C1_T2_Topic", "ITERGO_D_C2_T1_Topic",
				"ITERGO_D_C2_T2_Topic", "ITERGO_D_C3_T1_Topic", "ITERGO_D_C3_T2_Topic", "ITERGO_D_C3_T3_Topic",
				"ITERGO_D_C3_T4_Topic", "ITERGO_D_C3_T1_X1_Topic01", "ITERGO_D_C3_T1_X1_Topic02" };

		String[] subscopes = new String[] { "ITERGO_D_A3", "ITERGO_D_C2", "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C",
				"ITERGO_D_B3", "ITERGO_D_A1", "ITERGO_D_A2", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_A1_T1",
				"ITERGO_D_A1_T2", "ITERGO_D_B1_T1", "ITERGO_D_B1_T2", "ITERGO_D_C1_T1", "ITERGO_D_C1_T2", "ITERGO_D_C1"
//				"ITERGO_D_B"

		};

		List<String> topicList = new ArrayList<>();
		topicList.addAll(Arrays.asList(topics));

		List<String> subscopeList = new ArrayList<>();
		subscopeList.addAll(Arrays.asList(subscopes));

		// sort scopes in descending order of length
		subscopeList.sort((s1, s2) -> Math.abs(s1.length()) - Math.abs(s2.length()));

		Tree tree = new Tree();

		// create a tree structure with scope as node
		for (String scope : subscopeList) {
			tree.addScope(scope);
		}

		System.out.println("\n\n");

		// adding topics to scope
		for (String topic : topicList) {
			tree.addTopic(topic);
		}

		System.out.println("\n\n");

		System.out.println("Topics size: " + topicList.size());
//		System.out.println(root.value + "  | count " + root.topics.size() + " | " + root.topics);

		tree.traverseTree();
//		traverseTree(root);

		// Find node
//		System.out.println("ITERGO_D_C " + containsNode("ITERGO_D_C"));
//		System.out.println("ITERGO_D " + containsNode("ITERGO_D"));
//		System.out.println("ITERGO_D_X " + containsNode("ITERGO_D_X"));

	}

	public void addScope(String scope) {
		currentNode = addRecursive(currentNode, scope);
	}

	public void addTopic(String topic) {
		currentNode = addTopicSubscope(root, topic);
	}

	private Node addRecursive(Node current, String value) {
		// root node ITERGO_D
		if (current == null) {
			root = new Node(value);
//			if (value.equals("ITERGO_D"))
			root.level = 0;
			System.out.println("root added | " + value + "|  level " + root.level);
			return root;
		}

		if (value.startsWith(current.value)) {
			// ITERGO_D_A , ITERGO_D_C , ITERGO_D_A3, ITERGO_D_C2, ITERGO_D_B3, ITERGO_D_A1,
			// ITERGO_D_A2 , ITERGO_D_B1, ITERGO_D_B2, ITERGO_D_C1,
			// ITERGO_D_A1_T1 , ITERGO_D_A1_T2 , ITERGO_D_B1_T1 , ITERGO_D_B1_T2 ,
			// ITERGO_D_C1_T1 , ITERGO_D_C1_T2

			current = navigateToChildNode(current, value);

			Node next = new Node(value);
			next.level = current.level + 1;

			current.nodes.add(next);
			System.out.println("node added | " + value + "|  level " + next.level + " | parent " + current.value);
			return current;

		} else {
			// navigate to root
			addRecursive(root, value);
		}

		return current;
	}

	// navigate from root node(ITERGO_D | level 0) to child node(eg: ITERGO_D_B_B1 |
	// level 2) in multiple level recursively
	private Node navigateToChildNode(Node current, String value) {
		for (Node node : current.nodes) {
			if (value.startsWith(node.value)) {
				current = navigateToChildNode(node, value);
			}
		}
		return current;

	}

	private Node addTopicSubscope(Node current, String topic) {

		if (topic.startsWith(current.value)) {

			current = navigateToChildNode(current, topic);

			current.topics.add(topic);
			System.out.println("topic added | " + topic + "|  level " + current.level + " | scope " + current.value);
			return current;

		} else {
			// navigate to root
			addTopicSubscope(root, topic);
		}

		return current;

	}

	public void traverseTree() {
		traverseTree(root);
	}

	// tree traversal
	void traverseTree(Node node) {

		if (node != null) {
			System.out.println(node.value + " | count " + node.topics.size() + " | " + "size of nodes:"
					+ node.nodes.size() + " | " + node.topics + " \n");

			for (Node subNode : node.nodes) {
				System.out.print(" - " + subNode.value);
			}
			System.out.println("\n");
			for (Node subNode : node.nodes) {
				traverseTree(subNode);
			}

		}

	}

	// find if scope is present
	public boolean containsNode(String value) {
		return containsNodeRecursive(root, value);
	}

	// find scope in tree incomplete
	private boolean containsNodeRecursive(Node current, String value) {
		if (current == null) {
			return false;
		}
		if (value.equals(current.value)) {
			return true;
		} else {
			for (Node node : current.nodes) {
				if (!node.nodes.isEmpty())
					if (value.startsWith(node.value)) {
						containsNodeRecursive(node, value);
					}
			}
		}
		if (value.equals(current.value)) {
			return true;
		} else {
			return false;
		}

	}
}

class Node {
	String value;
	int level;
	List<Node> nodes;
	List<String> topics;

	Node(String value) {
		this.value = value;
		nodes = new ArrayList<>();
		topics = new ArrayList<>();
	}

}

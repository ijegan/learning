package com.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tree {

	static Node root = null;
	static Node currentNode = null;

	public static void main(String[] args) {

		String[] topics = new String[] { "ITERGO_D_E_Topic", "ITERGO_D_F_Topic", "ITERGO_D_A4_Topic",
				"ITERGO_D_A5_Topic", "ITERGO_D_A1_T1_Topic", "ITERGO_D_A1_T2_Topic", "ITERGO_D_A2_T1_Topic",
				"ITERGO_D_A2_T2_Topic", "ITERGO_D_A3_T1_Topic", "ITERGO_D_A3_T2_Topic", "ITERGO_D_B4_Topic",
				"ITERGO_D_B5_Topic", "ITERGO_D_B1_T1_Topic", "ITERGO_D_B1_T2_Topic", "ITERGO_D_B2_T1_Topic",
				"ITERGO_D_B2_T2_Topic", "ITERGO_D_B3_T1_Topic", "ITERGO_D_B3_T2_Topic", "ITERGO_D_C4_Topic",
				"ITERGO_D_C5_Topic", "ITERGO_D_C1_T1_Topic", "ITERGO_D_C1_T2_Topic", "ITERGO_D_C2_T1_Topic",
				"ITERGO_D_C2_T2_Topic", "ITERGO_D_C3_T1_Topic", "ITERGO_D_C3_T2_Topic", "ITERGO_D_C3_T3_Topic",
				"ITERGO_D_C3_T4_Topic", "ITERGO_D_C3_T1_X1_Topic01", "ITERGO_D_C3_T1_X1_Topic02" };

//		String[] subscopes = new String[] { "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C", "ITERGO_D_A1", "ITERGO_D_A2",
//				"ITERGO_D_A3", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_B3", "ITERGO_D_C1", "ITERGO_D_C2"
//
//		};

		String[] subscopes = new String[] { "ITERGO_D_A3", "ITERGO_D_C2", "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C",
				"ITERGO_D_B3", "ITERGO_D_A1", "ITERGO_D_A2", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_A1_T1",
				"ITERGO_D_A1_T2", "ITERGO_D_B1_T1", "ITERGO_D_B1_T2", "ITERGO_D_C1_T1", "ITERGO_D_C1_T2", "ITERGO_D_C1",

		};

		List<String> topicList = new ArrayList<>();
		topicList.addAll(Arrays.asList(topics));

		List<String> subscopeList = new ArrayList<>();
		subscopeList.addAll(Arrays.asList(subscopes));

//		Collections.sort(subscopeList);

		// sort in descending order of length
		subscopeList.sort((s1, s2) -> Math.abs(s1.length()) - Math.abs(s2.length()));

		subscopeList.forEach(value -> System.out.println(value));

		Tree tree = new Tree();

		for (String scope : subscopeList) {
			tree.add(scope);
		}

//		traverseInOrder(root);

		// pointing to root node
//		containsNode("ITERGO_D_A1_T1");
//		for (String topic : topicList) {
//			addTopic(topic);
//		}

		System.out.println("\n");
		System.out.println("\n");

//		traverseInOrderScope(root);

	}

	public void add(String value) {
		currentNode = addRecursive(currentNode, value);
	}

	private Node addRecursive(Node current, String value) {
		// root node
		if (current == null) {
			root = new Node(value);
			System.out.println("Root Node added " + value + " , level " + root.level);
			return root;
		}

		boolean insertionFlag = false;

		if (value.startsWith(current.value)) {
			// ITERGO_D , ITERGO_D_B , ITERGO_D_C , ITERGO_D_X, ITERGO_D_Y, ITERGO_D_B ,
			// ITERGO_D_B_B1 , ITERGO_D_B_B2 , ITERGO_D_B_B2_B3

			for (Node node : current.nodes) {
				if (value.startsWith(node.value)) {
					if (!insertionFlag)
						addRecursive(node, value);
				}
			}

			if (insertionFlag)
				return current;
			// next level
			if (current.nodes.isEmpty()) {
				Node next = new Node(value);
				next.level = current.level + 1;
				current.nodes.add(next);
				System.out.println("node added " + value + " , level " + next.level);
				insertionFlag = true;
				return next;
			}

			// same level ??
			if (!current.nodes.isEmpty()) {
				Node next = new Node(value);
				next.level = current.level + 1;
				current.nodes.add(next);
				System.out.println("node added in same level " + value + " level " + next.level);
				insertionFlag = true;
				current = next;
				return next;
			}
		}
		// start from root
		else {
			current = root;
			addRecursive(current, value);
		}

//	if(value.compareTo(current.value)<0)
//	{
//		current.left = addRecursive(current.left, value);
//	}else if(value.compareTo(current.value)>0)
//	{
//		current.right = addRecursive(current.right, value);
//	}else
//	{
		// value already exists
		return current;
	}

	private static boolean containsNodeRecursive(Node current, String value) {
		if (current == null) {
			return false;
		}
		if (value == current.value) {
			return true;
		}
//		return (value.compareTo(current.value) < 0) ? containsNodeRecursive(current.left, value)
//				: containsNodeRecursive(current.right, value);
		return false;
	}

	public static boolean containsNode(String value) {
		return containsNodeRecursive(root, value);
	}

	private static boolean addTopicsSubscopes(Node current, String value) {
		if (current == null) {
//			System.out.println("Not able to add");
			Node position = findIkepNamespaceNode(root, "ITERGO_D");
			position.topics.add(value);
			return false;
		}
		if (value.startsWith(current.value)) {
			current.topics.add(value);
			return true;
		}

		System.out.println(value + " ~~ " + current.value + " ~~" + value.compareTo(current.value));

//		return (value.compareTo(current.value) < 0) ? addTopicsSubscopes(current.left, value)
//				: addTopicsSubscopes(current.right, value);
		return false;
	}

	public static boolean addTopic(String value) {
		return addTopicsSubscopes(root, value);
	}

	private static Node findIkepNamespaceNode(Node current, String value) {
//		if (current == null) {
//			return current;
//		}
		if (value == current.value) {
			return current;
		}
//		return (value.compareTo(current.value) < 0) ? findIkepNamespaceNode(current.left, value)
//				: findIkepNamespaceNode(current.right, value);

		return current;
	}

//	public static void traverseInOrder(Node node) {
//		if (node != null) {
//			traverseInOrder(node.left);
//			System.out.println(" " + node.value);
//			traverseInOrder(node.right);
//		}
//	}
//
//	public static void traverseInOrderScope(Node node) {
//		if (node != null) {
//			traverseInOrderScope(node.left);
//			System.out.println(node.value + " ****  " + node.topics.toString());
//			traverseInOrderScope(node.right);
//		}
//	}
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
		if (value.equals("ITERGO_D"))
			level = 0;
	}

}

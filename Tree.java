package com.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		String[] subscopes = new String[] { "ITERGO_D_A3", "ITERGO_D_C2", "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C",
				"ITERGO_D_B3", "ITERGO_D_A1", "ITERGO_D_A2", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_A1_T1",
				"ITERGO_D_A1_T2", "ITERGO_D_B1_T1", "ITERGO_D_B1_T2", "ITERGO_D_C1_T1", "ITERGO_D_C1_T2", "ITERGO_D_C1"
//				"ITERGO_D_B"

		};

		List<String> topicList = new ArrayList<>();
		topicList.addAll(Arrays.asList(topics));

		List<String> subscopeList = new ArrayList<>();
		subscopeList.addAll(Arrays.asList(subscopes));

		// sort in descending order of length
		subscopeList.sort((s1, s2) -> Math.abs(s1.length()) - Math.abs(s2.length()));

//		subscopeList.forEach(
//				value -> System.out.println(value));

		Tree tree = new Tree();

		for (String scope : subscopeList) {
			tree.add(scope);
		}

		System.out.println("\n");
		System.out.println("\n");

		// adding topics to scope
		for (String topic : topicList) {
			tree.addTopic(topic);
		}

		System.out.println("\n");
		System.out.println("\n");

		System.out.println(root.value + " " + root.topics);
		traverseInOrder(root);

//		traverseInOrderScope(root);

	}

	public void add(String value) {
		currentNode = addRecursive(currentNode, value);
	}

	public void addTopic(String value) {
		currentNode = addTopicSubscope(root, value);
	}

	private Node addRecursive(Node current, String value) {
		// root node
		if (current == null) {
			root = new Node(value);
			System.out.println("root added | " + value + "|  level " + root.level);
			return root;
		}

		if (value.startsWith(current.value)) {
			// ITERGO_D , ITERGO_D_B , ITERGO_D_C , ITERGO_D_X, ITERGO_D_Y, ITERGO_D_B ,
			// ITERGO_D_B_B1 , ITERGO_D_B_B2 , ITERGO_D_B_B2_B3

			// ITERGO_D , ITERGO_D_A , ITERGO_D_C , ITERGO_D_A3, ITERGO_D_C2, ITERGO_D_B3 ,
			// ITERGO_D_A1 , ITERGO_D_A2

			current = navigateToChildNode(current, value);

			Node next = new Node(value);
			next.level = current.level + 1;

			current.nodes.add(next);
			System.out.println("node added | " + value + "|  level " + next.level + " | parent " + current.value);
			return current;

		}
		// start from root
		else {
//			current = root;
			addRecursive(root, value);
		}

		return current;
	}

	private Node addTopicSubscope(Node current, String topic) {

		if (topic.startsWith(current.value)) {

			current = navigateToChildNode(current, topic);

			current.topics.add(topic);
			System.out.println("topic added | " + topic + "|  level " + current.level + " | scope " + current.value);
			return current;

		}
		// start from root
		else {
			addRecursive(root, topic);
		}

		return current;

	}

	private Node navigateToChildNode(Node current, String value) {
		for (Node node : current.nodes) {
			if (value.startsWith(node.value)) {
				current = navigateToChildNode(node, value);
			}
		}
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

	public static void traverseInOrder(Node current) {
//		if (node != null) {
//			traverseInOrder(node.left);
//			System.out.println(" " + node.value);
//			traverseInOrder(node.right);
//		}
//		System.out.println(root.value + " " + root.topics);

		if (!current.nodes.isEmpty()) {
			for (Node node : current.nodes) {
				System.out.println(node.value + " " + node.topics);
				if (!node.nodes.isEmpty())
					traverseInOrder(node);
			}

		}

	}
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
	Set<Node> nodes;
	List<String> topics;

	Node(String value) {
		this.value = value;
		nodes = new HashSet<>();
		topics = new ArrayList<>();
		if (value.equals("ITERGO_D"))
			level = 0;
	}

}

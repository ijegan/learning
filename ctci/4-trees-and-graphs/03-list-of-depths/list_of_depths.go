package ctci_graph

import "fmt"

func Depth(ptr *Node, result *Result, level int) []*LinkedList {

	// base case
	if ptr == nil {
		return nil
	}

	// LinkedList array in result is nil
	if result == nil {
		result = &Result{}
	}

	// list is empty or new LinkedList for that level should be inserted
	if len(result.arr) == level {
		newLinkedList := &LinkedList{head: nil, rear: nil, level: level}
		result.arr = append(result.arr, newLinkedList)
	}

	// fetch the linkedlist from list splice
	ll := result.arr[level]

	// add node to linkedlist
	ll.llInsert(ptr)

	// traverse left
	Depth(ptr.left, result, level+1)

	// traverse right
	Depth(ptr.right, result, level+1)

	return result.arr
}

type Node struct {
	data  int
	left  *Node
	right *Node
	next  *Node
}

type LinkedList struct {
	head  *Node
	rear  *Node
	level int
}

type Result struct {
	arr []*LinkedList
}

func (ll *LinkedList) llInsert(newNode *Node) {

	if ll.head == nil || ll.rear == nil {
		ll.rear = newNode
		ll.head = newNode
		return
	}
	ptr := ll.rear
	ptr.next = newNode
	ll.rear = newNode
}

func BuildBst(arr []int) *Node {
	var root *Node
	for _, v := range arr {
		if root == nil {
			newNode := &Node{data: v, left: nil, right: nil, next: nil}
			root = newNode
		}

		treeCreateNode(root, v)
	}
	return root
}

func InOrder(ptr *Node) {
	if ptr != nil {
		InOrder(ptr.left)
		fmt.Print(ptr.data, " ")
		InOrder(ptr.right)
	}
}

func InOrderLL(ptr *Node, ll *LinkedList) {
	if ptr != nil {
		InOrderLL(ptr.left, ll)

		fmt.Print(ptr.data, " ")
		ll.llInsert(ptr)

		InOrderLL(ptr.right, ll)
	}
}

func PreOrder(ptr *Node) {
	if ptr != nil {
		fmt.Print(ptr.data, " ")
		PreOrder(ptr.left)
		PreOrder(ptr.right)
	}
}

func PostOrder(ptr *Node) {
	if ptr != nil {
		PostOrder(ptr.left)
		PostOrder(ptr.right)
		fmt.Print(ptr.data, " ")
	}
}

func treeCreateNode(root *Node, value int) {
	newNode := &Node{data: value, left: nil, right: nil, next: nil}

	ptr := root
	for ptr != nil {
		if value == ptr.data {
			return
		} else if value < ptr.data {
			if ptr.left == nil {
				ptr.left = newNode
				return
			}
			ptr = ptr.left
		} else if value > ptr.data {
			if ptr.right == nil {
				ptr.right = newNode
				return
			}
			ptr = ptr.right
		}

	}
}

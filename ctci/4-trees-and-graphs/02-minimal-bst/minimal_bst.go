package graph

import "fmt"

func Minimal(arr []int) *Node {
	return bst(arr, 0, len(arr)-1)
}

func bst(arr []int, l, h int) *Node {
	if l > h || h < l {
		return nil
	}

	mid := (l + h) / 2
	fmt.Print(arr[mid], ".")

	var n *Node = createNode(arr[mid])
	n.left = bst(arr, l, mid-1)
	n.right = bst(arr, mid+1, h)
	return n
}

func Search(value int, root *Node) bool {
	// fmt.Println()
	// fmt.Println("*search: ", value, "*")
	ptr := root

	for ptr != nil {
		// fmt.Print(ptr.data, "|")

		if value == ptr.data {
			return true
		} else if value < ptr.data {
			ptr = ptr.left
		} else if value > ptr.data {
			ptr = ptr.right
		}
	}
	return false
}

type Node struct {
	data  int
	left  *Node
	right *Node
}

func createNode(value int) *Node {
	var newNode *Node = &Node{data: value, left: nil, right: nil}
	return newNode
}

func Display(ptr *Node) []int {
	var data []int
	if ptr != nil {
		data = append(data, Display(ptr.left)...)
		data = append(data, ptr.data)
		data = append(data, Display(ptr.right)...)
	}
	return data
}

func PreOrder(ptr *Node) []int {
	var data []int
	if ptr != nil {
		data = append(data, ptr.data)
		data = append(data, PreOrder(ptr.left)...)
		data = append(data, PreOrder(ptr.right)...)
	}
	return data
}

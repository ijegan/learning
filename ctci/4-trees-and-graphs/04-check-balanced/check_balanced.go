package ctci_graph

import (
	"fmt"
	"math"
)

func CheckBalanced(root *Node) bool {
	result := checkHeight(root, 0)

	fmt.Println("result: ", result)

	if result == math.MinInt8 {
		return false
	} else {
		return true
	}
}

func checkHeight(ptr *Node, level int) int {
	if ptr == nil {
		return -1
	}

	leftHeight := checkHeight(ptr.left, level+1)

	if leftHeight == math.MinInt8 {
		return math.MinInt8
	}

	rightHeight := checkHeight(ptr.right, level+1)

	if rightHeight == math.MinInt8 {
		return math.MinInt8
	}

	fmt.Println(ptr.data, " | ", "left: ", leftHeight, " right: ", rightHeight)
	diff := leftHeight - rightHeight

	if diff < 0 {
		diff = diff * -1
	}

	if diff > 1 {
		return math.MinInt8
	}

	return diff
}

type Node struct {
	data  int
	left  *Node
	right *Node
}

func Bst(arr []int) *Node {
	var root *Node = &Node{data: math.MinInt8}
	for _, v := range arr {
		Insert(root, v)
	}
	return root
}

func Insert(root *Node, value int) {
	// root empty
	if root.data == math.MinInt8 {
		root.data = value
		return
	}

	for ptr := root; ptr != nil; {
		if value == ptr.data {
			return
		} else if value < ptr.data {
			if ptr.left == nil {
				ptr.left = &Node{data: value, left: nil, right: nil}
				return
			}
			ptr = ptr.left

		} else if value > ptr.data {
			if ptr.right == nil {
				ptr.right = &Node{data: value, left: nil, right: nil}
				return
			}
			ptr = ptr.right
		}
	}
}

func PreOrder(ptr *Node) {
	if ptr == nil {
		return
	}
	fmt.Print(ptr.data, " ")
	PreOrder(ptr.left)
	PreOrder(ptr.right)
}

func InOrder(ptr *Node) {
	if ptr == nil {
		return
	}
	InOrder(ptr.left)
	fmt.Print(ptr.data, " ")
	InOrder(ptr.right)
}

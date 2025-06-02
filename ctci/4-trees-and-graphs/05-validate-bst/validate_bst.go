package graph

import "fmt"

func ValidateBst(root *Node) bool {
	arr := InOrder(root)
	for i, j := 0, 1; j < len(arr); i, j = i+1, j+1 {
		if arr[i] > arr[j] {
			return false
		}
	}
	return true
}

type Node struct {
	data  int
	left  *Node
	right *Node
}

func BuildBst(arr []int) *Node {
	var root *Node
	for _, v := range arr {
		if root == nil {
			root = &Node{data: v, left: nil, right: nil}
			continue
		}
		createNode(root, v)
	}
	return root
}

func Search(root *Node, value int) *Node {
	for ptr := root; ptr != nil; {
		if value == ptr.data {
			return ptr
		} else if value < ptr.data {
			ptr = ptr.left
		} else if value > ptr.data {
			ptr = ptr.right
		}
	}
	return nil
}

// ptr point the node found using search and insert a new value
func Insert(ptr *Node, value int) {
	if ptr == nil {
		return
	}

	newNode := &Node{data: value, left: nil, right: nil}
	// insert newNode if the child is empty
	if value < ptr.data {
		if ptr.left == nil {
			ptr.left = newNode
		}
	} else if value > ptr.data {
		if ptr.right == nil {
			ptr.right = newNode
		}
	}
}

func createNode(root *Node, value int) {

	for ptr := root; ptr != nil; {
		if value == ptr.data {
			return
		} else {
			newNode := &Node{data: value, left: nil, right: nil}

			if value < ptr.data {
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
}

func InOrder(ptr *Node) []int {
	var arr []int
	if ptr != nil {
		arr = append(arr, InOrder(ptr.left)...)
		arr = append(arr, ptr.data)
		arr = append(arr, InOrder(ptr.right)...)
		return arr
	}
	return arr
}


func InOrderTest(ptr *Node) {
	if ptr != nil {
		InOrderTest(ptr.left)
		fmt.Println(ptr.data)
		InOrderTest(ptr.right)
	}
}

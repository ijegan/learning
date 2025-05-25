package ctci_graph

import (
	"fmt"
	"testing"
)

func TestListOfDepth(t *testing.T) {
	var arr = []int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20}
	var root *Node = BuildBst(arr)

	fmt.Println("InOrder: ")
	InOrder(root)
	fmt.Println()

	fmt.Println("PreOrder: ")
	PreOrder(root)
	fmt.Println()

	var list *Result

	output := Depth(root, list, 0)

	// display list
	for i, v := range output {
		fmt.Println("Level: ", i)
		for ptr := v.head; ptr != nil; ptr = ptr.next {
			fmt.Print(ptr.data, " | ")
		}
		fmt.Println()
	}
}

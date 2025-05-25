package ctci_graph

import (
	"fmt"
	"testing"
)

func TestCheckBalanced(t *testing.T) {
	tests := []struct {
		i []int
		o bool
	}{
		{[]int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20}, true},
		{[]int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20, 21}, false},
		{[]int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20, 6}, true},
		{[]int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20, 6, 7}, false},
	}

	for _, v := range tests {
		var root *Node
		root = Bst(v.i)

		PreOrder(root)
		fmt.Println()

		got := CheckBalanced(root)
		want := v.o

		if got!=want {
			t.Errorf("CheckBalanced(%v) = %v, want: %v", v.i, got, want)
		}
		fmt.Println("**** **** ****")
	}

}

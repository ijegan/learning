package graph

import (
	"fmt"
	"reflect"
	"testing"
)

func TestValidateBst(t *testing.T) {

	tests := []struct {
		i, o []int
	}{
		{i: []int{15, 12, 11, 13, 14, 18, 16, 17, 19, 20}, o: []int{11, 12, 13, 14, 15, 16, 17, 18, 19, 20}},
		{i: []int{3, 4, 1, 5, 6, 2}, o: []int{1, 2, 3, 4, 5, 6}},
	}

	for _, test := range tests {

		var root *Node
		root = BuildBst(test.i)

		got := InOrder(root)
		fmt.Println(got)
		want := test.o

		// validate InOrder display
		if !reflect.DeepEqual(got, want) {
			t.Errorf("Dispay(%v) =  %v, want %v", test.i, got, want)
		}

		flag := ValidateBst(root)
		if !flag {
			t.Errorf("Dispay(%v) =  %v, | Not a binary search tree", test.i, got)
		}

	}

	// Search and insert
	var root *Node
	root = BuildBst(tests[0].i)

	ptr := Search(root, 14)
	Insert(ptr, 25)
	ptr = Search(root, 11)
	Insert(ptr, 7)

	display := InOrder(root)
	fmt.Println(display)

	flag := ValidateBst(root)
	fmt.Println("flag: ", flag)
	if !flag {
		fmt.Println("Dispay([ 7,11..20,25 ]) | Not a binary search tree")
	}

	// Test another input
	root = BuildBst(tests[0].i)
	ptr = Search(root, 11)
	Insert(ptr, 7)
	display = InOrder(root)

	fmt.Println(display)
	flag = ValidateBst(root)
	if !flag {
		t.Errorf("Dispay(%v) =  %v, | Not a binary search tree", "7,11..20", display)
	}

	//In order test!
	fmt.Println("In order test!")
	InOrderTest(root)
}

package graph

import (
	"fmt"
	"testing"
)

func TestMinimal(t *testing.T) {
	var arr = []int{11, 12, 13, 14, 15, 16, 17, 18, 19, 20}
	ptr := Minimal(arr)

	fmt.Println()
	inOrder := Display(ptr)
	fmt.Println("Inorder: ", inOrder)

	preOrder := PreOrder(ptr)
	fmt.Println("preorder: ", preOrder)

	tests := []struct {
		i int
		o bool
	}{
		{1, false},
		{2, false},
		{3, false},
		{4, false},
		{5, false},
		{6, false},
		{7, false},
		{8, false},
		{9, false},
		{10, false},

		{11, true},
		{12, true},
		{13, true},
		{14, true},
		{15, true},
		{16, true},
		{17, true},
		{18, true},
		{19, true},
		{20, true},

		{21, false},
		{22, false},
		{23, false},
		{24, false},
		{25, false},
		{26, false},
		{27, false},
		{28, false},
		{29, false},
		{30, false},
	}

	for _, v := range tests {
		got := Search(v.i, ptr)
		want := v.o
		if want != got {
			t.Errorf("Search(%v) = %v want %v", v.i, got, want)
		}
	}
}

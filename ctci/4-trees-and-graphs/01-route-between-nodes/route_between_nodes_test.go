package graph

import (
	"fmt"
	"testing"
)

func TestQTest(t *testing.T) {

	input := [][]rune{{'a', 'c'}, {'a', 'd'}, {'a', 'b'}, {'d', 'c'}, {'d', 'b'}, {'b', 'f'}, {'f', 'e'}, {'e', 'b'}, {'e', 'g'}}
	// QueueTest()

	graph := BuildGraph(input)
	tests := []struct {
		a, b rune
		o    bool
	}{
		{'a', 'g', true},
		{'b', 'c', false},
		{'a', 'f', true},
	}

	for _, v := range tests {
		got := Route(graph, v.a, v.b)
		want := v.o
		if got != want {
			t.Errorf("Route(%v,%v) = %v, want: %v", string(v.a), string(v.b), got, want)
		}
		fmt.Println()
	}
}

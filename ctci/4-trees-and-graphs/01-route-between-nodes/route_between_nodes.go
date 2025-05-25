package graph

import "fmt"

func BuildGraph(input [][]rune) map[rune][]rune {
	var graph = make(map[rune][]rune)
	for _, val := range input {
		k := val[0]
		v := val[1]

		m, exists := graph[k]

		var data []rune
		if exists {
			data = append(data, m...)
		}
		data = append(data, v)
		graph[k] = data
	}
	return graph
}

func Route(graph map[rune][]rune, start rune, end rune) bool {
	if start == end {
		return true
	}

	var marked = make(map[rune]bool)
	var q Queue

	// add start to queue
	q.enQueue(start)
	// mark or set visited for start node as true
	marked[start] = true

	for !q.isEmpty() {
		// dequeue and update visited
		front := q.deQueue()
		marked[front] = true

		fmt.Print(string(front), " | ")

		if front == end {
			return true
		}

		//get all neighbours
		for _, v := range graph[front] {
			_, exist := marked[v]
			if !exist {
				q.enQueue(v)
				marked[v] = true
			}
		}
	}
	return false
}

func QueueTest() {
	var q Queue

	q.enQueue('a')
	q.enQueue('b')

	q.enQueue('c')
	q.enQueue('x')
	q.enQueue('y')

	q.display()
	q.deQueue()
	q.deQueue()

	q.display()
}

type Queue struct {
	data []rune
}

func (q *Queue) enQueue(value rune) {
	q.data = append(q.data, value)
}

func (q *Queue) deQueue() rune {
	if q.isEmpty() {
		return -1
	}

	front := q.data[0]
	q.data = q.data[1:]
	return front
}

func (q *Queue) isEmpty() bool {
	if len(q.data) == 0 {
		return true
	}
	return false
}

func (q *Queue) front() rune {
	if q.isEmpty() {
		return -1
	}
	return q.data[0]
}

func (q *Queue) display() {
	fmt.Println(string(q.data))
}

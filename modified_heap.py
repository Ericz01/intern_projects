class ModifiedHeap:
    def __init__(self, x):
        self.heap = []
        self.x = x  # Controls the number of children (2^x)

    def get_parent_index(self, index):
        return (index - 1) // (1 << self.x)

    def get_child_index(self, index, k):
        return (1 << self.x) * index + k

    def insert(self, value):
        self.heap.append(value)
        index = len(self.heap) - 1
        self.bubble_up(index)

    def bubble_up(self, index):
        parent_index = self.get_parent_index(index)
        while index > 0 and self.heap[parent_index] < self.heap[index]:
            self.swap(index, parent_index)
            index = parent_index
            parent_index = self.get_parent_index(index)

    def pop_max(self):
        if not self.heap:
            raise IndexError("Heap is empty")

        max_value = self.heap[0]
        last_index = len(self.heap) - 1

        self.heap[0] = self.heap[last_index]
        self.heap.pop()

        if self.heap:
            self.bubble_down(0)

        return max_value

    def bubble_down(self, index):
        max_index = index
        size = len(self.heap)

        for i in range(1, (1 << self.x) + 1):
            child_index = self.get_child_index(index, i)
            if child_index < size and self.heap[child_index] > self.heap[max_index]:
                max_index = child_index

        if max_index != index:
            self.swap(index, max_index)
            self.bubble_down(max_index)

    def swap(self, i, j):
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

# Main method for testing
if __name__ == "__main__":
    heap = ModifiedHeap(1)  # Example with x=1 (binary heap)
    heap.insert(10)
    heap.insert(20)
    heap.insert(5)
    heap.insert(30)

    print(heap.pop_max())  # Should print 30
    print(heap.pop_max())  # Should print 20
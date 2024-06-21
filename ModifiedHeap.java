import java.util.ArrayList;

public class ModifiedHeap {
    private ArrayList<Integer> heap;
    private int x; // controls the number of children (2^x).

    public ModifiedHeap(int x) {
        this.heap = new ArrayList<>();
        this.x = x;
    }

    // Helper method to get the parent index
    private int getParentIndex(int index) {
        return (index - 1) / (1 << x);
    }

    // Helper method to get the child indices.
    private int getChildIndex(int index, int k) {
        return (1 << x) * index + k;
    }

    // Insert method.
    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        bubbleUp(index);
    }

    private void bubbleUp(int index) {
        int parentIndex = getParentIndex(index);
        while (index > 0 && heap.get(parentIndex) < heap.get(index)) {
            // Swap the current index with the parent
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    // Pop max method
    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.get(0);
        int lastIndex = heap.size() - 1;

        // Swap the root with the last element
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);

        // Bubble down the new root to maintain heap property
        if (!heap.isEmpty()) {
            bubbleDown(0);
        }

        return max;
    }

    private void bubbleDown(int index) {
        int maxIndex = index;
        int size = heap.size();

        for (int i = 1; i <= (1 << x); i++) {
            int childIndex = getChildIndex(index, i);
            if (childIndex < size && heap.get(childIndex) > heap.get(maxIndex)) {
                maxIndex = childIndex;
            }
        }

        if (maxIndex != index) {
            swap(index, maxIndex);
            bubbleDown(maxIndex);
        }
    }

    // Helper method to swap elements
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Main method for testing.
    public static void main(String[] args) {
        ModifiedHeap heap = new ModifiedHeap(1); // Example with x=1 (binary heap)
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);

        System.out.println(heap.popMax()); // Prints 30
        System.out.println(heap.popMax()); // Prints 20
    }
}

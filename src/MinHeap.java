// https://www.geeksforgeeks.org/heap-implementation-in-java/

import java.util.ArrayList;


public class MinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }
    // Returns the index of the left child node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Returns the index of the right child node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Inserts a new value into the heap
    public void insert(T value) {
        heap.add(value);
        int currentIndex = heap.size() - 1;

        // Bubble up to restore heap property
        while (currentIndex > 0 && heap.get(currentIndex).compareTo(heap.get(parent(currentIndex))) < 0) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // Extracts and returns the minimum value from the heap
    public T extractMin() {
        if (heap.isEmpty()) {
            throw new RuntimeException("BADDDD");
        }

        // The minimum value is at the root
        T min = heap.get(0);
        // Remove the last element
        T lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            // Move the last element to the root
            heap.set(0, lastElement);

            // Bubble down to restore heap property
            int currentIndex = 0;
            while (true) {
                int left = leftChild(currentIndex);
                int right = rightChild(currentIndex);

                int smallest = currentIndex;

                // Find the smallest value among current, left child, and right child
                if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                    smallest = left;
                }

                if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                    smallest = right;
                }

                if (smallest == currentIndex) {
                    // Heap property is restored
                    break;
                }

                // Swap with the smallest child
                swap(currentIndex, smallest);
                // Move down to the smallest child's index
                currentIndex = smallest;
            }
        }

        // Return the minimum value
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

}
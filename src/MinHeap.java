// https://www.geeksforgeeks.org/heap-implementation-in-java/
// https://www.baeldung.com/java-min-max-heap
// coreman book ??? haha i forgot his name
public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private int capacity;

    public MinHeap() {
        // set some value - will double as needed
        this.capacity = 1;
        this.size = 0;
        // utilize comparable to analyze which value is smallest
        this.heap = (T[]) new Comparable[capacity];
    }

    // returns index of the parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }
    // returns the index of the left child node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // returns the index of the right child node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // swap function - typical swap w/ temp value
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // this is needed to append to the minheap array -- we will need to alter
    // the capacity and create a new array of the new capacity size, and then
    // transfer the current values into the new array
    private void resize() {
        capacity *= 2;
        T[] newHeap = (T[]) new Comparable[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    // inserts a new value into the heap
    public void insert(T value) {
        if (size == capacity) {
            resize(); // call resize if capacity is exceeded
        }

        heap[size] = value;
        int currentIndex = size;
        size++;

        // heapify --> swap nodes until minheap properties are met
        while (currentIndex > 0 && heap[currentIndex].compareTo(heap[parent(currentIndex)]) < 0) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // extracts min from heap
    public T extractMin() {
        if (isEmpty()) {
            throw new RuntimeException("BADDDD");
        }

        // extract min from root of heap
        T min = heap[0];
        heap[0] = heap[size-1];
        heap[size - 1] = null;
        size--;

            // heapify!!
            int currentIndex = 0;
            while (true) {
                int left = leftChild(currentIndex);
                int right = rightChild(currentIndex);

                int smallest = currentIndex;

                // Find the smallest value among current, left child, and right child
                if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
                    smallest = left;
                }

                if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
                    smallest = right;
                }

                if (smallest == currentIndex) {
                    break;
                }

                swap(currentIndex, smallest);
                currentIndex = smallest;
            }
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
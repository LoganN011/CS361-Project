public class Queue<T> {
    //https://www.geeksforgeeks.org/queue-linked-list-implementation/
    private Node<T> front, rear;

    public Queue() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return front == null && rear == null;
    }

    public void enqueue(T newData) {
        Node<T> newNode = new Node(newData);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Broken");
        }
        Node<T> temp = front;
        front = front.next();
        if (front == null) {
            rear = null;
        }
        return temp.data();
    }

    //Maybe make these last two return the node and not the data
    public T front() {
        if (isEmpty()) {
            System.out.println("Broken");
        }
        return front.data();
    }

    public T rear() {
        if (isEmpty()) {
            System.out.println("Broken");
        }
        return rear.data();
    }
    //https://www.geeksforgeeks.org/introduction-and-array-implementation-of-queue/
//    private int front;
//    private int rear;
//    private int size;
//    private int capacity;
//    private int[] array;
//
//    public Queue(int capacity) {
//        this.capacity = capacity;
//        front = this.size = 0;
//        rear = capacity - 1;
//        array = new int[this.capacity];
//    }
//
//    public boolean isFull() {
//        return (this.size == this.capacity);
//    }
//
//    public boolean isEmpty() {
//        return (this.size == 0);
//    }
//
//    public void enqueue(int item) {
//        if (this.isFull()) {
//            return;
//        }
//        this.rear = (this.rear + 1) % this.capacity;
//        this.array[this.rear] = item;
//        this.size = this.size + 1;
//        System.out.println(item + " enqueued to queue");
//    }
//
//    public int dequeue() {
//        if (this.isEmpty()) {
//            return Integer.MIN_VALUE;
//        }
//        int item = this.array[this.front];
//        this.front = (this.front + 1) % this.capacity;
//        this.size = this.size - 1;
//        return item;
//    }
//
//    public int front() {
//        if (this.isEmpty()) {
//            return Integer.MIN_VALUE;
//        }
//        return this.array[this.front];
//    }
//
//    public int rear() {
//        if (this.isEmpty()) {
//            return Integer.MIN_VALUE;
//        }
//        return this.array[this.rear];
//    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(5);



        System.out.println(queue.dequeue());
        System.out.println(queue.rear());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }


}

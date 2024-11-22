public class Queue<T> {
    //https://www.geeksforgeeks.org/queue-linked-list-implementation/
    private Node<T> front, rear;

    //Makes a new queue setting everything to empty
    public Queue() {
        front = rear = null;
    }

    //checks if the queue is empty
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    //Add something to the end of the queue
    public void enqueue(T newData) {
        Node<T> newNode = new Node(newData);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    //Get the first thing in the queue
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

    // clears the queue making everything null
    public void clear() {
        front = rear = null;
    }


}

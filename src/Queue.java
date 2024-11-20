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

    public void clear() {
        front = rear = null;
    }


}
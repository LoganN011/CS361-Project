import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private Node<T> front, rear;

    public Queue() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return front == null && rear == null;
    }

    public void enqueue(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        Node<T> temp = front;
        front = front.next();
        if (front == null) {
            rear = null;
        }
        return temp.data();
    }

    public T front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return front.data();
    }

    public T rear() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return rear.data();
    }

    public void clear() {
        front = rear = null;
    }

    // Implementing the Iterable interface
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements in queue");
                }
                T data = current.data();
                current = current.next();
                return data;
            }
        };
    }
}

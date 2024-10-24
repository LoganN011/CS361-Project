public class Node<T> {
    private T data;
    private Node next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T data() {
        return this.data;
    }

    public Node next() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

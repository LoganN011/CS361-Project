public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T data() {
        return this.data;
    }

    public Node<T> next() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

package utility;

/**
 * Утилитарный класс,
 * описывающий
 * @param <E>
 */
public class Node<E> {

    public E data;
    public Node<E> next;
    public Node<E> prev;

    public Node(Node<E> next, Node<E> prev, E value) {
        this.data = value;
        this.next = next;
        this.prev = prev;
    }
}

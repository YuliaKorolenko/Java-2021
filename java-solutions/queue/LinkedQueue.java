package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    private class Node {
        private Node next;
        private Object value;

        public Node(final Node next, final Object value) {
            this.next = next;
            this.value = value;
        }
    }

    protected void enqueueImp(Object e) {
        if (size == 1) {
            this.tail = new Node(null, e);
            this.head = this.tail;
        } else {
            Node tail = this.tail;
            this.tail = new Node(null, e);
            tail.next = this.tail;
        }
    }

    protected Object elementImp() {
        return (this.head.value);
    }


    protected Object dequeueImp() {
        Node head = this.head;
        this.head = this.head.next;
        if (size == 0) {
            this.tail = null;
        }
        return head.value;
    }

    protected Object getImp(int ind) {
        return find(ind).value;
    }

    protected void setImp(int ind, Object e) {
        Node changing = find(ind);
        changing.value = e;
    }


    private Node find(int number){
        int i = 0;
        Node nowNode = this.head;
        while (i < number) {
            nowNode = nowNode.next;
            i++;
        }
        return nowNode;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

}

package queue;

public class MyArrayQueueTest {
    public static void main(String[] args) {
        AbstractQueue s1 = new ArrayQueue();
        AbstractQueue s2 = new ArrayQueue();

        s1.enqueue(2);
        s2.enqueue(5);

        System.out.println(s2.dequeue());
        System.out.println(s1.dequeue());
    }
}

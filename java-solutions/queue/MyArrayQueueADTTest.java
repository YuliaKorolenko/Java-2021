package queue;

public class MyArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT s1 = ArrayQueueADT.create();
        ArrayQueueADT s2 = ArrayQueueADT.create();

        ArrayQueueADT.enqueue(s1,2);
        ArrayQueueADT.enqueue(s2,5);

        System.out.println(ArrayQueueADT.dequeue(s2));
        System.out.println(ArrayQueueADT.dequeue(s1));
    }
}

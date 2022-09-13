package queue;

public class MyArrayQueueModuleTest {
    public static void main(String[] args) {
        ArrayQueueModule.enqueue(2);

        System.out.println(ArrayQueueModule.dequeue());
    }
}

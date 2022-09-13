package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    /*
    Model:
        a[1]...a[n]
    Invariant : forall 1 <= i <= n : a[i]!=null && n>=0
    Immutable : forall 1 <= i <= n : a[i]'==a[i] && n' == n
     */

    private int head = 0;
    private int tail = 0;
    private Object[] elements = new Object[2];

    /*
    Pred: true
    Post: R = ArrayQueueADT;
     */
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    /*
    Pred: new element != null && queue != null
    Post: n' == n + 1 && a[n']' = new element && forall 1 <= i <= n : a[i]'== a[i]
    enqueue – добавить элемент в очередь
     */
    public static void enqueue(ArrayQueueADT queue, Object e) {
        assert (e != null);
        ensureCapacity(queue,size(queue) + 1);
        queue.elements[queue.tail] = e;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    /*
    Pred: n > 0 && queue != null
    Post: R == a[1] && Immutable
    element – первый элемент в очереди
     */
    public static Object element(ArrayQueueADT queue) {
        assert (size(queue) > 0);
        return queue.elements[queue.head];
    }

    /*
    Pred: n > 0 && queue != null
    Post: R == a[1] && n' == n-1 && forall 1 <= i < n : a[i]' == a[i+1]
    dequeue – удалить и вернуть первый элемент в очереди
     */
    public static Object dequeue(ArrayQueueADT queue) {
        assert (size(queue) > 0);
        Object answer = ArrayQueueADT.element(queue);
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return answer;
    }

    /*
    Pred: true && queue != null
    Post: R == (n==0) && Immutable
    isEmpty – является ли очередь пустой
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        return (size(queue) == 0);
    }

    /*
    Pred: true && queue != null
    Post: n' == 0
    clear – удалить все элементы из очереди
     */
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.tail = 0;
        queue.head = 0;
    }

    /*Pred: 1 <= ind <= n && n > 0 && queue != null
    Post: R = a[ind] && Immutable
    get – получить элемент по индексу, отсчитываемому с головы
     */
    public static Object get(ArrayQueueADT queue, int ind) {
        assert (ind + 1 <= size(queue));
        assert (ind + 1 >= 1);
        assert (size(queue) > 0);
        return queue.elements[(ind + queue.head) % queue.elements.length];
    }

    /*Pred: 1 <= ind <= n && n > 0 && queue != null
    Post: a[ind]' = new element && forall i!=ind : a[i]'=a[i] && n' == n
    set – заменить элемент по индексу, отсчитываемому с головы
     */
    public static void set(ArrayQueueADT queue, int ind, Object e) {
        assert (ind + 1 <= size(queue));
        assert (ind + 1 >= 1);
        assert (size(queue) > 0);
        queue.elements[(ind + queue.head) % queue.elements.length] = e;
    }

    /*
    Pred: true && queue != null
    Post: R == n && Immutable
    size – текущий размер очереди
    */
    public static int size(ArrayQueueADT queue) {
        if (queue.tail - queue.head >= 0) {
            return (queue.tail - queue.head);
        } else {
            return (queue.elements.length - queue.head + queue.tail);
        }
    }

    private static void ensureCapacity(ArrayQueueADT queue,int capacity) {
        if (capacity >= queue.elements.length) {
            if (queue.tail -  queue.head > 0) {
                queue.elements = Arrays.copyOf( queue.elements, capacity * 2);
            } else {
                Object[] res = new Object[capacity * 2];
                int j = 0;
                System.arraycopy( queue.elements,  queue.head, res, 0,  queue.elements.length -  queue.head);
                System.arraycopy( queue.elements, 0, res,  queue.elements.length -  queue.head,  queue.tail);
                queue.tail = size(queue);
                queue.elements = res;
                queue.head = 0;
            }
        }
    }
}

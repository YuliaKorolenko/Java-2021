package queue;

import java.util.Arrays;

public class ArrayQueueModule {
 /*
    Model:
        a[1]...a[n]
    Invariant : forall 1 <= i <= n : a[i]!=null && n>=0
    Immutable : forall 1 <= i <= n : a[i]'==a[i] && n' == n
     */

    private static int head = 0;
    private static int tail = 0;
    private static Object[] elements = new Object[2];


    /*
    Pred: new element != null
    Post: n' == n + 1 && a[n']' = new element && forall 1 <= i <= n : a[i]'== a[i]
    enqueue – добавить элемент в очередь
     */
    public static void enqueue(Object e) {
        assert (e != null);
        ensureCapacity(size() + 1);
        elements[tail] = e;
        tail = (tail + 1) % elements.length;
    }

    /*
    Pred: n > 0
    Post: R == a[1] && Immutable
    element – первый элемент в очереди
     */
    public static Object element() {
        assert (size() > 0);
        return elements[head];
    }

    /*
    Pred: n > 0
    Post: R == a[1] && n' == n-1 && forall 1 <= i < n : a[i]' == a[i+1]
    dequeue – удалить и вернуть первый элемент в очереди
     */
    public static Object dequeue() {
        assert (size() > 0);
        Object answer = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        return answer;
    }

    /*
    Pred: true
    Post: R == (n==0) && Immutable
    isEmpty – является ли очередь пустой
     */
    public static boolean isEmpty() {
        return (size() == 0);
    }

    /*
    Pred: true
    Post: n' == 0
    clear – удалить все элементы из очереди
     */
    public static void clear() {
        elements = new Object[2];
        tail = 0;
        head = 0;
    }

    /*Pred: 1 <= ind <= n && n > 0
    Post: R = a[ind] && Immutable
    get – получить элемент по индексу, отсчитываемому с головы
     */
    public static Object get(int ind) {
        assert (ind + 1 <= size());
        assert (ind + 1 >= 1);
        assert (size() > 0);
        return elements[(ind + head) % elements.length];
    }

    /*Pred: 1 <= ind <= n && n > 0
    Post: a[ind]' = new element && forall i!=ind : a[i]'=a[i] && n' == n
    set – заменить элемент по индексу, отсчитываемому с головы
     */
    public static void set(int ind, Object e) {
        assert (ind + 1 <= size());
        assert (ind + 1 >= 1);
        assert (size() > 0);
        elements[(ind + head) % elements.length] = e;
    }

    /*
    Pred: true
    Post: R == n && Immutable
    size – текущий размер очереди
    */
    public static int size() {
        if (tail - head >= 0) {
            return (tail - head);
        } else {
            return (elements.length - head + tail);
        }
    }

    private static void ensureCapacity(int capacity) {
        if (capacity >= elements.length) {
            if (tail - head > 0) {
                elements = Arrays.copyOf(elements, capacity * 2);
            } else {
                Object[] res = new Object[capacity * 2];
                int j = 0;
                System.arraycopy(elements, head, res, 0, elements.length - head);
                System.arraycopy(elements, 0, res, elements.length - head, tail);
                tail = size();
                elements = res;
                head = 0;
            }
        }
    }
}


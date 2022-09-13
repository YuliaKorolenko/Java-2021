package queue;


/*
Model:
    a[1]...a[n]
Invariant : forall 1 <= i <= n : a[i]!=null && n>=0
Immutable : forall 1 <= i <= n : a[i]'==a[i] && n' == n
 */
public interface Queue {
    /*
    Pred: new element != null
    Post: n' == n + 1 && a[n']' = new element && forall 1 <= i <= n : a[i]'== a[i]
    enqueue – добавить элемент в очередь
     */
    void enqueue(Object element);

    /*
    Pred: n > 0
    Post: R == a[1] && Immutable
    element – первый элемент в очереди
    }
     */
    Object element();


    /*
    Pred: n > 0
    Post: R == a[1] && n' == n-1 && forall 1 <= i < n : a[i]' == a[i+1]
    dequeue – удалить и вернуть первый элемент в очереди
     */
    Object dequeue();


    /*
    Pred: true
    Post: R == (n==0) && Immutable
    isEmpty – является ли очередь пустой
     */
    boolean isEmpty();

    /*
    Pred: true
    Post: n' == 0
    clear – удалить все элементы из очереди
     */
    void clear();


    /*
    Pred: true
    Post: R == n && Immutable
    */
    int size();

    /*
    Pred: 0 <= ind < n && n > 0
    Post: R = a[ind + 1] && Immutable
    get – получить элемент по индексу, отсчитываемому с головы
     */
    Object get(int ind);

    /*
   Pred: 0 <= ind < n && n > 0
   Post: a[ind+ 1]' = new element && forall i!=ind+1 : a[i]'=a[i] && n' == n
   set – заменить элемент по индексу, отсчитываемому с головы
    */
    void set(int ind, Object e);

    /*
    Pred : true
    Post : R = a[] && forall 1 <= i <= n : a[i]'==a[i] && n' == n
     */
    Object toArray();
}
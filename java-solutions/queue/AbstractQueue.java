package queue;

// +
public abstract class AbstractQueue implements Queue {

    protected int size = 0;

    public void enqueue(Object e) {
        assert (e != null);
        size++;
        enqueueImp(e);
    }

    protected abstract void enqueueImp(Object e);

    public Object element() {
        assert (size > 0);
        return elementImp();
    }

    protected abstract Object elementImp();

    public Object dequeue() {
        assert (size > 0);
        size--;
        return dequeueImp();
    }

    protected abstract Object dequeueImp();


    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return (size);
    }

    public Object get(int ind) {
        assert (ind + 1 <= size());
        assert (ind + 1 >= 1);
        assert (size() > 0);
        return getImp(ind);
    }

    public Object toArray() {
        Object[] res = new Object[size];
        for (int i = 0; i < size(); i++) {
            res[i] = dequeue();
            enqueue(res[i]);
        }
        return res;
    }


    protected abstract Object getImp(int ind);

    public void set(int ind, Object e) {
        assert (ind + 1 <= size());
        assert (ind + 1 >= 1);
        assert (size() > 0);
        setImp(ind, e);
    }

    protected abstract void setImp(int ind, Object e);

}

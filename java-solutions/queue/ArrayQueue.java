package queue;

import java.util.Arrays;

public class ArrayQueue extends AbstractQueue {

    private int head = 0;
    private int tail = 0;
    private Object[] elements = new Object[2];

    protected void enqueueImp(Object e) {
        ensureCapacity(size());
        elements[tail] = e;
        tail = (tail + 1) % elements.length;
    }

    protected Object elementImp() {
        return (elements[head]);
    }

    protected Object dequeueImp() {
        Object answer = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return answer;
    }

    public void clear() {
        size = 0;
        elements = new Object[2];
        tail = 0;
        head = 0;
    }


    protected Object getImp(int ind) {
        return elements[(ind + head) % elements.length];
    }

    protected void setImp(int ind, Object e) {
        elements[(ind + head) % elements.length] = e;
    }

    private void ensureCapacity(int capacity) {
        if (capacity >= elements.length) {
            if (tail - head > 0) {
                elements = Arrays.copyOf(elements, capacity * 2);
            } else {
                Object[] res = new Object[capacity * 2];
                System.arraycopy(elements, head, res, 0, elements.length - head);
                System.arraycopy(elements, 0, res, elements.length - head, tail);
                tail = size() - 1;
                elements = res;
                head = 0;
            }
        }
    }

}

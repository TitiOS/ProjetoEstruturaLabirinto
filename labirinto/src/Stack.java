// Stack.java
public class Stack<T> {
    private final T[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == data.length - 1; }

    public void push(T value) {
        if (!isFull()) data[++top] = value;
    }

    public T pop() {
        if (isEmpty()) return null;
        return data[top--];
    }

    public T peek() {
        if (isEmpty()) return null;
        return data[top];
    }

    public int size() { return top + 1; }
}

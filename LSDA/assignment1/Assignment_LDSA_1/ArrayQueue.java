package assignment1;

public class ArrayQueue implements Queue<Object> {
    private Object[] Q;
    int front;
    int rear;
    int size;

    public ArrayQueue(int capacity) {
        // TASK 3.A.a
        //i initialised the queue with a given capacity.
        Q = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;

    }

    public void enqueue(Object x) {
        // TASK 3.A.b
        // i check if the queue is full then i add x to the back of the queue.
        if (size == Q.length) {
            System.out.print("Queue is full.");
        }
        else {
            rear = (rear + 1) % Q.length;
            Q[rear] = x;
            size ++;
        }
    }

    public Object dequeue() {
        // TASK 3.A.c
        // i check if the queue is empty then i return the item at the front of the queue.
        if (size == 0) {
            System.out.print("Queue is empty.");
            return -1;
        }
        else {
            Object n = Q[front];
            front = (front + 1) % Q.length;
            size --;
            return n;
        }

    }

    public Object next() {
        // TASK 3.A.d
        // i check if the queue is empty then return the item in the front of the queue.
        if (size == 0) {
            System.out.print("Queue is empty.");
            return -1;
        }
        else {
            return Q[front];
        }

    }

    public boolean empty() {
        // TASK 3.A.e
        // i return true if the queue is empty and false otherwise.
        return size == 0;
    }

    public static void main(String[] args) {
        Queue<Object> test = new ArrayQueue(20);
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.enqueue(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.next());
        for (int i=0; i<5; i++) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.enqueue(i);
        }
        while (!test.empty()) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}

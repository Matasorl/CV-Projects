package assignment1;

public class DynamicQueue implements Queue<Object> {
    List<Object> Q = new DoubleLinkedList();

    public void enqueue(Object x) {
        // TASK 3.B.a
        // i used the append method to add to the queue because it adds x to the end of the queue
        Q.append(x);
    }

    public Object dequeue() {
        // TASK 3.B.b
        // i check if the queue was empty then used two methods to get and remove an element.
        if (Q.empty()) {
            System.out.print("Queue is empty.");
            return -1;
        }
        else {
            Object n = Q.getFirst();
            Q.deleteFirst();
            return n;
        }

    }

    public Object next() {
        // TASK 3.B.c
        // i used the getFirst method to return the next element in the queue.
        return Q.getFirst();
    }

    public boolean empty() {
        // TASK 3.B.d
        // i used the empty method the return a boolean if the queue was empty or not/
        return Q.empty();
    }

    public static void main(String[] args) {
        Queue<Object> test = new DynamicQueue();
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

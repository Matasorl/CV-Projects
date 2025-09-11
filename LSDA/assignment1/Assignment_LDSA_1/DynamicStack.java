package assignment1;

public class DynamicStack implements Stack<Object> {
    List<Object> S = new DoubleLinkedList();

    public void push(Object x) {
        // TASK 2.B.a
        // i used the prepend method because i am adding x to the top of the stack thus it is last in.
        S.prepend(x);
    }

    public Object pop() {
        // TASK 2.B.b
        // i check if the stack is empty then getting and deleting the element at the top of the stack.
        if (S.empty()) {
            System.out.print("Stack underflow, Stack is empty, can't pop.");
        }
        else {
            Object n = S.getLast();
            S.deleteLast();
            return n;
        }
        return -1;
    }

    public Object peek() {
        // TASK 2.B.c
        // i check if the stack is empty then returning the top element using getLast.
        if (S.empty()) {
            System.out.print("Stack is empty, can't peek.");
        }
        else {
            return S.getLast();
        }
        return -1;
    }

    public boolean empty() {
        // TASK 2.B.d
        // i return a boolean using the empty method.
        return S.empty();
    }

    public static void main(String[] args) {
        Stack<Object> test = new DynamicStack();
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.push(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.peek());
        for (int i=0; i<5; i++) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.push(i);
        }
        while (!test.empty()) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}

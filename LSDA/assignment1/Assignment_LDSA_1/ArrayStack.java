package assignment1;



public class ArrayStack implements Stack<Object> {
    private Object[] S;
    private int top;


    public ArrayStack(int capacity) {
        // TASK 2.A.a
        // i initialised the stack and set the top to -1 to show an empty stack
        S = new Object[capacity];
        top = -1;
    }

    public void push(Object x) {
        // TASK 2.A.b
        // i check if stack is empty then increment the top and then add x to the stack.
        if (top == S.length - 1) {
            System.out.print("Stack overflow, Stack is full, can't push.");
        }
        else {
            S[++top] = x;
        }
    }

    public Object pop() {
        // TASK 2.A.c
        // i check if the stack is empty then return the top element and then increment down the stack.
        if (top == -1) {
            System.out.print("Stack underflow, Stack is empty, can't pop.");
        }
        else {
            return S[top--];
        }
        return -1;
    }

    public Object peek() {
        // TASK 2.A.d
        // i check if the stack is empty then return the top element.
        if (top == -1) {
            System.out.print("Stack is empty, can't peek.");
        }
        else {
            return S[top];
        }
        return -1;
    }

    public boolean empty() {
        // TASK 2.A.e
        // i return a boolean true if the stack is empty and false otherwise.
        return top == -1;
    }

    public static void main(String[] args) {
        Stack<Object> test = new ArrayStack(20);
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

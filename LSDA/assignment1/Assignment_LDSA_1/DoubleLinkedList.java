package assignment1;

import java.util.ArrayList;


public class DoubleLinkedList implements List<Object> {
    private class ListNode {
        public ListNode(Object x) {
            key = x;
        }
        public Object key;
        public ListNode prev = null;
        public ListNode next = null;
    }

    private ListNode head;
    private ListNode tail;

    public DoubleLinkedList() {
        // TASK 1.A
        // i initialised an empty list

        this.head = null;
        this.tail = null;

    }

    public void prepend(Object x) {
        // TASK 1.B
        // i check if the list is empty then add x based on if empty or not.
        ListNode newNode = new ListNode(x);

        if (head == null) {
            head = newNode;
            tail = newNode;

        }
        else {
            newNode.next = head;
            newNode.prev = null;
            head.prev = newNode;
            head = newNode;
        }
    }

    public Object getFirst() {
        // TASK 1.C
        // i returned the element x that is in the first position of the list after checking if it was empty
        if (head == null) {
            System.out.print("List is empty");
        }
        else {
            return head.key;
        }
        return -1;
    }

    public void deleteFirst() {
        // TASK 1.D
        // i check if list is empty then delete the first based on amount of objects in list.
        if (head == null) {
            System.out.print("List is empty");
        }
        else {
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                head = head.next;
            }
        }
    }

    public void append(Object x) {
        // TASK 1.E
        // i check if list is empty then add x based on that.
        ListNode newNode = new ListNode(x);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;

        }
    }

    public Object getLast() {
        // TASK 1.F
        // i returned the element x that is in the last position of the list after checking if the list was empty.
        if (tail == null) {
            System.out.print("List is empty");
        }
        else {
            return tail.key;
        }
    return -1;
    }

    public void deleteLast() {
        // TASK 1.G
        // i check if the list is empty then delete the last element in the list based on amount of objects in list
        if (tail == null) {
            System.out.print("List is empty.");
        }
        else {
            if (tail == head) {
                tail = null;
                head = null;
            }
            else {
                tail = tail.prev;
            }
        }
    }

    public boolean empty() {
        // TASK 1.H
        // i returned a boolean if the list was empty
        if (head == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<Object> test = new DoubleLinkedList();
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.prepend(i + 100);
        }
        System.out.println(test.empty());
        for (int i=0; i<5; i++) {
            int x = (int)test.getFirst();
            System.out.print(x + " ");
            test.deleteFirst();
        }
        System.out.println();
        for (int i=0; i<10; i++) {
            test.append(i + 200);
        }
        while (!test.empty()) {
            int x = (int)test.getLast();
            System.out.print(x + " ");
            test.deleteLast();
        }
    }
}

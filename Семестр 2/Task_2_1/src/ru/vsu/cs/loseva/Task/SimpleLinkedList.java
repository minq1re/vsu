package ru.vsu.cs.loseva.Task;

import ru.vsu.cs.loseva.Task.ListNode;

import java.util.Iterator;

public class SimpleLinkedList<T extends Comparable<T>> implements Iterable<T>{


    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }
    private ListNode<T> head = null;
    private ListNode<T> tail = null;
    private int count = 0;
    private ListNode<T> sorted = null;

    public int size(){ return count; }

    public void addFirst(T value) {
        head = new ListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        ListNode<T> temp = new ListNode<>(value);
        if (count == 0) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
    }

    //Task
    private void sortedInsert(ListNode<T> newNode) {
        if (sorted == null || (sorted.value.compareTo(newNode.value) > 0)) {
            newNode.next = sorted;
            sorted = newNode;
        } else {
            ListNode<T> current = sorted;
            while (current.next != null && (current.next.value.compareTo(newNode.value) < 0)) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void insertionSort() {
        sorted = null;
        ListNode<T> current = head;
        while (current != null) {
            ListNode<T> next = current.next;
            sortedInsert(current);
            current = next;
        }
        head = sorted;
        for (ListNode<T> newTail = head; newTail != null; newTail = newTail.next) {
            tail = newTail;
        }
    }

    private ListNode<T> getNode(int index) {
        ListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T removeFirst() {
        T value = head.value;
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
        return value;
    }

    public T removeLast() throws SimpleLinkedListException {
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            ListNode<T> prev = getNode(index - 1);
            ListNode<T> curr = prev.next;
            value = curr.value;
            prev.next = curr.next;
            if (index == count - 1) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            ListNode<T> prev = getNode(index - 1);
            prev.next = new ListNode<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
        }
        count++;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();

        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();

        return tail.value;
    }

    public T get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }

    private void checkEmpty() throws SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            ListNode<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ListNode<T> current = head; current != null; current = current.next) {
            sb.append(current.value);
            sb.append(" ");
        }

        return sb.toString();
    }
}

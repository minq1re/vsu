package ru.vsu.cs.loseva;

public class SimpleLinkedListQueue2<T extends Comparable<T>> implements SimpleQueue<T> {

    private class SimpleLinkedListNode {
        public T value;
        public SimpleLinkedListNode next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode head = null;  // first, top
    private SimpleLinkedListNode tail = null;  // last
    private int count = 0;
    public int size(){ return count; }

    @Override
    public void bubbleSorted() {
        if (count <= 1) {
            return;
        }
        SimpleLinkedListNode sortedHead = null;
        SimpleLinkedListNode sortedTail = null;
        while (head != null) {
            SimpleLinkedListNode current = head;
            SimpleLinkedListNode previous = null;
            SimpleLinkedListNode tmp = head;
            while (current != null && current.next != null) {
                if (tmp.value.compareTo(current.next.value) > 0) {
                    previous = current;
                    tmp = current.next;
                }
                current = current.next;
            }
            if (tmp == head) {
                head = head.next;
            } else {
                previous.next = tmp.next;
            }
            tmp.next = null;
            if (sortedHead == null) {
                sortedHead = tmp;
                sortedTail = tmp;
            } else {
                sortedTail.next = tmp;
                sortedTail = tmp;
            }
        }
        head = sortedHead;
        tail = sortedTail;

    }
    @Override
    public void add(T value) {
        if (count == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        count++;
    }

    @Override
    public T remove() throws Exception {
        T result = element();
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
        return result;
    }

    @Override
    public T element() throws Exception {
        if (count() == 0) {
            throw new Exception("Queue is empty");
        }
        return head.value;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public T get(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        SimpleLinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }
}

package com.gb.hw2.linkedList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;
    private CustomIterator<T> iterator;

    public LinkedList() {
        first = null;
        last = null;
        iterator = new CustomIterator<>(this);
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private void resetIterator() {
        if (iterator != null) {
            iterator.reset();
        }
    }

    public void push(T object) {
        if (object == null) return;
        Node<T> newNode = new Node<>(object);
        newNode.setNext(first);
        if (isEmpty()) last = newNode;
        else first.setPrevious(newNode);
        first = newNode;
        resetIterator();
        size++;
    }

    public void pushLast(T object) {
        if (object == null) return;
        Node<T> newNode = new Node<>(object);
        newNode.setPrevious(last);
        if (isEmpty()) first = newNode;
        else last.setNext(newNode);
        last = newNode;
        resetIterator();
        size++;
    }

    public T popFirst() {
        if (isEmpty()) return null;
        T object = first.getObject();
        if (first.getNext() == null) last = null;
        first = first.getNext();
        first.setPrevious(null);
        resetIterator();
        size--;
        return object;
    }

    public T popLast() { // Queue
        if (isEmpty()) return null;
        T object = last.getObject();
        last = last.getPrevious();
        if (last != null) last.setNext(null);
        resetIterator();
        size--;
        return object;
    }

    public T peek() {
        if (isEmpty()) return null;
        return first.getObject();
    }

    public T peekLast() {
        if (isEmpty()) return null;
        return last.getObject();
    }

    public void display() {
        Node<T> current = first;
        System.out.println("List start");
        while (current != null) {
            System.out.println(current.getObject());
            current = current.getNext();
        }
        System.out.println("List end");
    }

    @Override
    public String toString() {
        Node<T> current = first;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.getNext();
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }

    public T find(T object) {
        Node<T> curr = first;
        while (!curr.getObject().equals(object)) {
            if (curr.getNext() == null) {
                return null;
            } else {
                curr = curr.getNext();
            }
        }
        return curr.getObject();
    }

    public T delete(T object) {
        if (object == null) return null;
        Node<T> curr = first;
        Node<T> prev = first;
        while (!curr.getObject().equals(object)) {
            if (curr.getNext() == null) return null;
            else {
                prev = curr;
                curr = curr.getNext();
            }
        }
        if (curr == first && curr == last) {
            first = null;
            last = null;
        }
        else if (curr == first) {
            first = first.getNext();
            first.setPrevious(null);
        } else if (curr == last) {
            last = last.getPrevious();
            last.setNext(null);
        } else {
            curr.getPrevious().setNext(curr.getNext());
            curr.getNext().setPrevious(prev);
        }
        resetIterator();
        size--;
        return curr.getObject();
    }
}

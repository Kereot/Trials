package com.gb.hw2.linkedList;

import lombok.Getter;

import java.util.Objects;

public class CustomIterator<T> {
    @Getter
    private Node<T> first;
    private final LinkedList<T> list;

    public CustomIterator(LinkedList<T> list) {
        this.list = list;
        reset();
    }

    public void reset() {
        first = list.getFirst();
    }

    public void nextLink() {
        if (first.getNext() != null) first = first.getNext();
        else reset();
    }

    public Object getCurrent() {
        if (first == null) return null;
        return first.getObject();
    }

    public boolean atEnd() {
        return first.getNext() == null;
    }

    public void insertAfter(T object) {
        if (object == null) return;
        Node<T> newNode = new Node<>(object);
        if (!list.isEmpty()) {
            if (!atEnd()) {
                newNode.setNext(first.getNext());
                first.getNext().setPrevious(newNode);
            } else list.setLast(newNode);
            first.setNext(newNode);
            newNode.setPrevious(first);
        } else {
            list.setFirst(newNode);
            list.setLast(newNode);
            first = newNode;
        }
        list.setSize(list.getSize() + 1);
    }

    public void insertBefore(T object) {
        if (object == null) return;
        Node<T> newNode = new Node<>(object);
        if (!list.isEmpty()) {
            if (first.getPrevious() != null) {
                newNode.setPrevious(first.getPrevious());
                first.getPrevious().setNext(newNode);
            }
            else list.setFirst(newNode);
            first.setPrevious(newNode);
            newNode.setNext(first);
        } else {
            list.setFirst(newNode);
            list.setLast(newNode);
            first = newNode;
        }
        list.setSize(list.getSize() + 1);
    }

    public T deleteCurrent() {
        if (first == null) return null;
        T object = first.getObject();
        if (first.getPrevious() == null && !atEnd()) {
            list.setFirst(null);
            list.setLast(null);
            reset();
        } else if (first.getPrevious() == null) {
            list.setFirst(first.getNext());
            list.getFirst().setPrevious(null);
            reset();
        } else if (atEnd()) {
            list.setLast(first.getPrevious());
            list.getLast().setNext(null);
            first = first.getPrevious();
        } else {
            first.getPrevious().setNext(first.getNext());
            first.getNext().setPrevious(first.getPrevious());
            first = first.getNext();
        }
        list.setSize(list.getSize() - 1);
        return object;
    }


    private Node<T> getNode(int idx) {
        if (list.isEmpty()) return null;
        Node<T> mark = list.getFirst();
        for (int i = 0; i <= idx; i++) {
            if (i == idx) {
                return mark;
            }
            if (mark.getNext() == null) return null;
            mark = mark.getNext();
        }
        return null;
    }

    public T getMyObject(int idx) {
        if (idx < 0 || idx > (list.getSize() - 1))
            throw new IndexOutOfBoundsException("There's no such object basket");
        if (getNode(idx) == null) throw new NullPointerException("There's no object in this basket");
        else return Objects.requireNonNull(getNode(idx)).getObject();
    }
}

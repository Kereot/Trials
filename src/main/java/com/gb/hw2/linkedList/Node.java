package com.gb.hw2.linkedList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node<T> {
    private T object;
    private Node<T> next;
    private Node<T> previous;

    public Node(T object){
        this.object = object;
    }

    @Override
    public String toString() {
        return "Node{" +  "object=" + object;
    }
}

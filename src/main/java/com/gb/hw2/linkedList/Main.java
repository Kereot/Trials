package com.gb.hw2.linkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.display();
        list.pushLast(55);
        System.out.println(list.delete(3));
        System.out.println(list.popFirst());
        list.push(6);
        list.display();
        System.out.println(list.getIterator().getMyObject(2));
        System.out.println(list.getIterator().getCurrent());
        list.getIterator().insertAfter(3);
        list.display();
        System.out.println(list.getIterator().deleteCurrent());
    }
}

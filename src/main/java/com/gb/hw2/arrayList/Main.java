package com.gb.hw2.arrayList;

import lombok.AllArgsConstructor;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> intArray = new ArrayList<>(5);
        ArrayList<Cat> catArray = new ArrayList<>();

        intArray.add(10);
        intArray.add(20);
        intArray.add(30);
        System.out.println(intArray.getSize());
        try {intArray.rewrite(3, 40);}
        catch (IndexOutOfBoundsException e) {System.out.println("meow");}
        intArray.rewrite(2, 40);
        intArray.add(50);
        System.out.println(intArray);
        intArray.add(9, 100);
        System.out.println(intArray);
        intArray.add(7, 60);
        System.out.println(intArray);
        System.out.println(intArray.removeByIndex(2));
        System.out.println(intArray);
        intArray.add(60);
        intArray.add(120);
        intArray.add(60);
        System.out.println(intArray.removeFirstByValue(60));
        System.out.println(intArray);
        System.out.println(intArray.removeFirstByValue(null));
        System.out.println(intArray.get(5));
        System.out.println(intArray);
        System.out.println(intArray.countValues(70));
        System.out.println(intArray.countValues(null));
        while (intArray.removeFirstByValue(null));
        System.out.println(intArray);
        intArray.removeAll();
        intArray.add(200);
        intArray.add(150);
        intArray.add(4, 100);
        System.out.println(intArray.removeFirstByValue(60));
        System.out.println(intArray);

        catArray.add(12, new Cat(1));
        catArray.add(new Cat(2));
        catArray.add(2, new Cat(3));
        catArray.rewrite(5, new Cat(4));
        System.out.println(catArray);

    }

    @AllArgsConstructor
    private static class Cat {
        private int number;

        @Override
        public String toString() {
            return "Cat{" +
                    "number=" + number +
                    '}';
        }
    }
}

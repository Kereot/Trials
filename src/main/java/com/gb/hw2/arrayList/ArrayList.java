package com.gb.hw2.arrayList;

import lombok.Getter;

public class ArrayList<T> {
    private T[] arr;
    @Getter
    private int size;
    private final int DEFAULT_SIZE = 10;

    public ArrayList(int size) {
        this.size = 0;
        if (size < DEFAULT_SIZE) {
            size = 10;
        }
        arr = (T[]) new Object[size];
    }

    public ArrayList() {
        this.size = 0;
        arr = (T[]) new Object[DEFAULT_SIZE];
    }

    private void grow() {
        T[] temp = arr;
        arr = (T[]) new Object[temp.length * 2];
        System.arraycopy(temp, 0, arr, 0, temp.length);
    }

    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    public void add(T object) {
        if (size >= arr.length){ // ==
            grow();
        }
        arr[size++] = object;
    }

    private void checkIndex(int index) {
        if (index < 0) throw new IllegalArgumentException();
        while (index >= arr.length) grow();
    }

    public void add(int index, T object) { // Here I assume one can add at any desired index, not limited to the size
        checkIndex(index);
        if (index >= size) {
            for (int i = size; i < index; i++) {
                arr[i] = null; // To rewrite any old values
            }
            arr[index] = object;
            size = index + 1;
        } else {
            T[] temp = (T[]) new Object[++size];
            for (int i = 0; i < size; i++) {
                if (i < index - 1) {
                    temp[i] = arr[i];
                } else if (i == index - 1) {
                    temp[i] = object;
                } else {
                    temp[i] = arr [i - 1];
                }
            }
            arr = temp;
        }
    }

    public void rewrite(int index, T object) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        arr[index] = object;
    }

    public T removeByIndex(int index) {
        checkIndex(index);
        if (arr == null || index >= size) {
            throw new RuntimeException();
        }
        T removedItem = arr[index];
        T[] temp = (T[]) new Object[size--];
        for (int i = 0, j = 0; i <= size; i++) {
            if (i == index) continue;
            temp[j++] = arr[i];
        }
        arr = temp;
        return removedItem;
    }

    public boolean removeFirstByValue(T object) {
        for (int i = 0; i < size; i++) {
            if (object == null) {
                if (arr[i] == null) {
                    System.arraycopy(arr, i + 1, arr, i, --size - i);
                    return true;
                }
            } else if (object.equals(arr[i])) {
                System.arraycopy(arr, i + 1, arr, i, --size - i);
                return true;
            }
        }
        return false;
    }

    public boolean removeAll() {
        size = 0;
        return true;
    }

    public int countValues(T object) {
        int count = 0;
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    count++;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(arr[i])) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        if (arr == null)
            return "null";

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}

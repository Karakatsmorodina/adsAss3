package com.company;

import java.util.ArrayList;

public class MyHashTable<K, V> {

    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private final ArrayList<ArrayList<HashNode<K, V>>> chainArray;
    private final int size;

    public MyHashTable() {
        this(11); // default number of chains
    }

    public MyHashTable(int M) {
        this.size = M;
        chainArray = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            chainArray.add(i, new ArrayList<HashNode<K, V>>());
    }

    private int hash(K key) {
        return key.hashCode() % size;
    }

    public void put(K key, V value) {
        int index = hash(key);
        ArrayList<HashNode<K, V>> entryList = chainArray.get(index);
        HashNode<K, V> newEntry = new HashNode<K, V>(key, value);
        boolean isFound = false;
        for (HashNode<K, V> entry : entryList) {
            if (entry.key.equals(newEntry.key)) {
                isFound = true;
                entry.value = newEntry.value;
            }
        }
        if (!isFound) {
            entryList.add(newEntry);
        }
    }

    public V get(K key) {
        int index = hash(key);
        ArrayList<HashNode<K, V>> entryList = chainArray.get(index);
        for (HashNode<K, V> entry : entryList) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        ArrayList<HashNode<K, V>> entryList = chainArray.get(index);
        for (HashNode<K, V> entry : entryList) {
            if (entry.key.equals(key)) {
                chainArray.remove(index);
                return entry.value;
            }
        }
        return null;
    }

    public boolean contains(V value) {
        for (ArrayList<HashNode<K, V>> hashNodes : chainArray) {
            if (hashNodes.size() != 0 && hashNodes.get(0).value == value) {
                return true;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (ArrayList<HashNode<K, V>> hashNodes : chainArray) {
            if (hashNodes.size() != 0 && hashNodes.get(0).value == value) {
                return hashNodes.get(0).key;
            }
        }
        return null;
    }
}
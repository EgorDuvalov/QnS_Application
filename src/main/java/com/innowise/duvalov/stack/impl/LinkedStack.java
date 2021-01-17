package com.innowise.duvalov.stack.impl;

import com.innowise.duvalov.exception.EmptyCollectionException;
import com.innowise.duvalov.exception.ExceedCapacityException;
import com.innowise.duvalov.exception.IllegalCapacityValueException;
import com.innowise.duvalov.node.Node;
import com.innowise.duvalov.stack.Stack;

import java.util.ArrayList;

/**
 * Stack...
 *
 * @param <T>
 */

public class LinkedStack<T> implements Stack<T> {
    private Node<T> topNode;
    private int amount = 0; // amount of Nodes
    private int capacity = 0;

    public LinkedStack() {
    }

    public LinkedStack(int capacity) throws IllegalCapacityValueException {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        throw new IllegalCapacityValueException();
    }

    public LinkedStack(ArrayList<T> array)
            throws ExceedCapacityException {
        for (T element : array) {
            push(element);
        }
    }

    public LinkedStack(ArrayList<T> array, int capacity)
            throws ExceedCapacityException, IllegalCapacityValueException {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalCapacityValueException();
        }
        for (T element : array) {
            push(element);
        }
    }

    public void push(T element) throws ExceedCapacityException {
        if (topNode == null) {
            topNode = new Node<>(element);
            amount++;

        } else if (capacity != 0 && amount > capacity) {
            throw new ExceedCapacityException();

        } else {
            topNode = new Node<>(element, topNode);
            amount++;
        }
    }

    public T pop() throws EmptyCollectionException {
        if (topNode == null) {
            throw new EmptyCollectionException();
        }
        T value = topNode.getValue();
        topNode = topNode.getNext();
        return value;
    }

    public T peek() throws EmptyCollectionException {
        if (topNode == null) {
            throw new EmptyCollectionException();
        }
        return topNode.getValue();
    }

    public int size() {
        return amount;
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    public boolean isFull() throws IllegalCapacityValueException {
        if (capacity == 0) {
            throw new IllegalCapacityValueException();
        }
        return amount == capacity;
    }
}

/* pop for queue
            Node<T> runner = topNode;
            while (runner.getNextNode().hasNext()) {
                runner = runner.getNextNode();
            }
            value = runner.getNextNode().getValue();
            runner.setNextNode(null);
* */
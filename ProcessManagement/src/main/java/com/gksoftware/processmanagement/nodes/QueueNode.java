/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processqueues.nodes;

import java.io.Serializable;


/**
 *
 * @author Geek-Programmer
 * @param <E> Generic class of element
 */
public class QueueNode<E> implements Serializable{
    
    /**
     * Constants for serialization
     */
    private static final long serialVersionUID = 1L;
    private E element;
    private QueueNode<E> next;
    private int size;

    /**
     * Save the first node
     * @param front first node
     */
    public QueueNode(E front) {
        this.element = front;
    }

    /**
     * Return the element of node queue
     * @return node queue
     */
    public E getElement() {
        return element;
    }

    /**
     * Return the next element of queue
     * @return Next element of queue
     */
    public QueueNode<E> getNext() {
        return next;
    }

    /**
     * Return the size of queue
     * @return size of queue
     */
    public int size() {
        return size;
    }
    
    /**
     * Save the next element of queue
     * @param next Element to save in next pointer
     */
    public void setNext(QueueNode<E> next) {
        this.next = next;
    }

    /**
     * Convert the node in String
     * @return Node String
     */
    @Override
    public String toString() {
        return "(" + "front=" + element + ')';
    }
    
}

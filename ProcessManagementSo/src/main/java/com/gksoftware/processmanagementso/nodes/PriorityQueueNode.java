/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.nodes;

import java.io.Serializable;

/**
 *
 * @author Geek-Programmer
 * @param <T> Generic class Type
 * @param <E> Generic class Element
 */
public class PriorityQueueNode<T extends Comparable<? super T>, E> implements Serializable {
    
    /**
     * Constants for serialization
     */
    private static final long serialVersionUID = 1L;

    private E element;//Element of node

    private T priority;//Element of priority queue
    
    private T pid;//Element of pid queue

    private PriorityQueueNode<T, E> nextNode;//Next element

    /**
     * Constructor by parameters. <br>
     * <b>post: </b> The node with the specified element was built,
     * sigNodo = null and element = pElement.<br>
     *
     * @param priority Element set priority of queue.
     * Different null.<br>
     * @param element Element set queue different null<br>
     * @param pid Element set queue of id
     */
    public PriorityQueueNode(T priority, E element, T pid) {
        this.element = element;
        this.priority = priority;
        this.pid = pid;
        nextNode = null;
    }

    /**
     * Return element of node. <br>
     * <b>post: </b> The element contained in the node was returned.<br>
     * @return The element contained in the node. Different from null<br>
     */
    public E getElement() {
        return element;
    }

    /**
     * Returns the element that gives priority to the node.<br>
     * <b>post: </b> The element that gives priority to the node was returned.<br>
     *
     * @return The element that gives priority to the node. Different from null<br>
     */
    public T getPriority() {
        return priority;
    }
    
    /**
     * Returns the element that gives id to the node.<br>
     * <b>post: </b> The element that gives id to the node was returned.<br>
     *
     * @return The element that gives priority to the node. Different from null<br>
     */
    public T getPid() {
        return pid;
    }
    
    /**
     * Disconnect the node from the queue assuming it is the first. <br>
     * <b>pre: </b> The current node is the first node of the queue. <br>
     * <b>post: </b> The node with which the queue begins now was returned,
     * nextNode=null.<br>
     *
     * @return Node with which the queue begins now.<br>
     */
    public PriorityQueueNode<T, E> unlinkFirst() {
        PriorityQueueNode<T, E> p = nextNode;
        nextNode = null;
        return p;
    }

    /**
     * Insert the specified node after the current node. <br>
     * <b>post: </b> The node was inserted after the current node, which implies
     * nextNode=node.<br>
     *
     * @param node The node to be inserted<br>
     * @return Node that was inserted after the current node<br>
     */
    public PriorityQueueNode<T, E> insertAfter(PriorityQueueNode<T, E> node) {
        nextNode = node;
        return node;
    }

    /**
     * Returns the next node. <br>
     * <b>post: </b> The next node was returned.<br>
     *
     * @return Node next<br>
     */
    public PriorityQueueNode<T, E> getNext() {
        return nextNode;
    }

    /**
     * Convert the node to a String. <br>
     * <b>post: </b> The representation in String of the node was returned. Bliss
     * representation corresponds to the representation in String of the element that
     * contains.
     *
     * @return The String representation of the node
     */
    @Override
    public String toString() {
        return "(" + priority.toString() + "," + element.toString() + ")";
    }
}

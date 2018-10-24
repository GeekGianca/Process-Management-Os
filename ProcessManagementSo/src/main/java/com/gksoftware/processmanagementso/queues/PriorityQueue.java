/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.queues;

import com.gksoftware.processmanagementso.nodes.PriorityQueueNode;
import java.io.Serializable;
import com.gksoftware.processmanagementso.exceptions.QueueEmptyException;

/**
 *
 * @author Geek-Programmer
 * @param <T> Generic class Type
 * @param <E> Generic class Element
 */
public class PriorityQueue<T extends Comparable<? super T>, E> implements Serializable {

    /**
     * Constant for serialization
     */
    private static final long serialVersionUID = 1L;

    protected PriorityQueueNode<T, E> front;//First element of queue

    protected PriorityQueueNode<T, E> back;//Last element of queue

    protected int size;//Size of queue

    public PriorityQueue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Return number elements of queue
     *
     * @return @size
     */
    public int size() {
        return size;
    }

    /**
     * Return element of queue.<br>
     * Throw Exception if Queue is empty<br>
     *
     * @return @element
     * @throws QueueEmptyException
     */
    public E getElement() throws QueueEmptyException {
        if (front == null) {
            throw new QueueEmptyException("There are no elements in the queue");
        } else {
            PriorityQueueNode<T, E> p = front;
            front = front.unlinkFirst();
            if (front == null) {
                back = null;
            }
            size--;
            return p.getElement();
        }
    }

    /**
     * Add element to queue by priority. <br>
     * <b>pre: </b> element!=null. <br>
     * <b>post: </b> Insert element in the queue by priority.
     *
     * @param priority this element get the priority of queue
     * @param element This element is the insert in the queue
     * @param pid This element of id the process in the queue
     */
    public void push(T priority, E element, T pid) {
        PriorityQueueNode<T, E> node = new PriorityQueueNode<>(priority, element, pid);
        if (front == null) {
            front = node;
            back = node;
        } //Check if have most value of priority in the first element of queue
        else if (front.getPriority().compareTo(priority) > 0) {
            node.insertAfter(front);
            front = node;
        } else {
            //Go through queue to find node a minor priority
            boolean insert = false;
            for (PriorityQueueNode<T, E> p = front; !insert && p.getNext() != null; p = p.getNext()) {
                if (p.getNext().getPriority().compareTo(priority) > 0) {
                    node.insertAfter(p.getNext());
                    p.insertAfter(node);
                    insert = true;
                }
            }
            if (!insert) {
                //You have not inserted it because it has the lowest priority of the entire queue
                back = back.insertAfter(node);
            }
        }
        size++;
    }

    /**
     * Return first element of node but not delete<br>
     *
     * @return first element of queue
     */
    public PriorityQueueNode<T, E> peek() {
        return front;
    }

    /**
     * Return lasted element of queue<br>
     *
     * @return Last element of queue<br>
     */
    public PriorityQueueNode<T, E> getBack() {
        return back;
    }
    
    /**
     * Remove the node that contains an equal pid
     * @param pid node to delete
     * @return node deleted
     */
    public PriorityQueueNode<T, E> remove(T pid) {
        PriorityQueueNode<T, E> deletedNode = null;
        if (front != null) {
            PriorityQueueNode<T, E> tempNode = front;
            front = null;
            while (tempNode != null) {
                if (!pid.equals(tempNode.getPid())) {
                    push(tempNode.getPriority(), (E) tempNode, tempNode.getPid());
                } else {
                    deletedNode = tempNode;
                }
                tempNode = tempNode.getNext();
            }
            return deletedNode;
        }
        return null;
    }

    /**
     * Remove the first node from the queue
     * @return node removed
     */
    public PriorityQueueNode<T, E> pop() {
        PriorityQueueNode<T, E> temp = null;
        if (!isEmpty()) {
            temp = front;
            front = front.getNext();
            if (front == null) {
                back = null;
            }
            size--;
        }
        return temp;
    }

    /**
     * Check the queue is empty (without elements). <br>
     * <b>post: </b> returns true if it has no elements and otherwise false.<br>
     *
     * @return True == null or False otherwise<br>
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Convert String queue. <br>
     * <b>post: </b> The representation in String of the queue was returned. He
     * String has the format "[numberElements]: e1-> e2-> e3 ..-> in", where e1,
     * e2, ..., in are the elements of the queue and numberElements its size.
     * <br>
     *
     * @return Representation of String queue
     */
    @Override
    public String toString() {
        String resp = "[" + size + "]:";
        for (PriorityQueueNode<T, E> p = front; p != null; p = p.getNext()) {
            resp += "->" + p.toString() + "\n\n";
        }
        return resp;
    }
}

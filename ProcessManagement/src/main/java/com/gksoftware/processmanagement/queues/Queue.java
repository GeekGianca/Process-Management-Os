/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.queues;

import com.gksoftware.processqueues.nodes.QueueNode;
import java.io.Serializable;

/**
 *
 * @author Geek-Programmer
 * @param <E>
 */
public class Queue<E> implements Serializable{
    
    /**
     * Constant for serialization
     */
    private static final long serialVersionUID = 1L;
    
    protected QueueNode<E> front;
    protected QueueNode<E> back;
    protected int size;

    public Queue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    private void setFront(QueueNode<E> front) {
        this.front = front;
    }

    private void setBack(QueueNode<E> back) {
        this.back = back;
    }

    public QueueNode<E> peek() {
        return front;
    }

    public QueueNode<E> last() {
        return back;
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty(){
        return front == null;
    }
    
    public void push(E nodeElement){
        QueueNode<E> newnode = new QueueNode<>(nodeElement);
        if (front == null) {
            setFront(newnode);
            setBack(newnode);
        }else{
            last().setNext(newnode);
        }
        setBack(newnode);
        size++;
    }
    
    public QueueNode<E> pop() {
        QueueNode<E> temp = null;
        if (!isEmpty()) {
            temp = peek();
            front = front.getNext();
            if (front == null) {
                setBack(null);
            }
            size--;
        }
        return temp;
    }
    
    @Override
    public String toString() {
        String resp = "[" + size + "]:";
        for (QueueNode<E> p = front; p != null; p = p.getNext()) {
            resp += "->" + p.toString() + "\n\n";
        }
        return resp;
    }
}

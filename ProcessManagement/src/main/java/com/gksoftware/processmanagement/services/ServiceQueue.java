/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import com.gksoftware.processmanagement.model.Process;

/**
 *
 * @author Geek-Programmer
 */
public class ServiceQueue {

    private int size;
    private Process frontNode;
    private Process finalNode;

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Process getFrontNode() {
        return frontNode;
    }

    public void setFrontNode(Process frontNode) {
        this.frontNode = frontNode;
    }

    public Process getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Process finalNode) {
        this.finalNode = finalNode;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public void push(Process newprocess) {
        if (getFrontNode() == null) {
            setFrontNode(newprocess);
        } else {
            getFinalNode().setNext(newprocess);
        }
        setFinalNode(newprocess);
        size++;
    }

    public void pop() {
        Process temp = null;
        if (!isEmpty()) {
            temp = getFrontNode();
            setFrontNode(getFrontNode().getNext());
            if (getFrontNode() == null) {
                setFinalNode(null);
            }
            temp = null;
            size--;
        }
    }

    public Process peek() {
        return getFrontNode();
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    public boolean remove(String pid) {
        ServiceQueue temp = new ServiceQueue();
        boolean isDelete = false;
        while (!isEmpty()) {
            if (!peek().getPid().equalsIgnoreCase(pid)) {
                temp.push(frontNode);
                pop();
            }
        }
        while (!temp.isEmpty()) {
            if (!temp.peek().getPid().equalsIgnoreCase(pid)) {
                push(temp.peek());
                temp.pop();
                isDelete = true;
            }
        }
        return isDelete;
    }
}

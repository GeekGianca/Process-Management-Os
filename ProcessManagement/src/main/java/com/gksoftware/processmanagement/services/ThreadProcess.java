/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import static com.gksoftware.processmanagement.Common.REPLACEMENT_CHAR;
import com.gksoftware.processmanagement.model.Process;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Geek-Programmer
 */
public class ThreadProcess implements Runnable {

    private Thread tProcess;
    private Process process;
    private long timeArrive;
    private JFXProgressBar progress;
    private Label lblPid;
    private Label lblName;
    private boolean isSuspend = false;
    private int counterTime;

    public ThreadProcess(Process process) {
        this.process = process;
        this.counterTime = 0;
    }

    public Thread gettProcess() {
        return tProcess;
    }

    public Label getLblPid() {
        return lblPid;
    }

    public void setLblPid(Label lblPid) {
        this.lblPid = lblPid;
    }

    public Label getLblName() {
        return lblName;
    }

    public void setLblName(Label lblName) {
        this.lblName = lblName;
    }

    public Process getProcess() {
        return process;
    }

    public long getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(long timeArrive) {
        this.timeArrive = timeArrive;
    }

    public JFXProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JFXProgressBar progress) {
        this.progress = progress;
    }

    @Override
    public void run() {
        try {
            double progress = 100/process.getBurst();
            double percentage = 0;
            while (process.getBurst() <= 0) {
                lblName.setText(process.getName());
                lblPid.setText(process.getPid());
                String newChar = changeCharacters(process.getCharacters(), process.getPositionsChange(counterTime));
                process.setCharacterReplaced(newChar);
                this.progress.setProgress(percentage);
                System.out.println(process.getCharacters());
                percentage += progress;
                synchronized (this) {
                    while (isSuspend) {
                        wait();
                    }
                }
                process.setBurst(process.getBurst()-1);
                Thread.sleep(process.getBurstTime());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void execute() {
        if (tProcess == null) {
            tProcess = new Thread(this);
            tProcess.start();
        }
    }

    public void suspend() {
        isSuspend = true;
    }

    public synchronized void resume() {
        isSuspend = false;
        notify();
    }

    public String changeCharacters(String characters, int position) {
        String newcharacter = characters;
        if (position <= characters.length()) {
            newcharacter = characters.subSequence(0, position - 1) + REPLACEMENT_CHAR + characters.substring(position, characters.length());
        }
        return newcharacter;
    }

}

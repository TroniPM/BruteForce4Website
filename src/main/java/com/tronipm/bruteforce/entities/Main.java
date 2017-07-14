/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforce.entities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Main {

    public Robot r = null;
    public Thread thread = null;

    public Main() {
        this.r = new Robot();
        this.thread = new Thread(r);

    }

    public void start() {
        if (thread != null) {

            thread.start();

            synchronized (thread) {
                try {
                    thread.wait();
                } catch (InterruptedException e) {
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (Settings.suppressWarnings) {
            Logger.getLogger("com.gargoylesoftware.htmlunit.javascript").setLevel(Level.OFF);
            Logger.getLogger("com.gargoylesoftware.htmlunit.WebClient").setLevel(Level.OFF);
        }

        new Main().start();
    }
}

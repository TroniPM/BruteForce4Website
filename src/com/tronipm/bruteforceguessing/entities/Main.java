/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

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
        this.r = new Robot("http://darkedenghostx.servegame.com/index.php", 100);
        this.thread = new Thread(r);

    }

    public void print() {
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
        Logger.getLogger("com.gargoylesoftware.htmlunit.javascript").setLevel(Level.OFF);
        Logger.getLogger("com.gargoylesoftware.htmlunit.WebClient").setLevel(Level.OFF);

        PrintStream oldErr = System.err;
        PrintStream newErr = new PrintStream(new ByteArrayOutputStream());
        //System.setErr(newErr);//suppress warnings

        Main m = new Main();
        m.print();

        //System.setErr(oldErr);//suppress warnings
    }
}

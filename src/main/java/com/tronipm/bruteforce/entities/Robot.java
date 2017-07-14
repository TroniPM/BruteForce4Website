/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforce.entities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Robot extends Thread {

    private WebSite webSite = null;
    private Reader reader = null;
    private Writer writer = null;
    private int delayBetweenAttempts = 0;

    private static final boolean needsToRedirectAfterAttemp = false;

    public ArrayList<Credential> arraySuccess = new ArrayList<>();

    public Robot() {
        this.webSite = new WebSite(Settings.url);
        this.delayBetweenAttempts = Settings.delay;

        reader = new Reader(Settings.file1, Settings.file2);
        writer = new Writer(Settings.fileSuccess);
    }

    @Override
    public void run() {
        synchronized (this) {
            reader.start();
            webSite.open(true);

            System.out.println("Starting tests...");
            int attempts = 1;

            int applyDelay = 0;

            for (int u = 0; u < reader.arrayUsers.size(); u++) {
                String user = reader.arrayUsers.get(u);

                for (int p = 0; p < reader.arrayPasswords.size(); p++) {
                    String pass = reader.arrayPasswords.get(p);

                    Credential c = new Credential(user, pass);

                    System.out.println("Attempt: " + (attempts++));

                    tryToLogin(c);

                    if (needsToRedirectAfterAttemp) {
                        restart();
                    }

                    if (Settings.quantityOfAttempsUntilApplyDelay > 0) {
                        applyDelay++;
                        if (applyDelay == Settings.quantityOfAttempsUntilApplyDelay) {
                            applyDelay = 0;

                            try {
                                Thread.sleep(Settings.delay);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
            System.out.println("Finishing tests...");

            this.notify();
        }
    }

    public synchronized void tryToLogin(Credential c) {

        boolean flag = WantedBehaviour.checkNOT(webSite, c);

        if (flag) {
            arraySuccess.add(c);

            writer.writeOnFile(c.getData() + "\n");
            //c.print();
            webSite.restart();//After success, start over
        }
    }

    synchronized private void restart() {
        webSite.restart();
    }

}

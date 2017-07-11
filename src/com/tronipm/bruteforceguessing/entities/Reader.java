/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Change this class to suit your needs.
 *
 * @author Mateus
 */
public class Reader {

    //private static final String separator = "*-*";
    public List<String> arrayUsers;// = new ArrayList<String>();
    public List<String> arrayPasswords;// = new ArrayList<String>();
    public String file1, file2;

    public Reader(String file1, String file2) {
        this.file1 = file1;
        this.file2 = file2;
    }

    synchronized public void start() {
        System.out.println("Starting Reader: " + file1 + ", " + file2);
        arrayUsers = readFromFile(file1);
        arrayPasswords = readFromFile(file2);
        System.out.println("Started Reader: " + file1 + ", " + file2);
    }

    synchronized private static List<String> readFromFile(String file) {
        String[] linhas = null;
        List<String> array = new ArrayList<String>();
        try {
            FileInputStream fin = new FileInputStream(file);
            byte[] a = new byte[fin.available()];
            fin.read(a);
            fin.close();
            linhas = new String(a).split("\n");
        } catch (Exception e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
        }

        array = Arrays.asList(linhas);
        return array;
    }
}

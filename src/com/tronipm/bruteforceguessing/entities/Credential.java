/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

/**
 * Change this class to suit your needs. (One more field, etc)
 *
 * @author Mateus
 */
public class Credential {

    private String username = null;
    private String password = null;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    synchronized public void print() {
        System.out.println("Username: " + username + "\tPassword: " + password);
    }

    synchronized public String getUsername() {
        return username;
    }

    synchronized public void setUsername(String username) {
        this.username = username;
    }

    synchronized public String getPassword() {
        return password;
    }

    synchronized public void setPassword(String password) {
        this.password = password;
    }

    synchronized public String getData() {
        return username + "\t" + password;
    }

}

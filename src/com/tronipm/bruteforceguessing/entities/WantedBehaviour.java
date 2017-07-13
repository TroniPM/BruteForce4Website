/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

/**
 *
 * TODO. Change this class to suit your needs. (One more field, etc)
 *
 */
public class WantedBehaviour {

    private static final String idInputLogin = "inp_login";
    private static final String idInputPassword = "inp_senha";
    private static final String unsuccessfulAttemptMessage = "Login ou senha incorretos";
    private static final String successfulAttemptMessage = "Olá";
    private static final String idInputButton = "btn_login";

    /**
     * Check if website respond as you expected
     *
     * @param webSite do whatever you need with the webpage
     * @param c edit the class to suit your needs
     * @return
     */
    public static boolean check(WebSite webSite, Credential c) {
        webSite.setInputByName(idInputLogin, c.getUsername());
        webSite.setInputByName(idInputPassword, c.getPassword());
        webSite.clickByName(idInputButton);

        return webSite.checkPageHasText(successfulAttemptMessage);
    }

    /**
     * Check if website respond as you expected, but in negative way
     *
     * @param webSite do whatever you need with the webpage
     * @param c edit the class to suit your needs
     * @return
     */
    public static boolean checkNOT(WebSite webSite, Credential c) {
        webSite.setInputByName(idInputLogin, c.getUsername());
        webSite.setInputByName(idInputPassword, c.getPassword());
        webSite.clickByName(idInputButton);

        return webSite.checkPageHasText(unsuccessfulAttemptMessage);
    }
}

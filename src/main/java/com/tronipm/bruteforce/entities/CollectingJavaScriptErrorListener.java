/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforce.entities;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Mateus
 */
public class CollectingJavaScriptErrorListener implements JavaScriptErrorListener {

    private final StringBuilder scriptExceptions_ = new StringBuilder();
    private final StringBuilder timeoutErrors_ = new StringBuilder();
    private final StringBuilder loadScriptErrors_ = new StringBuilder();
    private final StringBuilder malformedScriptURLErrors_ = new StringBuilder();

    @Override
    public void scriptException(HtmlPage hp, ScriptException scriptException) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        scriptExceptions_.append(scriptException.toString());
    }

    @Override
    public void timeoutError(HtmlPage hp, long allowedTime, long executionTime) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        timeoutErrors_.append("Timeout allowed: " + allowedTime);
    }

    @Override
    public void malformedScriptURL(HtmlPage hp, String string, MalformedURLException murle) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        malformedScriptURLErrors_.append(string + ", " + murle);
    }

    @Override
    public void loadScriptError(HtmlPage hp, URL url, Exception excptn) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        loadScriptErrors_.append(url + ", " + excptn);
    }

    public StringBuilder getScriptExceptions_() {
        return scriptExceptions_;
    }

    public StringBuilder getTimeoutErrors_() {
        return timeoutErrors_;
    }

    public StringBuilder getLoadScriptErrors_() {
        return loadScriptErrors_;
    }

    public StringBuilder getMalformedScriptURLErrors_() {
        return malformedScriptURLErrors_;
    }
}

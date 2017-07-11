/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class WebSite {

    private String urlSite = null;
    private WebClient webClient = null;
    private HtmlPage page = null;

    public WebSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public synchronized String getUrlSite() {
        return urlSite;
    }

    public synchronized void open(boolean flag) {
        if (flag) {
            System.out.println("Starting WebClient: " + this.urlSite);
        }
        this.webClient = new WebClient();
        this.webClient.getOptions().setThrowExceptionOnScriptError(false);
        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

        final CollectingJavaScriptErrorListener javaScriptErrorListener = new CollectingJavaScriptErrorListener();
        //this.webClient.setJavaScriptErrorListener(javaScriptErrorListener);

        try {
            this.page = webClient.getPage(this.urlSite);
        } catch (IOException ex) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FailingHttpStatusCodeException ex) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (flag) {
            System.out.println("Started WebClient");
        }
    }

    public synchronized boolean setInputByName(String name, String content) {
        try {
            ((HtmlInput) page.getElementByName(name)).setAttribute("value", content);
            return true;
        } catch (Exception e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public synchronized boolean setInputById(String id, String content) {
        try {
            page.getElementById(id).setAttribute("value", content);
            return true;
        } catch (Exception e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public synchronized boolean clickByName(String name) {
        try {
            page = ((HtmlSubmitInput) page.getElementByName(name)).click();
            return true;
        } catch (Exception e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public synchronized boolean clickById(String id) {
        try {
            page = ((HtmlSubmitInput) page.getElementById(id)).click();
            return true;
        } catch (Exception e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public synchronized String getWebPageAsText() {
        try {
            return page.asText();
        } catch (Exception ex) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public synchronized String getWebPageAsHtml() {
        try {
            return page.asXml();
        } catch (Exception ex) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public synchronized boolean checkPageHasText(String pattern) {
        String webPageAsText = page.asText();

        return webPageAsText.contains(pattern);
    }

    public synchronized boolean checkPageHasElemendById(String id) {
        try {
            return page.getElementById(id) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized boolean checkPageHasElemendByName(String name) {
        try {
            return page.getElementByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void close() {
        webClient.close();//closeAllWindows();
    }

    public synchronized void restart() {
        open(false);
    }

    /*
public class HtmlUnitFormExample {
    public static void main(String[] args) throws Exception {
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://www.google.com");

        HtmlInput searchBox = page.getElementByName("q");
        searchBox.setValueAttribute("htmlunit");

        HtmlSubmitInput googleSearchSubmitButton = 
                          page.getElementByName("btnG"); // sometimes it's "btnK"
        page=googleSearchSubmitButton.click();

        HtmlDivision resultStatsDiv =
                                page.getFirstByXPath("//div[@id='resultStats']");

        System.out.println(resultStatsDiv.asText()); // About 309,000 results
        webClient.closeAllWindows();
    }
}
     */
}

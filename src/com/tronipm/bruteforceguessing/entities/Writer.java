/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Writer {

    public String path = null;
    private boolean append = false;

    public Writer(String path) {
        this.path = path;

        writeOnFile("");//cleaning
        append = true;
    }

    synchronized public void writeOnFile(String content) {
        FileOutputStream fop = null;
        File file;
        try {
            file = new File(path);
            fop = new FileOutputStream(file, append);
            //Se arquivo não existe, é criado
            if (!file.exists()) {
                file.createNewFile();
            }
            //pega o content em bytes
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            //flush serve para garantir o envio do último lote de bytes
            fop.flush();
            fop.close();

        } catch (IOException e) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}

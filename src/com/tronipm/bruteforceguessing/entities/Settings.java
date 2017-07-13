/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tronipm.bruteforceguessing.entities;

/**
 *
 * @author Mateus
 */
public abstract class Settings {

    public static boolean suppressWarnings = true; //debug purpose

    public static String url = "http://darkedenghostx.servegame.com/index.php";
    public static int delay = 1000;//mili
    public static int quantityOfAttempsUntilApplyDelay = 0;//every three (four, five, etc) attempts, apply the delay; 0 for disabling

    public static String file1 = "./data/user.txt";
    public static String file2 = "./data/pass.txt";
    public static String fileSuccess = "./data/success.txt";
}

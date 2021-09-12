package com.eu;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        final int LOMUTO = 0;
        final int HORAE = 1;
        final int MEDIAN3 = 0;
        final int RANDOM = 1;
        if (args.length <= 0)
            throw new IllegalArgumentException("Give me a file name");

        QuickSort mySorter = new QuickSort("C:\\Users\\nikol\\IdeaProjects\\Lab2CPD\\src\\com\\eu\\entrada.txt",LOMUTO,MEDIAN3);
            mySorter.sortArraysInFile();


    }
}



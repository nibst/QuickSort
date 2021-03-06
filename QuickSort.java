package com.eu;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickSort {

    private static final int lomuto = 0;
    private static final int hoare = 1;
    private static final int median3 = 0;
    private static final int random = 1;

    private static final Logger LOG = Logger.getLogger(QuickSort.class.getName());
    private static final String OUTPUT = "output.txt";
    private int partitioning;
    private int partitioner;
    private int time;
    private int nRecursions;
    private Scanner scanner;
    private File outputFile;
    public int nSwaps;


    public QuickSort(final String fileName, final int hoareOrLom, final int randOrMed) {
        partitioning = hoareOrLom;
        partitioner = randOrMed;
        nSwaps = 0;
        File inputFile = new File(fileName);
        File outputFile = new File(OUTPUT);
        try {
            scanner = new Scanner(inputFile);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, "No input file");
        }
    }

    public void sortArraysInFile() {
        while (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            int[] unsortedArray = new int[size];
            for (int i = 0; i < size; i++) {
                unsortedArray[i] = scanner.nextInt();
            }
            int i = 0;
            int f = size - 1;
            int[] sortedArray = quickSort(unsortedArray, i, f);
            for (i = 0; i < size; i++)
                System.out.print(sortedArray[i] + " ");
        }

        scanner.close();
    }


    public int[] sortArray(int[] array, int i, int f) {
        // TODO
        return null;
    }

    private void swap(int i, int j, int[] array) {
        int aux;
        aux = array[i];
        array[i] = array[j];
        array[j] = aux;
        nSwaps++;
    }

    private int medianIndex(int[] array, int i, int f) {
        //DIMINUIR ESSA FUNC
        int index = 0;
        int a, b, c;
        a = array[i];
        b = array[(f+1-i) / 2];
        c = array[(f+1-i)  - 1];

        int maior = a;

        if (maior < b)
            maior = b;
        if (maior < c)
            maior = c;
        //pega a mediana entre os 3 numeros
        if (maior == a) {
            if (b > c) {
                //mediana ?? b
                index = (f+1-i)  / 2;
            } else {
                //mediana ?? a
                index = i;
            }
        }
        if (maior == b) {
            if (a > c) {
                //mediana ?? a
                index = i;
            } else {
                //mediana ?? c
                index = f;
            }
        }
        if (maior == c) {
            if (b > a) {
                //mediana ?? b
                index = (f+1-i) / 2;
            } else {
                //mediana ?? a
                index = i;
            }
        }
        return index;

    }

    public int medianPartition(int[] array, int i, int f) {

        int index = medianIndex(array, i, f);
        //swap entre o index da mediana de 3 e a primeira posi????o
        swap(i, index, array);
        if (partitioning == 1)
            return horaePartitioning(array, i, f);
        else
            return lomutoPartitioning(array, i, f);
    }


    private int randomPartition(int[] array, int i, int f) {
        int random = (int) (Math.random() * (f-i)) +i ;
        swap(i, random, array);
        if (partitioning == 1)
            return horaePartitioning(array, i, f);
        else
            return lomutoPartitioning(array, i, f);
    }

    public int[] quickSort(int[] array, int i, int f) {
        int p;
        if (f > i) {
            for (int j = i; j <= f; j++)
                System.out.print(array[j] + " ");
            System.out.println("");
            if (partitioner == 1)
                p = randomPartition(array, i, f);
            else
                p = medianPartition(array, i, f);
            quickSort(array, i, p - 1);
            quickSort(array, p + 1, f);
        }
        //talvez tirar isso dps
        return array;

    }

    private int lomutoPartitioning(int[] array, int i, int f) {
        int particionador = array[i];
        int m = i;
        int n;
        for (n = i + 1; n <= f; n++) {
            if (array[n] <= particionador) {
                m++;
                //n??o faz sentido dar swap no msm lugar
                if (m != n)
                    swap(m, n, array);
            }
        }
        //troca o primeiro elemento(particionador), pelo ultimo elemento a esquerda
        swap(m, i, array);
        //retorn o index do elemento que agora est?? sorted.
        return m;

    }

    public int horaePartitioning(int[] array, int i, int f) {
        int particionador = array[i];
        int m = i;
        int n = f;
        while(n>m) {
            while (array[n] > particionador)
                n--;
            while (array[m] <= particionador && n>m)
                m++;
            swap(m, n, array);
        }
        //troca o primeiro elemento, que ?? o particionador, pelo ultimo elemento da esquerda
        swap(i,n,array);
        return n;
    }
}

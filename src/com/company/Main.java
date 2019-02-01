package com.company;

import com.company.interfaces.GeneticAlgorithm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int a,b,c,d,count;
        Scanner scanner = new Scanner(System.in);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlghoritmImpl();

        System.out.println("podaj parametr a: ");
        a = scanner.nextInt();

        System.out.println("podaj parametr b: ");
        b = scanner.nextInt();

        System.out.println("podaj parametr c: ");
        c = scanner.nextInt();

        System.out.println("podaj parametr d: ");
        d = scanner.nextInt();

        System.out.println("podaj ilosc obrotw: ");
        count = scanner.nextInt();

        geneticAlgorithm.generateTwentyPopulations();
        geneticAlgorithm.calculateAdjustmentFunction(a, b, c, d);

        for(int i=0;i<count;i++) {
            geneticAlgorithm.rouletteWheel();
            geneticAlgorithm.crucifixion();
            geneticAlgorithm.mutation();
            geneticAlgorithm.calculateAdjustmentFunction(a, b, c, d);
            geneticAlgorithm.increaseQuantityPhenotype();
            if(geneticAlgorithm.ifLargestPhenotype())
            {
                System.out.println("ilośc obrotów: "+i);
                break;
            }

        }

    }
}


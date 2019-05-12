package com.company;

import com.company.interfaces.GeneticAlgorithm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        double a,b,c,d;
        int count;
        Scanner scanner = new Scanner(System.in);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlghoritmImpl();

        System.out.println("podaj parametr a: ");
        a = scanner.nextDouble();

        System.out.println("podaj parametr b: ");
        b = scanner.nextDouble();

        System.out.println("podaj parametr c: ");
        c = scanner.nextDouble();

        System.out.println("podaj parametr d: ");
        d = scanner.nextDouble();

        System.out.println("podaj ilosc obrotw: ");
        count = scanner.nextInt();

        geneticAlgorithm.generateTwentySubjects();
        geneticAlgorithm.adjustmentFunction(a, b, c, d);

        for(int i=0;i<count;i++) {
            geneticAlgorithm.rouletteWheel();
            geneticAlgorithm.crucifixion();
            geneticAlgorithm.mutation();
            geneticAlgorithm.adjustmentFunction(a, b, c, d);
            geneticAlgorithm.increaseQuantityPhenotype();
            if(geneticAlgorithm.ifLargestPhenotype())
            {
                System.out.println("ilośc obrotów: "+i);
                break;
            }

        }
        ((GeneticAlghoritmImpl) geneticAlgorithm).showMax();

//        System.out.println(String.format("%e", 12.345));
    }
}


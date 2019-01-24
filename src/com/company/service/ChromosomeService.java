package com.company.service;

public class ChromosomeService {
    public static String phenotypeToChromosome(int number) {
        String chromosome;
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (number > 0){
            result.append(number%2);
            i++;
            number = number/2;
        }

        while((result.length())<7){
            result.append("0");
        }

        result.reverse();
        chromosome = String.valueOf(result);
        return chromosome;
    }

    public static int chromosomeToPhenotype(String bit){
        return 0;
    }
}

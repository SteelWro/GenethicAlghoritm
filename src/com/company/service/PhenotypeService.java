package com.company.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class PhenotypeService {
    static public Map<Integer, java.lang.String> randTwentyPhenotypes() {
        Map<Integer, java.lang.String> phenotypes = new LinkedHashMap();
        Random randPhenotype;
        while (phenotypes.size() < 20) {
            randPhenotype = new Random();
            int tmpRand = randPhenotype.nextInt(129);
            phenotypes.put(tmpRand, ChromosomeService.phenotypeToChromosome(tmpRand));
        }
    return phenotypes;
    }
}

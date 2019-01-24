package com.company.interfaces;

public interface GeneticAlgorithm {
    void generateTwentyPopulations();
    void calculateAdjustmentFunction(int a, int b, int c, int d);
    void rouletteWheel();
    void crucifixion();
    void mutation();

    void showPopulations();
}

package com.company.interfaces;

public interface GeneticAlgorithm {
    void generateTwentySubjects();
    void adjustmentFunction(double a, double b, double c, double d);
    void rouletteWheel();
    void crucifixion();
    void mutation();
    boolean ifLargestPhenotype();
    void increaseQuantityPhenotype();
    void showPopulations();
}

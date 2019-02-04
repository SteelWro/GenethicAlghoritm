package com.company.service;

public class AdjustmentFunctionService {
    public static Double calculateAdjustmentFunction(double a, double b, double c, double d, int phenotype){
        double adjustmentFunction = 0;
        adjustmentFunction = Math.abs(a * Math.cos(phenotype+1) + b * Math.pow(phenotype,3) + c * Math.log(phenotype) + d * Math.sqrt(phenotype));
        return adjustmentFunction;
    }
}

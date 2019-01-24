package com.company.service;

public class AdjustmentFunctionService {
    public static Double getAdjustmentFunction(int a, int b, int c, int d, int phenotype){
        double adjustmentFunction = 0;
        adjustmentFunction = a * Math.cos(phenotype+1) + b * Math.pow(phenotype,3) + c * Math.log(phenotype) + d * Math.sqrt(phenotype);
        return adjustmentFunction;
    }
}

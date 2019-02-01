package com.company.Model;

public class Population {
    private int id;
    private int phenotype;
    private String chromosome;
    private double adjustmentFunction;

    public Population(int id, int phenotype, String chromosome) {
        this.id = id;
        this.phenotype = phenotype;
        this.chromosome = chromosome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(int phenotype) {
        this.phenotype = phenotype;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public double getAdjustmentFunction() {
        return adjustmentFunction;
    }

    public void setAdjustmentFunction(double adjustmentFunction) {
        this.adjustmentFunction = adjustmentFunction;
    }

    @Override
    public String toString() {
        return "Population{" +
                "id=" + id +
                ", phenotype=" + phenotype +
                ", chromosome=" + chromosome +
                ", adjustmentFunction=" + adjustmentFunction +
                '}';
    }
}



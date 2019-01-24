package com.company;

import com.company.Model.Population;
import com.company.interfaces.GeneticAlgorithm;
import com.company.service.AdjustmentFunctionService;
import com.company.service.PhenotypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneticAlghoritmImpl implements GeneticAlgorithm {

    private List<Population> populations;
    private double[] rouleteWheelPercentage;
    private double Pk;
    private double Pm;
    private double sumOfAdjustmentFunction;

    GeneticAlghoritmImpl() {
        populations = new ArrayList<Population>();
        Pk = 0.75;
        Pm = 0.1;
        sumOfAdjustmentFunction = 0;
        rouleteWheelPercentage = new double[20];
    }

    public void generateTwentyPopulations(){
        int i = 0;
        Map<Integer,String> phenotypesAndChromosomes = PhenotypeService.randTwentyPhenotypes();

        for(Map.Entry<Integer, String> entry : phenotypesAndChromosomes.entrySet()){
            populations.add(new Population(i,entry.getKey(),entry.getValue()));
            i++;
        }
    }

    @Override
    public void calculateAdjustmentFunction(int a, int b, int c, int d) {
        for(Population sub : populations){
            double tmpAdjustmentFunction = AdjustmentFunctionService.getAdjustmentFunction(a,b,c,d,sub.getPhenotype());
            sub.setAdjustmentFunction(tmpAdjustmentFunction);
            sumOfAdjustmentFunction += tmpAdjustmentFunction;
        }
    showPopulations();
    }

    @Override
    public void rouletteWheel() {
        int i = 0;
        boolean flag = false;
        for(Population sub : populations){
            double adjustmentFunction = sub.getAdjustmentFunction();
            if(flag == true)
                rouleteWheelPercentage[i] =  (adjustmentFunction/sumOfAdjustmentFunction) * 100 + rouleteWheelPercentage[i-1];
            else{
                rouleteWheelPercentage[i] =  (adjustmentFunction/sumOfAdjustmentFunction) * 100;
                flag = true;
            }
            i++;
        }
        showPopulations();
    }

    @Override
    public void crucifixion() {
        Random rand;
        float los;
        int crucifixionPlace;
        char[] chromosomeOne;
        char[] chromosomeTwo;

        for(int i = 0; i < 20; i+=2){
            rand = new Random();
            los = (float) Math.random();
            System.out.println(los);
            chromosomeOne = populations.get(i).getChromosome().toCharArray();
            chromosomeTwo = populations.get(i+1).getChromosome().toCharArray();
            if(los<Pk){
                crucifixionPlace = rand.nextInt(7);
                System.out.println(crucifixionPlace);
                char tmp = chromosomeOne[crucifixionPlace];
                chromosomeOne[crucifixionPlace] = chromosomeTwo[crucifixionPlace];
                chromosomeTwo[crucifixionPlace] = tmp;
            }
            populations.get(i).setChromosome(String.valueOf(chromosomeOne));
            populations.get(i+1).setChromosome(String.valueOf(chromosomeTwo));
        }
        showPopulations();
    }

    @Override
    public void mutation() {
        Random rand;
        int mutationPlace;
        char[] chromosome;
        for(int i=0;i<20;i++){
            double p = Math.random();
            if(Math.random()<Pm) {
                rand = new Random();
                mutationPlace = rand.nextInt(7);
                chromosome = populations.get(i).getChromosome().toCharArray();
                chromosome[mutationPlace] = changeBit(chromosome[mutationPlace]);
                populations.get(i).setChromosome(String.valueOf(chromosome));
                populations.get(i).setPhenotype(Integer.parseInt(String.valueOf(chromosome), 2));
            }
        }
        showPopulations();
    }

    public void showPopulations(){
        for(Population sub : populations){
            System.out.println(sub);
        }
        System.out.printf("dexp: %f\n", sumOfAdjustmentFunction);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    private char changeBit(char c){
        if(c=='1') return '0';
        return '1';
    }
}

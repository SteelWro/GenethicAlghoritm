package com.company;

import com.company.Model.Population;
import com.company.interfaces.GeneticAlgorithm;
import com.company.service.AdjustmentFunctionService;
import com.company.service.PhenotypeService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlghoritmImpl implements GeneticAlgorithm {

    private List<Population> populations;
    int[] quantityOfEnlargedPhenotypes;
    int[] enlargedPhenotypes;
    private double Pk;
    private double Pm;
    private double sumOfAdjustmentFunction;
    private double yMAX;
    private double xMAX;
    private int idMAX;

    GeneticAlghoritmImpl() {
        populations = new ArrayList<>();
        enlargedPhenotypes = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        quantityOfEnlargedPhenotypes = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        sumOfAdjustmentFunction = 0;
        Pk = 0.75;
        Pm = 0.1;
        xMAX = 0;
        yMAX = 0;
        idMAX = 999999;
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
        sumOfAdjustmentFunction = 0;
        for(Population sub : populations){
            double tmpAdjustmentFunction = AdjustmentFunctionService.getAdjustmentFunction(a,b,c,d,sub.getPhenotype());
            sub.setAdjustmentFunction(tmpAdjustmentFunction);

            sumOfAdjustmentFunction += tmpAdjustmentFunction;
            if(yMAX<=sub.getAdjustmentFunction()){
                yMAX = sub.getAdjustmentFunction();
                idMAX = sub.getId();
                xMAX = sub.getPhenotype();
            }
        }
        showPopulations();
        System.out.println("największy osobnik: "+populations.get(idMAX));
    }

    @Override
    public void rouletteWheel() {
        List<Population> selectionPopulation = populations;
        double[] rouleteWheelPercentage = new double[populations.size()];
        double[] draw = new double[populations.size()];
        boolean flag = false;
        int i = 0;

        for(Population sub : populations){
                if(flag == true)
                rouleteWheelPercentage[i] =  (sub.getAdjustmentFunction()/sumOfAdjustmentFunction) * 100 + rouleteWheelPercentage[i-1];
                else{
                rouleteWheelPercentage[i] =  (sub.getAdjustmentFunction()/sumOfAdjustmentFunction) * 100;
                flag = true;
                }
                draw[i] = ThreadLocalRandom.current().nextDouble(1, 100.0);
                i++;
                }
        for(int j=0;j<populations.size();j++) {
            int index = Arrays.binarySearch(rouleteWheelPercentage, draw[j]);
            if (index < 0) {
                index = Math.abs(index + 1);
            }
            populations.get(j).setChromosome(selectionPopulation.get(index).getChromosome());
            populations.get(j).setPhenotype(selectionPopulation.get(index).getPhenotype());
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
            los = (float) Math.random();
            chromosomeOne = populations.get(i).getChromosome().toCharArray();
            chromosomeTwo = populations.get(i+1).getChromosome().toCharArray();
            if(los<Pk){
                rand = new Random();
                crucifixionPlace = rand.nextInt(7);
                for(int j=crucifixionPlace+1;j<7;j++) {
                    char tmp = chromosomeOne[j];
                    chromosomeOne[j] = chromosomeTwo[j];
                    chromosomeTwo[j] = tmp;
                }
                populations.get(i).setChromosome(String.valueOf(chromosomeOne));
                populations.get(i+1).setChromosome(String.valueOf(chromosomeTwo));
                populations.get(i).setPhenotype(Integer.parseInt(String.valueOf(chromosomeOne), 2));
                populations.get(i+1).setPhenotype(Integer.parseInt(String.valueOf(chromosomeTwo), 2));
            }
        }
    }

    @Override
    public void mutation() {
        Random rand;
        int mutationPlace;
        char[] chromosome;

        for(int i=0;i<20;i++){
            if(Math.random()<Pm) {
                rand = new Random();
                mutationPlace = rand.nextInt(7);
                chromosome = populations.get(i).getChromosome().toCharArray();
                chromosome[mutationPlace] = changeBit(chromosome[mutationPlace]);
                populations.get(i).setChromosome(String.valueOf(chromosome));
                populations.get(i).setPhenotype(Integer.parseInt(String.valueOf(chromosome), 2));
            }
        }

    }

    public void showPopulations(){
        for(Population sub : populations){
            System.out.println(sub);
        }
        System.out.printf("suma funkcji przystosowania : %f\n", sumOfAdjustmentFunction);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public void showBestPhenotype(){
        System.out.println(populations.get(idMAX));
    }

    private char changeBit(char c){
        if(c=='1') return '0';
        return '1';
    }

    public void increaseQuantityPhenotype(){
        int i = 0;
        for(Population population : populations){
            if(population.getPhenotype() == enlargedPhenotypes[i]) quantityOfEnlargedPhenotypes[i]++;
            else quantityOfEnlargedPhenotypes[i] = 0;
            enlargedPhenotypes[i] = population.getPhenotype();
        }
    }

    public boolean ifLargestPhenotype(){
        for(int i = 0;i < populations.size(); i++){
            if(quantityOfEnlargedPhenotypes[i]==20)
            {
                idMAX = i;
                return true;
            }
        }
        return false;
    }
}



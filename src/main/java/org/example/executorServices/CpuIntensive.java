package org.example.executorServices;

public class CpuIntensive {

    public static void main(String[] args){
        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println(numOfCores);
    }


}

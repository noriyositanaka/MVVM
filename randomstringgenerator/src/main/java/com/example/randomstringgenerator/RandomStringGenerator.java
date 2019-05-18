package com.example.randomstringgenerator;

import java.security.Policy;
import java.util.Random;

import static com.example.randomstringgenerator.RandomStringGenerator.GENERATE_MODE.*;
import static java.lang.Math.abs;

public class RandomStringGenerator {

    private String NUMERS="1234567890";
    private String UPPER_CHARS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String LOWER_CHARS="abcdefghijklmnopqrstuvwxyz";

    private int NUM_COUNT =10;
    private int CHAR_COUNT=52;
    private int NUM_AND_CHAR_COUNT = 62;

    private String POPULATION=null;
    private int COUNT;


    public enum GENERATE_MODE{
        NUM,
        CHAR,
        NUM_AND_CHAR
    }
    private GENERATE_MODE generate_mode = NUM;
    private Integer generate_degits = 8;


    public RandomStringGenerator() {
    }

    public RandomStringGenerator(GENERATE_MODE generate_mode) {
        this.generate_mode = generate_mode;
    }

    public RandomStringGenerator(GENERATE_MODE generate_mode, Integer generate_degits) {
        this.generate_mode = generate_mode;
        this.generate_degits = generate_degits;
    }

    public GENERATE_MODE getGenerate_mode() {
        return generate_mode;
    }

    public void setGenerate_mode(GENERATE_MODE generate_mode) {
        this.generate_mode = generate_mode;
    }

    public Integer getGenerate_degits() {
        return generate_degits;
    }

    public void setGenerate_degits(Integer generate_degits) {
        this.generate_degits = generate_degits;
    }

    public String obtain(){

        String result=null;
        Random random = new Random();
        int i;

        switch(generate_mode){
            case NUM:
                COUNT = NUM_COUNT;
                POPULATION = NUMERS;
                break;
            case CHAR:
                COUNT = CHAR_COUNT;
                POPULATION=UPPER_CHARS+LOWER_CHARS;
                break;
            case NUM_AND_CHAR:
                COUNT=NUM_AND_CHAR_COUNT;
                POPULATION=NUMERS+UPPER_CHARS+LOWER_CHARS;
                break;
        }

        for (int j =0 ;j<generate_degits;j++) {

            i = random.nextInt();
            i = abs(i % COUNT);
            String s = POPULATION.substring(i,i+1);
            if (result == null) {
                result = s;
            } else {

                result = result + s;
            }
        }
        return result;

    }

}

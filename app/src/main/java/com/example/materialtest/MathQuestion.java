package com.example.materialtest;

/**
 * Created by luna on 17-2-6.
 */

public class MathQuestion {
    private int optionNumberA;
    private int optionNumberB;
    private String operator;
    private int result;

    public MathQuestion(String operator, int optionNumberB, int optionNumberA) {
        this.operator = operator;
        this.optionNumberB = optionNumberB;
        this.optionNumberA = optionNumberA;
    }

    public int getOptionNumberA() {
        return optionNumberA;
    }

    public void setOptionNumberA(int optionNumberA) {
        this.optionNumberA = optionNumberA;
    }

    public int getOptionNumberB() {
        return optionNumberB;
    }

    public void setOptionNumberB(int optionNumberB) {
        this.optionNumberB = optionNumberB;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getResult() {
        switch (operator){
            case "+":
                result=optionNumberA+optionNumberB;
                break;

            default:
                break;


        }
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

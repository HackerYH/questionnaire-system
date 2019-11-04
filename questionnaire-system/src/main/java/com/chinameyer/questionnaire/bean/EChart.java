package com.chinameyer.questionnaire.bean;

/**
 * @Author HongYe
 * @Date 2019/10/30 18:22
 */
public class EChart {
    private String questionNum;
    private int optionA;
    private int optionB;
    private int optionC;
    private int optionD;
    private int optionE;
    private int optionF;

    public EChart(String questionNum, int optionA, int optionB, int optionC) {
        this.questionNum = questionNum;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    public EChart(String questionNum, int optionA, int optionB, int optionC, int optionD) {
        this.questionNum = questionNum;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    public EChart(String questionNum, int optionA, int optionB, int optionC, int optionD, int optionE) {
        this.questionNum = questionNum;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
    }

    public EChart(String questionNum, int optionA, int optionB, int optionC, int optionD, int optionE, int optionF) {
        this.questionNum = questionNum;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
        this.optionF = optionF;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public int getOptionA() {
        return optionA;
    }

    public void setOptionA(int optionA) {
        this.optionA = optionA;
    }

    public int getOptionB() {
        return optionB;
    }

    public void setOptionB(int optionB) {
        this.optionB = optionB;
    }

    public int getOptionC() {
        return optionC;
    }

    public void setOptionC(int optionC) {
        this.optionC = optionC;
    }
    public int getOptionD() {
        return optionD;
    }

    public void setOptionD(int optionD) {
        this.optionD = optionD;
    }

    public int getOptionE() {
        return optionE;
    }

    public void setOptionE(int optionE) {
        this.optionE = optionE;
    }

    public int getOptionF() {
        return optionF;
    }

    public void setOptionF(int optionF) {
        this.optionF = optionF;
    }
}

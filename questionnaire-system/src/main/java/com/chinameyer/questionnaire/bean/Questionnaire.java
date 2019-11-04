package com.chinameyer.questionnaire.bean;

import lombok.Data;

/**
 * @Author HongYe
 * @Date 2019/11/1 9:16
 */
@Data
public class Questionnaire {

    private int id;
    private int questionId;
    private int type;
    private int questionOrder;
    private String title;
    private String questionnaireIndex;
    private String question;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getQuestionnaireIndex() {
        return questionnaireIndex;
    }

    public void setQuestionnaireIndex(String questionnaireIndex) {
        this.questionnaireIndex = questionnaireIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

package com.chinameyer.questionnaire.bean;

/**
 * @Author HongYe
 * @Date 2019/11/1 15:12
 */
public class Event {
    private int id;
    private String questionnaireIndex;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionnaireIndex() {
        return questionnaireIndex;
    }

    public void setQuestionnaireIndex(String questionnaireIndex) {
        this.questionnaireIndex = questionnaireIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

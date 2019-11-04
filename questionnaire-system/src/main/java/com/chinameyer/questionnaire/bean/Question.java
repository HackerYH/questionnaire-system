package com.chinameyer.questionnaire.bean;
import lombok.Data;

/**
 * @Author HongYe
 * @Date 2019/10/29 17:24
 */
@Data
public class Question {
    private int id;
    private String question;
    private int type;
    private String options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}

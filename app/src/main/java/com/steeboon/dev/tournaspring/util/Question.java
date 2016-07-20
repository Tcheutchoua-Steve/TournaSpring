package com.steeboon.dev.tournaspring.util;

/**
 * Created by tcheutchoua on 7/19/16.
 */
public class Question  {

    // Variables for the question and it's corresponding answers respectively
    private String question_asked ;
    private String answer1 ;
    private String answer2;
    private String answer3;
    private String answer4;

    public Question() {
    }

    // for questions with three answers
    public Question(String question_asked, String answer1, String answer2, String answer3) {
        this.question_asked = question_asked;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    // For questions with four answers
    public Question(String question_asked, String answer1, String answer2, String answer3, String answer4) {
        this.question_asked = question_asked;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public String getQuestion_asked() {
        return question_asked;
    }

    public void setQuestion_asked(String question_asked) {
        this.question_asked = question_asked;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_asked='" + question_asked + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                '}';
    }
}

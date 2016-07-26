package com.steeboon.dev.tournaspring.util;

/**
 * Created by tcheutchoua on 7/26/16.
 */
public class AnswerCompare {
    private int correctAnswer ;
    private int markedAnswer ;

    public AnswerCompare() {
    }

    public AnswerCompare(int correctAnswer, int markedAnswer) {
        this.correctAnswer = correctAnswer;
        this.markedAnswer = markedAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getMarkedAnswer() {
        return markedAnswer;
    }

    public void setMarkedAnswer(int markedAnswer) {
        this.markedAnswer = markedAnswer;
    }

    public int score (int  correctAnswer , int markedAnswer){
        if (correctAnswer == markedAnswer)
            return 1 ;
        else
            return 0 ;
    }

    @Override
    public String toString() {
       /*return "Answer to Compare {" +
                "correct Answer " + this.correctAnswer +  '\'' +
                "marked answer " + this.markedAnswer + '\'' +
                "}";*/
        return getCorrectAnswer() + "," + getMarkedAnswer();
    }
}

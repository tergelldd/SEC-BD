package com.biyDaalt;

public class Card {
    private final String question;
    private final String answer;
    private int correctCount = 0;
    private int totalAttempts = 0;
    private Boolean lastAttemptCorrect = null;
    private boolean hadMistake = false;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public boolean hadMistake() {return hadMistake;}
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public int getCorrectCount() { return correctCount; }
    public int getTotalAttempts() { return totalAttempts; }
    public boolean isLastAttemptCorrect() { return lastAttemptCorrect; }

    public void recordResult(boolean isCorrect) {
        this.totalAttempts++;
        this.lastAttemptCorrect = isCorrect;
        if (isCorrect) this.correctCount++;
        else this.hadMistake = true;
    }
}
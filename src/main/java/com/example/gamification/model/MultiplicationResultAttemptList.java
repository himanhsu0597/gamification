package com.example.gamification.model;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationResultAttemptList {

    private List<MultiplicationResultAttempt> attempts;

    public MultiplicationResultAttemptList() {
        attempts = new ArrayList<>();
    }

    public List<MultiplicationResultAttempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<MultiplicationResultAttempt> attempts) {
        this.attempts = attempts;
    }

    public MultiplicationResultAttemptList(List<MultiplicationResultAttempt> attempts) {
        this.attempts = attempts;
    }
}

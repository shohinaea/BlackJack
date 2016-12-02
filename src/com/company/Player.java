package com.company;

import java.util.Random;

/**
 * Created by student2 on 14.11.16.
 */
public abstract class Player {
    Condition condition = Condition.LOSS;
    Hand hand = new Hand();
    String name;
    int balance;
    int stavka;

    private Intellect intellect;
    private Stavtshik stavtshik;


    public Player(Stavtshik stavtshik,Intellect intellect, String name) {
        this.intellect = intellect;
        this.name = name;
        this.stavtshik = stavtshik;
        this.balance = 500;
        this.stavka = 0;
    }

    public void take(Card current) {
        hand.add(current);
    }

    public Command decision() {
        int score = hand.getScore();
        if (score > 21)
            return Command.STAND;
        return intellect.decide(score, balance, stavka);
    }

    public int stavka() {
        if (balance == 0) {
            condition = Condition.LOSS;
            return 0;
        }
        int stavka = stavtshik.decide(balance);
        balance -= stavka;
        return stavka;
    }
}

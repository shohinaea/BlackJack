package com.company;

/**
 * Created by shohinaea.17 on 28.11.2016.
 */
public class Divide3Stavtshik extends Stavtshik {
    @Override
    public int decide(int balance) {
        if (balance>=3)return (balance/3);
        return 1;
    }
}

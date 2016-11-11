package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        for (Suit s : Suit.values())
            for (Value v : Value.values()) {

                Card c = new Card(s,v);
                System.out.println(c);
            }

    }
}

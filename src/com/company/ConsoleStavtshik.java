package com.company;


import java.util.Scanner;

/**
 * Created by shohinaea.17 on 28.11.2016.
 */
public class ConsoleStavtshik extends Stavtshik {
    private Scanner in = new Scanner(System.in);
    @Override
    public int decide(int balance) {
        while (true)
        {
            System.out.println("YOUR BALANCE ="+balance);
            System.out.println("MAKE A BID:");
            int c = in.nextInt();
            if (balance-c>=0) {
                return c;
            }
            System.out.println("Not enough money");

        }
    }
}

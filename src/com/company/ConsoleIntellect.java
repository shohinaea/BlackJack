package com.company;

import java.util.Scanner;

/**
 * Created by student2 on 21.11.16.
 */
public class ConsoleIntellect extends Intellect {
    private Scanner in = new Scanner(System.in);


    @Override
    public Command decide(int score,int balance, int stavka) {
        while (true) {
            System.out.println("HIT/STAND/DOUBLE? ");
            String c = in.nextLine();
            if ("hit".startsWith(c.toLowerCase()))
                return Command.HIT;

            if ("stand".startsWith(c.toLowerCase()))
                return Command.STAND;
            if ("double".startsWith(c.toLowerCase())) {
                if (balance-stavka>0)
                return Command.DOUBLE;
            }



            System.out.println("You can't do it, please repeat");
        }

    }

}

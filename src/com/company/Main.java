package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code h
       List<Player> players = new LinkedList<>();
        players.add(new Computer(new LimitIntellect(14),"Komputer1"));
        players.add(new Computer(new LimitIntellect(20),"Komputer2"));
        players.add(new Human(new ConsoleIntellect(), "Liza"));
        Dealer dealer = new Dealer();
        players.add(dealer);
        for (Player player : players) {
            dealer.deal(player);
            dealer.deal(player);
            System.out.println(player.name+ " - "+player.hand);
        }

        for (Player player : players){
            while (true){
                System.out.println(player.name+ " - "+player.hand.getScore() + ":" + player.hand);
                Command command = player.decision();
                System.out.println(command);
                if (command==Command.STAND)
                    break;
                if (command==Command.HIT)
                    dealer.deal(player);

            }
        }
        for (Player player : players){



            if (!player.equals(dealer)) {
                if (player.hand.getScore()>21)
                    player.condition = Condition.LOSS;
                else if (dealer.hand.getScore()>21)
                    player.condition = Condition.WIN;
                else if (dealer.hand.getScore()
                        >player.hand.getScore())
                    player.condition = Condition.LOSS;
                else if (dealer.hand.getScore()
                        ==player.hand.getScore())
                    player.condition = Condition.DRAW;
                else
                    player.condition = Condition.WIN;
                System.out.println(player.name + " - "+player.hand+" : "+player.condition);
            }
            else System.out.println(player.hand);
        }


    }
}

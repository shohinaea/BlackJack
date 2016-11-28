package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by shohinaea.17 on 28.11.2016.
 */
public class Table {
    List<Player> players ;
    Dealer dealer;
    private Scanner in = new Scanner(System.in);
    String[] names = {"STEPAN","SUSIE","LOLA","DIMADULAEV"};
    Random random = new Random();

    public Table() {
        players = new LinkedList<>();
        players.add(new Computer(new LimitIntellect(14),names[random.nextInt(names.length)]));
        players.add(new Computer(new LimitIntellect(20),names[random.nextInt(names.length)]));
        System.out.println("Write your name:");
        players.add(new Human(new ConsoleIntellect(),in.nextLine()));
        dealer = new Dealer();
        players.add(dealer);
    }

    public void dealCards() {
        for (Player player : players) {
            dealer.deal(player);
            dealer.deal(player);
            System.out.println(player.name+ " - "+player.hand);
        }
    }

    public void VashiStavki() {
        for (Player player : players){

        }
    }

    public void game() {
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
    }

    public void whoWon() {
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
                System.out.println(player.name + " - "+player.hand.getScore()+":"+player.hand+" : "+player.condition);
            }
            else System.out.println(player.name+" - "+player.hand.getScore()+":"+player.hand);
        }
    }
}

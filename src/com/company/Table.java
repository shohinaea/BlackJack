package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setOut;

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
        players.add(new Computer(new Divide3Stavtshik(), new LimitIntellect(14),names[random.nextInt(names.length)]));
        players.add(new Computer(new Divide3Stavtshik(),new LimitIntellect(20),names[random.nextInt(names.length)]));
        System.out.println("Write your name:");
        players.add(new Human(new ConsoleStavtshik(),new ConsoleIntellect(),in.nextLine()));
        dealer = new Dealer();
        players.add(dealer);
    }
    public void killPoorPlayers() {
        for (int i= 0;i<players.size();i++){
            if (players.get(i).equals(dealer) != true && players.get(i).balance==0)
            {
                players.remove(i);
            }
        }
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
            System.out.println(player.name+ " balance="+player.balance);
            if (player.condition != Condition.DRAW) player.stavka = player.stavka();
            System.out.println(" - stavka="+player.stavka);;

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
                if (command==Command.DOUBLE){
                    player.stavka *=2;
                    break;}


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

    public void balanceUpdate() {
        for (Player player : players){
            if (!player.equals(dealer)) {
                if (player.condition== Condition.WIN)
                    player.balance +=player.stavka*3;
                if (player.condition== Condition.LOSS)
                    player.stavka=0;
                System.out.println(player.name+" - "+player.hand.getScore()+":"+player.condition+"=> balance="+player.balance);
            }

            else System.out.println(player.name+" - "+player.hand.getScore());
        }
    }

    public void cardsOff() {
        for (Player player : players){
            player.hand.clear();
        }
    }

    public void checkTable() {
        for (Player player : players){
            if (!player.equals(dealer))
                System.out.println("name="+player.name+" ; score="+player.hand.getScore()+" ; hand="+player.hand+" ; condition="
                    + player.condition+" ; stavka="+ player.stavka+" ; balance="+player.balance+" ; ");
            else System.out.println("name="+player.name+" ; score="+player.hand.getScore()+" ; hand="+player.hand);
        }

    }

    public boolean again() {
        String c;
        System.out.println("WANNA PLAY AGAIN?");
        while (true) {
        c=in.nextLine();
        if ("yes".startsWith(c.toLowerCase()))
            return true;
        if ("no".startsWith(c.toLowerCase()))
            return false;
        System.out.println("CAN'T UNDERSTAND, TRY AGAIN");}
    }

    public boolean isTableEmpty() {
        if (players.size()==1) return true;
        else return false;
    }
}

package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


	Table table = new Table();
        while (true) {
            table.killPoorPlayers();
            if (table.isTableEmpty()) break;
            table.dealCards();
            table.VashiStavki();
            table.game();
            table.whoWon();
            table.balanceUpdate();
            table.cardsOff();
            if (table.again()==true) continue;
            else break;

        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.*;

/**
 *
 * @author cyrus
 */
public class CareTaker {

    private ArrayList<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento m) {
        mementos.add(m);
    }

    public Memento removeMemento() {
        if (!mementos.isEmpty()) {

//            int index = 0;
//            for (Memento m : mementos) {
//                System.out.print("\n" + index + ":");
//                index++;
//                int[][] board = m.getBoard();
//                for (int i = 0; i < board[0].length; i++) {
//                    System.out.println();
//                    for (int l = 0; l < board.length; l++) {
//                        System.out.print(board[l][i] + " ");
//                    }
//                }
//                System.out.println();
//            }
//            System.out.println("End of all stored boards");
            Memento memento;
            if (mementos.size() < 2) {
                memento = mementos.remove(0);
                return memento;
            } else {
                int lastIndex = mementos.size() - 1;
                mementos.remove(lastIndex);
                lastIndex--;
                memento = mementos.get(lastIndex);
            }

//            int[][] board = memento.getBoard();
//            for (int i = 0; i < board[0].length; i++) {
//                System.out.println();
//                for (int l = 0; l < board.length; l++) {
//                    System.out.print(board[l][i] + " ");
//                }
//            }
//            System.out.println();
//            System.out.println("End of retrieved board");
            return memento;
        } else {
            return null;
        }
    }

    public void removeAllMemento() {
        if (!mementos.isEmpty()) {
            mementos.removeAll(mementos);
        }
    }
}

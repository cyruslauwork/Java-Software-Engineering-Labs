/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author cyrus
 */
public class Memento {

    private int[][] board = new int[8][8]; 	// The logical board
    private int turn;               		// Who's turn to move (BLACK or WHITE)
    private int numPieces;					// How many pieces are there on the board
    private int blackScore, whiteScore; 	// How many BLACK and WHITE pieces are there currently
    private int status;						// 0:Game in progress, 1:Black Win, 2: White Win, 3:Draw
    private boolean nextPlayerMustPass;		// The next play has no moves. He must pass.

    public Memento(int[][] board, int turn, int numPieces, int blackScore, int whiteScore, int status, boolean nextPlayerMustPass) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.turn = turn;
        this.numPieces = numPieces;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.status = status;
        this.nextPlayerMustPass = nextPlayerMustPass;
    }

    public void setBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNextPlayerMustPass(boolean nextPlayerMustPass) {
        this.nextPlayerMustPass = nextPlayerMustPass;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getStatus() {
        return status;
    }

    public boolean isNextPlayerMustPass() {
        return nextPlayerMustPass;
    }
}

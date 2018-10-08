package edu.wofford;

import java.util.Scanner;

public class ConsoleMain {

	public static void main(String[] args) {
		TicTacToeModel game = new TicTacToeModel();
		Scanner keyboard = new Scanner(System.in);
		while (game.getResult() == TicTacToeModel.Result.NONE){
			int row = keyboard.nextInt();
			int column = keyboard.nextInt();
			game.setMarkAt(row, column);
			System.out.println(game.toString());
		}
		if (game.getResult() == TicTacToeModel.Result.XWIN) {
			System.out.println("X wins");
		} else if(game.getResult() == TicTacToeModel.Result.OWIN) {
			System.out.println("O wins");
		} else if (game.getResult() == TicTacToeModel.Result.TIE){
			System.out.println("Tie");
		}
	}
}
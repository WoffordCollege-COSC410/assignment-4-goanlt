package edu.wofford;


public class TicTacToeModel {

    public enum Mark { EMPTY, XMARK, OMARK };
    public enum Result { XWIN, OWIN, TIE, NONE };
	protected Mark[][] board ;
	protected Boolean xTurn;
	
	private String rowToString(int row) {
		if (row >= 0 && row < board.length) {
			String str = "";
			for (int column = 0; column < board[0].length; column ++) {
				if (board[row][column] == Mark.EMPTY) {
					str += " ";
				} else {
                str += (board[row][column] == Mark.XMARK)? "X" : "O";					
				}
			}
			return str;
		} else {
			return "";
		}
	}
	
    public TicTacToeModel() {
		xTurn = true;
		board = new Mark[3][3];
		for(int col = 0; col < 3; col ++) {
			board[0][col] = Mark.EMPTY;
			board[1][col] = Mark.EMPTY;
			board[2][col] = Mark.EMPTY;	
 		}
    }
   
	public Boolean getxTurn() {
		return xTurn;
	}

   
    public boolean setMarkAt(int row, int col) {
		if (getResult() == Result.NONE && board[row][col] == Mark.EMPTY) { 
			if(xTurn){
				board[row][col] = Mark.XMARK; 
			} else {
				board[row][col] = Mark.OMARK;
			}
			xTurn = !xTurn;
			return true;
		}
        return false;
    }
    
    public Mark getMarkAt(int row, int col) {
        return board[row][col];
    }

	private boolean isTied () {
		boolean boardFull = true;
		int row = 0;
		while(row < board.length && boardFull) {
			if (rowToString(row).replaceAll("\\s+", "").length() != 3) {
				boardFull = false;
			}
			row ++;
		}
		if (boardFull) {
			return true;
		} else {
			return false;
		}
	}
	
	private String columnWin() {
		int xCount = 0;
		int oCount = 0;
		for(int column = 0; column < board[0].length; column ++) {
			int row = 0;
			while (row < board.length && board[row][column] != Mark.EMPTY) {
				if (board[row][column] == Mark.XMARK) {
					xCount ++;
				} else {
					oCount ++;
				}
				row ++;
			}
			if (xCount == 3) {
				return "X";
			} else if(oCount == 3) {
				return "O";
			}
			xCount = 0;
			oCount = 0;
		}
		return "";
	}
	
	private String rowWin() {
		int xCount = 0;
		int oCount = 0;
		for(int row = 0; row < board.length; row ++) {
			int column = 0;
			while (column < board.length && board[row][column] != Mark.EMPTY) {
				if (board[row][column] == Mark.XMARK) {
					xCount ++;
				} else {
					oCount ++;
				}
				column ++;
			}
			if (xCount == 3) {
				return "X";
			} else if(oCount == 3) {
				return "O";
			}
			xCount = 0;
			oCount = 0;
		}
		return "";
	}
	
	private String diagonalWin() {
		Mark middleMark = board[1][1];
		if (middleMark == Mark.EMPTY) {
			return "";
		} else if (((board[0][0] == middleMark && board[2][2] == middleMark) || (board[0][2] == middleMark && board[2][0] == middleMark))) {
			if (middleMark == Mark.XMARK) {
				return "X";
			} else {
				return "O";
			}
		}
		return "";
	}
	
    public Result getResult() {
		if (isTied()) {
			return Result.TIE;
		} else if (columnWin() != "") {
			if (columnWin() == "X") {
				return Result.XWIN;
			} else {
				return Result.OWIN;
			}
		} else if (rowWin() != "") {
			if (rowWin() == "X") {
				return Result.XWIN;
			} else {
				return Result.OWIN;
			}
		} else if (diagonalWin() != "") {
			if (diagonalWin() == "X") {
				return Result.XWIN;
			} else {
				return Result.OWIN;
			}
		}
        return Result.NONE;
    }
    
    public String toString() {
		String rowStr = "";
		String boardStr = "";
		for (int row = 0; row < board.length - 1; row ++) {
			rowStr = rowToString(row);
			boardStr += rowStr.charAt(0) + "|" + rowStr.charAt(1) + "|" + rowStr.charAt(2) + "\n" + "-----\n";
		}
		rowStr = rowToString(2);
		boardStr += rowStr.charAt(0) + "|" + rowStr.charAt(1) + "|" + rowStr.charAt(2);
		return boardStr;
    }    

}
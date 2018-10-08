package edu.wofford;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GuiMain extends JFrame implements ActionListener {
	
	private static TicTacToeModel game;
	private JLabel result;
	
    public void actionPerformed(ActionEvent event) {
		JButton button = (JButton)event.getSource();
		TicTacToeModel.Result gameResult = game.getResult();
		if (button.getText() == "" && gameResult == TicTacToeModel.Result.NONE) {
			String str = button.getName().substring(button.getName().length() -2, button.getName().length());
			int column = Character.getNumericValue(str.charAt(0));
			int row = Character.getNumericValue(str.charAt(1));
			if (game.getxTurn()) {
				button.setText("X");
			} else {
				button.setText("O");
			}
			game.setMarkAt(row, column);
		}
		gameResult = game.getResult();
		if (gameResult != TicTacToeModel.Result.NONE) {
			if ( gameResult== TicTacToeModel.Result.XWIN) {
				result.setText("X wins");
			} else if (gameResult == TicTacToeModel.Result.OWIN) {
				result.setText("O wins");
			} else {
				result.setText("Tie");
			}
		} 
    }
    
    public GuiMain() {
        setTitle("Tic Tac Toe");
		setSize(new Dimension(450, 325));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		result = new JLabel("");
		result.setHorizontalAlignment(JLabel.CENTER);
		result.setName("result");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 20;
		c.insets = new Insets(0,5,15,5);
		mainPanel.add(result, c);

		c.gridwidth = 1;
		c.ipady = 30;
		c.ipadx = 35;
		c.insets = new Insets(5,5,5,5);
		int x = 0;
		int y = 1;
		for (int i = 0; i < 9; i ++) {
			JButton b = new JButton();
			b.setName("location" + Integer.toString(y - 1) + Integer.toString(x));
			b.setText("");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = x;
			c.gridy = y;
			mainPanel.add(b, c);
			b.addActionListener(this);
			x++;
			if (x == 3) {
				x = 0;
			}
			if ((i + 1) % 3 == 0) {
				y ++;
			}
		}
    add(mainPanel);
	}

    
	public static void main(String[] args) {
		game = new TicTacToeModel();
	    GuiMain window = new GuiMain();
        window.setVisible(true);
	}
}
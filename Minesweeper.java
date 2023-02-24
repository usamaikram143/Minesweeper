//package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Minesweeper extends JFrame{

	public static int NUM_ROWS=5;
	public static int NUM_COLS=5;
	public static int NUM_BOMBS=5;
	
	
	private Grid g;
	
	
	private JPanel jpMain;
	private MS_Board msBoard;
	
	
	public Minesweeper() {
		super("Minesweeper");
		askForInputs();
		g=new Grid(NUM_ROWS,NUM_COLS,NUM_BOMBS);
		g.print();
		jpMain=new JPanel();
		jpMain.setLayout(new BorderLayout());

		msBoard=new MS_Board();
		jpMain.add(msBoard,BorderLayout.CENTER);
		
		add(jpMain);
		setSize(NUM_ROWS*100,NUM_COLS*100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void askForInputs() {
		String rows=JOptionPane.showInputDialog(null,"Enter number of Rows");
		String cols=JOptionPane.showInputDialog(null,"Enter number of Columns");
		String bombs=JOptionPane.showInputDialog(null,"Enter number of Bombs");
		try {
			NUM_ROWS=Integer.parseInt(rows);
		}
		catch(Exception e) {
			NUM_ROWS=10;
		}
		try {
			NUM_COLS=Integer.parseInt(cols);
		}
		catch(Exception e) {
			NUM_COLS=10;
		}
		try {
			NUM_BOMBS=Integer.parseInt(bombs);
		}
		catch(Exception e) {
			NUM_BOMBS=25;
		}
		
	}
	private class MS_Board extends JPanel implements ActionListener {
		ImageIcon bomb=new ImageIcon("bomb.png");
		private MyButton[][] board;
		private int numOfButtonsClicked=0;
		
		private MS_Board() {
			setLayout(new GridLayout(NUM_ROWS,NUM_COLS));
			displayBoard();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int row;
			int col;
			int count;
			MyButton btnClicked=(MyButton) e.getSource();
			row=btnClicked.getRowLocation();
			col=btnClicked.getColLocation();
			count=g.getCountAtLocation(row, col);
			if(g.isBombAtLocation(row, col)) {
				btnClicked.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(50,50,Image.SCALE_FAST)));
				for(int i=0;i<NUM_ROWS;i++) {
					for(int j=0;j<NUM_COLS;j++) {
						int c=g.getCountAtLocation(i, j);
						if(g.isBombAtLocation(i, j)) {
							board[i][j].setIcon(new ImageIcon(bomb.getImage().getScaledInstance(50,50,Image.SCALE_FAST)));
						}
						else {
							board[i][j].setText(Integer.toString(c));
						}
					}
				}
				displayWinLost("\t\tLOST!\nSORRY YOU HAVE CLICKED A BOMB!");
				askPlayAgain();
			}
			else {
				btnClicked.setText(Integer.toString(count));
				btnClicked.setEnabled(false);
				this.numOfButtonsClicked++;
				if(count==0) {
					revealSurrBtns(row,col);
				}
				if(this.numOfButtonsClicked==(NUM_ROWS*NUM_COLS-NUM_BOMBS)) {
					displayWinLost("CONGRATS YOU HAVE WON!");
					askPlayAgain();
				}
			}
			
		}
		
		private void revealSurrBtns(int row,int col) {
			
			if(row>0) {
				if(board[row-1][col].isEnabled()) {
					board[row-1][col].setText(Integer.toString(g.getCountAtLocation(row-1, col)));
					board[row-1][col].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row-1, col)==0) {
						revealSurrBtns(row-1,col);
					}
				}
			}
			if(col>0) {
				if(board[row][col-1].isEnabled()) {
					board[row][col-1].setText(Integer.toString(g.getCountAtLocation(row, col-1)));
					board[row][col-1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row, col-1)==0) {
						revealSurrBtns(row,col-1);
					}
				}
			}
			if(row>0 && col>0) {
				if(board[row-1][col-1].isEnabled()) {
					board[row-1][col-1].setText(Integer.toString(g.getCountAtLocation(row-1, col-1)));
					board[row-1][col-1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row-1, col-1)==0) {
						revealSurrBtns(row-1,col-1);
					}
				}
			}
			if(row<(NUM_ROWS-1)) {
				if(board[row+1][col].isEnabled()) {
					board[row+1][col].setText(Integer.toString(g.getCountAtLocation(row+1, col)));
					board[row+1][col].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row+1, col)==0) {
						revealSurrBtns(row+1,col);
					}
				}
			}
			if(col<(NUM_COLS-1)) {
				if(board[row][col+1].isEnabled()) {
					board[row][col+1].setText(Integer.toString(g.getCountAtLocation(row, col+1)));
					board[row][col+1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row, col+1)==0) {
						revealSurrBtns(row,col+1);
					}
				}
			}
			if(row<(NUM_ROWS-1) && col<(NUM_COLS-1)) {
				if(board[row+1][col+1].isEnabled()) {
					board[row+1][col+1].setText(Integer.toString(g.getCountAtLocation(row+1, col+1)));
					board[row+1][col+1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row+1, col+1)==0) {
						revealSurrBtns(row+1,col+1);
					}
				}
			}
			if(row>0 && col<(NUM_COLS-1)) {
				if(board[row-1][col+1].isEnabled()) {
					board[row-1][col+1].setText(Integer.toString(g.getCountAtLocation(row-1, col+1)));
					board[row-1][col+1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row-1, col+1)==0) {
						revealSurrBtns(row-1,col+1);
					}
				}
			}
			if(row<(NUM_ROWS-1) && col>0) {
				if(board[row+1][col-1].isEnabled()) {
					board[row+1][col-1].setText(Integer.toString(g.getCountAtLocation(row+1, col-1)));
					board[row+1][col-1].setEnabled(false);
					this.numOfButtonsClicked++;
					if(g.getCountAtLocation(row+1, col-1)==0) {
						revealSurrBtns(row+1,col-1);
					}
				}
			}
		}
		
		private void displayBoard() {
			board=new MyButton[NUM_ROWS][NUM_COLS];
			for(int row=0;row<NUM_ROWS;row++) {
				for(int col=0;col<NUM_COLS;col++) {
					board[row][col]=new MyButton(row,col);
					board[row][col].setSize(10, 10);
					board[row][col].setBackground(Color.DARK_GRAY);
					Font bigFont=new Font(Font.SANS_SERIF,Font.BOLD,25);
					board[row][col].setForeground(Color.GRAY);
					board[row][col].setFont(bigFont);
					board[row][col].addActionListener(this);
					board[row][col].setEnabled(true);
					this.add(board[row][col]);
				}
			}
		}
		
		public void displayWinLost(String s) {
			JOptionPane.showMessageDialog(null, s);
		}
		
		
		public void askPlayAgain() {
			int yesNo=JOptionPane.showConfirmDialog(null,"Play Again?","Yes or No",JOptionPane.YES_NO_OPTION);
			if(yesNo==JOptionPane.YES_OPTION) {
				clearBoard();
			}
			else {
				System.exit(EXIT_ON_CLOSE);
			}
		}
		public void clearBoard() {
			g=new Grid(NUM_ROWS,NUM_COLS,NUM_BOMBS);
			g.print();
			this.numOfButtonsClicked=0;
			for(int row=0;row<NUM_ROWS;row++) {
				for(int col=0;col<NUM_COLS;col++) {
					board[row][col].setText("");
					board[row][col].setIcon(null);
					board[row][col].setEnabled(true);
				}
			}
			
		}
		
		
	}
	
	private class MyButton extends JButton{
		
		private int rowLocation;
		private int colLocation;
		
		public MyButton(int rowLocation,int colLocation) {
			super();
			this.rowLocation=rowLocation;
			this.colLocation=colLocation;
		}
		
		public int getRowLocation() {
			return this.rowLocation;
		}
		
		public int getColLocation() {
			return this.colLocation;
		}
		
	}
	

}

//package project2;

import java.util.Random;

public class Grid {
	
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		numRows=10;
		numColumns=10;
		if(numRows*numColumns>25) {numBombs=25;}
		else {numBombs=numRows*numColumns;}
		bombGrid=new boolean[numRows][numColumns];
		countGrid=new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows,int columns) {
		numRows=rows;
		numColumns=columns;
		if(numRows*numColumns>25) {numBombs=25;}
		else {numBombs=numRows*numColumns;}
		bombGrid=new boolean[numRows][numColumns];
		countGrid=new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows,int columns,int numBombs) {
		numRows=rows;
		numColumns=columns;
		if(numBombs<=rows*columns) {this.numBombs=numBombs;}
		else {this.numBombs=rows*columns;}
		bombGrid=new boolean[numRows][numColumns];
		countGrid=new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public int getNumRows() {
		return this.numRows;
	}
	
	public int getNumColumns() {
		return this.numColumns;
	}
	
	public int getNumBombs() {
		return this.numBombs;
	}
	
	public boolean[][] getBombGrid(){
		boolean[][] copy=new boolean[this.numRows][this.numColumns];
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				copy[row][col]=this.bombGrid[row][col];
			}
		}
		return copy;
	}
	
	public int[][] getCountGrid(){
		int[][] copy=new int[this.numRows][this.numColumns];
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				copy[row][col]=this.countGrid[row][col];
			}
		}
		return copy;
	}
	
	public boolean isBombAtLocation(int row,int column) {
		return this.bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row,int column) {
		return this.countGrid[row][column];
	}
	
	private void createBombGrid() {
		
		//Initializing all the indices with a false...
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				this.bombGrid[row][col]=false;
			}
		}
		
		// now randomly pick a row and column and then change it to "true" (bomb). If there is already a true there skipping it.
		Random rand = new Random();
		int numberOfBombsPlaced=0;
		int randRow;
		int randColumn;
		
		while(numberOfBombsPlaced!=this.numBombs) {
			randRow=rand.nextInt(this.numRows);
			randColumn=rand.nextInt(this.numColumns);
			if(this.bombGrid[randRow][randColumn]==false) {
				this.bombGrid[randRow][randColumn]=true;
				numberOfBombsPlaced++;
			}
		}
	}
	
	private void createCountGrid() {
		int numBombsSurr;
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				numBombsSurr=0;
				if(this.bombGrid[row][col]==true) {
					numBombsSurr++;
				}
				if(row>0) {
					if(this.bombGrid[row-1][col]==true) {
						numBombsSurr++;
					}
				}
				if(col>0) {
					if(this.bombGrid[row][col-1]==true) {
						numBombsSurr++;
					}
				}
				if(row>0 && col>0) {
					if(this.bombGrid[row-1][col-1]==true) {
						numBombsSurr++;
					}
				}
				if(row<(this.numRows-1)) {
					if(this.bombGrid[row+1][col]) {
						numBombsSurr++;
					}
				}
				if(col<(this.numColumns-1)) {
					if(this.bombGrid[row][col+1]) {
						numBombsSurr++;
					}
				}
				if(row<(this.numRows-1) && col<(this.numColumns-1)) {
					if(this.bombGrid[row+1][col+1]==true) {
						numBombsSurr++;
					}
				}
				if(row>0 && col<(this.numColumns-1)) {
					if(this.bombGrid[row-1][col+1]) {
						numBombsSurr++;
					}
				}
				if(row<(this.numRows-1) && col>0) {
					if(this.bombGrid[row+1][col-1]) {
						numBombsSurr++;
					}
				}
				
				this.countGrid[row][col]=numBombsSurr;
			}
		}
	}
	
	public void print() {
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				System.out.print(this.bombGrid[row][col]+"\t");
			}
			System.out.println();
		}
		
		for(int row=0;row<this.numRows;row++) {
			for(int col=0;col<this.numColumns;col++) {
				System.out.print(this.countGrid[row][col]+"\t");
			}
			System.out.println();
		}
	}

}



//package project2;

public class Play_Minesweeper {
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Minesweeper ms=new Minesweeper();
			}
		});
	}

}

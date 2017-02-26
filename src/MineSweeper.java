import javax.swing.*;
/*
 * a class that runs minesweeper
 */
public class MineSweeper {

	public static void main(String[] args) {
		JPanel board;
		board = new MineSweeperPanel();
		JFrame frame = new JFrame("Mine Sweeper");
		frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

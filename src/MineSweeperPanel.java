import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MineSweeperPanel extends JPanel implements ActionListener, MouseListener{
	private JButton[][] board;
	private Cell iCell;
	private JButton quitBtn;
	private JButton resetBtn;
	private JLabel lossLabel;
	private JLabel winLabel;
	private MineSweeperGame game;
	
	private int length;
	private int losses;
	private int wins;
	
	ImageIcon mineIcon = new ImageIcon("src/mine.png");
	ImageIcon flagIcon = new ImageIcon("src/flag.png");
	
	/*
	 * creates layout for the Mine sweeper panel
	 */
	public MineSweeperPanel(){
		game = new MineSweeperGame();
		length = game.getLength();
		board = new JButton[length][length];
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(length,length));
		add(center, BorderLayout.CENTER);
		
		for (int row = 0; row < length; row++){		//ADD BUTTONS
			for (int col = 0; col < length; col++) {
			board[row][col] = new JButton (""/*, emptyIcon*/);
			board[row][col].setPreferredSize(new Dimension(45,45));
			board[row][col].setBackground(Color.LIGHT_GRAY);
			board[row][col].addActionListener(this);
			board[row][col].addMouseListener(this);
			center.add(board[row][col]);
			}
		}
		displayButtons();
		displayBoard();
	}
	
	/*
	 * Decides what buttons to show
	 */
	private void displayBoard(){
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++){
				iCell = game.getCell(row,col);
				board[row][col].setText("");
				if (iCell.isExposed()){
					board[row][col].setEnabled(false);
					board[row][col].setBackground(Color.WHITE);
				}
				else{
					board[row][col].setEnabled(true);
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
				if (iCell.isExposed() && !iCell.isMine())
					if(iCell.getCount() == 0)
						board[row][col].setText("");
					else
						board[row][col].setText("" + iCell.getCount());
				board[row][col].setIcon(null);
				if(iCell.isFlagged())
					board[row][col].setIcon(flagIcon);
				if(iCell.isMine() && iCell.isExposed())
					board[row][col].setIcon(mineIcon);
			}
		}
	}
	
	/*
	 * Adds buttons for quit or reset
	 */
	private void displayButtons(){
		quitBtn = new JButton("Quit");
		resetBtn = new JButton("Reset");
		quitBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		
		JPanel north = new JPanel();
		north.setLayout(new GridLayout());
		add(north, BorderLayout.NORTH);
		
		
		north.add(quitBtn);
		north.add(resetBtn);
		
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout());
		winLabel = new JLabel();
		lossLabel = new JLabel();
		
		winLabel.setText("Won: " + wins);
		lossLabel.setText("Lost: " + losses);
		south.add(winLabel);
		south.add(lossLabel);
	}

	/*
	 * action listener
	 */
	public void actionPerformed(ActionEvent event) {
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++){
				if(event.getSource() == board[row][col]){
					game.select(row, col);
				}
			}
		}
		if(event.getSource() == quitBtn){
			System.exit(0);
		}
		if(event.getSource() == resetBtn){
			game.reset();
		}
		displayBoard();
		if (game.getGameStatus() == GameStatus.Lost) {
			JOptionPane.showMessageDialog(null, "You Lost");
			losses++;
			lossLabel.setText("Lost: " + losses);
			for (int row = 0; row < length; row++){
				for (int col = 0; col < length; col++){
					iCell = game.getCell(row,col);
					iCell.setExposed(true);
				}
			}
		}
		if (game.getGameStatus() == GameStatus.Won) {
			JOptionPane.showMessageDialog(null, "You Won");
			wins++;
			winLabel.setText("Won: " + wins);
		}
	}


	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			for (int row = 0; row < length; row++){
				for (int col = 0; col < length; col++){
					if(e.getSource() == board[row][col]){
						iCell = game.getCell(row,col);
						if(iCell.isFlagged())
							iCell.setFlag(false);
						else
							iCell.setFlag(true);
					}
				}
			}
		}
		displayBoard();
	}

}

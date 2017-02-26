import javax.swing.JOptionPane;
import java.util.Random;

public class MineSweeperGame {
	private int length;
	private int mineCount;	//mines on the board
	private Cell[][] board;
	private GameStatus status;
	private int toWin;	//amount of spaces cleared to win
	private int maxMines; //board size -1
	
	
	/*
	 * constructor for mine sweeper
	 */
	public MineSweeperGame(){
		setLength();
		maxMines = (length * length) -1;
		setMineCount();
		toWin = (length * length) - mineCount;
		status = GameStatus.NotOverYet;
		board = new Cell[length][length];
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++) {
				board[row][col] = new Cell();
			}
		}
		assignMines();
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++) {
				mineLocation(row,col);
			}
		}
	}
	
	/*
	 * sets the length of the board
	 */
	public void setLength(){
		String message = "Enter board length between 3 and 30.";
		length = getValidInteger(JOptionPane.showInputDialog(message), message);
		if(length < 3 || length > 30)
			setLength();
	}
	
	/*
	 * sets the minecount	
	 */
	public void setMineCount(){
		String message = "Enter mine count between 1 and " + maxMines + ".";
		mineCount = getValidInteger(JOptionPane.showInputDialog(message), message);
		if(mineCount < 1 || mineCount > maxMines)
			setMineCount();
	}
	
	/*
	 * returns minecount
	 */
	public int getMineCount(){
		return mineCount;
	}
	
	/*
	 * returns length
	 */
	public int getLength(){
		return length;
	}
	
	/*
	 * returns a requested cell given the row and column
	 */
	public Cell getCell(int pRow, int pCol){
		return board[pRow][pCol];
	}
	
	/*
	 * Adds mine count to surrounding cells if it's a mine
	 */
	public void mineLocation(int pRow, int pCol){
		Cell c = getCell(pRow, pCol);
		if(c.isMine()){
			if(pRow == 0 && pCol == 0){
				incCell(pRow, pCol, "right");
				incCell(pRow, pCol, "botRight");
				incCell(pRow, pCol, "bot");
			}
			else if(pRow == 0 && pCol == length-1){
				incCell(pRow, pCol, "left");
				incCell(pRow, pCol, "botLeft");
				incCell(pRow, pCol, "bot");
			}
			else if(pRow == length-1 && pCol == 0){
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topRight");
				incCell(pRow, pCol, "right");
			}
			else if(pRow == length-1 && pCol == length-1){
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topLeft");
				incCell(pRow, pCol, "left");
			}
			else if(pRow == 0){
				incCell(pRow, pCol, "right");
				incCell(pRow, pCol, "botRight");
				incCell(pRow, pCol, "left");
				incCell(pRow, pCol, "botLeft");
				incCell(pRow, pCol, "bot");
			}
			else if(pRow == length-1){
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topLeft");
				incCell(pRow, pCol, "left");
				incCell(pRow, pCol, "topRight");
				incCell(pRow, pCol, "right");
			}
			else if(pCol == 0){
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topRight");
				incCell(pRow, pCol, "right");
				incCell(pRow, pCol, "botRight");
				incCell(pRow, pCol, "bot");
			}
			else if(pCol == length-1){
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topLeft");
				incCell(pRow, pCol, "left");
				incCell(pRow, pCol, "botLeft");
				incCell(pRow, pCol, "bot");
			}
			else{
				incCell(pRow, pCol, "top");
				incCell(pRow, pCol, "topLeft");
				incCell(pRow, pCol, "left");
				incCell(pRow, pCol, "botLeft");
				incCell(pRow, pCol, "topRight");
				incCell(pRow, pCol, "right");
				incCell(pRow, pCol, "botRight");
				incCell(pRow, pCol, "bot");
			}
		}
	}
	
	/*
	 * displays surrounding mines
	 */
	private void expand(int pRow, int pCol){
		if(pRow == 0 && pCol == 0){
			expose(pRow, pCol, "right");
			expose(pRow, pCol, "botRight");
			expose(pRow, pCol, "bot");
		}
		else if(pRow == 0 && pCol == length-1){
			expose(pRow, pCol, "left");
			expose(pRow, pCol, "botLeft");
			expose(pRow, pCol, "bot");
		}
		else if(pRow == length-1 && pCol == 0){
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topRight");
			expose(pRow, pCol, "right");
		}
		else if(pRow == length-1 && pCol == length-1){
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topLeft");
			expose(pRow, pCol, "left");
		}
		else if(pRow == 0){
			expose(pRow, pCol, "right");
			expose(pRow, pCol, "botRight");
			expose(pRow, pCol, "left");
			expose(pRow, pCol, "botLeft");
			expose(pRow, pCol, "bot");
		}
		else if(pRow == length-1){
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topLeft");
			expose(pRow, pCol, "left");
			expose(pRow, pCol, "topRight");
			expose(pRow, pCol, "right");
		}
		else if(pCol == 0){
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topRight");
			expose(pRow, pCol, "right");
			expose(pRow, pCol, "botRight");
			expose(pRow, pCol, "bot");
		}
		else if(pCol == length-1){
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topLeft");
			expose(pRow, pCol, "left");
			expose(pRow, pCol, "botLeft");
			expose(pRow, pCol, "bot");
		}
		else{
			expose(pRow, pCol, "top");
			expose(pRow, pCol, "topLeft");
			expose(pRow, pCol, "left");
			expose(pRow, pCol, "botLeft");
			expose(pRow, pCol, "topRight");
			expose(pRow, pCol, "right");
			expose(pRow, pCol, "botRight");
			expose(pRow, pCol, "bot");
		}
	}
	
	/*
	 * assigns mines to random cells
	 */
	public void assignMines(){
		Random rnd = new Random();
		Cell temp;
		int x;
		int y;
		int i = 0;
		while(i< mineCount){
			x = rnd.nextInt(length);
			y = rnd.nextInt(length);
			temp = getCell(x,y);
			if(temp.isMine() == false){
				temp.setMine(true);
				i++;
			}
		}
	}
	
	/*
	 * resets the game
	 */
	public void reset(){
		Cell temp;
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++) {
				temp = getCell(row,col);
				temp.setExposed(false);
				temp.setFlag(false);
				temp.setMine(false);
				temp.setCount(0);
			}
		}
		toWin = (length * length) - mineCount;
		status = GameStatus.NotOverYet;
		assignMines();
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++) {
				mineLocation(row,col);
			}
		}
	}
	
	/*
	 * choose what to do when a cell is selected
	 */
	public void select (int pRow, int pCol){
		Cell c = getCell(pRow, pCol);
		if(c.isFlagged() == true)
			c.setExposed(false);
		else{
			c.setExposed(true);
			if(c.isMine())
				status = GameStatus.Lost;	
			else{
				if(c.getCount() == 0){
					expand(pRow, pCol);
					updateGameStatus();
				}
				else
					updateGameStatus();
			}
		}
	}
	
	/*
	 * returns the game status if its lost won or not over
	 */
	public GameStatus getGameStatus(){
		return status;
	}
	
	/*
	 * determines if game is won
	 */
	public void updateGameStatus(){
		toWin = toWin-1;
		if(toWin == 0)
			status = GameStatus.Won;
	}
	
	//Helper methods
	/*
	 * takes a row, a column and a specific direction and increments the count
	 * on that cell.
	 */
	private void incCell(int pRow, int pCol, String dir){
		Cell c;
		if(dir.equals("top")){
			c = getCell(pRow-1, pCol);
			c.incCount();
		}
		if(dir.equals("topLeft")){
			c = getCell(pRow-1, pCol-1);
			c.incCount();
		}
		if(dir.equals("topRight")){
			c = getCell(pRow-1, pCol+1);
			c.incCount();
		}
		if(dir.equals("right")){
			c = getCell(pRow, pCol+1);
			c.incCount();
		}
		if(dir.equals("left")){
			c = getCell(pRow, pCol-1);
			c.incCount();
		}
		if(dir.equals("bot")){
			c = getCell(pRow+1, pCol);
			c.incCount();
		}
		if(dir.equals("botRight")){
			c = getCell(pRow+1, pCol+1);
			c.incCount();
		}
		if(dir.equals("botLeft")){
			c = getCell(pRow+1, pCol-1);
			c.incCount();
		}
	}
	
	/*
	 * take the row column and specific direction and exposes that cell
	 */
	private void expose(int pRow, int pCol, String dir){
		Cell c;
		if(dir.equals("top")){
			c = getCell(pRow-1, pCol);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow-1, pCol);
			}
		}
		if(dir.equals("topLeft")){
			c = getCell(pRow-1, pCol-1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow-1, pCol-1);
			}
		}
		if(dir.equals("topRight")){
			c = getCell(pRow-1, pCol+1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow-1, pCol+1);
			}
		}
		if(dir.equals("right")){
			c = getCell(pRow, pCol+1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow, pCol+1);
			}
		}
		if(dir.equals("left")){
			c = getCell(pRow, pCol-1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow, pCol-1);
			}
		}
		if(dir.equals("bot")){
			c = getCell(pRow+1, pCol);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow+1, pCol);
			}
		}
		if(dir.equals("botRight")){
			c = getCell(pRow+1, pCol+1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow+1, pCol+1);
			}
		}
		if(dir.equals("botLeft")){
			c = getCell(pRow+1, pCol-1);
			if(c.isMine() == false && c.isExposed() == false){
				c.setExposed(true);
				select(pRow+1, pCol-1);
			}
		}
	}
	
	/*
	 * checks for valid integers, try catch modified from an old project
	 */
	private int getValidInteger(String numStr, String display){
        int val;
        try{
            val = Integer.parseInt(numStr);

            // display error message if not a valid integer    
        }catch(NumberFormatException e){
            String str = JOptionPane.showInputDialog(display);
            val = getValidInteger(str, display);
        }    
        return val;
    }
}

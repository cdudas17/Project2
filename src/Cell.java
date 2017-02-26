
public class Cell {
	private int count = 0; //number of mines touching
	private boolean isFlagged;
	private boolean isExposed;
	private boolean isMine;
	
	//Setters
	public void setFlag(boolean flag){
		isFlagged = flag;
	}
	
	public void setExposed(boolean exposed){
		isExposed = exposed;
	}
	
	public void setMine(boolean mine){
		isMine = mine;
	}
	
	public void setCount(int pCount){
		count = pCount;
	}
	
	/*
	 * Increments count of cell
	 */
	public void incCount(){
		count++;
	}
	
	//Accessor methods
	public boolean isExposed(){
		return isExposed;
	}
	
	public boolean isFlagged(){
		return isFlagged;
	}
	
	public boolean isMine(){
		return isMine;
	}
	
	public int getCount(){
		return count;
	}
}

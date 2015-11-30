package sjsu.cs146.project3;

public class Cell {

	// Cell's coordinator on the maze
	private int xCoord;
	private int yCoord;

	// Cell's value
	private int step;

	// Denote the parent of each cell
	private Cell parent;

	// Denote whether the cell is on the shortest path or not
	private boolean shortPath;

	// Denote whether a cell has been visited or not
	private boolean visit;

	// Walls of Cell on four directions
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	// initialize the cells
	public Cell(int xCoord, int yCoord) {

		this.xCoord = xCoord;
		this.yCoord = yCoord;

		step = 0;
		parent = null;
		
		up = false;
		down = false;
		left = false;
		right = false;
		
		visit = false;
		shortPath = false;
	}

	// initialize the boundary entries
	public Cell(boolean visit) {

		this.visit = true;
	}

	public boolean isIntact() {

		return (up && down && left && right) ? true : false;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isVisit() {
		return visit;
	}

	public void setVisit(boolean visit) {
		this.visit = visit;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public Cell getParent() {
		return parent;
	}

	public void setParent(Cell parent) {
		this.parent = parent;
	}

	public boolean isShortPath() {
		return shortPath;
	}

	public void setShortPath(boolean shortPath) {
		this.shortPath = shortPath;
	}

}

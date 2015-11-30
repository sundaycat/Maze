package sjsu.cs146.project3;

import java.util.Random;
import java.util.Stack;

public class Maze {

	private int row;
	private int col;
	private Cell[][] maze = null;

	public Maze(int row, int col) {

		this.row = row;
		this.col = col;

		// maintain an (row+2) x (col+2) grid of cells to avoid tedious special
		// cases
		maze = new Cell[row + 2][col + 2];
		for (int i = 0; i < row + 2; i++) {
			maze[i][0] = new Cell(true);
			maze[i][col + 1] = new Cell(true);
		}

		for (int i = 0; i < col + 2; i++) {
			maze[0][i] = new Cell(true);
			maze[row + 1][i] = new Cell(true);
		}

		for (int i = 1; i <= row; i++)
			for (int j = 1; j <= col; j++)
				maze[i][j] = new Cell(i, j);

		// generate the maze
		generate(1, 1);
	}

	public void generate(int x, int y) {

		int visitedCells = 1;
		int totalCells = row * col;

		Cell curCell = maze[x][y];
		Random rnd = new Random();
		Stack<Cell> cellStack = new Stack<Cell>();
		while (visitedCells < totalCells) {

			x = curCell.getxCoord();
			y = curCell.getyCoord();

			// check if there is an unvisited neighbor
			if (!maze[x + 1][y].isVisit() || !maze[x - 1][y].isVisit()
					|| !maze[x][y + 1].isVisit() || !maze[x][y - 1].isVisit()) {

				// pick a random neighbor with all walls intact
				int dirs = rnd.nextInt(4) + 1;
				switch (dirs) {
				case 1: // Up
					// If the cell hasn't been visit before, then remove walls
					if (!maze[x - 1][y].isVisit()) {
						maze[x - 1][y].setDown(true);
						maze[x][y].setUp(true);
						curCell.setVisit(true);
						// curCell.setStep(visitedCells);
						visitedCells++;

						cellStack.push(maze[x][y]);
						curCell = maze[x - 1][y];
					}
					break;
				case 2: // Left
					// If the cell hasn't been visit before, then remove walls
					if (!maze[x][y - 1].isVisit()) {
						maze[x][y - 1].setRight(true);
						maze[x][y].setLeft(true);
						curCell.setVisit(true);
						// curCell.setStep(visitedCells);
						visitedCells++;

						cellStack.push(maze[x][y]);
						curCell = maze[x][y - 1];
					}
					break;
				case 3: // Right
					// If the cell hasn't been visit before, then remove walls
					if (!maze[x][y + 1].isVisit()) {
						maze[x][y + 1].setLeft(true);
						maze[x][y].setRight(true);
						curCell.setVisit(true);
						// curCell.setStep(visitedCells);
						visitedCells++;

						cellStack.push(maze[x][y]);
						curCell = maze[x][y + 1];
					}
					break;
				case 4: // Down
					// If the cell hasn't been visit before, then remove walls
					if (!maze[x + 1][y].isVisit()) {
						maze[x + 1][y].setUp(true);
						maze[x][y].setDown(true);
						curCell.setVisit(true);
						// curCell.setStep(visitedCells);
						visitedCells++;

						cellStack.push(maze[x][y]);
						curCell = maze[x + 1][y];
					}
					break;
				}

			} else {

				// if all the neighbor has been visited, then pop the most
				// recent cell entry off the stack and make it current cell
				if (!curCell.isVisit()) {
					curCell.setVisit(true);
					// curCell.setStep(visitedCells);
				}
				curCell = cellStack.pop();
			}
		}
	}

	// reset the visit status of each cell in maze
	public void reset() {

		for (int i = 1; i <= row; i++)
			for (int j = 1; j <= col; j++)
				maze[i][j].setVisit(false);
		;
	}

	public void depthFirstSearch(int x, int y) {

		// reset the visit status of each cell
		reset();

		int vOrder = 0;
		Cell curCell = maze[x][y];
		Stack<Cell> cellStack = new Stack<Cell>();
		while (curCell.getxCoord() != row && curCell.getyCoord() != col) {

			x = curCell.getxCoord();
			y = curCell.getyCoord();

			// check if there is a unexplored path on the up, left, right and
			// down direction respectively.
			boolean upPath = maze[x][y].isUp() && !maze[x - 1][y].isVisit();
			boolean ltPath = maze[x][y].isLeft() && !maze[x][y - 1].isVisit();
			boolean rtPath = maze[x][y].isRight() && !maze[x][y + 1].isVisit();
			boolean dnPath = maze[x][y].isDown() && !maze[x + 1][y].isVisit();

			if (upPath || ltPath || rtPath || dnPath) {

				if (upPath) {
					curCell.setStep(vOrder++);
					maze[x - 1][y].setParent(curCell);
					curCell.setVisit(true);
					cellStack.push(curCell);
					curCell = maze[x - 1][y];
				}

				if (ltPath) {
					curCell.setStep(vOrder++);
					maze[x][y - 1].setParent(curCell);
					curCell.setVisit(true);
					cellStack.push(curCell);
					curCell = maze[x][y - 1];
				}

				if (rtPath) {
					curCell.setStep(vOrder++);
					maze[x][y + 1].setParent(curCell);
					curCell.setVisit(true);
					cellStack.push(curCell);
					curCell = maze[x][y + 1];
				}

				if (dnPath) {
					curCell.setStep(vOrder++);
					maze[x + 1][y].setParent(curCell);
					curCell.setVisit(true);
					cellStack.push(curCell);
					curCell = maze[x + 1][y];
				}

			} else {

				if (!curCell.isVisit()) {
					curCell.setStep(vOrder++);
					curCell.setVisit(true);
				}
				curCell = cellStack.pop();
			}

		}
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		maze[1][1].setUp(true);
		maze[row][col].setDown(true);

		for (int i = 1; i <= row; i++) {

			// print out horizontal wall
			for (int j = 1; j <= col; j++) {
				sb.append("+");
				if (maze[i][j].isUp()) {
					sb.append("  ");
				} else {
					sb.append("--");
				}
			}

			sb.append("+");
			sb.append('\n');

			// print out vertical wall
			for (int j = 1; j <= col; j++) {
				// print out left wall
				if (maze[i][j].isLeft()) {
					sb.append(" ");
				} else {
					sb.append("|");
				}

				// print out the empty space of cell
				// sb.append("  ");
				int step = maze[i][j].getStep();
				if (step != 0) {
					sb.append(step + " ");
				} else {
					sb.append("  ");
				}
			}

			sb.append("|");
			sb.append('\n');
		}

		// print out the last horizontal wall
		for (int i = 1; i <= col - 1; i++) {
			sb.append("+--");
		}
		sb.append("+");
		sb.append("  +");

		return sb.toString();
	}
}
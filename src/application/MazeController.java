package application;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import searches.*;

public class MazeController {

	private SearchAlgorithm search;
	
	
	// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.
	

	// Where to start and stop the search
	private Point start;
	private Point goal;
	private SearchFactory searchFactory;
	// The maze to search
	private Maze maze;
	

	
	private MazeDisplay view;

	public MazeController(int num_rows, int num_columns, MazeDisplay mazeDisplay) {
		start = new Point(1,1);
		goal = new Point(num_rows-2, num_columns-2);
		maze = new Maze(num_rows, num_columns);
		view = mazeDisplay;
		searchFactory = new SearchFactory();

	}
	
	public Point getMazeDimensions(int num_rows, int num_columns) {
		return new Point(num_rows, num_columns);
	}
	

	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		view.redraw();
	}
	
	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime){
		if(!view.isPaused()) {
			doOneStep(elapsedTime);
		}
	}

	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		if(search!=null) {
			search.step();
			view.redraw();
		}
	}
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		search = searchFactory.makeSearch(searchType, maze, start, goal);
	}
	
	public int getCellState(Point position) {
		return maze.get(position);
	}
	


}

package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import application.Maze;

public class DFS extends SearchAlgorithm{

	
	public DFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks,  startPoint, goalPoint);
		// The data structure for DFS is a stack.
		Stack<Point> stack =new Stack<>();
		stack.push(startPoint);
		data = stack;
	}
	
	
	protected void removeVisited() {
		Stack<Point> stack = (Stack<Point>)data;
		stack.pop();
		//System.out.println("remove_visited");
	}
	


	
	// When a new node is chosen, push it on the stack
	protected void recordLink(Point next){
		Stack<Point> stack = (Stack<Point>)data;
		// FIXME: add try/catch for ClassCastException
		stack.push(next);
	}
	
	/*
	 * Get the next fringe point to consider.
	 * 
	 * This implementation resets the "current" instance variable 
	 * to be the next one on the fringe data structure.
	 */
	protected void resetCurrent(){
		Stack<Point> stack = (Stack<Point>)data;
		current = stack.peek();
	}
	
	
	protected boolean isGoal(Point square){
		return square!= null && square.equals(goal);
	}
	
	@Override
	protected void noNextStepFound() {
		maze.markVisited(current);
		removeVisited();
	}
}

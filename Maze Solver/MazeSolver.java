import java.util.ArrayList;


public class MazeSolver {

	char[][] maze;
	int pointsVisited = 0;
	
	public static void main(String[] args) {
		MazeSolver m = new MazeSolver();
		m.run();

	}

	public void run() {
		
		maze = new char[41][41];
		convertStringToArray(0,"*****************************************");
		convertStringToArray(1,"  *   *   *     * *   *   *   *     *   *");
		convertStringToArray(2,"* * * * * * *** * * * * * * * * *** *** *");
		convertStringToArray(3,"* * * * *     * *   * * * * * * *   *   *");
		convertStringToArray(4,"* * * *** * *** ***** *** *** * ***** * *");
		convertStringToArray(5,"*   *   * * *   *   * *       * *   * * *");
		convertStringToArray(6,"******* * *** ***** * * * ***** * * * * *");
		convertStringToArray(7,"*     * * *       *     * *   *   * * * *");
		convertStringToArray(8,"* *** * * * * *** *** *** *** ***** * * *");
		convertStringToArray(9,"* * * *   * * *   *   *     *   *   * * *");
		convertStringToArray(10,"* * * ***** * ***** *** *** * * *** *** *");
		convertStringToArray(11,"*   *   *   *       *   * * * *   *   * *");
		convertStringToArray(12,"***** *** *********** *** * * *** *** * *");
		convertStringToArray(13,"*   *     *       *   * *     *   *   * *");
		convertStringToArray(14,"* * ******* ***** * *** *** *** *** *** *");
		convertStringToArray(15,"* *   *   *   *   *     *   * *     *   *");
		convertStringToArray(16,"* *** *** * *** ***** *** *** ******* ***");
		convertStringToArray(17,"*   *   *     * *       *         *   * *");
		convertStringToArray(18,"*** *** *** * *** ***** ********* * *** *");
		convertStringToArray(19,"*     * *   *     *   *   *     *   *   *");
		convertStringToArray(20,"* *** * * *********** ***** * * ***** ***");
		convertStringToArray(21,"*   * *   *         *   *   * * *       *");
		convertStringToArray(22,"*** ******* ******* * *** *** * * ***** *");
		convertStringToArray(23,"* * *   *   *     * *   * * * * * * *   *");
		convertStringToArray(24,"* * * * * *** *** * *** * * * *** * * * *");
		convertStringToArray(25,"* *   * *   * *   *   *   * *     *   * *");
		convertStringToArray(26,"* *** * ***** * *** * ***** * ********* *");
		convertStringToArray(27,"*   * * * *   *     *   *   * *   *   * *");
		convertStringToArray(28,"*** * * * * *********** * *** *** *** * *");
		convertStringToArray(29,"* *   *   *   *     * *     *   *   * * *");
		convertStringToArray(30,"* ******* *** * *** * *** * *** *** * * *");
		convertStringToArray(31,"*     * *     * *         *   * *     * *");
		convertStringToArray(32,"***** * ******* * ***** ******* *** *****");
		convertStringToArray(33,"*   *     *     *   * * *   *   *   *   *");
		convertStringToArray(34,"* ******* * ******* * * * * * * * *** * *");
		convertStringToArray(35,"*       *   *       *     * * *   *   * *");
		convertStringToArray(36,"* ***** ******* ***** ******* ***** *** *");
		convertStringToArray(37,"*     *   *     *     *   *   *       * *");
		convertStringToArray(38,"***** *** * *** * ***** * * *********** *");
		convertStringToArray(39,"*     *     *   *       *                ");
		convertStringToArray(40,"*****************************************");
		
		solveMaze(maze, new Point(0,1), new Point(40,39));
		printMaze();
		System.out.println("Points visited:" + pointsVisited);

	}
	
	
	private void convertStringToArray(int y, String input) {
		for (int x = 0; x < input.length(); x++) {
			maze[x][y] = input.charAt(x);			
		}			
	}
	
	private void printMaze(){
		for (int y = 0; y < maze[0].length; y++){
			for (int x = 0; x < maze.length; x++){
				System.out.print(maze[x][y]);
			}
			System.out.println();
		}
			
	}
	
	private boolean solveMaze(char[][] maze, Point current, Point end){

		boolean solved = false;
    pointsVisited++;
		maze[current.x][current.y] = 'o'; //tracker
		if(current.x<40 && !solved && maze[current.x+1][current.y]==' '){ //move right x-axis
      solved = solveMaze(maze, new Point(current.x+1, current.y), end);
    }
    if(current.x>0 && !solved && maze[current.x-1][current.y]==' '){ //move left x-axis
      solved = solveMaze(maze, new Point(current.x-1, current.y), end);
    }
    if(current.y>0 && !solved && maze[current.x][current.y-1]==' '){ //move up y-axis
      solved = solveMaze(maze, new Point(current.x, current.y-1), end);
    }
    if(current.y<40 && !solved && maze[current.x][current.y+1]==' '){//move down y-axis
      solved = solveMaze(maze, new Point(current.x, current.y+1), end);
    }
    if(!solved){
      maze[current.x][current.y] = ' ';//clearing redundancy
    }
    if(current.x==end.x && current.y==end.y){
      solved = true;
    }
		return solved;
    
	}

}


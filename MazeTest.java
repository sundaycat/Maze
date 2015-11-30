package sjsu.cs146.project3;

public class MazeTest {
	
	public static void main(String[] args){
		
		Maze test = new Maze(4,4);
		System.out.println(test);
		
		test.depthFirstSearch(1, 1);
		
		System.out.println();
		System.out.println();
		System.out.println(test);

	}

}

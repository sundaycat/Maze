package sjsu.cs146.project3;

public class MazeTest {
	
	public static void main(String[] args){
		
		Maze test = new Maze(10,10);
		System.out.println(test.toString(1));
		
		System.out.println("############# DSF ###############");
		test.depthFirstSearch(1, 1);
		System.out.println(test.toString(2));
		
		System.out.println();
		System.out.println(test.toString(3));
		System.out.println("############# DSF ###############");
		
		System.out.println();
		System.out.println("############# BSF ###############");
		test.breadthFirstSearch(1, 1);
		System.out.println(test.toString(2));
		
		System.out.println();
		System.out.println(test.toString(3));
		System.out.println("############# BSF ###############");

		
	}

}

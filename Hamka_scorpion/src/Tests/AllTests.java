//package Tests;
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import Model.Game;
//
//class AllTests {
//
//	
//	private Game gm ; 
////	private Pair expected ;
//	
//	@Before
//    public void setup() {
//        gm = new Game("P1", "P2");
//     //   expected =  gm.getPair(1,2);
//    }
//	
//	@Test
//	void test() {
//		Game g = new Game("a", "b") ; 
//		//Pair p1 = g.getPair(1,2) ; 
//		
//	}
//
//	@Test
//    public void queenMovementPrintTest() throws Exception {
//        setup();
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//          System.setOut(new PrintStream(outContent));
//
//          int[][] board = {{-1,2,-1,2,-1,2,-1,2},
//                              {2,-1,2,-1,2,-1,2,-1},
//                              {-1,22,-1,2,-1,2,-1,2},
//                              {0,-1,0,-1,1,-1,0,-1},
//                            {-1,0,-1,0,-1,0,-1,0},
//                            {1,-1,1,-1,1,-1,1,-1},
//                            {-1,1,-1,1,-1,1,-1,1},
//                            {1,-1,1,-1,1,-1,1,-1}
//};
//          gm.setBoard(board);
//      //    gm.moveQueen(gm.getContentWithXandY(2, 1), gm.getPair(2, 1), gm.getPair(4, 3), gm.getQueenMoves(gm.getContentWithXandY(2, 1), gm.getPair(2, 1)));
//  //("You have moved your queen from " + cur + " to " + next);
////          String expectedOutput1 = "You have moved your queen from " + gm.getPair(2, 1) + " to " + gm.getPair(4, 3);
//      //    String expectedOutput2 = "You have moved your queen from " + gm.getPair(2, 1) + " to " + gm.getPair(4, 3) + ", and killed an enemy";
//          System.err.println(outContent.toString()+"***");
//       //   System.err.println(expectedOutput1+"***");
//          System.err.println(String.valueOf(outContent).getClass());
//          System.err.println(outContent.toString()+"***");
//          String temp = String.valueOf(outContent) ;
//          StringBuffer sb= new StringBuffer(temp); 
//          sb.deleteCharAt(sb.length()-1);  
//          sb.deleteCharAt(sb.length()-1); 
////          StringBuffer ss= new StringBuffer(expectedOutput1); 
//
//          
//        //   ss.deleteCharAt(ss.length()-1); 
////          System.err.println("->"+sb.charAt(sb.length()-1)+"<-");
////          System.err.println("->"+ss.charAt(ss.length()-1)+"<-");
////          assertTrue(sb.equals(ss));
//        //  assertEquals("the should be equals", sb,expectedOutput1);
//          
//          
//        //  assertTrue(String.valueOf(outContent).equals(expectedOutput1) || outContent.toString().equals(expectedOutput2));
//          System.err.println(outContent.toString()+"***");
//          
//          }
//	
//}

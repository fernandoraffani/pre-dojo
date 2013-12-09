package functionalTest;

import java.io.IOException;

import org.junit.Test;

import fpsmatch.main.FPSGameLogAnalyser;

public class FunctionalTests {
	
	private FPSGameLogAnalyser FPSGameLogAnalyser;

	@Test
	public void testCase1() throws IOException{
		String[] args = new String[1];
		args[0] = "/home/sasha/workspace/fps.match/src/test/java/functionalTest/files/case1.txt";
		FPSGameLogAnalyser.main(args);
	}
	
	@Test
	public void testCase2() throws IOException{
		String[] args = new String[1];
		args[0] = "/home/sasha/workspace/fps.match/src/test/java/functionalTest/files/case2.txt";
		FPSGameLogAnalyser.main(args);
	}
	
}

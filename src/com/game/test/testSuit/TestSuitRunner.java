package com.game.test.testSuit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestSuitRunner {

	public static void main(String[] args) {
		Result runClasses = JUnitCore.runClasses(SnakeAndLadderAllTestSuite.class);
		System.out.println("Total Junit Test Cases are :"+runClasses.getRunCount());
		System.out.println("Total Success :"+(runClasses.getRunCount() - runClasses.getFailureCount()));
		System.out.println("Total Failure :"+runClasses.getFailureCount());
	}
}

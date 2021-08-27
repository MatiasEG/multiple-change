package controllers;

import sets.CredibilityBase;

public class RunningExamples {
	
	public static CredibilityBase<Integer,Integer> get_example_1_prioritized_multiple_change() {
		CredibilityBase<Integer, Integer> cb = new CredibilityBase<Integer, Integer>();
		
		// Credibility order in context 1
		cb.addCredibilityObject(2, 3, 1);
		cb.addCredibilityObject(4, 2, 1);
		cb.addCredibilityObject(6, 3, 1);
		cb.addCredibilityObject(5, 6, 1);		
		
		// Credibility order in context 2
		cb.addCredibilityObject(2, 5, 2);
		cb.addCredibilityObject(1, 2, 2);
		cb.addCredibilityObject(4, 2, 2);
		cb.addCredibilityObject(3, 2, 2);
			
		return cb;
	}
	
	public static CredibilityBase<Integer,Integer> get_example_2_prioritized_multiple_change() {
		CredibilityBase<Integer, Integer> cb = new CredibilityBase<Integer, Integer>();
		
		// Credibility order in context 1
		cb.addCredibilityObject(4, 3, 1);
		cb.addCredibilityObject(1, 4, 1);
		cb.addCredibilityObject(2, 4, 1);
		cb.addCredibilityObject(1, 2, 1);
		cb.addCredibilityObject(5, 2, 1);
		cb.addCredibilityObject(9, 6, 1);
		cb.addCredibilityObject(10, 9, 1);
				
		// Credibility order in context 2
		cb.addCredibilityObject(3, 1, 2);
		cb.addCredibilityObject(4, 3, 2);
		cb.addCredibilityObject(2, 1, 2);
		cb.addCredibilityObject(2, 9, 2);
		cb.addCredibilityObject(6, 5, 2);
		
		return cb;
	}
	
	public static CredibilityBase<Integer,Integer> get_example_7_prioritized_multiple_change() {
		CredibilityBase<Integer, Integer> cb = new CredibilityBase<Integer, Integer>();
		
		// Credibility order in context 1
		cb.addCredibilityObject(2, 3, 1);
		cb.addCredibilityObject(4, 2, 1);
		cb.addCredibilityObject(6, 3, 1);
		cb.addCredibilityObject(5, 6, 1);
		cb.addCredibilityObject(7, 5, 1);
		cb.addCredibilityObject(8, 3, 1);
		
		// Credibility order in context 2
		cb.addCredibilityObject(2, 5, 2);
		cb.addCredibilityObject(1, 2, 2);
		cb.addCredibilityObject(4, 2, 2);
		cb.addCredibilityObject(3, 2, 2);
			
		return cb;
	}
}
package com.cole.flowanalysis;

public class FlowAnalysis {
	
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		SourceData sd = new SourceData();
		try {
			sd.readSourceData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

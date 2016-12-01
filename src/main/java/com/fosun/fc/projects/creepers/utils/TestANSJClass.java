package com.fosun.fc.projects.creepers.utils;
import java.util.ListIterator;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class TestANSJClass {
	
	public TestANSJClass()
	{
		super();
	}
	
	/*
	 */
	public static void main(String[] args) throws Exception 
	{ 
		//
		Result parse = ToAnalysis.parse("中信银行股份有限公司安阳银行");
		
	    //
	    ListIterator<Term> iter = parse.getTerms().listIterator();
	    //
	    while(iter.hasNext())
	    {
	    	Term currItem = iter.next();
	    	
	    	System.out.println(currItem.getName());
	    	System.out.println(currItem.getNatureStr());
	    	System.out.println(currItem.item().getName());
	    	System.out.println(currItem.item().param);
	    }
	    //
	}
}

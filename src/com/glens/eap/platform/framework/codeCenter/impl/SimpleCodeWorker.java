package com.glens.eap.platform.framework.codeCenter.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.glens.eap.platform.framework.codeCenter.CodeWorker;

public class SimpleCodeWorker implements CodeWorker {

	private static final int MIN_NO = 100000;
	private static final int MAX_NO = 1000000;
	
	private static Object lock = new Object();
	
	@Override
	public String createCode(String prefix) {
		// TODO Auto-generated method stub
		return nextId(prefix);
	}
	
	private String nextId(String prefix) {
		
		String _nextId = "";
		
		synchronized (lock) {
			
			String time = this.timeGen();
			int _nextInt = this.nextInt(MIN_NO, MAX_NO);
			_nextId = prefix + time + _nextInt;
		}
		
		return _nextId;
	}
	
	private String timeGen() {
		
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return df.format(now);
	}
	
	private int nextInt(final int min, final int max) {
		
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}

}

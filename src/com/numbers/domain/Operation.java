package com.numbers.domain;

public enum Operation {
	ADD{
		@Override
		public int calculate(int a, int b) {
			return a + b;
		}
		
		public String toString() {
			return "+";
		}
	}, SUB {
		@Override
		public int calculate(int a, int b) {
			return a - b;
		}
		
		public String toString() {
			return "-";
		}
	}, TIMES {
		@Override
		public int calculate(int a, int b) {
			return a * b;
		}
		
		public String toString() {
			return "*";
		}
	};
	
	
	abstract public int calculate(int a, int b); 
}

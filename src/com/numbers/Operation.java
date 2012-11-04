package com.numbers;

public enum Operation {
	ADDITION{
		@Override
		public int calculate(int a, int b) {
			return a + b;
		}
		
		public String toString() {
			return "+";
		}
	}, SUBTRACTION {
		@Override
		public int calculate(int a, int b) {
			return a - b;
		}
		
		public String toString() {
			return "-";
		}
	};
	
	abstract public int calculate(int a, int b); 
}

package de.htwsaar.sosa.task5;

import java.util.function.IntFunction;
public class Factorial implements IntFunction<Integer> {

	@Override
	public Integer apply(int value) {
		if (value==0) {
			return 1;
		}
		else {
			return value * apply(value-1);
		}
	}

}

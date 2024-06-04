package de.htwsaar.sosa.task5;

import java.util.function.IntFunction;

public class ApplyAnPrint {
	public void applyAndPrint(int start, int end, IntFunction<Integer> function) {
		for(int i=start; i<end;++i) {
			int result = function.apply(i);
			System.out.format("f(%d)=%d\n",i,result);
		}
	}
}

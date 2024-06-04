package de.htwsaar.sosa.task5;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface ConditionalIntFunction extends IntFunction<Integer> {
	
	default IntFunction<Integer> conditionateInput(Predicate<Integer> inputPredicate) {
		return x -> inputPredicate.test(x)? apply(x):0;
	}
	
	default IntFunction<Integer> conditionateOutput(Predicate<Integer> outputPredicate) {
		return x -> outputPredicate.test(apply(x))? apply(x):0;
	}
}

package de.htwsaar.sosa.task5;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Main {
	static final int START = 0;
	static final int END = 10;

	public static void main(String[] args) {
		ApplyAnPrint ap = new ApplyAnPrint();
		// Lambda implementation
		System.out.println("Lambda implementation");

		System.out.println("\nf(x)=x*x");
		ap.applyAndPrint(START, END, (x) -> x * x); //elegant

		System.out.println("\nf(x)=x!");
		ap.applyAndPrint(START, END, (x) -> {
			int r = 1;
			for (int i = 1; i <= x; ++i) {
				r *= i;
			}
			return r;
		}); // ugly

		System.out.println("\nf(x)=x^(x+1)");
		ap.applyAndPrint(START, END, (x) -> (int) Math.pow(x, x + 1)); //elegant

		System.out.println("\nf(x)=fib(x)");
		ap.applyAndPrint(START, END, (x) -> {
			if (x < 2)
				return x;
			int first = 0, second = 1;
			int r = 0;
			for (int i = 2; i <= x; ++i) {
				r = first + second;
				first = second;
				second = r;
			}
			return r;
		}); // ugly

		
		System.out.println("Anonym classes");

		System.out.println("\nf(x)=x*x");
		ap.applyAndPrint(START, END, new IntFunction<Integer>() {
			@Override
			public Integer apply(int value) {
				return value*value;
			}
			
		}); //verbose
		
		System.out.println("\nf(x)=x!");
		ap.applyAndPrint(START, END, new IntFunction<Integer>() {
			@Override
			public Integer apply(int value) {
				if (value==0) return 1;
				return value*apply(value-1);
			}
			
		}); // better as with lambda, allows recursive call
		
		System.out.println("\nf(x)=x^(x+1)");
		ap.applyAndPrint(START, END, new IntFunction<Integer>() {
			@Override
			public Integer apply(int value) {
				return (int) Math.pow(value, value+1);
			}
			
		}); //verbose
		
		//Predicates
		Predicate<Integer> odd = new Predicate<Integer>() {
			@Override
			public boolean test(Integer value) {
				return value%2==1;
			}
			
		};
		
		Predicate<Integer> even = value -> value%2==0;
		System.out.println("f(x)=x*x, x%2=0");
		ConditionalIntFunction condXsquare = x->x*x;
		ap.applyAndPrint(START, END, condXsquare.conditionateInput(even));
		
		System.out.println("f(x)=x!, x!%2=1");
		ConditionalIntFunction condFactorial = x->new Factorial().apply(x);
		ap.applyAndPrint(START, END, condFactorial.conditionateOutput(odd));
	}
}
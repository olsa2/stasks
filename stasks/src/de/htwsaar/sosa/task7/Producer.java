package de.htwsaar.sosa.task7;

import java.util.Random;

public class Producer {
	Random random = new Random();
	public int produce() {
		return random.nextInt(1000);
	}
}

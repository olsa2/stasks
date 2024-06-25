package de.htwsaar.sosa.task7;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class ProducerConsumerMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Queue<Integer> q = args.length != 0 ? new PriorityQueue<>() : new LinkedList<>();
		Producer p = new Producer();
		Consumer c = new Consumer();
		Random ran = new Random();
		for (int i = 0; i < 10000; i++) {
			if (ran.nextInt(2) > 0) {
				q.offer(p.produce());
			} else {
				Integer n = q.poll();
				if (n != null) {
					c.consume(n);
				}
			}
		}
		System.out.println("Number of different results: " + c.numberOfDifferentResults());
		System.out.println("getCrossTotalsAscending");
		c.getCrossTotalsAscending().forEach(System.out::println);
		System.out.println("getCrossTotalsDescending");
		c.getCrossTotalsDescending().forEach(System.out::println);

		while (true) {
			System.out.print("Enter checksum: ");
			String input = scanner.nextLine();
			try {
				int n = Integer.parseInt(input);
				List<Long> timestamps = c.getTimestampsForResult(n);
				if (timestamps != null) {
					System.out.println("Timestamps");
					timestamps.forEach(System.out::println);
				} else {
					System.out.println("Don found!");
				}
			} catch (Exception e) {
				System.exit(0);
			}

		}

	}

}

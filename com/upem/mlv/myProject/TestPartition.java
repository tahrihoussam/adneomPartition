package com.upem.mlv.myProject;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.adneom.lib.partition.Partition;

public class TestPartition {
	public static void main(String[] args) {
		
		Integer[] listInteger = { 1, 2, 3, 4, 5 };
		Partition<Integer> partition = new Partition<Integer>(listInteger, 2);
		System.out.println(partition.getPartition());

		partition = new Partition<Integer>(listInteger, 3);
		System.out.println(partition.getPartition());

		partition = new Partition<Integer>(listInteger, 1);
		System.out.println(partition.getPartition());
		double ttc = 0.2;
		System.out.println(partition.getPartition((x) -> {
			return x - (x * ttc);
		}));

		List<Integer> intList = IntStream.range(1, 21).boxed().collect(Collectors.toList());
		partition = new Partition<>(intList, 10);
		System.out.println(partition.getPartition());

		List<Point> listP = new ArrayList<>();
		listP.add(new Point(0, 1));
		listP.add(new Point(0, 2));
		listP.add(new Point(0, 3));
		listP.add(new Point(0, 4));
		listP.add(new Point(0, 5));
		listP.add(new Point(0, 6));
		listP.add(new Point(0, 7));
		Partition<Point> partitionP = new Partition<>(listP, 6);
		System.out.println(partitionP.getPartition());
		System.out.println(partitionP.getPartition((x) -> {
			return x.getY();
		}));

		Integer[] Tab1 = { 1, 2, 3, 4, 5 };
		

		Partition<Integer> p = new Partition<>(Tab1, 3);

		Thread t0 = new Thread(() -> {
			System.out.println(p.getPartition());
		});
		Thread t1 = new Thread(() -> {
			System.out.println(p.getPartition());
		});
		Thread t2 = new Thread(() -> {
			System.out.println(p.getPartition());
		});
		Thread t3 = new Thread(() -> {
			System.out.println(p.getPartition());
		});
		t1.start();
		t2.start();
		t3.start();
		t0.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t0.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

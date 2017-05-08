package com.adneom.lib.partitionTest;

import static org.junit.Assert.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.adneom.lib.partition.Partition;

public class TestCasePartion {

	@Test(expected = NullPointerException.class)
	public void testPartitionNullList() {
		Integer[] l = null;
		Partition<Integer> p = new Partition<>(l, 12);
		p.getPartition();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPartitionNegatifSize() {
		Integer[] l = { 1, 2, 3, 4, 5 };
		Partition<Integer> p = new Partition<>(l, -2);
		p.getPartition();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPartitionOverSize() {
		Integer[] l = { 1, 2, 3, 4, 5 };
		Partition<Integer> p = new Partition<>(l, 6);
		p.getPartition();

	}
	@Test(expected = NullPointerException.class)
	public void testPartitionElementInTabObjectRequiredNonNull() {
		List<Point> l3 = new ArrayList<>();
		l3.add(new Point(0, 1));
		l3.add(new Point(0, 2));
		l3.add(new Point(0, 3));
		l3.add(new Point(0, 4));
		l3.add(new Point(0, 5));
		l3.add(new Point(0, 6));
		l3.add(null);
		Partition<Point> p = new Partition<>(l3, 1);
		List<Integer> intList = IntStream.range(1, 8).boxed().collect(Collectors.toList());
		List<List<Integer>> intListList = intList.stream().map((x) -> {
			List<Integer> l = new ArrayList<>();
			l.add(x);
			return l;
		}).collect(Collectors.toList());
		p.getPartition((x)->{return (int)x.getY();});
		assertTrue(intListList.equals(p.getPartition((x)->{return (int)x.getY();})));
	}
	@Test
	public void testPartitionElementInTabObject() {
		List<Point> l3 = new ArrayList<>();
		l3.add(new Point(0, 1));
		l3.add(new Point(0, 2));
		l3.add(new Point(0, 3));
		l3.add(new Point(0, 4));
		l3.add(new Point(0, 5));
		l3.add(new Point(0, 6));
		l3.add(new Point(0, 7));
		Partition<Point> p = new Partition<>(l3, 1);
		List<Integer> intList = IntStream.range(1, 8).boxed().collect(Collectors.toList());
		List<List<Double>> intListList = intList.stream().map((x) -> {
			List<Double> l = new ArrayList<>();
			l.add(x.doubleValue());
			return l;
		}).collect(Collectors.toList());
		p.getPartition((x)->{return (int)x.getY();});
		assertTrue(intListList.equals(p.getPartition((x)->{return x.getY();})));
	}
	@Test
	public void testPartitionTabObject() {
		List<Point> l3 = new ArrayList<>();
		l3.add(new Point(0, 1));
		l3.add(new Point(0, 2));
		l3.add(new Point(0, 3));
		l3.add(new Point(0, 4));
		l3.add(new Point(0, 5));
		l3.add(new Point(0, 6));
		l3.add(new Point(0, 7));
		Partition<Point> p = new Partition<>(l3, 6);

		List<Point> l4 = new ArrayList<>();
		l4.add(new Point(0, 1));
		l4.add(new Point(0, 2));
		l4.add(new Point(0, 3));
		l4.add(new Point(0, 4));
		l4.add(new Point(0, 5));
		l4.add(new Point(0, 6));

		List<Point> l5 = new ArrayList<>();
		l5.add(new Point(0, 7));

		List<List<Point>> l6 = new ArrayList<>();
		l6.add(l4);
		l6.add(l5);
		assertTrue(l6.equals(p.getPartition()));
	}

	@Test
	public void testPartitionListInteger() {
		List<Integer> intList = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
		List<List<Integer>> intListList = intList.stream().map((x) -> {
			List<Integer> l = new ArrayList<>();
			l.add(x);
			return l;
		}).collect(Collectors.toList());
		Partition<Integer> p = new Partition<>(intList, 1);
		assertTrue(intListList.equals(p.getPartition()));
	}

	@Test
	public void testPartitionListObject() {

		Integer[] Tab1 = { 1, 2, 3, 4, 5 };
		List<List<Integer>> l = new ArrayList<>();

		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);

		List<Integer> l2 = new ArrayList<>();
		l2.add(4);
		l2.add(5);

		l.add(l1);
		l.add(l2);
		Partition<Integer> p = new Partition<>(Tab1, 3);
		assertTrue(l.equals(p.getPartition()));
	}

	@Test
	public void testPartitionListObjectFalse() {

		Integer[] Tab1 = { 1, 2, 3, 4};
		List<List<Integer>> l = new ArrayList<>();

		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);

		List<Integer> l2 = new ArrayList<>();
		l2.add(4);
		l2.add(5);

		l.add(l1);
		l.add(l2);
		Partition<Integer> p = new Partition<>(Tab1, 3);
		assertFalse(l.equals(p.getPartition()));
	}
	
	@Test
	public void testPartitionMultiThread() {
		Integer[] Tab1 = { 1, 2, 3, 4, 5 };
		List<List<Integer>> l = new ArrayList<>();

		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);

		List<Integer> l2 = new ArrayList<>();
		l2.add(4);
		l2.add(5);

		l.add(l1);
		l.add(l2);
		Partition<Integer> p = new Partition<>(Tab1, 3);

		Thread t0 =  new Thread(() -> {
			assertTrue(l.equals(p.getPartition()));
			
		});
		Thread t1 = new Thread(() -> {
			assertTrue(l.equals(p.getPartition()));
			
		});
		Thread t2 = new Thread(() -> {
			assertTrue(l.equals(p.getPartition()));
			
		});
		Thread t3 = new Thread(() -> {
			assertTrue(l.equals(p.getPartition()));
			
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

package com.adneom.lib.partition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Date : May 8-2017 this application transform your list into list of list
 * @author htahri
 * @version 1.8
 * 
 * @param <T>
 */
public class Partition<T> {

	private final List<T> partition;
	private final int size;
	private final Pratitionable<T> pratitionable;
	private boolean canAdd;

	/**
	 * 
	 * @param partition
	 *            list to perform
	 * @param size
	 *            length of sub list
	 */
	public Partition(List<T> partition, int size) {
		super();
		Objects.requireNonNull(partition);
		if (size > partition.size() || size <= 0)
			throw new IllegalArgumentException("partition size must be between 1 and " + partition.size());
		this.partition = partition;
		this.size = size;
		this.pratitionable = new PartitionList<>(size);
		this.canAdd = true;
	}

	/**
	 * 
	 * @param partition
	 *            Tab to perform
	 * @param size
	 *            length of sub list
	 */
	public Partition(T[] partition, int size) {
		super();
		Objects.requireNonNull(partition);
		if (size > partition.length || size <= 0)
			throw new IllegalArgumentException("partition size must be between 1 and " + partition.length);

		this.partition = Arrays.asList(partition);
		this.size = size;
		this.pratitionable = new PartitionList<>(size);
		this.canAdd = true;
	}
	
	/**
	 * 
	 * @return returning list of list element
	 */
	public List<List<T>> getPartition() {
		synchronized (partition) {
			if (canAdd) {
				partition.stream().forEach((x) -> pratitionable.add(x));
			}
			canAdd = false;
		}
		return pratitionable.get();

	}

	/**
	 * 
	 * @param f
	 *            function to apply on your object
	 * @return returning list of list element with applying function
	 */
	public <R> List<List<R>> getPartition(Function<T, R> f) {

		List<R> subList = this.partition.stream().map((x) -> {
			Objects.requireNonNull(x);
			return f.apply(x);
		}).collect(Collectors.toList());

		Partition<R> subpartition = new Partition<R>(subList, size);
		return subpartition.getPartition();

	}

}

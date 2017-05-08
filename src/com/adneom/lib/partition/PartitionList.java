package com.adneom.lib.partition;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author htahri
 *
 * @param <T>
 */
public class PartitionList<T> implements Pratitionable<T> {
	private final List<List<T>> partition;
	private final int size;
	private int cpt;
	

	/**
	 * 
	 * @param size
	 */
	public PartitionList(int size) {
		super();
		this.partition = new ArrayList<>();
		this.size = size;
		this.cpt = 0;
		
	}

	@Override
	public  boolean add(T element) {
		
		
		if (cpt / size >= partition.size())
			partition.add(new ArrayList<>());
		List<T> tmp ;
	    tmp = partition.get(cpt / size);
		cpt++;
		
		return tmp.add(element);

	}

	@Override
	public List<List<T>> get() {
		
		return partition;
	}

}
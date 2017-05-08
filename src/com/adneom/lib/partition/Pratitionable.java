package com.adneom.lib.partition;

import java.util.List;
/**
 * 
 * @author htahri
 *
 * @param <T>
 */
public interface Pratitionable<T> {
	/**
	 * 
	 * @param element 
	 * @return boolean 
	 */
	public boolean add(T element);

	
	/**
	 * 
	 * @return list partitioned
	 */
	public List<List<T>> get();
}

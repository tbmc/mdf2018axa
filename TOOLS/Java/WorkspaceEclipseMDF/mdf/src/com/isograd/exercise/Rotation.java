package com.isograd.exercise;

import java.util.Arrays;
import java.util.List;


public class Rotation<T> {
	// public static class Rotation<T> {
	public static enum DIR {
		RIGHT, TOP, LEFT, DOWN 
	}
	List<T> list;
	int current = -1;
	public Rotation(T... list) {
		this.list = Arrays.asList(list);
	}
	public Rotation(List<T> list) {
		this.list = list;
	}
	public T next() {
		current++;
		if ( current >= list.size() ) current = 0;
		return current();
	}
	public T current() {
		return list.get(current);
	}
}

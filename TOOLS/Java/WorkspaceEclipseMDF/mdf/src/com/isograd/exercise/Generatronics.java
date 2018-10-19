package com.isograd.exercise;

import java.util.Arrays;
import java.util.stream.Stream;

public class Generatronics {
	// static class Generatronics {
	static class Pointer {
		int i; int j;
	}
	public static <T> Stream<T[]> permute( T[] array ) {
		final Pointer p = new Pointer();
		p.i = 0; p.j = array.length;
		return Stream.iterate(array, arr -> p.i != p.j, arr -> {
			T obj = arr[p.i+1];
			arr[p.i+1] = arr[p.i];
			arr[p.i] = obj;
			p.i++;
			if ( p.i == p.j -1) {
				p.i = 0;
				p.j--;
			}
			return arr;
		});
	}
	
	public static <T> Stream<T[]> combine( final T[] array, int n ) {
		final int[] state = new int[n];
		final T[] seed = Arrays.copyOf(array, n);
		for (int i = 0; i < state.length; i++) {
			state[i] = i;
		}
		return Stream.iterate(seed, arr -> state[0] >= 0, arr -> {
			if ( state[0] == array.length - n ) {
				state[0] = -1;
				return arr;
			}
			int i = state.length - 1;
			state[i]++;
			while ( state[i] > array.length - arr.length + i ) {
				i--;
				state[i]++;
			}
			int k = state[i];
			for ( int j = i; j < state.length; j++ ) state[j] = k++;
			for (int j = 0; j < state.length; j++) {
				arr[j] = array[ state[j] ];
			}
			return arr;
		});
	}
	
	public static <T> Stream<T[]> iterate( final T[] array, int n ) {
		final int[] state = new int[n];
		final T[] seed = Arrays.copyOf(array, n);
		for (int i = 0; i < state.length; i++) {
			seed[i] = array[ state[i] ];
		}
		return Stream.iterate(seed, arr -> state[0] >= 0, arr -> {
			int i = 0;
			state[i]++;
			while ( state[i] > array.length - 1 ) {
				state[i] = 0;
				i++;
				if ( i > state.length - 1 ) {
					state[0] = -1;
					return arr;
				}
				state[i]++;
			}
			for (int j = 0; j < state.length; j++) {
				arr[j] = array[ state[j] ];
			}
			return arr;
		});
	}
	
	public static Integer[] ints(int nb) {
		Integer[] res = new Integer[nb];
		for (int i = 0; i < res.length; i++) res[i] = i;
		return res;
	}
	
	public static Character[] chars(int nb) {
		Character[] res = new Character[nb];
		char c = 'a';
		for (int i = 0; i < res.length; i++) res[i] = c++;
		return res;
	}
	
	public static void main(String[] args) {
		Generatronics.iterate(Generatronics.chars(3), 3)
			.forEach( arr -> {
				for (int i = 0; i < arr.length; i++) {
					System.out.print( arr[i] + ", " );
				}
				System.out.println();
			});
	}

}

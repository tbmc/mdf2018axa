package com.isograd.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Canva {
	// static class Canva {
	public interface Visitor {
		public void visit(int idx, char c);
	}
	public interface VisitorXY {
		public void visit(int x, int y, char c);
	}
	public enum SYMETRIE {
		NONE,
		VERTICAL,
		HORIZONTAL,
		ROTATION4
	}
	private class Case {
		char c;
		int x;
		int y;
		Case(char c, int x, int y) {
			this.c = c; this.x = x; this.y = y;
		}
		@Override
		public String toString() {
			return String.format("%d;%d %c", x, y, c);
		}
	}
	char[] chars;
	int size, Ysize ;
	SYMETRIE symetrie = SYMETRIE.NONE;
	Canva(int Xsize, int Ysize) {
		this.size = Xsize;
		this.Ysize = Ysize;
		chars = new char[Xsize*Ysize];
	}
	public void visit(Visitor visitor) {
		for (int i = 0; i < chars.length; i++) {
			visitor.visit(i, chars[i]);
		}
	}
	public void visit(VisitorXY visitor) {
		for (int i = 0; i < chars.length; i++) {
			visitor.visit(i%size, i/size, chars[i]);
		}
	}
	Canva(int size) {
		this.size = this.Ysize = size;
		chars = new char[size*size];
	}
	void fill(char c) {
		for (int i = 0; i < chars.length; i++) {
			chars[i] = c;
		}
	}
	Stream<Case> around(int x, int y) {
		List<Case> cases = new ArrayList<>();
		if ( y > 0 ) {
			if ( x > 0 ) cases.add(new Case(get(x-1,y-1), x-1, y-1));
			cases.add(new Case(get(x,y-1), x, y-1));
			if ( x < size - 1 ) cases.add(new Case(get(x+1,y-1), x+1, y-1));
		}
		if ( x > 0 ) cases.add(new Case(get(x-1,y), x-1, y));
		if ( x < size - 1 ) cases.add(new Case(get(x+1,y), x+1, y));
		if ( y < Ysize - 1) {
			if ( x > 0 ) cases.add(new Case(get(x-1,y+1), x-1, y+1));
			cases.add(new Case(get(x,y+1), x, y+1));
			if ( x < size - 1 ) cases.add(new Case(get(x+1,y+1), x+1, y+1));
		}
		return cases.stream();
	}
	@SuppressWarnings("unchecked")
	Stream<Case> allCases() {
		Iterator<Case> iterator = new Iterator<Case>() {
			int c = 0;
			@Override
			public Case next() {
				int x = c % size;
				int y = c / size;
				return new Case(chars[c++], x, y);
			}
			
			@Override
			public boolean hasNext() {
				return c < chars.length;
			}
		};
		return StreamSupport.stream(new Iterable<Case>() {
			@Override
			public Iterator<Case> iterator() {
				return iterator;
			}
		}.spliterator(), false);
	}
	void symetrie(SYMETRIE symetrie) {
		if ( symetrie == SYMETRIE.ROTATION4 && Ysize != size ) 
			throw new RuntimeException("Symetrie rot4 impossible sur les matrices non carrées");
		this.symetrie = symetrie;
	}
	char get(int x, int y) {
		return chars[y*size+x];
	}
	void set( char c, int x, int y ) {
		try {
			chars[y*size+x] = c;
			
			if ( symetrie == SYMETRIE.NONE ) return;
			if ( symetrie == SYMETRIE.VERTICAL ) {
				chars[(Ysize-y-1)*size+x] = c;
			}
			if ( symetrie == SYMETRIE.HORIZONTAL ) {
				chars[y*size+(size-x-1)] = c;
			}
			if ( symetrie == SYMETRIE.ROTATION4 ) {
				chars[(size-x-1) * size+y]
				= chars[(size-y-1) * size+(size-x-1)]
				= chars[x* size+(size-y-1)]
				= c;
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException(String.format("map[%d,%d] out !", x, y));
		}
	}
	void hLine(char c, int x, int y, int w ) {
		while ( w-- > 0 )
			set(c, x + w, y );
	}
	void vLine(char c, int x, int y, int w ) {
		while ( w-- > 0 )
			set(c, x, y + w );
	}
	void setLine(String str, int line) {
		str.getChars(0, size, chars, size*line);
	}
	void draw() {
		for (int i = 0; i < chars.length; i++) {
			System.out.print( chars[i] );
			if ( (i+1)%size == 0 ) System.out.println();
		}
	}
}
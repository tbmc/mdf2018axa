package com.isograd.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Graph {
	
	// public static class Graph {
	
	public static class Node {
		List<Link> voisins;
		public int weight = 0;
		Object value;
		Node() {
			voisins = new ArrayList<>();
		}
		@Override
		public String toString() {
			return value.toString();
		}
		Node link(Node n) {
			Link l = new Link();
			l.target = n;
			voisins.add(l);
			return this;
		}
		static ArrayList<Node> list(int i) {
			ArrayList<Node> nodes = new ArrayList<>(i);
			while ( i-- > 0 ) nodes.add( new Node() );
			return nodes;
		}
		public int getWeight() {
			return weight;
		}
		public boolean connectedTo(Node node) {
			return 
				node.voisins.stream()
				.map( l -> l.target )
				.anyMatch( n -> n.equals(node) );
		}
	}
	
	public static class Link {
		Node target;
		int weight = 0;
	}
	
	List<Node> nodes;
	Graph() {
		nodes = new ArrayList<>();
	}
	Graph(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public List<Node> findHamiltonCycle(int size, Node start) {
		Node[] cycle = new Node[size];
		cycle[0] = start;
		return hamiltonTry(cycle, 0);
	}
	
	public List<Node> hamiltonTry(Node[] res, int k) {
		if ( res.length < 2 ) return null;
		ArrayList<Node> tried = new ArrayList<>();
		do {
			Node next = hamiltonNext(res, tried, k);
			if ( next == null ) {
				// return hamiltonTry(Arrays.copyOf(res, k), k-1);
					// Arrays.asList(Arrays.copyOf(res, k));
				return null;
			}
			k++;
			res[k] = next;
			if ( k == res.length - 1 && res[k].connectedTo(res[0]) ) {
				return Arrays.asList(res);
			}
			tried.add(next);
			return hamiltonTry(res, k);
		} while( true );
	}
	
	public Node hamiltonNext(Node[] res, List<Node> tried, int k) {
		return
			res[k].voisins.stream()
			.map( l -> l.target )
			.filter( n -> 
				!tried.contains(n) )
			.filter( n -> 
				!Arrays.asList(res).contains(n) )
			.filter( n -> 
				( k < res.length - 1 || n.connectedTo(res[0]) ) )
			.findFirst()
			.orElse( null );
	}
}
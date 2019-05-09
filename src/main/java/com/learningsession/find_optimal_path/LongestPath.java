package com.learningsession.find_optimal_path;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class LongestPath {
	
	private static final int INF = Integer.MAX_VALUE;

	public class AdjListNode {
		private int v;
		private int weight;

		AdjListNode(int v, int w) {
			this.v = v;
			this.weight = w;
		}

		int getV() {
			return v;
		}

		int getWeight() {
			return weight;
		}
	}

	// Class to represent graph as an adjcency list of
	// nodes of type AdjListNode
	public class Graph {
		private int v;
		private LinkedList<AdjListNode>[] adj;

		@SuppressWarnings("unchecked")
		public Graph(int v) {
			this.v = v;
			adj = new LinkedList[this.v];
			for (int i = 0; i < v; ++i) {
				adj[i] = new LinkedList<>();
			}
		}

		public void addEdge(int u, int v, int weight) {
			AdjListNode node = new AdjListNode(v, weight);
			adj[u].add(node);// Add v to u's list
		}

		// A recursive function used by shortestPath.
		// See below link for details
		public void topologicalLongestUtil(int v, Boolean visited[],
				Stack<Integer> stack) {
			// Mark the current node as visited.
			visited[v] = true;

			// Recur for all the vertices adjacent to this vertex
			Iterator<AdjListNode> it = adj[v].iterator();
			while (it.hasNext()) {
				AdjListNode node = it.next();
				if (!visited[node.getV()]) {
					topologicalLongestUtil(node.getV(), visited, stack);
				}
			}
			// Push current vertex to stack which stores result
			stack.push(v);
		}

		// The function to find longest paths from given vertex. It
		// uses recursive topologicalLongestUtil() to get topological
		// sorting of given graph.
		public String longestPath(int s) {
			Stack<Integer> stack = new Stack<>();
			int[] dist = new int[v];

			// Mark all the vertices as not visited
			Boolean[] visited = new Boolean[v];
			for (int i = 0; i < v; i++) {
				visited[i] = false;
			}

			// Call the recursive helper function to store Topological
			// Sort starting from all vertices one by one
			for (int i = 0; i < v; i++) {
				if (!visited[i]) {
					topologicalLongestUtil(i, visited, stack);
				}
			}
			// Initialize distances to all vertices as infinite and
			// distance to source as 0
			for (int i = 0; i < v; i++) {
				dist[i] = INF;
			}
			dist[s] = 0;

			// Process vertices in topological order
			while (!stack.empty()) {
				// Get the next vertex from topological order
				int u = (int) stack.pop();

				// Update distances of all adjacent vertices
				if (dist[u] != INF) {
					Iterator<AdjListNode> it;
					it = adj[u].iterator();
					while (it.hasNext()) {
						AdjListNode i = it.next();
						if (dist[i.getV()] > dist[u] + i.getWeight() * -1) {
							dist[i.getV()] = dist[u] + i.getWeight() * -1;
						}
					}
				}
			}

			// Print the calculated longest distances
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < v; i++) {
				builder.append(dist[i] == INF ? "INF " : (dist[i] * -1) + " ");
			}
			return builder.toString();
		}
	}

	// Method to create a new graph instance through an object
	// of ShortestPath class.
	public Graph newGraph(int number) {
		return new Graph(number);
	}
	
	public static String test() {
		LongestPath t = new LongestPath();
		Graph g = t.newGraph(6);
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 3, 6);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 4, 4);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 7);
		g.addEdge(3, 5, 1);
		g.addEdge(3, 4, -1);
		g.addEdge(4, 5, -2);
		int s = 1;
		return g.longestPath(s).trim();
	}
} 
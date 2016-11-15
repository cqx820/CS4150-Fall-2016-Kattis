package SinkCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Vertex implements Comparable<Vertex> {
	public String name;

	public Vertex(String name) {
		this.name = name;
	}

	public LinkedList<Edge> edges = new LinkedList<Edge>();
	public double minValue = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public String toString() {
		return this.name;
	}

	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return Double.compare(this.minValue, o.minValue);
	}
}

class Edge {
	public Vertex goal;
	public double cost;

	public Edge(Vertex goal, double cost) {
		this.goal = goal;
		this.cost = cost;
	}
}

public class AutoSink {
	public static void findPath(Vertex start) {
		start.minValue = 0.0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);
		while (!vertexQueue.isEmpty()) {
			Vertex v = vertexQueue.poll();
			for (Edge e : v.edges) {
				Vertex v1 = e.goal;
				double weight = e.cost;
				double distance = v.minValue + weight;
				if (distance < v1.minValue) {
					vertexQueue.remove(v1);
					v1.minValue = distance;
					v1.previous = v;
					vertexQueue.add(v1);
				}
			}
		}
	//	start.minValue = Double.POSITIVE_INFINITY;
	}

	public static void resetAll(HashMap<String, Vertex> map)
	{
		for (Map.Entry<String, Vertex> entry : map.entrySet()) {
	          map.get(entry.getKey()).minValue = Double.POSITIVE_INFINITY;
	      }
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sr = new Scanner(System.in);
		int cities = 0;
		int highways = 0;
		int trips = 0;
		// String firstLine = sr.nextLine();
		cities = Integer.parseInt(sr.nextLine());
		HashMap<String, Integer> vertex = new HashMap<String, Integer>();
		// List<Vertex> vertices = new ArrayList<Vertex>();
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
		for (int i = 0; i < cities; i++) {
			String[] nl = sr.nextLine().split(" ");
			String first = nl[0].toString();
			vertices.put(first, new Vertex(first));
			int second = Integer.parseInt(nl[1]);
			vertex.put(first, second);
		}
		// System.out.println(vertices.indexOf(""))
		// String nextNum = sr.nextLine();
		highways = Integer.parseInt(sr.nextLine());
		for (int i = 0; i < highways; i++) {
			String[] nx = sr.nextLine().split(" ");
			String first = nx[0].toString();
			String second = nx[1].toString();
			vertices.get(first).edges.add(new Edge(vertices.get(second), vertex
					.get(second)));
		}
		trips = Integer.parseInt(sr.nextLine());
		List<String> forPrint = new ArrayList<String>();
		for (int i = 0; i < trips; i++) {
			if (sr.hasNextLine()) {
				// System.out.println(sr.hasNextLine());
				String[] nv = sr.nextLine().split(" ");
				String first = nv[0].toString();
				String second = nv[1].toString();
				if (first.equals(second)) {
					// System.out.println(0);
					forPrint.add("" + 0);
					continue;
				}
				findPath(vertices.get(first));
				if (vertices.get(second).minValue == Double.POSITIVE_INFINITY) {
					// System.out.println((int) vertices.get(second).minValue);
					forPrint.add("NO");
				} else {
					forPrint.add("" + (int) vertices.get(second).minValue);
				}
				resetAll(vertices);
			}
		}
		// System.out.println(forPrint.size());
		for (String s : forPrint) {
			System.out.println(s);
		}
		return;

//		 Vertex A = new Vertex("Sourceville");
//		 Vertex B = new Vertex("SinkCity");
//		 Vertex C = new Vertex("Easton");
//		 Vertex D = new Vertex("Weston");
//		 // Vertex E = new Vertex("E");
//		 // Vertex F = new Vertex("F");
//		 // Vertex G = new Vertex("G");
//		 A.edges.add(new Edge(C, 20));
//		 A.edges.add(new Edge(D, 15));
//		 D.edges.add(new Edge(B, 10));
//		 C.edges.add(new Edge(B, 10));
//		 // B.edges.add(new Edge(F, 0));
//		 // E.edges.add(new Edge(G, 0));
//		 // F.edges.add(new Edge(G, 0));
//		 // D.edges.add(new Edge(G, 0));
//		 findPath(A);
//		 if(B.minValue == Double.POSITIVE_INFINITY)
//		 {
//			 System.out.println("NO");
//		 }
//		 else 
//			 System.out.println("Distance to " + D.toString() + ": " +
//		 (int) B.minValue);
//		 
//		 findPath(C);
//		 if(B.minValue == Double.POSITIVE_INFINITY)
//		 {
//			 System.out.println("NO");
//		 }
//		 else 
//			 System.out.println("Distance to " + D.toString() + ": " +
//		 (int) B.minValue);
//		 
//		 findPath(C);
//		 if(C.minValue == Double.POSITIVE_INFINITY)
//		 {
//			 System.out.println("NO");
//		 }
//		 else 
//			 System.out.println("Distance to " + D.toString() + ": " +
//		 (int) C.minValue);
//		 
//		 findPath(D);
//		 if(A.minValue == Double.POSITIVE_INFINITY)
//		 {
//			 System.out.println("NO");
//		 }
//		 else 
//			 System.out.println("Distance to " + D.toString() + ": " +
//		 (int) A.minValue);
		 
		 // System.out.println(Integer.MAX_VALUE);
	}
}
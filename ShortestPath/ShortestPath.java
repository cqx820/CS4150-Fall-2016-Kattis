package Path;

import java.util.*;

class Vertex implements Comparable<Vertex> {

	public int nodeTitle;

	public Vertex(int nodeNum) {
		this.nodeTitle = nodeNum;
	}

	public LinkedList<Edge> edges = new LinkedList<Edge>();
	public double minValue = Double.POSITIVE_INFINITY;
	public Vertex previous;

	@Override
	public int compareTo(Vertex arg0) {
		// TODO Auto-generated method stub
		return Double.compare(this.minValue, arg0.minValue);
	}

}

class Edge {
	public Vertex goal;
	public double weight;

	public Edge(Vertex goal, double weight) {
		this.goal = goal;
		this.weight = weight;
	}

}

public class ShortestPath {
	public static void findPath(Vertex start) {
		start.minValue = 0.0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);
		while (!vertexQueue.isEmpty()) {
			Vertex v = vertexQueue.poll();
			for (Edge e : v.edges) {
				Vertex v1 = e.goal;
				double weight = e.weight;
				double distance = v.minValue + weight;
				if (distance < v1.minValue) {
					vertexQueue.remove(v1);
					v1.minValue = distance;
					v1.previous = v;
					vertexQueue.add(v1);
				}
			}
		}
	}

	public static void resetAll(HashMap<Integer, Vertex> map) {
		for (Map.Entry<Integer, Vertex> entry : map.entrySet()) {
			map.get(entry.getKey()).minValue = Double.POSITIVE_INFINITY;
		}
	}

	public static void main(String[] args) {
		Scanner sr = new Scanner(System.in);
		while (sr.hasNextLine()) {
			String[] info = sr.nextLine().split(" ");
			if (info.length == 4 && Integer.parseInt(info[0]) == 0 && Integer.parseInt(info[1]) == 0
					&& Integer.parseInt(info[2]) == 0 && Integer.parseInt(info[3]) == 0) {
				return;
			} else {
				int nodeCount = Integer.parseInt(info[0]);
				int edgeCount = Integer.parseInt(info[1]);
				int queryCount = Integer.parseInt(info[2]);
				int startNode = Integer.parseInt(info[3]);
				Vertex start = new Vertex(startNode);
				HashMap<Integer, Vertex> vertex = new HashMap<Integer, Vertex>();
				vertex.put(startNode, start);
				// HashMap<Integer, Integer> vertices = new HashMap<Integer,
				// Integer>();
				for (int i = 0; i < nodeCount; i++) {
					if (i == startNode) {
						continue;
					}
					vertex.put(i, new Vertex(i));
				}
				for (int i = 0; i < edgeCount; i++) {
					String[] edgesArray = sr.nextLine().split(" ");
					int second = Integer.parseInt(edgesArray[1]);
					int weight = Integer.parseInt(edgesArray[2]);
					// vertices.put(second, weight);
					vertex.get(Integer.parseInt(edgesArray[0])).edges.add(new Edge(vertex.get(second), weight));
				}
				LinkedList<Integer> queryList = new LinkedList<Integer>();
				findPath(start);
				for (int i = 0; i < queryCount; i++) {
				//	while (sr.hasNextLine()) {
						queryList.add(Integer.parseInt(sr.nextLine()));
					//}
					// int query = Integer.parseInt(sr.nextLine());
					// if(vertex.get(query).minValue ==
					// Double.POSITIVE_INFINITY)
					// {
					// System.out.println("Impossible");
					// }
					// else
					// {
					// System.out.println((int)vertex.get(query).minValue);
					// }
					// resetAll(vertex);
				}
				for(int query : queryList)
				{
					if(vertex.get(query).minValue == Double.POSITIVE_INFINITY)
					{
						System.out.println("Impossible");
					}
					else
					{
						System.out.println((int)vertex.get(query).minValue);
					}
				}
				resetAll(vertex);
				System.out.print('\n');
			}
		}
		// Vertex A = new Vertex(0);
		// Vertex B = new Vertex(1);
		// Vertex C = new Vertex(2);
		// Vertex D = new Vertex(3);
		// // Vertex E = new Vertex("E");
		// // Vertex F = new Vertex("F");
		// // Vertex G = new Vertex("G");
		// A.edges.add(new Edge(B, 2));
		// B.edges.add(new Edge(C, 2));
		// D.edges.add(new Edge(A, 2));
		// // C.edges.add(new Edge(B, 10));
		// // B.edges.add(new Edge(F, 0));
		// // E.edges.add(new Edge(G, 0));
		// // F.edges.add(new Edge(G, 0));
		// // D.edges.add(new Edge(G, 0));
		// findPath(A);
		// System.out.println((int)A.minValue);
		// System.out.println((int)B.minValue);
		// System.out.println((int)C.minValue);
		// if(D.minValue == Double.POSITIVE_INFINITY)
		// {
		// System.out.println("Impossible");
		// }
		//
		// }
	}
}

package Rumor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DependencyGraph {
	public HashMap<String, HashSet<String>> dependents;
	public HashMap<String, HashSet<String>> dependee;

	DependencyGraph() {
		this.dependents = new HashMap<String, HashSet<String>>();
		this.dependee = new HashMap<String, HashSet<String>>();
	}

	public boolean hasDependents(String s) {
		HashSet<String> temp;
		if (this.dependents.containsKey(s)) {
			temp = this.dependents.get(s);
			if (temp.size() == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	public boolean hasDependee(String s) {
		HashSet<String> temp;
		if (this.dependee.containsKey(s)) {
			temp = this.dependee.get(s);
			if (temp.size() == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	public void AddDependency(String s, String t) {
		HashSet<String> temp;
		if (this.dependents.containsKey(s)) {
			temp = this.dependents.get(s);
			if (!temp.contains(t)) {
				temp.add(t);
				this.dependents.put(s, temp);
				if (!dependee.containsKey(t)) {
					temp = new HashSet<String>();
					temp.add(s);
					this.dependee.put(t, temp);
				} else {
					temp = dependee.get(t);
					temp.add(s);
					this.dependee.put(t, temp);
				}
			}
		} else {
			temp = new HashSet<String>();
			temp.add(t);
			this.dependents.put(s, temp);
			if (!this.dependee.containsKey(t)) {
				temp = new HashSet<String>();
				temp.add(s);
				this.dependee.put(t, temp);
			} else {
				temp = this.dependee.get(t);
				temp.add(s);
				this.dependee.put(t, temp);
			}
		}
	}

	public HashSet<String> getDependents(String s) {
		if (this.dependents.containsKey(s)) {
			return this.dependents.get(s);
		}
		return new HashSet<String>();
	}

	public HashSet<String> getDependee(String s) {
		if (this.dependee.containsKey(s)) {
			return this.dependee.get(s);
		}
		return new HashSet<String>();
	}

	public HashSet<String> GetChildren(String s) {
		HashSet<String> set = new HashSet<String>();
		set.addAll(getDependee(s));
		set.addAll(getDependents(s));
		return set;
	}
//
//	public ArrayList<String> getVistedHelper(String name) {
//		HashSet<String> s = new HashSet<String>();
//		s.add(name);
//		return getVisted(s, name);
//	}
//
//	public ArrayList<String> getVisted(HashSet<String> names, String myName) {
//		ArrayList<String> visited = new ArrayList<String>();
//		for (String name : names) {
//			if (!visited.contains(name)) {
//				Visit(name, name, visited);
//			}
//		}
//		visited.remove(myName);
//		return visited;
//	}
//
//	public void Visit(String start, String name, ArrayList<String> visited) {
//		visited.add(name);
//		for (String n : getDependee(name)) {
//			if (!visited.contains(n)) {
//				Visit(start, n, visited);
//			}
//		}
//		for (String n : getDependents(name)) {
//			if (!visited.contains(n)) {
//				Visit(start, n, visited);
//			}
//		}
//	}
}

public class RumorMill {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<String> childrenList = new ArrayList<String>();
		ArrayList<String> starterList = new ArrayList<String>();
		// DependencyGraph dg = new DependencyGraph();
		// dg.AddDependency("Cassandra", "Alberforth");
		// dg.AddDependency("Dan", "Bea");
		// dg.AddDependency("Art", "Dan");
		// dg.AddDependency("Bea", "Edy");
		//
		//
		// ArrayList<String> s = dg.getVistedHelper("Alberforth");
		// System.out.println(s);
		// dg.AddDependency("b", "e");
		// dg.AddDependency("b", "f");
		// dg.AddDependency("c", "g");
		// dg.AddDependency("c", "h");
		// dg.AddDependency("c", "e");
		// dg.AddDependency("d", "q");
		// dg.AddDependency("d", "w");
		// dg.AddDependency("d", "o");
		// dg.AddDependency("e", "l");
		// dg.AddDependency("e", "j");
		// for(String nimabi : dg.getDependents("Dan")){
		// System.out.println(nimabi);
		// }
		Scanner sr = new Scanner(System.in);
		DependencyGraph graph = new DependencyGraph();
		int studentNum = Integer.parseInt(sr.nextLine());
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < studentNum; i++) {
			list.add(sr.nextLine());
		}
		int friendPairs = Integer.parseInt(sr.nextLine());
		for (int i = 0; i < friendPairs; i++) {
			String[] pairs = sr.nextLine().split(" ");
			String s = pairs[0];
			String t = pairs[1];
			graph.AddDependency(s, t);
		}
	
		int starters = Integer.parseInt(sr.nextLine());
		for (int i = 0; i < starters; i++) {
		
			String starter = sr.nextLine();
			starterList.add(starter);
		}
			// forPrint.add(starter);
			// if (graph.getVistedHelper(starter).size() > 0) {
			// thisQueue.addAll(graph.getVistedHelper(starter));
			// while(!thisQueue.isEmpty()){
			// forPrint.add(thisQueue.poll());
			// }
			// list.removeAll(forPrint);
			// forPrint.addAll(list);
			// } else {
			// list.remove(starter);
			// thisQueue.addAll(list);
			// while(!thisQueue.isEmpty()){
			// forPrint.add(thisQueue.poll());
			// }
			// }
		for(String starter : starterList){
			PriorityQueue<String> thisQueue = new PriorityQueue<String>();
			ArrayList<String> forPrint = new ArrayList<String>();
			HashSet<String> copyStudent = new HashSet<String>(list);
			thisQueue.add(starter);
			while (!thisQueue.isEmpty()) {
				childrenList.clear();
				while (!thisQueue.isEmpty()) {
					String temp = thisQueue.poll();
					forPrint.add(temp);
					copyStudent.remove(temp);
					childrenList.add(temp);
				}
				for (String s : childrenList) {
					HashSet<String> set = graph.GetChildren(s);
					if (set.size() != 0) {
						for (String s1 : set) {
							if (copyStudent.contains(s1) & !thisQueue.contains(s1))
								thisQueue.add(s1);
						}

					}
				}
			}
			thisQueue.clear();
			for(String name :copyStudent){
			//	System.out.println(name);
				thisQueue.add(name);
			}
			while(!thisQueue.isEmpty()){
			forPrint.add(thisQueue.poll());	
			}
			//forPrint.addAll(thisQueue);
			for (int j = 0; j < forPrint.size(); j++) {
				if(j==forPrint.size()-1){
					System.out.print(forPrint.get(j));
				}
				else
				System.out.print(forPrint.get(j) + " ");
			}
			//list.clear();
			//list.addAll(forPrint);
			//thisQueue.clear();
			//forPrint.clear();
			System.out.print('\n');
		}
	
	}
}

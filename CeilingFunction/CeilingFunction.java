package CeilingFunc;

import java.util.*;
import java.util.Scanner;

public class CeilingFunction {
	 Node root;

	public CeilingFunction() {
		this.root = null;
	}

	public void add(int x) {
		Node newNode = new Node(x);
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (x < current.data) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	static boolean equalTrees(Node lhs, Node rhs)
	{
	    // Empty trees are equal
	    if (lhs == null && rhs == null)
	        return true;

	    // Empty tree is not equal to a non-empty one
	    if ((lhs == null && rhs != null)
	        || (lhs != null && rhs == null))
	        return false;

	    // otherwise check recursively
	    return equalTrees(lhs.left, rhs.left)
	        && equalTrees(lhs.right, rhs.right);
	}

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line;
		CeilingFunction function = new CeilingFunction();
		ArrayList<Node> list = new ArrayList<Node>();
		String[] k = sc.nextLine().split(" ");
		int q = Integer.parseInt(k[0]);
		for (int h = 0; h < q; h++) {
			line = sc.nextLine().split(" ");
			for (int i = 0; i < line.length; i++) {
				if (line[i] != "") {
					int num = Integer.parseInt(line[i]);
					function.add(num);
				}
			}
			list.add(function.root);
			function = new CeilingFunction();
			//System.out.println(h + " and q is " + q);
		}
		sc.close();
		int thisSize = list.size();
		for (int i = 0; i < thisSize - 1; i++) {
			for (int j = i + 1; j < thisSize; j++) {
				if (equalTrees(list.get(i), list.get(j))) {
					list.remove(j);
					j--;
					thisSize--;
				}
			}
		}
		System.out.println(list.size());
	}
}

package Rainbow;

import java.util.*;

public class UnderRainbow {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sr = new Scanner(System.in);
		List<Node> milesList = new ArrayList<Node>();
		List<Integer> lines = new ArrayList<Integer>();
		sr.nextLine();
		while (sr.hasNextLine()) {

			String newLine = sr.nextLine();
			if (newLine.length() == 0)
				break;
			lines.add(Integer.parseInt(newLine));
		}
		for (int i = 0; i < lines.size(); i++) {
//			printf
			milesList.add(new Node(lines.get(i)));
		}

		milesList.get(0).total = 0;
		for (int i = 0; i < milesList.size(); i++) {
			updateList(i, milesList);
		}
		System.out.println(milesList.get(milesList.size() - 1).total);

	}

	public static void updateList(int i, List<Node> list) {
		int current = i + 1;
		while (current < list.size() && (list.get(current).cost - list.get(i).cost) < 800) {
			int temp = (int) ((int) (list.get(i).total)
					+ Math.pow(Math.abs(400 - (list.get(current).cost - list.get(i).cost)), 2));
			
			//System.out.println("temp is" +temp);
			if (temp < list.get(current).total) {
				list.get(current).total = temp;
			}
			current++;
		}
	}

	static class Node {
		// double cost = Double.POSITIVE_INFINITY;
		int cost;
		int total;

		public Node(int cost) {
			this.cost = cost;
			this.total = Integer.MAX_VALUE;
		}
	}

}

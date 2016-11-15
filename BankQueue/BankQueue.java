package Queue;

import java.util.*;

public class BankQueue {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int people = Integer.parseInt(firstLine[0]);
		int minToClose = Integer.parseInt(firstLine[1]);
		// HashMap<Integer, Integer> pair = new HashMap<Integer, Integer>();
		ArrayList<Costumer> costumers = new ArrayList<Costumer>();
		ArrayList<Integer> sumList = new ArrayList<Integer>();
		// for(int i = 0; i < 10; i++)
		// System.out.println(sumList.get(i));
		int k = 0, totalTime = people + minToClose;
		while (k < people) {
			String[] nextLine = sc.nextLine().split(" ");
			int money = Integer.parseInt(nextLine[0]);
			int min = Integer.parseInt(nextLine[1]);
			totalTime += min;
			Costumer c = new Costumer(money, min);
			// timeRemain.add(Integer.parseInt(nextLine[1]));
			costumers.add(c);
			k++;
		}
		Collections.sort(costumers, new Costumer());
		// for (int i = 0; i < 4; i++)
		// System.out.println(costumers.get(i).money);
		for (int i = 0; i < totalTime; i++) {
			sumList.add(0);
		}
		// int i = 0, j = 0;
		// int maxMoney = 0;
		// while(i <= minToClose)
		// {
		// int a = timeRemain.get(j);
		// if(a == timeRemain.get(j + 1)){
		// maxMoney += Math.max(pair.get(a), pair.get(j + 1));
		// }
		// else
		// {
		// maxMoney += pair.get(a);
		// }
		// i += a;
		// j++;
		// }

		int sum = 0;
		for (int i = 0; i < costumers.size(); i++)
			queueCostumer(sumList, costumers.get(i));
		for (int i = 0; i < sumList.size(); i++)
			// int time = costumers.get(i).time;
			sum += sumList.get(i);
		System.out.println(sum);

	}

	public static void queueCostumer(ArrayList<Integer> list, Costumer c) {
		int t = c.time;
		while (t >= 0) {
			if (list.get(t) == 0) {
				// list.remove(t);
				//System.out.println(list);
				list.set(t, c.money);
				return;
			}
			t--;
		}
	}

	static class Costumer implements Comparator<Costumer> {
		int money, time;

		Costumer() {
		}

		Costumer(int money, int time) {
			this.money = money;
			this.time = time;
		}

		public String toString() {
			return money + "";
		}

		@Override
		public int compare(Costumer a, Costumer b) {
			// TODO Auto-generated method stub
			return b.money - a.money;
		}
	}
}

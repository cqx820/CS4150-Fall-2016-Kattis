package PlantingTree;
import java.util.*;
public class PlantingTrees {
	public static void main(String[] args)
	{
		Scanner sr = new Scanner(System.in);
		int number_of_seeds = sr.nextInt();
		int[] trees = new int[number_of_seeds];
		int num = 0;
		while(sr.hasNext())
		{
			trees[num++] = sr.nextInt();
		}
		Arrays.sort(trees);
		int daysUntilParty = 2;
        int daysLeft = 0;
        int i = 0;
        for(int day = number_of_seeds - 1; day >= 0; day--) {
            trees[day] -= number_of_seeds - i;
            daysUntilParty++;
            i++;
            if (trees[day] > daysLeft)
            {
                daysLeft = trees[day];
            }
        }
        daysUntilParty += daysLeft;
        System.out.println(daysUntilParty);
		
	}
}

package Theory;

import java.util.*;

public class NumTheory {

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static int exp(int a, int b, int n) {
		long x = 1;
		long y = a;
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x * y) % n;
			}
			y = (y * y) % n;
			b /= 2;
		}
		return ((int) x % n);
	}

	public static String isPrime(int num) {
		if (num < 2) {
			return "no";
		}
		if (num == 2) {
			return "yes";
		}
		if (num % 2 == 0) {
			return "no";
		}
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return "no";
			}
		}
		return "yes";
	}

	public static String modInverse(long a, long b) {
		long i = b, v = 0, d = 1;
		if (gcd(a, b) != 1) {
			return "none";
		}
		while (a > 0) {
			long t = i / a, x = a;
			a = i % x;
			i = x;
			x = d;
			d = v - t * x;
			v = x;
		}
		v %= b;
		if (v < 0) {
			v = (v + b) % b;
		}
		return v + "";
	}
	
	public static String rsa(long a, long b)
	{
		long N = a * b;
		long Euler = (a - 1) * (b - 1);
		long e = 0;
		for(int i = 2; i <= Euler; i++)
		{
			if(gcd(i, Euler) == 1)
			{
				e = i;
				break;
			}
		}
		long d = Long.parseLong(modInverse(e, Euler));
		return N + " " + e + " " + d;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// System.out.println(modInverse(6, 9));
		while (sc.hasNextLine()) {
			String[] newLine = sc.nextLine().split(" ");
			String command = newLine[0];
			if (command.equals("gcd")) {
				int a = Integer.parseInt(newLine[1]);
				int b = Integer.parseInt(newLine[2]);
				System.out.println(gcd(a, b));
			} else if (command.equals("exp")) {
				int a = Integer.parseInt(newLine[1]);
				int b = Integer.parseInt(newLine[2]);
				int c = Integer.parseInt(newLine[3]);
				System.out.println(exp(a, b, c));
			} else if (command.equals("isprime")) {
				int a = Integer.parseInt(newLine[1]);
				System.out.println(isPrime(a));
			}
			else if(command.equals("inverse"))
			{
				int a = Integer.parseInt(newLine[1]);
				int b = Integer.parseInt(newLine[2]);
				System.out.println(modInverse(a, b));
			}
			else
			{
				int a = Integer.parseInt(newLine[1]);
				int b = Integer.parseInt(newLine[2]);
				System.out.println(rsa(a, b));
			}
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalaxyQuest
{
    class Program
    {
        static void Main(string[] args)
        {
            string s = Console.ReadLine();
            string[] dimension = s.Split(' ');
            int d = Int32.Parse(dimension[0]);
            int n = Int32.Parse(dimension[1]);
            Coordinate[] arr = new Coordinate[n];
            for(int i = 0; i < n; i++)
            {
                string coord = Console.ReadLine();
                string[] leftRight = coord.Split(' ');
                int left = Int32.Parse(leftRight[0]);
                int right = Int32.Parse(leftRight[1]);
                arr[i] = new Coordinate(left, right);
            }
            Coordinate majority = findMajority(arr, d);
            if (majority == null)
            {
                Console.WriteLine("NO");
                Console.Read();
                return;
            }
            int count = 0;
            for (int h = 0; h < n; h++)
            {
                double diff = Math.Sqrt(Math.Pow((arr[h].x - majority.x), 2) + Math.Pow((arr[h].y - majority.y), 2));
                if (diff < d)
                {
                    count++;
                }
            }
            Console.WriteLine(count);
            Console.Read();
            return;
        }

        //ublic static Coordinate findMajority(Coordinate[] arr, int startIndex, int endIndex, int d)
        public static Coordinate findMajority(Coordinate[] arr, int d)
        {
            if (arr.Length == 1) { return arr[0]; }
            else
            {
                int mid = arr.Length / 2;
                Coordinate[] A1 = new Coordinate[mid];
                Coordinate[] A2 = new Coordinate[arr.Length - mid];
                Array.Copy(arr, 0, A1, 0, mid);
                Array.Copy(arr, mid, A2, 0, A2.Length);
                Coordinate x = findMajority(A1, d);
                Coordinate y = findMajority(A2, d);
                if (x == null && y == null) { return null; }
                else if (x == null)
                {
                    int count = countOccurrences(arr, y, d);
                    if (count > arr.Length / 2)
                    {
                        return y;
                    }
                    return null;
                }
                else if (y == null)
                {
                    int count = countOccurrences(arr, x, d);
                    if (count > arr.Length / 2)
                    {
                        return x;
                    }
                    return null;
                }
                else
                {
                    int countX = countOccurrences(arr, x, d);
                    int countY = countOccurrences(arr, y, d);
                    if (countX > arr.Length / 2)
                    {
                        return x;
                    }
                    else if (countY > arr.Length / 2)
                    {
                        return y;
                    }
                    else { return null; }
                }
            }
        }

        public static int countOccurrences(Coordinate[] arr, Coordinate target, int d)
        {
            int count = 0;
            for (int i = 0; i < arr.Length; i++)
            {
                double diff = Math.Sqrt(Math.Pow((arr[i].x - target.x), 2) + Math.Pow((arr[i].y - target.y), 2));
                if (diff < d)
                {
                    count++;
                }
            }
            return count;
        }

        public class Coordinate
        {
            public int x;
            public int y;
            public Coordinate(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }
    }
}

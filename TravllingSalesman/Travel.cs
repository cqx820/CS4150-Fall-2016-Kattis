﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TSP
{
    class Program
    {
        static List<int> output_list = new List<int>();
        static int[,] g;
        static int[,] p;
        static int npow;
        static int[,] d;
        static int N;


        static List<int> TSP(int[,] matrix, int n)
        {
            N = n;
            npow = (int)Math.Pow(2, n);
            g = new int[n, npow];
            p = new int[n, npow];
            d = matrix;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < npow; j++)
                {
                    g[i, j] = -1;
                    p[i, j] = -1;

                }
            }

            for (int i = 0; i < n; i++)
            {
                g[i, 0] = matrix[i, 0];
            }


            int temp = tsp(0, npow - 2);
            output_list.Add(0);
            output_list.Add(temp);
            return output_list;
        }

        static int tsp(int i, int a)
        {
            int result = -1;
            int masked, mask, temp;
            if (g[i, a] != -1)
            {
                return g[i, a];
            }
            else
            {
                for (int j = 0; j < N; j++)
                {
                    mask = npow - (int)Math.Pow(2, j) - 1;
                    masked = a & mask;
                    if (masked != a)
                    {
                        temp = d[i, j] + tsp(j, masked);
                        if (result == -1 || result > temp)
                        {
                            result = temp;
                            p[i, a] = j;
                        }
                    }

                }
                g[i, a] = result;
                return result;
            }

            //return 0;
        }


        static void Main(string[] args)
        {

            int nodes_num = Int32.Parse(Console.ReadLine());

            int[,] matrix = new int[nodes_num, nodes_num];
            for (int i = 0; i < nodes_num; i++)
            {
                String[] s = Console.ReadLine().Split(' ');
                for (int j = 0; j < nodes_num; j++)
                {
                    matrix[i, j] = Int32.Parse(s[j]);
                }
            }

            for (int i = 0; i < nodes_num; i++)
            {
                for (int j = 0; j < nodes_num; j++)
                {
                    if (matrix[i, j] == 1 && matrix[j, i] == 0)
                    {
                        matrix[j, i] = 1;
                    }
                }
            }

            TSP(matrix, nodes_num);
            int result = output_list[output_list.Count - 1];
            Console.WriteLine(result);
            Console.Read();




        }




    }
}

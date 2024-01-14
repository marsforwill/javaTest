import java.util.*;

public class Graph
{
    // https://www.hackerrank.com/challenges/components-in-graph/problem
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);	
		int n = scan.nextInt();
		UF uf = new UF(n*2);
		for(int i = 0; i < n; i++)
		{
			int a = scan.nextInt()-1;
			int b = scan.nextInt()-1;
			uf.union(a, b);
		}
		
		int min = 999999;
		int max = -1;
		
		for(int i = 0; i < n*2; i++)
		{
			if(uf.sizes[i] > max)
			{
				max = uf.sizes[i];
			}
			if(uf.sizes[i] < min && uf.counts[i] == 1 && uf.sizes[i] > 0)
			{
				min = uf.sizes[i];
			}
		}
		System.out.println(min + " " + max);
	}
}

// union find set: new UF(n*2); findSet;union
class UF
{
	static int count;
	static int[] arr;
	static int[] sizes;
	static int[] counts;
	
	UF(int n)
	{
		count = n;
		arr = new int[n];
		sizes = new int[n];
		counts = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			arr[i] = i;
			sizes[i] = 1;
			counts[i] = 0;
		}
	}
	
	static int findSet(int i)
	{
		if(arr[i] == i)
		{
			return i;
		}
		else
		{
			return findSet(arr[i]);
		}
	}
	
	static boolean isSameSet(int i, int j)
	{
		return findSet(i) == findSet(j);
	}
	
	void union(int i, int j)
	{
		if(isSameSet(i,j))
			return;
		
		counts[i] = 1;
		counts[j] = 1;
		
		int a = findSet(i);
		int b = findSet(j);
		
		arr[a] = b;
		sizes[b] = sizes[b] + sizes[a];
		sizes[a] = 0;
	}
}

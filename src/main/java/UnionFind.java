import java.util.*;

public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int x, int y){
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty){
            if (rank[rootx] > rank[rooty]){
                parent[rooty] = rootx;
                rank[rootx] += rank[rooty];
            } else {
                parent[rootx] = rooty;
                rank[rooty] += rank[rootx];
            }
        }
    }

    public int find(int x){
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(3, 4);
        System.out.println(uf.find(3));
        System.out.println(uf.find(4));
    }
}

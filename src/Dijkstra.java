/*******************************************************
 * Copyright (C) 2020/11/29 iQIYI.COM - All Rights Reserved
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * Author(s):
 * liushimin <liushimin@qiyi.com>
 *******************************************************/

import java.util.*;

/*******************************************************
 *
 * @Description
 * @author liushimin
 * @Modify
 * @Date 2020/11/29
 *******************************************************/
//单源最短路 可环无负边 format dij code
public class Dijkstra {

    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
        // init graph 邻接表 node -> {List{node1,value1   node2,value2}}
        //map< Integer,List< int[]>>来存放源到目的节点及权值，而朴素dijkstra直接用数组就可以了
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
        }
        // init dist from source node K
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (info1,info2) -> info1[0] - info2[0]
        );
        // {dis, node}
        heap.offer(new int[]{0,K});
        while (!heap.isEmpty()) {
            // heap 存未处理的node 同时保证和source node的value dis 最小
            int[] info = heap.poll();
            int d = info[0];
            int node = info[1];
            // already deal
            if (dist[node] != Integer.MAX_VALUE) {
                continue;
            }
            dist[node] = d;
            if (graph.containsKey(node)) {
                for (int[] edg : graph.get(node)) {
                    int nei = edg[0];
                    int d2 = edg[1];
                    if (dist[nei] == Integer.MAX_VALUE) {
                        heap.offer(new int[]{d+d2, nei});
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= N ; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans,dist[i]);
        }
        return ans;

    }


    //787. K 站中转内最便宜的航班
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            graph[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (info1,info2) -> info1[0] - info2[0]
        );
        Map<Integer,Integer> best = new HashMap<>(); // node+k*1000, cost
        // 起始节点放入
        pq.offer(new int[]{0,0,src});// cost k node
        while (!pq.isEmpty()){
            int[] info = pq.poll();
            int cost = info[0], k = info[1], node = info[2];
            if (k>K+1 || cost > best.getOrDefault(k*1000+node,Integer.MAX_VALUE)) {
                continue;
            }
            if (node == dst) {
                return cost;
            }
            // 遍历当前节点的下一步的邻居
            for (int nei = 0; nei < n; ++nei) if (graph[node][nei] > 0) {
                int newcost = cost + graph[node][nei];
                if (newcost < best.getOrDefault(nei+(k+1)*1000,Integer.MAX_VALUE)) {
                    pq.offer(new int[]{newcost, k+1, nei});
                    best.put(nei+(k+1)*1000,newcost);
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        //3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        //src = 0, dst = 2, k = 1
        System.out.println(findCheapestPrice(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1));
//        System.out.println(networkDelayTime(new int[][]{{2,1,13},{2,3,1},{3,4,1},{4,1,1}},4,2));
    }
}

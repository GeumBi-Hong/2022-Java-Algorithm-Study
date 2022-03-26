package 스터디.Week_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    private int index;
    private int cost;

    public int getIndex() {
        return index;
    }

    public int getCost() {
        return cost;
    }

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        if(this.cost > other.cost){
            return -1;
        }
        return 1;
    }
}

public class 합승택시요금 {
    static ArrayList<ArrayList<Node>> graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int maxVal = Integer.MAX_VALUE;


        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] fare : fares){
            graph.get(fare[0]).add(new Node(fare[1],fare[2]));
            graph.get(fare[1]).add(new Node(fare[0],fare[2]));
        }
        int[] starta = new int[n+1];
        int[] startb = new int[n+1];
        int[] starts = new int[n+1];

        Arrays.fill(starta,maxVal);
        Arrays.fill(startb,maxVal);
        Arrays.fill(starts,maxVal);

        starta = dijkstra(a,starta);
        startb = dijkstra(b,startb);
        starts = dijkstra(s,starts);

        for(int i=1; i<=n; i++)
            answer = Math.min(answer,starta[i]+startb[i]+starts[i]);

        return answer;
    }

    static int[] dijkstra(int start,int[] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        costs[start] = 0;

        while (!pq.isEmpty()){
            Node now = pq.poll();
            int nowIndex = now.getIndex();
            int nowCost = now.getCost();

            if(nowCost > costs[nowIndex]) continue;

            ArrayList<Node> nodes = graph.get(nowIndex);
            for(Node node : nodes){
                int cost = costs[nowIndex] + node.getCost();

                if(cost < costs[node.getIndex()]) {
                    costs[node.getIndex()] = cost;
                    pq.offer(new Node(node.getIndex(),cost));
                }
            }

        }
        return costs;
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    static int N, M, A, B, money;
    static List<Node>lists[];
    static int [] distance;


    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());


        lists = new ArrayList[N + 1];
        distance = new int[N+1];

        for (int i = 1; i <= N; i++) lists[i] = new ArrayList<>();



        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int  start = Integer.parseInt(st.nextToken());
            int  end = Integer.parseInt(st.nextToken());
            int  cost = Integer.parseInt(st.nextToken());
            lists[start].add(new Node(end, cost));
            lists[end].add(new Node(start, cost));


        }

        for(int i =1 ; i <=20;i++){

            if(dijkstra(i)){
                System.out.println(i);
                return;
            }
        }
        System.out.print(-1);
    }


    public static boolean dijkstra(int maxCost){
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[A] = 0; //출발은 항상 0

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(A,0));

        while (!queue.isEmpty()){
            Node  currNode =  queue.poll();

            if(distance[currNode.idx] < currNode.cost)continue;


            for(Node next  : lists[currNode.idx]){
                if (next.cost > maxCost) continue;
                if(distance[next.idx]>distance[currNode.idx]+next.cost){
                    distance[next.idx] =distance[currNode.idx]+next.cost;
                    queue.add(new Node(next.idx, distance[next.idx]));
                }

            }


        }

     return distance[B]<=money;
    }

}

import java.util.*;
import java.io.*;

class Solution {
    
    static ArrayList<Node>[] arrayLists;
    static int [] dist;
    static int [] distA;
    static int [] distB;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int INF=20000001;
      
        dist = new int [n+1];
        distA = new int [n+1];
        distB = new int [n+1];
        //배열 초기화
        Arrays.fill(dist,INF);
        Arrays.fill(distA,INF);
        Arrays.fill(distB,INF);
        arrayLists = new ArrayList[n+1];
        
        for(int i=0; i<=n;i++){
            arrayLists[i] = new ArrayList<Node>();
        }
        
        
        for( int []f:fares){
            int n1=f[0];
            int n2=f[1];
            int c=f[2];
                
            arrayLists[n1].add(new Node(n2,c));
            arrayLists[n2].add(new Node(n1,c));
            
        }
        dijstra(s,dist);
        dijstra(a,distA);
        dijstra(b,distB);
        
        
        //최소 합승 택시 요금 찾기 
        for(int i =1; i<=n;i++){
          answer =Math.min(answer,dist[i]+distA[i]+distB[i]);
        }
        
        
        return answer;
    }
    
    static void dijstra(int start,int []d){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
      //출발지는 0
        d[start]=0;
        queue.add(new Node(start,0));
       
        
        while(!queue.isEmpty()){
            Node n = queue.poll();
            int currIdx = n.idx;
            int currCost = n.cost;
            
            if(d[currIdx] < currCost){
                continue;
            }
            
            for(int i = 0 ;  i<arrayLists[currIdx].size();i++){
                Node n2 = arrayLists[currIdx].get(i);
                int nextIdx = n2.idx;
                int nextCost = n2.cost;
                
                if(d[nextIdx] > currCost+nextCost){
                    d[nextIdx] = currCost +nextCost;
                    queue.add(new Node(nextIdx,d[nextIdx]));
                }
            }
        }
    }
    
    
  static  class Node implements Comparable<Node>{
        int idx; 
        int cost;
        
        public Node(int idx ,int cost){
            this.idx=idx;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Node o){
            return cost - o.cost;
        }
    }
}

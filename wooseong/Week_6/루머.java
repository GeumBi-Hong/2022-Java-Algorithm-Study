package 스터디.Week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 루머 {
    static class Info {
        ArrayList<Integer> connect;
        int connectCnt;
        int indegree;
        int time;
        boolean visited;

        public Info() {
            connect = new ArrayList<>();
            connectCnt = 0;
            indegree = 0;
            time =-1;
            visited = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Info[] graph = new Info[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Info();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = 0;
            while (true) {
                int x = Integer.parseInt(st.nextToken())-1;
                if (x == 0) {break;}
                graph[i].connect.add(x);
                cnt += 1;
            }
            if (cnt % 2 == 0) {graph[i].indegree = cnt / 2;} else {graph[i].indegree = cnt / 2 + 1;}
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Queue<Info> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            q.offer(graph[x]);
            graph[x].connectCnt += 1;
            graph[x].visited = true;
            graph[x].time+=1;
        }

        while (!q.isEmpty()) {
            Info now = q.poll();
            for (int next : now.connect) {
                if (graph[next].visited) {continue;}
                graph[next].connectCnt += 1;
                if (graph[next].connectCnt >= graph[next].indegree) {
                    graph[next].visited = true;
                    graph[next].time = now.time+1;
                    q.offer(graph[next]);
                }
            }
        }

        for(int i=0 ; i<n; i++){
            System.out.print(graph[i].time+" ");
        }

    }
}



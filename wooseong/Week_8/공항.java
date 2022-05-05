package 스터디.Week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공항 {
    /*
    1. 게이트 n+1 많큼 배열을 만든다
    2. gi에 배치, 있으면 왼쪽으로 한칸씩, 유니온파인드사용
     */
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[g+1];
        for(int i=1; i<=g; i++){
            parent[i] = i;
        }
        int cnt=0;
        for(int i=0; i<p; i++){
            int airPlane = Integer.parseInt(br.readLine());
            int nowGate = find(airPlane);
            if(nowGate == 0){
                break;
            }
            cnt++;
            union(nowGate, nowGate-1);
        }
        System.out.println(cnt);
    }

    public static int find(int x){
        if(x!=parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}

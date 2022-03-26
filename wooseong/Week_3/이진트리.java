package 스터디.Week_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13325 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int depth = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = (int) (Math.pow(2, depth+1) -1);
        int[] node =new int[n];

        node[0] = 0;
        int answer =0;

        for(int i=1; i<n; i++){
            node[i] = Integer.parseInt(st.nextToken());
            answer+=node[i];
        }
        for(int i=depth; i>0; i--){
            int dn = (int) Math.pow(2,i);
            for(int j=0; j<dn/2; j++){
                int left =dn-1+2*j;
                int right = dn+2*j;

                int parent = right/2 -1;
                node[parent] += Math.max(node[left],node[right]);
                answer+= Math.abs(node[left]-node[right]);

            }
        }

        System.out.println(answer);

    }
}


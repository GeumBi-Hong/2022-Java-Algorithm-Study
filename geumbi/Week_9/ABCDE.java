import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> []list ;

    static boolean []isVisited;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new List[N];

        for(int i = 0; i<N;i++){
            list[i] = new ArrayList<>();
        }
         isVisited= new boolean[N];

        while (M-->0){
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list[start].add(end);
            list[end].add(start);


        }



       
        for(int i = 0 ; i <N;i++){
            isVisited = new boolean[N];
            dfs(i,1);

            if(flag){//abcde관계 존재하면
                System.out.println(1);
                return;

            }
        }
        System.out.println(0);

    }

    private static void  dfs (int start,int depth){
        if(depth==5){
            flag=true;
            return;
        }

        isVisited[start]=true;
        for(int next: list[start]){
            if(isVisited[next])continue;
            dfs(next,depth+1);

        }
        isVisited[start]=false;
    }
}

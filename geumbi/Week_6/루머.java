import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static ArrayList<Integer>[] map ;
    static int [] answer ;
    static boolean [] visited;
    static int N ;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map =  new ArrayList[N+1];

        for(int i =1 ;  i <=N;i++){
            map[i] = new ArrayList<>();
        }

        answer =  new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(answer,-1);


        for(int start =1 ; start<=N;start++){
           st = new StringTokenizer(br.readLine()," ");

            while (st.hasMoreTokens()){
                int end = Integer.parseInt(st.nextToken());
                if(end==0)continue;
                map[start].add(end);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

         for(int i = 0; i<M;i++){
             int  n = Integer.parseInt(st.nextToken());
             answer[n]=0; //최초 유포자는 0분
             queue.add(n);
         }


        bfs();

         for(int i=1; i<=N;i++){
             sb.append(answer[i]+" ");
         }
        System.out.print(sb);
    }

    public static void bfs(){
         //주변인의 수의 절반을 계산한 값을 저장
        int []half = new int[N+1];


        for(int i = 1 ; i<=N;i++){
            int n = map[i].size()/2;
            if(map[i].size()%2!=0){ //나머지가 있으면 +1
                half[i]=n+1;
            }else {
                half[i]=n;
            }

        }

        while (!queue.isEmpty()){

         int curr=  queue.poll(); // 루머에 걸린 사람

        //현재 사람에서 루머를 퍼트릴 수 있는 주변인을 있는지 찾는다.
         for(int next : map[curr]){

             half[next]-=1; // 루머를 믿는 사람이 있으니(curr) 이 사람의 주변인의 주변인 수 절반에서 -1 를 해준다.

             //루머를 믿지 않는 사람이고 , 주변인 절반이 루머를 믿고 있는 경우(0이하가 되었다는 뜻은 절반이상이 루머를 믿는다는의미)
             if(answer[next]==-1&&half[next]<=0){ 

                 queue.add(next);
                 answer[next]=answer[curr]+1;
             }

               }
           }

        }

    }




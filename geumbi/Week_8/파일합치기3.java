import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        while (T-->0){
            //합을 구할때 int 범위를 넘어갈 수 있으므로 long
            PriorityQueue<Long> priorityQueue  = new PriorityQueue<Long>();

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()){
                priorityQueue.add(Long.parseLong(st.nextToken()));

            }

            Long sum =(long) 0;

            //2개를 꺼낼꺼니까 사이즈가 2이상일때만 꺼냄
            while (priorityQueue.size()>1){
                Long n1 = priorityQueue.poll();
                Long n2 = priorityQueue.poll();

                sum+=n1+n2;
                priorityQueue.add(n1+n2);
            }

            sb.append(sum).append("\n");

        }
        System.out.print(sb);

    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] tree;
    static int k,answer;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        int n = (int)Math.pow(2,k+1)-1;
        tree = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i =2; i<=n;i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,1);

        System.out.println(answer);
    }
    public static int dfs(int depth ,int index){
        if(k==depth){ 
            answer+=tree[index];
            return tree[index];
        }


      int left=  dfs(depth+1,index*2);
      int right = dfs(depth+1,index*2+1);


      answer += tree[index] + Math.abs(left -right);
      return tree[index]+Math.max(left,right); 

    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long answer = 0 ;
        int [] array = new int[N];

        for (int i = 0 ; i<N;i++){
            array[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(array);

        for (int i = 0 ; i<N ; i++){
            answer = answer + Math.abs(array[i]-i-1);
        }

        System.out.print(answer);


//이사하네
    }
}

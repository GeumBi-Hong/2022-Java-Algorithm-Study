import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String Ipv6 = br.readLine();
        String []str = Ipv6.split("::",2);

        if(str.length==1){
            StringTokenizer st = new StringTokenizer(str[0], ":");
            int count = st.countTokens();
            for(int i = 0 ; i <count;i++){
                String s = st.nextToken();

                for(int j = 0; j<4-s.length();j++){
                    sb.append(0);
                }
                sb.append(s+":");
            }
            String answer = sb.toString();
            System.out.println(answer.substring(0,answer.length()-1));

        }else {
            StringTokenizer front = new StringTokenizer(str[0],":");
            StringTokenizer end = new StringTokenizer(str[1],":");

            int countFront = front.countTokens();
            int countEnd = end.countTokens();
            int dif = (8-(countFront+countEnd));


            for(int i = 0 ; i <countFront;i++){
                String s = front.nextToken();

                for(int j = 0; j<4-s.length();j++){
                    sb.append(0);
                }
                sb.append(s+":");
            }

            if(dif!=0){
                for(int j = 0; j<dif;j++){
                    sb.append("0000:");
                }
            }

            for(int i = 0; i <countEnd;i++){
                String s = end.nextToken();

                for(int j = 0; j<4-s.length();j++){
                    sb.append(0);
                }
                sb.append(s+":");
            }

            String answer = sb.toString();
            System.out.println(answer.substring(0,answer.length()-1));
        }



    }
}

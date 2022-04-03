package 스터디.Week_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;

public class IPv6 {
    // 문자열처리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String beforeIp = br.readLine();
        beforeIp = beforeIp.replace("::",":group:");
        String[] ips = beforeIp.split(":",-1);

        String answer = "";
        System.out.println(Arrays.toString(ips));
        for(int i=0; i<ips.length; i++){
            if( ips[i].length()==5 ){
                for( int j=0; j<=8-ips.length; j++)
                    answer += "0000:";
            } else {
                while (ips[i].length() < 4 ){
                    ips[i] = "0" + ips[i];
                }
                answer += ips[i] + ":";
            }
        }
        System.out.println(answer.substring(0,answer.length()-1));

    }

    // ip 라이브러리 사용
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String s = in.readLine().trim();
        InetAddress ipv6 = java.net.Inet6Address.getByName(s);
        for (String token : ipv6.getCanonicalHostName().split(":")) {
            out.append(String.format("%4s", token).replace(' ', '0')).append(':');
        }
        System.out.println(out.substring(0, out.length() - 1));
    }
}

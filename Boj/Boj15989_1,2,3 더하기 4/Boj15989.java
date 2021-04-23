package boj;
import java.util.*;
import java.io.*;

public class Boj15989 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
			int[][] dp = new int[10002][3];
			
			dp[1][0] = 1;
			dp[2][0] = 1;
			dp[2][1] = 1;
			dp[3][0] = 1;
			dp[3][1] = 1;
			dp[3][2] = 1;
			
			for(int i=4; i<=10000; i++) {
				dp[i][0] = 1;
				dp[i][1] = dp[i-2][1] + 1;
				dp[i][2] = dp[i-3][2] + dp[i-3][1] + 1;
			}
			
			int tc = stoi(br.readLine());
			while(--tc >= 0) {
				int n = stoi(br.readLine());
				bw.write(dp[n][0]+dp[n][1]+dp[n][2]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

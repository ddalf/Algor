package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj15989_2 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
			int[] dp = new int[10005];
			dp[0] =1;
			for(int i=1; i<=3; i++) {
				for(int j=i; j<=10000; j++) {
					dp[j] += dp[j-i];
				}
			}
			int tc = stoi(br.readLine());
			while(--tc >= 0) {
				int n = stoi(br.readLine());
				bw.write(dp[n]+"\n");
			}
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

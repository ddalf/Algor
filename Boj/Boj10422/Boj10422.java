package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj10422 {
	public static long[] dp;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			dp = new long[5005];
			int testCase = stoi(br.readLine());
			dp[0] = 1;
			dp[2] = 1;
			for(int i=4; i<=5000; i+=2) {
				for(int j=2; j<=i; j+=2) {
					if(i-j >= 0) {
						dp[i] += dp[j-2] * dp[i-j];
						dp[i] %= 1000000007;
					}
				}
			}
			for(int i=0; i<testCase; i++) {
				int n = stoi(br.readLine());
				if(n%2 == 0 ) bw.write(dp[n]+"\n");
				else bw.write("0\n");
			}
			bw.flush();	bw.close();	br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
/*
	문제정리
	* 괄호 문자열 : (랑 )로만 이루어진 문자열 
	* 열리고 닫힌 쌍 제대로 이루어지만 올바른 괄호 문자열. 1,000,000,007로 나눈 나머지
	
	결과
	길이 L인 올바른 괄호 문자열 개수
	올바른 괄호 문자열의 개수 출력
	
*/
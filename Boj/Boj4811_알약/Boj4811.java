package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj4811 {
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			long [][] dp = new long[31][63];
			
			for(int h=1; h<=60; h++) dp[0][h] = 1;
			dp[1][0] = 1;
			for(int w=1; w<=30; w++) {
				for(int h=0; h<=60; h++) {
					dp[w][h] = dp[w-1][h+1] + (h > 0 ? dp[w][h-1] : 0);
				}
			}
			
			while(true) {
				int n = stoi(br.readLine());
				if(n == 0) break;
				bw.write(dp[n][0]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

/*
문제정리
* 약 N개 담긴 병
* 첫날 -> 병에서 약 하나 꺼냄. 
* 다음 날 
 	-> 약 전체 : 약 반으로 쪽개서 한 조각 먹음 / 다른 조각 병에 넣음. W 보냄
	-> 반 조각 : 그 약 먹음.H 보냄
* 2N일 지남 -> 길이 2N인 문자열. 가능한 서로 다른 문자열의 개수

[입력]
* 테스트 케이스 : 1000개
* N : 약의 개수. ~ 30

[출력]
* 가능한 문자열의 개수

문제풀이
* DP로 품. 
* dp[i][j] : 한조각 i개 반 조각 j개 가질 때 가능한 서로 다른 문자열 개수 
* dp[i][j] = dp[i-1][h+1] + dp[w][h-1]
EX. ●●△  = ●● 문자열 개수 + ●△△  문자열 개수 (● - 한 조각 / △ - 반 조각)  
*/
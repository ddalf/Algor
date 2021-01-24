package boj;
import java.util.*;
import java.io.*;
public class Boj2698 {
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int t = stoi(br.readLine());
			int dp[][][] = new int[101][100][2];
			
			dp[1][0][0] = 1;
			dp[1][0][1] = 1;
			
			for(int i=2; i<=100; i++) {//수열 크기
				for(int j=0; j<i; j++) {//인접비트 개수
					dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];					
					dp[i][j][1] = (j-1) >= 0 ? dp[i-1][j][0]+dp[i-1][j-1][1] : dp[i-1][j][0];
				}
			}
			
			for(int i=0; i<t; i++) {
				st = new StringTokenizer(br.readLine());
				int n = stoi(st.nextToken());
				int k = stoi(st.nextToken());
				bw.write((dp[n][k][0] + dp[n][k][1])+"\n");
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
	[문제정리]
	* 수열 S : 0과 1로 이루어진 수열
	* s1 : S의 첫 수
	* sn : S의 마지막 수
	* 인접한 비트의 개수 : s1 * s2 + s2 * s3 + .. + sn-1 + sn
	* 수열 S의 크기, n과 k 주어졌을 때 인접한 비트의 개수가 k개인 수열 S의 개수 구하기
	* ex. n=5, k=2 -> 11100, 01110, 00111, 10111, 11101, 11011
	
	입력
	* T : 테스트 ㅔ이스 수
	* n, k : 1~100.
	
	출력
	* 각 테스트 케이스에 대해 인접한 비트의 개수가 k인 수열 S의 개수
	* 2,147,483,647보다 작거나 같음

	[문제풀이]
	dp[i][j][k] : 수열의 크기가 i일 때 인접비트 값이 j이면서 끝자리 값이 k인 개수
	i : 1~n
	j : 0~i-1
	k : 0,1
*/
package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj5557 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static boolean checkRange(int x) {
		return x >= 0 && x <= 20;
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			int[] arr = new int[n];
			int[][] dp = new int[n][21];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = stoi(st.nextToken());
			}
			
			dp[0][arr[0]] = 1;
			for(int i=1; i<n-1; i++) {
				for(int j=0; j<=20; j++) {
					if(dp[i-1][j] > 0) {//이전에 0~20 중 가능한 경우의 수가 있는 것
						int plusValue = j + arr[i];
						int minusValue = j - arr[i];
						if(checkRange(plusValue)) dp[i][plusValue] += dp[i-1][j];
						if(checkRange(minusValue)) dp[i][minusValue] += dp[i-1][j];
					}			
				}
			}
			bw.write(dp[n-2][arr[n-1]]+"");
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
	* 줄지은 숫자.
	* 마지막 두 숫자 사이 "=", 나머지 숫자 사이 "+" or "-"넣음
	* 올바른 등식 만들고자 함.
	* 음수 나오면 안됨.
	* 2중간 나오는 수 : 0~20 이여야 함.
	
	입력
	* N : 숫자의 개수. 3~100. 
	
	출력
	* 가능한 올바른 등식의 개수. 2^63-1 이하(=int 범위 내)
	
	[문제풀이]
	dp[i][j] : i 번째 순서 일 때 j일 수 있는 경우의 수
	dp[i][j] = dp[i-1][j-arr[i]]
	
*/
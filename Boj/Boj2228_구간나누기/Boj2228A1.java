package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2228A1 {
	static final int MINV = -2000000000;
	static int n;
	static int m;
	static int[] arr;
	static int[] sum;
	static int[][] dp;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	//DP[i][j] = i번째 수부터 j개의 구간으로 나눌 때 최댓값
	public static int go(int idx, int section) {
		if(section == 0) return 0;
		if(idx > n || section < 0) return MINV;
		int ret = dp[idx][section];
		if(ret == MINV) {
			ret = MINV;
			//1. i번째 값 선택하지 않을 경우
			ret = go(idx+1, section);
			//2. idx번째 ~ idx+i번째 까지 구간 선택할 경우
			//	= 	idx번째 ~ idx+i번째 구간 값 			: sum[idx+i]-sum[idx-1]
			//		+ (idx+i+2)번째 부터 구간 나눌 때 최댓값	: go(idx+2+i, section-1)
			for(int i=0; idx+i<=n; i++) {
				ret = Math.max(ret, go(idx+2+i, section-1)+sum[idx+i]-sum[idx-1]);
			}
			dp[idx][section] = ret;
		}
		return ret;
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			arr = new int[n+1];
			sum = new int[n+1];
			dp = new int[n+1][m+1];
	
			for(int i=1; i<=n; i++) {
				arr[i] = stoi(br.readLine());
				sum[i] = sum[i-1] + arr[i];
			}
			
			for(int i=0; i<=n; i++) {
				Arrays.fill(dp[i], MINV);
			}
			
			bw.write(go(1, m)+"");

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
N개의 수로 이루어진 1차원 배열
M개 구간 선택 -> 구간에 속한 수들의 합 최대가 되도록
조건
	각 구간 한 개 이상의 연속된 수
	서로 다른 두 구간 겹침 or 인접 X
	정확히 M개의 구간. 미만이여서는 X

입력
N : 1~100
M : 1~N/2
배열 이루는 값 : -32768 ~ 32768

출력
구간에 속한 수들의 총 합의 최댓값

문제풀이
DP[i][j] = i번째 수부터 j개의 구간으로 나눌 때 최댓값

*/

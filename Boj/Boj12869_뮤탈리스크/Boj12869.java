package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj12869 {
	private static int[][] attack = {{9,3,1}, {9,1,3}, {3,1,9}, {3,9,1}, {1,3,9}, {1,9,3}};
	private static int[] hp;
	private static int[][][] dp = new int[61][61][61];
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static int getMinAttack(int a, int b, int c) {
		//1. 0보다 작아질 경우 0으로 취급
		if(a < 0) a = 0;
		if(b < 0) b = 0;
		if(c < 0) c = 0;
		
		//2. dp배열에 현재 구하고자 하는 값이 있을 경우(-1 아닌 경우) 해당 값으로 return
		if(dp[a][b][c] != -1) return dp[a][b][c];
		
		//3. dp배열에 현재 구하고자 하는 값이 없는 경우
		//3-1. 가능한 조합에서 한번에 모두 0이하 되는 경우 재귀 종료. 1 반환해 줌
		for(int i=0; i<6; i++) {
			if(a-attack[i][0] <= 0 && b-attack[i][1] <= 0 && c-attack[i][2] <= 0) return 1;
		}
		
		//3-2. 한번에 모두 0 되지 않는 경우 -> 가능한 조합에서 최솟값 구함
		int ans = 7 * 7 * 7; // 최대 경우의 수. 60 / 9 = 6.xxxx -> 7 ^ 3
		for(int i=0; i<6; i++) {
			ans = Math.min(ans, getMinAttack(a-attack[i][0], b-attack[i][1], c-attack[i][2])+1);
		}
		dp[a][b][c] = ans;
		return ans;
	}
	
	private static void initialize() {
		for(int[][] row2 : dp) {
			for(int[] row : row2) {
				Arrays.fill(row, -1);
			}
		}
	}
		
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			initialize();
			int n = stoi(br.readLine());
			hp = new int[3];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				hp[i] = stoi(st.nextToken());
			}
			int result = getMinAttack(hp[0],hp[1],hp[2]);
			bw.write(result+"");
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
	뮤탈리스크 : 1개. 한 번에 세 개 scv 공격(9/3/1). 한 번에 같은 scv 여러번 공격 불가.
	scv : N개. 체력 0 이하 ->파괴
	
	결과
	모든 scv 파괴하기 위해 공격해야 하는 횟수 최솟값
	
	9,3,1 조합 -> 3 * 2 * 1 = 6
	모든 값이 0이 될 때 까지 최악의 경우 6^60(60에서 1만 계속 뺄 경우) -> 완전탐색 불가.
	
	
	3 * 2 * 1 
	3
	12 10 4
	
	- 9 1 3 = 3 9 11
	- 3 9 1 = 9 1 3
	
	
	
*/
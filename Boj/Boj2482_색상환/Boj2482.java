package boj;

import java.io.*;
import java.util.*;

public class Boj2482 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int n = stoi(br.readLine());
			int k = stoi(br.readLine());
			long[][] dp = new long[k+1][n];
			
			if(k > n/2) {
				bw.write("0");
			}
			else {
				//dp 초기화
				for(int i=0; i<n; i++) {
					dp[1][i] = 1;
				}
				
				for(int i=2; i<=k; i++) {
					for(int j=(i-1)*2; j<n; j++) {//(i-1)*2
						for(int z=j-2; z>=(i-2)*2; z--) {
							if(j == n-1) {
								dp[i][j] = dp[i][j-1];								
							}
							else {
								dp[i][j] = dp[i][j]%1000000003 + dp[i-1][z]%1000000003;
								dp[i][j] %= 1000000003;
							}
						}						
					}
				}
				long rs = 0;
				for(int i=0; i<n; i++) {
					rs = rs%1000000003 + dp[k][i]%1000000003;
					rs %= 1000000003;
				}
				bw.write(rs+"");
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
	* 색상환 : 색을 표현하는 기본 요소 이용 -> 표시할 수 있는 모든 색 중 대표적인 색 고리모양으로 연결해 나타낸 것
	* for 시각적 효과 -> 인접한 두 색 동시에 사용하지 않기로 함
	* 서로 이웃하지 않은 색 선택하는 경우의 수
	* n개의 색으로 구성되어 있는 색상환 -> 어떤 인접한 두 색도 동시에 선택하지 않으면서 서로 다른 K개의 색 선택하는 경우의 수
	
	입력
	* N : 색상환에 포함된 색의 개수(4~1000)
	* K : N색상환에서 선택할 색의 개수 (1~N)
	
	출력
	N색상환에서 어떤 인접한 두 색도 동시에 선택하지 않고 K개의 색 고를 수 있는 경우의 수 % 1,000,000,003 로 나눈 나머지

	[문제풀이]
	DP 이차원 배열로 풀이.
	* k가 n/2 초과하면 경우의 수는 무조건 0
	* dp[i][j] : (N색상환 중)0~j번째까지 색상환에서 인접한 색 동시에 선택하지 않고  i개 고를 수 있는 경우의 수 
	* dp[i][j] = dp[i-1][(i-2)*2] ~ dp[i-1][j-2]까지 합
	* 유의점 : 원형이므로 0과 n-1이 이웃하게 된다 -> 따라서 n-1번째에서는 선택하는 색상 수가 1개 줄어드는 것과 같으므로
		j == n-1 일 때 dp[i-1][j]은 dp[i-1][j-1]와 같은 경우의 수가 나온다.
*/
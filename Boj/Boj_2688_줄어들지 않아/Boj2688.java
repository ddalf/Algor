package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj2688 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int t = stoi(br.readLine());
			long[][] dp = new long[65][10];
			long[] rs = new long[65];
			
			for(int i=0; i<10; i++) {
				dp[1][i] = 1;
				rs[1] += dp[1][i];
			}
			
			for(int i=2; i<65; i++) {
				long bfsum = rs[i-1];
				for(int j=0; j<10; j++) {
					dp[i][j] = bfsum;
					bfsum -= dp[i-1][j];
					rs[i] += dp[i][j];
				}
			}
			
			
			while(t-->0) {
				int n = stoi(br.readLine());
				bw.write(rs[n]+"\n");
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
	* 어떤 숫자 줄어들지 않음 = 왼쪽 자리 수가 작거나 같을 때
	* 숫자 앞에 0(leading zero) 있어도 됨.
	* n이 주어졌을 때 줄어들지 않는 n자리 수 개수 구함
	
	입력
	* T : 테스트 케이스 개수(1~1000)
	* n : 각 테스트 케이스 숫자 n으로 이루어짐(1~64)
	
	출력
	* 각 테스트 케이스 -> 줄어들지 않는 n자리 수의 개수 출력
	
	[문제풀이]
	* dp[i][j] : i자리 수에서 끝이 j 일 때 줄어들지 않는 개수
	* dp[i][j] = 줄어들지 않는 i-1 자리 총 개수 - i-1자리 수에서 끝이 (0 ~ j-1)인 줄어들지 않는 개수의 합
	* rs[i] : 줄어들지 않는 i자리 개수
	
	
	
*/
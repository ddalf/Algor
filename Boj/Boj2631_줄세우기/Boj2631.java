package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2631 {

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int n = stoi(br.readLine());
			int[] line = new int[n];
			int[] dp = new int[n+1];
			for(int i=0; i<n; i++) {
				line[i] = stoi(br.readLine());
			}
			
			int ans = 1;
			dp[line[0]] = 1;
			for(int i=1; i<n; i++) {
				int curNum = line[i];
				dp[curNum] = 1;
				for(int j=0; j<i; j++) {
					int bfNum = line[j];
					if(curNum > bfNum) {
						dp[curNum] = Math.max(dp[curNum], dp[bfNum]+1);
					}
				}
				ans = Math.max(ans, dp[curNum]);
			}
			int result = n - ans;
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
	[문제정리]
	* 1~N번 까지 有 -> 번호 순서 바뀜.
	* 번호 순서대로 줄 세우기 위해 필요한 최소 수
	
	입력
	* N : 아이들의 수. 2~200
	 
	출력
	* 번호 순대로 줄 세우는데 옮겨지는 아이들의 최소 수
	
	문제풀이
	번호를 교환 하면서 순서 바꾸는 것이 아니라 밀어내면서 바뀜
	-> 연속된 숫자의 최대 개수를 구하고 이를 N에서 빼면 옮겨지는 아이의 최소 수 구할 수 있을 것이라 생각.
	
*/
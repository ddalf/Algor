package boj;
import java.util.*;
import java.io.*;

public class Boj1932 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			int n = stoi(br.readLine()); //삼각형 크기
			List<Integer>[]tri = new ArrayList[n];
			int[][] dp = new int[n][n];
			StringTokenizer st;
			for(int i=0; i<n; i++) {
				st= new StringTokenizer(br.readLine());
				tri[i] = new ArrayList<>();
				for(int j=0; j<i+1; j++) {
					tri[i].add(stoi(st.nextToken()));
				}
			}
			//첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
			dp[0][0] = tri[0].get(0);
			for(int i=1; i<n; i++) {
				for(int j=0; j<tri[i].size(); j++) {
					int left = -1, right = -1;
					if(j-1 >=0 ) left = dp[i-1][j-1];
					if(j < tri[i-1].size()) right = dp[i-1][j];
					dp[i][j] = tri[i].get(j) + Math.max(left, right);
				}
			}
			int ans = -1;
			for(int i=0; i<tri[n-1].size(); i++) {
				ans = Math.max(ans, dp[n-1][i]);
			}
			bw.write(ans+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

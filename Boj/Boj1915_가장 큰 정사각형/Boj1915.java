package boj;
import java.io.*;
import java.util.StringTokenizer;
public class Boj1915 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			int m = stoi(st.nextToken());
			int[][] arr = new int[n+1][m+1];
			int dp[][] = new int[n+1][m+1];
			for(int i=1; i<=n; i++) {
				char[] line = br.readLine().toCharArray();
				for(int j=1; j<=m; j++) {
					arr[i][j] = line[j-1]-'0';
				}
			}
			int ans = 0;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(arr[i][j] == 1) {
						int top = dp[i-1][j];
						int left = dp[i][j-1];
						int diag = dp[i-1][j-1];
						
						dp[i][j] = Math.min(top, left);
						dp[i][j] = Math.min(dp[i][j], diag);
						dp[i][j] += 1;
						ans = Math.max(ans, dp[i][j]);						
					}
				}
			}
			bw.write(ans*ans+"");
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
	n*m 배열. 1로 된 가장 큰 정사각형의 크기
	
	[입력]
	n,m : 1~1000
	
	[출력]
	가장 큰 정사각형 넓이
	
	문제풀이
	dp[i][j] : 현재 위치가 포함 된 가장 큰 정사각형 크기
	
*/
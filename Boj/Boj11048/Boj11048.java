package boj;
import java.util.*;
import java.io.*;

class Boj11048 {
	public static int[][] board;
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			board = new int[n+1][m+1];
			int[][] dp = new int[n+1][m+1];
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=m; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[1][1] = board[1][1];
			for(int j=2; j<=m; j++) {
				dp[1][j] = dp[1][j-1] + board[1][j];
			}
			for(int i=2; i<=n; i++) {
				dp[i][1] = dp[i-1][1] + board[i][1];
			}
			for(int i=2; i<=n; i++) {
				for(int j=2; j<=m; j++) {
					int maxv = Math.max(dp[i-1][j], dp[i][j-1]);
					maxv = Math.max(maxv,  dp[i-1][j-1]);
					dp[i][j] = board[i][j] + maxv;
				}
			}
			System.out.println(dp[n][m]);
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
/*
	��������
	�̷� : N*M
	�ر� : (1,1) -> (n,m) �̵��Ϸ� ��
	�� �� ������ �ִ� ���� ������ �� ����
	�̷� ������ ���� �� X
	�̵� �� �� ������ �� �ִ� ���� �ִ� ����
	
	N : 1~1000
	M : 1~1000
	�� ĭ ���� ���� : 0~100
*/
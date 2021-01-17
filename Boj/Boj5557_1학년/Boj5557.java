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
					if(dp[i-1][j] > 0) {//������ 0~20 �� ������ ����� ���� �ִ� ��
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
	[��������]
	* ������ ����.
	* ������ �� ���� ���� "=", ������ ���� ���� "+" or "-"����
	* �ùٸ� ��� ������� ��.
	* ���� ������ �ȵ�.
	* 2�߰� ������ �� : 0~20 �̿��� ��.
	
	�Է�
	* N : ������ ����. 3~100. 
	
	���
	* ������ �ùٸ� ����� ����. 2^63-1 ����(=int ���� ��)
	
	[����Ǯ��]
	dp[i][j] : i ��° ���� �� �� j�� �� �ִ� ����� ��
	dp[i][j] = dp[i-1][j-arr[i]]
	
*/
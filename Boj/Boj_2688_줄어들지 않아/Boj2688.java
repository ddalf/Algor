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
	[��������]
	* � ���� �پ���� ���� = ���� �ڸ� ���� �۰ų� ���� ��
	* ���� �տ� 0(leading zero) �־ ��.
	* n�� �־����� �� �پ���� �ʴ� n�ڸ� �� ���� ����
	
	�Է�
	* T : �׽�Ʈ ���̽� ����(1~1000)
	* n : �� �׽�Ʈ ���̽� ���� n���� �̷����(1~64)
	
	���
	* �� �׽�Ʈ ���̽� -> �پ���� �ʴ� n�ڸ� ���� ���� ���
	
	[����Ǯ��]
	* dp[i][j] : i�ڸ� ������ ���� j �� �� �پ���� �ʴ� ����
	* dp[i][j] = �پ���� �ʴ� i-1 �ڸ� �� ���� - i-1�ڸ� ������ ���� (0 ~ j-1)�� �پ���� �ʴ� ������ ��
	* rs[i] : �پ���� �ʴ� i�ڸ� ����
	
	
	
*/
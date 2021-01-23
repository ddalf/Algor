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
				//dp �ʱ�ȭ
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
	[��������]
	* ����ȯ : ���� ǥ���ϴ� �⺻ ��� �̿� -> ǥ���� �� �ִ� ��� �� �� ��ǥ���� �� ��������� ������ ��Ÿ�� ��
	* for �ð��� ȿ�� -> ������ �� �� ���ÿ� ������� �ʱ�� ��
	* ���� �̿����� ���� �� �����ϴ� ����� ��
	* n���� ������ �����Ǿ� �ִ� ����ȯ -> � ������ �� ���� ���ÿ� �������� �����鼭 ���� �ٸ� K���� �� �����ϴ� ����� ��
	
	�Է�
	* N : ����ȯ�� ���Ե� ���� ����(4~1000)
	* K : N����ȯ���� ������ ���� ���� (1~N)
	
	���
	N����ȯ���� � ������ �� ���� ���ÿ� �������� �ʰ� K���� �� �� �� �ִ� ����� �� % 1,000,000,003 �� ���� ������

	[����Ǯ��]
	DP ������ �迭�� Ǯ��.
	* k�� n/2 �ʰ��ϸ� ����� ���� ������ 0
	* dp[i][j] : (N����ȯ ��)0~j��°���� ����ȯ���� ������ �� ���ÿ� �������� �ʰ�  i�� �� �� �ִ� ����� �� 
	* dp[i][j] = dp[i-1][(i-2)*2] ~ dp[i-1][j-2]���� ��
	* ������ : �����̹Ƿ� 0�� n-1�� �̿��ϰ� �ȴ� -> ���� n-1��°������ �����ϴ� ���� ���� 1�� �پ��� �Ͱ� �����Ƿ�
		j == n-1 �� �� dp[i-1][j]�� dp[i-1][j-1]�� ���� ����� ���� ���´�.
*/
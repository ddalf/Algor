package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj4811 {
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			long [][] dp = new long[31][63];
			
			for(int h=1; h<=60; h++) dp[0][h] = 1;
			dp[1][0] = 1;
			for(int w=1; w<=30; w++) {
				for(int h=0; h<=60; h++) {
					dp[w][h] = dp[w-1][h+1] + (h > 0 ? dp[w][h-1] : 0);
				}
			}
			
			while(true) {
				int n = stoi(br.readLine());
				if(n == 0) break;
				bw.write(dp[n][0]+"\n");
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
��������
* �� N�� ��� ��
* ù�� -> ������ �� �ϳ� ����. 
* ���� �� 
 	-> �� ��ü : �� ������ �ʰ��� �� ���� ���� / �ٸ� ���� ���� ����. W ����
	-> �� ���� : �� �� ����.H ����
* 2N�� ���� -> ���� 2N�� ���ڿ�. ������ ���� �ٸ� ���ڿ��� ����

[�Է�]
* �׽�Ʈ ���̽� : 1000��
* N : ���� ����. ~ 30

[���]
* ������ ���ڿ��� ����

����Ǯ��
* DP�� ǰ. 
* dp[i][j] : ������ i�� �� ���� j�� ���� �� ������ ���� �ٸ� ���ڿ� ���� 
* dp[i][j] = dp[i-1][h+1] + dp[w][h-1]
EX. �ܡܡ�  = �ܡ� ���ڿ� ���� + �ܡ��  ���ڿ� ���� (�� - �� ���� / �� - �� ����)  
*/
package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2228A1 {
	static final int MINV = -2000000000;
	static int n;
	static int m;
	static int[] arr;
	static int[] sum;
	static int[][] dp;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	//DP[i][j] = i��° ������ j���� �������� ���� �� �ִ�
	public static int go(int idx, int section) {
		if(section == 0) return 0;
		if(idx > n || section < 0) return MINV;
		int ret = dp[idx][section];
		if(ret == MINV) {
			ret = MINV;
			//1. i��° �� �������� ���� ���
			ret = go(idx+1, section);
			//2. idx��° ~ idx+i��° ���� ���� ������ ���
			//	= 	idx��° ~ idx+i��° ���� �� 			: sum[idx+i]-sum[idx-1]
			//		+ (idx+i+2)��° ���� ���� ���� �� �ִ�	: go(idx+2+i, section-1)
			for(int i=0; idx+i<=n; i++) {
				ret = Math.max(ret, go(idx+2+i, section-1)+sum[idx+i]-sum[idx-1]);
			}
			dp[idx][section] = ret;
		}
		return ret;
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			arr = new int[n+1];
			sum = new int[n+1];
			dp = new int[n+1][m+1];
	
			for(int i=1; i<=n; i++) {
				arr[i] = stoi(br.readLine());
				sum[i] = sum[i-1] + arr[i];
			}
			
			for(int i=0; i<=n; i++) {
				Arrays.fill(dp[i], MINV);
			}
			
			bw.write(go(1, m)+"");

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
N���� ���� �̷���� 1���� �迭
M�� ���� ���� -> ������ ���� ������ �� �ִ밡 �ǵ���
����
	�� ���� �� �� �̻��� ���ӵ� ��
	���� �ٸ� �� ���� ��ħ or ���� X
	��Ȯ�� M���� ����. �̸��̿����� X

�Է�
N : 1~100
M : 1~N/2
�迭 �̷�� �� : -32768 ~ 32768

���
������ ���� ������ �� ���� �ִ�

����Ǯ��
DP[i][j] = i��° ������ j���� �������� ���� �� �ִ�

*/

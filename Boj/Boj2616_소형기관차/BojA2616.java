package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BojA2616 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] capacityArr = new int[n+1];
			int[] dp = new int[n+1];
			int[] maxAsc = new int[n+1];
			int[] maxDesc = new int[n+1];
			for(int i=1; i<=n; i++) {
				capacityArr[i] = stoi(st.nextToken());
				dp[i] = dp[i-1] + capacityArr[i];
			}
			
			int mxC = stoi(br.readLine());
			for(int i=1; i<n-mxC+2; i++) {
				maxAsc[i] = Math.max(maxAsc[i-1], dp[i+mxC-1]-dp[i-1]);
			}
			
			for(int i=n-mxC+1; i>=1; i--) {
				maxDesc[i] = Math.max(maxDesc[i+1], dp[i+mxC-1]-dp[i-1]);
			}
			int ans = 0;
			for(int i=1+mxC; i<n-2*mxC+2; i++) {
				ans = Math.max(ans, dp[i+mxC-1]-dp[i-1]+maxAsc[i-mxC]+maxDesc[i+mxC]);
			}
			bw.write(ans+"");
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
	* ���� : �� �� ����� 1�밡 ����ĭ ��
	* ���� ����� : ����� ���� ���. ���� ��������� ���� ĭ ���� ����. 3��
		1. ���� ����� �ִ�� �� �� �ִ� ���� �� ������ ����. ��� ����.
		2. �ִ��� ���� �մ� ���. �� ��ü �մ� �� ������ ����
		3. �� ��������� -> ��ȣ ���������� �̾��� ���� ��
	�Է�
	n : ������� ���� ���� ���� ��(1~50000)
	capacityArr : �� ���� �մ� ��
	maxCapacity : ���� ������� �ִ�� �� �� �ִ� ���� ��(1~50000/3)
	
	���
	���� ����� 3��� �ִ� ����� �� �ִ� �մ� ��
	
	[����Ǯ��]
	* dp[i][j] : i�� ����������� j��° �� �� �ִ� �մ� ��
*/

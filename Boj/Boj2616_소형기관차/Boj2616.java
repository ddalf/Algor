package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2616 {
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
			int[][] dp = new int[3][n+1];
			for(int i=1; i<=n; i++) {
				capacityArr[i] = stoi(st.nextToken());
			}
			int maxCapacity = stoi(br.readLine());
			
			for(int i=1; i<=n; i++) {
				if(i+maxCapacity > n+1) break;
				for(int j=0; j<maxCapacity; j++) {
					dp[0][i] += capacityArr[i+j];
				}
			}
			
			for(int i=1; i<3; i++) {
				for(int j=i*maxCapacity+1; j<=n-(2-i)*maxCapacity; j++) {
					int ans = Integer.MIN_VALUE;
					for(int k= (i-1)*maxCapacity+1; k<=j-maxCapacity; k++) {
						ans = Math.max(ans, dp[i-1][k]);
					}
					dp[i][j] = ans + dp[0][j];
				}
			}
			int rs  = Integer.MIN_VALUE;
			for(int i=0; i<=n; i++) {
				rs = Math.max(rs, dp[2][i]);
			}
			bw.write(rs+"");
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

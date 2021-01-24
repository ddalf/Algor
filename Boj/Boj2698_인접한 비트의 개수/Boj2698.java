package boj;
import java.util.*;
import java.io.*;
public class Boj2698 {
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int t = stoi(br.readLine());
			int dp[][][] = new int[101][100][2];
			
			dp[1][0][0] = 1;
			dp[1][0][1] = 1;
			
			for(int i=2; i<=100; i++) {//���� ũ��
				for(int j=0; j<i; j++) {//������Ʈ ����
					dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];					
					dp[i][j][1] = (j-1) >= 0 ? dp[i-1][j][0]+dp[i-1][j-1][1] : dp[i-1][j][0];
				}
			}
			
			for(int i=0; i<t; i++) {
				st = new StringTokenizer(br.readLine());
				int n = stoi(st.nextToken());
				int k = stoi(st.nextToken());
				bw.write((dp[n][k][0] + dp[n][k][1])+"\n");
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
	* ���� S : 0�� 1�� �̷���� ����
	* s1 : S�� ù ��
	* sn : S�� ������ ��
	* ������ ��Ʈ�� ���� : s1 * s2 + s2 * s3 + .. + sn-1 + sn
	* ���� S�� ũ��, n�� k �־����� �� ������ ��Ʈ�� ������ k���� ���� S�� ���� ���ϱ�
	* ex. n=5, k=2 -> 11100, 01110, 00111, 10111, 11101, 11011
	
	�Է�
	* T : �׽�Ʈ ���̽� ��
	* n, k : 1~100.
	
	���
	* �� �׽�Ʈ ���̽��� ���� ������ ��Ʈ�� ������ k�� ���� S�� ����
	* 2,147,483,647���� �۰ų� ����

	[����Ǯ��]
	dp[i][j][k] : ������ ũ�Ⱑ i�� �� ������Ʈ ���� j�̸鼭 ���ڸ� ���� k�� ����
	i : 1~n
	j : 0~i-1
	k : 0,1
*/
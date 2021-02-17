package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2229 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static int[] scores;
	static int[] dp;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			scores = new int[n];
			dp = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				scores[i] = stoi(st.nextToken());
			}
			
			for(int i=1; i<=n; i++) {
				int maxScore = scores[i-1];
				int minScore = scores[i-1];
				for(int j=i-1; j>=0; j--) {
					maxScore = Math.max(maxScore, scores[j]);
					minScore = Math.min(minScore, scores[j]);
					dp[i] = Math.max(dp[i], dp[j] + maxScore - minScore);
				}
			}
			bw.write(dp[n]+"");
			
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
* N�� ķ���� ����.
* �Ƿ� ���� ���� ������ �� ��
* 1. ���� �� ����
  2. �л� ������ ����
* �� ���� ��� X
* �� �� �� ¥���� ���� : ���� ���� ���� ���� �л� - ���� ���� ���� �л�
* ��ü �� �� ¥���� ���� = �� �� �� ¥���� ���� ��

�Է�
* N : �л� ��
* �� �л� ����. 0~10,000

���
* �� �� ¥���� ������ �ִ�

2 5 7 1 3 4 8 6 9 3
3   6   5     6 

����Ǯ��
* dp[i] = i��° ���� ���� ®�� �� �� �� ¥���� ������ �ִ�
* dp[i] = 

*/
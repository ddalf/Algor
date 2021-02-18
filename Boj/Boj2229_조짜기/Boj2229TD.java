package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2229TD {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int n;
	static int[] scores;
	static int[] dp;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static int go(int stdIdx) {
		if(stdIdx < 1) return 0;
		if(dp[stdIdx] > 0) return dp[stdIdx];
		int minScore = scores[stdIdx-1];
		int maxScore = scores[stdIdx-1];
		for(int j = stdIdx - 1; j>=0; j--) {
			minScore = Math.min(minScore, scores[j]);
			maxScore = Math.max(maxScore, scores[j]);
			dp[stdIdx] = Math.max(dp[stdIdx], go(j) + maxScore - minScore);
		}
		return dp[stdIdx];
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
			go(n);
			bw.write(dp[n] + "");
			bw.flush(); bw.close(); br.close();
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

����Ǯ��
* dp[i] = i��° ���� ���� ®�� �� �� �� ¥���� ������ �ִ�
*/

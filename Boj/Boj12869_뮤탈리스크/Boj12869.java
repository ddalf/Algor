package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj12869 {
	private static int[][] attack = {{9,3,1}, {9,1,3}, {3,1,9}, {3,9,1}, {1,3,9}, {1,9,3}};
	private static int[] hp;
	private static int[][][] dp = new int[61][61][61];
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static int getMinAttack(int a, int b, int c) {
		//1. 0���� �۾��� ��� 0���� ���
		if(a < 0) a = 0;
		if(b < 0) b = 0;
		if(c < 0) c = 0;
		
		//2. dp�迭�� ���� ���ϰ��� �ϴ� ���� ���� ���(-1 �ƴ� ���) �ش� ������ return
		if(dp[a][b][c] != -1) return dp[a][b][c];
		
		//3. dp�迭�� ���� ���ϰ��� �ϴ� ���� ���� ���
		//3-1. ������ ���տ��� �ѹ��� ��� 0���� �Ǵ� ��� ��� ����. 1 ��ȯ�� ��
		for(int i=0; i<6; i++) {
			if(a-attack[i][0] <= 0 && b-attack[i][1] <= 0 && c-attack[i][2] <= 0) return 1;
		}
		
		//3-2. �ѹ��� ��� 0 ���� �ʴ� ��� -> ������ ���տ��� �ּڰ� ����
		int ans = 7 * 7 * 7; // �ִ� ����� ��. 60 / 9 = 6.xxxx -> 7 ^ 3
		for(int i=0; i<6; i++) {
			ans = Math.min(ans, getMinAttack(a-attack[i][0], b-attack[i][1], c-attack[i][2])+1);
		}
		dp[a][b][c] = ans;
		return ans;
	}
	
	private static void initialize() {
		for(int[][] row2 : dp) {
			for(int[] row : row2) {
				Arrays.fill(row, -1);
			}
		}
	}
		
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			initialize();
			int n = stoi(br.readLine());
			hp = new int[3];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				hp[i] = stoi(st.nextToken());
			}
			int result = getMinAttack(hp[0],hp[1],hp[2]);
			bw.write(result+"");
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
	��Ż����ũ : 1��. �� ���� �� �� scv ����(9/3/1). �� ���� ���� scv ������ ���� �Ұ�.
	scv : N��. ü�� 0 ���� ->�ı�
	
	���
	��� scv �ı��ϱ� ���� �����ؾ� �ϴ� Ƚ�� �ּڰ�
	
	9,3,1 ���� -> 3 * 2 * 1 = 6
	��� ���� 0�� �� �� ���� �־��� ��� 6^60(60���� 1�� ��� �� ���) -> ����Ž�� �Ұ�.
	
	
	3 * 2 * 1 
	3
	12 10 4
	
	- 9 1 3 = 3 9 11
	- 3 9 1 = 9 1 3
	
	
	
*/
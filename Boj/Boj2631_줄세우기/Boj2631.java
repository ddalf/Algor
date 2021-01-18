package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2631 {

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int n = stoi(br.readLine());
			int[] line = new int[n];
			int[] dp = new int[n+1];
			for(int i=0; i<n; i++) {
				line[i] = stoi(br.readLine());
			}
			
			int ans = 1;
			dp[line[0]] = 1;
			for(int i=1; i<n; i++) {
				int curNum = line[i];
				dp[curNum] = 1;
				for(int j=0; j<i; j++) {
					int bfNum = line[j];
					if(curNum > bfNum) {
						dp[curNum] = Math.max(dp[curNum], dp[bfNum]+1);
					}
				}
				ans = Math.max(ans, dp[curNum]);
			}
			int result = n - ans;
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
	[��������]
	* 1~N�� ���� �� -> ��ȣ ���� �ٲ�.
	* ��ȣ ������� �� ����� ���� �ʿ��� �ּ� ��
	
	�Է�
	* N : ���̵��� ��. 2~200
	 
	���
	* ��ȣ ����� �� ����µ� �Ű����� ���̵��� �ּ� ��
	
	����Ǯ��
	��ȣ�� ��ȯ �ϸ鼭 ���� �ٲٴ� ���� �ƴ϶� �о�鼭 �ٲ�
	-> ���ӵ� ������ �ִ� ������ ���ϰ� �̸� N���� ���� �Ű����� ������ �ּ� �� ���� �� ���� ���̶� ����.
	
*/
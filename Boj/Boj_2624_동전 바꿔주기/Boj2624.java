package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Coin {
	int p,n;
	Coin(){this.p=0;this.n=0;}
	Coin(int p, int n){this.p = p; this.n = n;}
}

public class Boj2624 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int t = stoi(br.readLine());
			int k = stoi(br.readLine());
			Coin[] coinInfo = new Coin[k];
			int[] dp = new int[t+1];
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int p = stoi(st.nextToken());
				int n = stoi(st.nextToken());
				coinInfo[i] = new Coin(p,n);
			}
			
			dp[0] = 1;
			for(int i=0; i<k; i++) {
				Coin c = coinInfo[i];
				for(int j=t; j>=1; j--) {
					for(int n=1; n<=c.n; n++) {
						int cost = c.p * n;
						if(j-cost >= 0 && dp[j-cost] > 0) dp[j] += dp[j-cost];
					}
				}
			}
			
			bw.write(dp[t]+"");
			
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
	* T�� ���� -> �������� �ٲ�.
	* ���� ��ȯ ��� �������� -> ���� �������� ��ȯ�ϴ� ��� ���� ��
	* ����� �� 2^31-1 �ʰ� X
	
	�Է�
	* T : ���� �ݾ�(1~10000)
	* k : ���� ���� ��(1~100)
	* p(i), n : �� ������ �ݾ�(0~1000), �� ���� ����
	
	���
	* ���� ��ȯ ����� ���� ��
	* ��� ���� -> 0 ���

	[����Ǯ��]
	dp[i] = i���� ���� �� �ִ� ����� ���� ��
	
	
*/
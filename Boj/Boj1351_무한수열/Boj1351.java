package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1351 {
	static long n;
	static long dp[];
	static int p;
	static int q;
	static final long MAXV = 10000000;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static long stol(String s) {
		return Long.parseLong(s);
	}

	private static long go(long x) {
		if(x <= MAXV) return dp[(int)x];
		return go(x/p) + go(x/q);
	}
	
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n =stol(st.nextToken());
			p = stoi(st.nextToken());
			q = stoi(st.nextToken());
			dp = new long[(int)(Math.min(n, MAXV)+1)];
			
			long k = Math.min(n, MAXV);
			dp = new long[(int)k+1];
			dp[0] = 1;
			for(int i=1; i<=k; i++) {
				dp[i] = dp[i/p] + dp[i/q];
			}
			
			bw.write(go(n)+"");
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
	* A0 = 1
	* A(i) = A(i/P) + A(i/Q) i>=1
	* N�� P�־��� �� Q ���ϱ�
	
	�Է�
	* N : 1~10^12
	* P,Q : 2~10^9
	 
	���
	ù° �� A(N) ���
*/
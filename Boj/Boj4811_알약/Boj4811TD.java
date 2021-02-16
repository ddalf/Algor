package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj4811TD {
	static long [][] dp;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static long go(int w, int h) {
		if(w < 0 || h < 0 || w > 30 || h > 60) return 0;
		if(dp[w][h] > 0) return dp[w][h];
		return dp[w][h] = go(w, h-1) + go(w-1, h+1);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			dp = new long[31][63];
			for(int h=0; h<=60; h++) dp[0][h] = 1;
			dp[1][0] = 1;
			go(30,0);

			while(true) {
				int n = stoi(br.readLine());
				if(n == 0) break;
				bw.write(dp[n][0]+"\n");
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

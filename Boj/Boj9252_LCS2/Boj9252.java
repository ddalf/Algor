package boj;
import java.util.*;
import java.io.*;

public class Boj9252 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static String ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			ans = "";
			char[] x = br.readLine().toCharArray();
			char[] y = br.readLine().toCharArray();
			int xLen = x.length;
			int yLen = y.length;
			int dp[][] = new int[xLen+1][yLen+1];
			
			for(int i=1; i<=xLen; i++) {
				for(int j=1; j<=yLen; j++) {
					if(x[i-1] == y[j-1]) dp[i][j] = dp[i-1][j-1]+1;
					else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
			bw.write(dp[xLen][yLen]+"\n");
			StringBuffer sb = new StringBuffer("");
			while(xLen > 0 && yLen > 0) {
				if(dp[xLen][yLen] == dp[xLen-1][yLen]) {
					--xLen;
				}
				else if(dp[xLen][yLen] == dp[xLen][yLen-1]) {
					--yLen;
				}
				else {
					sb.append(x[xLen-1]);
					--xLen;
					--yLen;
				}
			}
			bw.write(sb.reverse().toString());
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

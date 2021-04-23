package boj;
import java.util.*;
import java.io.*;

public class Boj9251 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			char[] x = br.readLine().toCharArray();
			char[] y = br.readLine().toCharArray();
			int xLen = x.length;
			int yLen = y.length;
			int[][] dp = new int[xLen+1][yLen+1];
			for(int i=1; i<=xLen; i++) {
				for(int j=1; j<=yLen; j++) {
					if(x[i-1] != y[j-1]) dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					else dp[i][j] = dp[i-1][j-1]+1;//최장 공통 부분 수열에 포함되는 문자
				}
			}
			bw.write(dp[xLen][yLen]+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

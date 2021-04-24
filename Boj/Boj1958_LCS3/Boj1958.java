package boj;
import java.util.*;
import java.io.*;
public class Boj1958 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			char[] x = br.readLine().toCharArray();
			char[] y = br.readLine().toCharArray();
			char[] z = br.readLine().toCharArray();
			
			int xLen = x.length;
			int yLen = y.length;
			int zLen = z.length;
			int[][][] dp = new int[xLen+1][yLen+1][zLen+1];
			
			for(int i=1; i<=xLen; i++) {
				for(int j=1; j<=yLen; j++) {
					for(int k=1; k<=zLen; k++) {
						 if(x[i-1] == y[j-1] && y[j-1]== z[k-1]) dp[i][j][k] = dp[i-1][j-1][k-1]+1;
						 else {
							 //두 개가 겹치는 경우
							 dp[i][j][k] = Math.max(dp[i-1][j-1][k], dp[i-1][j][k-1]);
							 dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k-1]);
							 //모두 겹치지 않는 경우
							 dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k]);
							 dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k]);
							 dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j][k-1]);
						 }
					}
				}
			}
			
			bw.write(dp[xLen][yLen][zLen]+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

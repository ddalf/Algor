package boj;
import java.util.*;
import java.io.*;

public class Boj2447 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static char[][] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static boolean isStar(int x, int y, int num) {
		if(ans[x][y] != 'x') return ans[x][y] == '*' ? true : false;
		if(x%3 == 1 && y%3 ==1) return false;
		if(num == 0) return true;
		return isStar(x/3, y/3, num/3);
	}
	
	public static void solve() {
		try {
//			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = stoi(br.readLine());
			ans = new char[n][n];
			for(int i=0; i<n; i++) {
				Arrays.fill(ans[i], 'x');
				for(int j=0; j<n; j++) {
					if(isStar(i,j,n)) ans[i][j] = '*';
					else ans[i][j] = ' ';
					bw.write(ans[i][j]);
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

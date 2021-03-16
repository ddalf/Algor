package boj;
import java.util.*;
import java.io.*;

public class Boj10830 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static long b;
	static long[][] matrix;
	static long[][] ans;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static long stol(String s) {
		return Long.parseLong(s);
	}
	
	public static long[][] mulMatrix(long[][] x, long[][] y) {
		long[][] ret = new long[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					ret[i][j] = (ret[i][j] + x[i][k] * y[k][j]) % 1000;
				} 
			}
		}
		return ret;
	}
	
	public static void powMatrix() throws IOException {
		while(b > 0) {
			if(b % 2 == 1) {
				ans = mulMatrix(ans, matrix);
			}
			b/=2;
			matrix = mulMatrix(matrix, matrix);
		}
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			n =stoi(st.nextToken());
			b = stol(st.nextToken());
			matrix = new long[n][n];
			ans = new long[n][n];

			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					matrix[i][j] = stoi(st.nextToken());
					if(i == j) ans[i][j] = 1;
				}
			}
			
			powMatrix();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					bw.write(ans[i][j]+" ");
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

/*
행렬 : n*n
a의 b제곱 구하는 프로그램
a^b의 각 원소 1000으로 나눈 나머지

n: 2~5;
b: 1,000,000,000,000

5 999999999999
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
*/
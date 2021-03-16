package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj2749 {
	static BufferedReader br;
	static BufferedWriter bw;
	static long n;
	static long[][] matrix = {{1,1},{1,0}};
	static long[][] ans = {{1,0},{0,1}};
	public static long stol(String s) {
		return Long.parseLong(s);
	}
	
	public static long[][] mulMatrix(long[][] x, long[][] y) {
		long[][] ret = new long[2][2];
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<2; k++) {
					ret[i][j] = (ret[i][j] + x[i][k] * y[k][j]) % 1000000;
				} 
			}
		}
		return ret;
	}
	
	public static void powMatrix() throws IOException {
		while(n > 0) {
			if(n % 2 == 1) {
				ans = mulMatrix(ans, matrix);
			}
			n/=2;
			matrix = mulMatrix(matrix, matrix);
		}
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			n = stol(br.readLine()) - 1;
			powMatrix();
			bw.write(ans[0][0]+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

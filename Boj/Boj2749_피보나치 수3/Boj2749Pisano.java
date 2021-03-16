package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj2749Pisano {
	static BufferedReader br;
	static BufferedWriter bw;
	static final int MODV = 1000000;
	static long n;
	static long[] fibNums;
	
	public static long stol(String s) {
		return Long.parseLong(s);
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			int period = 1500000;
			n = stol(br.readLine());
			fibNums = new long[period];
			fibNums[0]=0; fibNums[1] = 1;
 			for(int i=2; i<period && i<=n; i++) {
				fibNums[i] = (fibNums[i-1] + fibNums[i-2]) % MODV;
			}
 			n %= period;
 			bw.write(fibNums[(int)n]+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

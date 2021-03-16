package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj11401 {
	static BufferedReader br;
	static BufferedWriter bw;
	static final int P = 1000000007;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static long pow(long b, long ex) {
		long ret = 1;
		long num = b;
		while(ex > 0) {
			if(ex % 2 == 1) {
				ret = ret * num % P;
			}
			ex /= 2;
			num = (num*num) % P;
		}
		return ret;
	}
	
	public static long factorial(int num) {
		long ret = 1;
		for(int i=2; i<=num; i++) {
			ret = ret * i % P;
		}
		return ret;
	}
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			int k = stoi(st.nextToken());
			long a = factorial(n);
			long b = factorial(k) * factorial(n-k) % P;
			long ans = a * pow(b, P-2) % P;
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

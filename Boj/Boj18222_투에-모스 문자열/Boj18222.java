package boj;
import java.util.*;
import java.io.*;

public class Boj18222 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static long[] arr;
	public static long stol(String s) {
		 return Long.parseLong(s);
	}
	static long tMos(long x) {
		if(x == 1) return 0;
		long maxI = 0;
		for(int i=0; i<arr.length; i++) {
			if(x > arr[i]) maxI = arr[i];
			else break;
		}
		return 1-tMos(x-maxI);
	}
	public static void solve() {
		try {
			arr = new long[64];//10^18 (2^10)^6 = (10^3)^6
			long n = stol(br.readLine());
			for(int i=0; i<arr.length; i++) {
				arr[i] = (long)Math.pow(2, i);//i¹øÂ° 2ÀÇ °ÅµìÁ¦°ö °ª
			}
			bw.write(tMos(n)+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

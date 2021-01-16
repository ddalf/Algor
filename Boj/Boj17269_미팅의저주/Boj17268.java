package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj17268 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int n = stoi(br.readLine());
			n/=2;//n은 짝수값만 들어옴 -> 2나눠줘서 범위 줄여줌
			long[] arr = new long[n+1];
			arr[0] = 1;
			for(int i=1; i<=n; i++) {
				for(int j=0; j<=i-1; j++) {
					arr[i] += (arr[j] % 987654321 * arr[i-1-j] % 987654321) % 987654321;
					arr[i] %= 987654321;
				}
			}
			bw.write(arr[n]+"");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

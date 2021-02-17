package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj11687 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static int m;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static int isPossibleNum (int n) {
		int cnt = 0;
		for(int i=5; i<=n; i*=5) {
			cnt += n / i;
		}
		return cnt;
	}
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			m = stoi(br.readLine());
			int left = 1, right = 1000000000, mid;
			boolean flag = false;
			while(left <= right) {
				mid = (left + right) / 2;
				int cnt = isPossibleNum(mid);
				if(cnt >= m) {
					if(cnt == m) flag = true;
					right = mid - 1;
				}
				else left = mid + 1;
			}
			if(!flag) bw.write("-1");
			else bw.write(left + "");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

/*
문제정리
* 가장 끝의 0의 개수가 M개이 N! 중에서 가장 작은 N 찾음

[입력]
M : 100,000,000

[출력]
가장 작은 N 출력. 없는 경우 -1 출력

*/
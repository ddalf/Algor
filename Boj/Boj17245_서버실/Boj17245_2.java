package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj17245_2 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static long sum;
	static int[][] room;
	static int[] height;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isNormalOP(int num) throws IOException {
		long cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(room[i][j]< num) cnt += (long)room[i][j];
				else cnt += (long)num;
			}
		}
		bw.write(num+" : " +cnt+"\n");
		return cnt >= sum;
	}
	
	public static void solve() {
		try {
			n = stoi(br.readLine());//서버실 크기
			room = new int[n][n];
			sum = 1;
			int maxCnt = 0;
			StringTokenizer st;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					room[i][j] = stoi(st.nextToken());
					sum += (long)room[i][j];
					maxCnt = Math.max(maxCnt, room[i][j]);
				}
			}
			sum /= 2;
			int left = 0, right = maxCnt;
			int ans = 0;
			while(left <= right) {
				int mid = (left+right)/2;
				if(isNormalOP(mid)) {
					ans = mid;
					right = mid-1;
				}
				else left = mid+1;
			}
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

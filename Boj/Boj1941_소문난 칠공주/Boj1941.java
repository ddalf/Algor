package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1941 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n, ans;
	static char[] students;
	static boolean[] visit;
	static Queue<Integer> visitQ;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isPossibleGroup() throws IOException {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] check = new boolean[n][n];
		for(int i=0; i<n*n; i++) {
			if(visit[i]) {
				if(q.size() == 0) q.add(new Pair(i/5, i%5));
				else check[i/5][i%5] = true;
			}
		}
		int cnt = 1;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int i=0; i<4; i++) {
				int xx = cur.x + dx[i];
				int yy = cur.y + dy[i];
				if(xx < 0 || yy < 0 || xx >=n || yy >= n) continue;
				if(check[xx][yy]) {
					check[xx][yy] = false;
					q.add(new Pair(xx, yy));
					++cnt;
				}
			}
		}
		return cnt == 7;
	}
	
	static void combination(int idx, int cnt, int sCnt) throws IOException  {
		if(cnt == 7) {
			if(sCnt >= 4 && isPossibleGroup()) {
				++ans;
			}
			return;
		}
		for(int i = idx; i<n*n; i++) {
			visit[i] = true;
			if(students[i] == 'S') combination(i+1, cnt+1, sCnt+1);
			else combination(i+1, cnt+1, sCnt);
			visit[i] = false;
		}
	}

	
	public static void solve() {
		try {
			n = 5;
			ans = 0;
			students = new char[n*n];
			visit = new boolean[n*n];
			for(int i=0; i<n; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=0; j<n; j++) {
					students[i*5+j] = tmp[j];
				}
			}
			
			combination(0, 0, 0);
			bw.write(ans+"");
			//S : 이다솜
			//Y : 이도연
			//7명학생. 인접한 7자리. S최소 4명 포함
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

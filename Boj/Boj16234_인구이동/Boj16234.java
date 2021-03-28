package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
public class Boj16234 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int n,l,r;
	static int[][] countries;
	static boolean[][] visit;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int findUnion(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		List<Pair> unions = new ArrayList<>();
		q.add(new Pair(x, y));
		visit[x][y] = true;
		unions.add(new Pair(x,y));
		int sum = countries[x][y];
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int i=0; i<4; i++) {
				int xx = cur.x + dx[i];
				int yy = cur.y + dy[i];
				if(xx < 0 || yy < 0 || xx >=n || yy>=n || visit[xx][yy]) continue;
				int diff = Math.abs(countries[cur.x][cur.y] - countries[xx][yy]);
				if(diff < l || diff > r) continue;
				q.add(new Pair(xx, yy));
				visit[xx][yy] = true;
				unions.add(new Pair(xx, yy));
				sum += countries[xx][yy];
			}
		}
		if(unions.size() == 1) return 0;
		int avg = sum / unions.size();
		for(Pair p : unions) {
			countries[p.x][p.y] = avg;
		}
		return 1;
	}

	
	static int movePopulation() throws IOException{
		int ret = 0;
		while(true) {
			int flag = 0;
			for(int i=0; i<n; i++) {
				Arrays.fill(visit[i], false);
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(visit[i][j]) continue;
						flag += findUnion(i,j);
				}
			}
			if(flag == 0) break;
			++ret;
		}
		return ret;
	}

	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//r행 c열 나라 A[r][c]명 살고 있다.
			//인접한 나라 사이에 국경선 존재
			//국경선 공유하는 두 나라 인구차이 R이상 L이하 
			n = stoi(st.nextToken());
			l = stoi(st.nextToken());
			r = stoi(st.nextToken());
			countries = new int[n][n];
			visit = new boolean[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					countries[i][j] = stoi(st.nextToken());
				}
			}
			
			bw.write(movePopulation()+"");
			
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

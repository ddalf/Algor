package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Raiser{
	int x;
	int y;
	int dir;
	
	Raiser(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Boj3987 {
	static int n, m, pr, pc;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, - 1};
	public static int[] sp1 = {3,2,1,0}; //'\'만날 때 바뀌는 방향
	public static int[] sp2 = {1,0,3,2}; //'/'만날 때 바뀌는 방향
	public static char[] dirChar = {'U', 'R', 'D', 'L'};
	public static char[][] maps;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static int findPath(int startDir) {
		Queue<Raiser> q = new LinkedList<>();
		q.add(new Raiser(pr, pc, startDir));
		boolean[][][] visit = new boolean[n+2][m+2][4];
		int cnt = 1;
		while(!q.isEmpty()) {
			Raiser cur = q.poll();
			int xx = cur.x + dx[cur.dir];
			int yy = cur.y + dy[cur.dir];
			int dir = cur.dir;
			if(maps[xx][yy] == 'C') break;
			if(visit[xx][yy][dir]) return 0;
			visit[xx][yy][dir] = true;
			if(maps[xx][yy] == '\\') {
				dir = sp1[dir];
			}
			else if(maps[xx][yy] == '/'){
				dir = sp2[dir];
			}
			++cnt;
			q.add(new Raiser(xx, yy, dir));
		}
		return cnt;
	}
	
	public static void solve(){
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//행 크기
			m = stoi(st.nextToken());//열 크기
			maps = new char[n+2][m+2];
			for(int i=0; i<=n+1; i++) {
				Arrays.fill(maps[i], 'C');
			}
			for(int i=1; i<=n; i++) {
				char[] tmp= br.readLine().toCharArray();
				for(int j=1; j<=m; j++) {
						maps[i][j] = tmp[j-1];
				}
			}
			st = new StringTokenizer(br.readLine());
			pr = stoi(st.nextToken());//팀사선 위치
			pc = stoi(st.nextToken());//탐사선 위치

			boolean flag = true;
			int ans = -1;
			int dir = -1;
			for(int i=0; i<4; i++) {
				int p = findPath(i);
				if(p == 0) {
					dir = i;
					flag = false;
					break;
				}
				else if(p > ans) {
					ans = p;
					dir = i;
				}
			}

			if(flag) 
				bw.write(dirChar[dir]+"\n"+ans);
			else 
				bw.write(dirChar[dir]+"\n"+"Voyager");

			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

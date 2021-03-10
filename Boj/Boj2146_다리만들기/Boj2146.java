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

class Land{
	int x;
	int y;
	Land(){ this.x = 0; this.y = 0;}
	Land(int x, int y){this.x = x; this.y=y;}
}

public class Boj2146 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static int[][] maps;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] edges;
	static int[][] distances;
	static int ans;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static void distinguishLand(int lx, int ly, int landNum) {
		Queue<Land> q = new LinkedList();
		maps[lx][ly] = landNum;
		q.add(new Land(lx,ly));
		while(!q.isEmpty()) {
			Land l = q.poll();
			for(int d=0; d<4; d++) {
				int x = l.x + dx[d];
				int y = l.y + dy[d];
				if(x < 0 || y <0 || x>=n || y>=n) continue;
				if(maps[x][y] == 1) {
					maps[x][y] = landNum;
					q.add(new Land(x,y));
				}
			}
		}
	}
	static void findPath(int landNum) {
		int x, y;
		Queue<Land> q = new LinkedList();
		for(int i=0; i<n; i++) {
			Arrays.fill(distances[i], -1);
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(maps[i][j] == landNum) {
					q.add(new Land(i,j));
					distances[i][j] = 0;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Land ln = q.poll();
			for(int i=0; i<4; i++) {
				int xx = ln.x + dx[i];
				int yy = ln.y + dy[i];
				if(xx < 0 || yy < 0 || xx >= n || yy >= n) continue;
				if(maps[xx][yy] != 0 && maps[xx][yy] != landNum) {
					if(ans > distances[ln.x][ln.y]) ans = distances[ln.x][ln.y];
					return;
				}
				if(maps[xx][yy] == 0 && distances[xx][yy] == -1) {
					distances[xx][yy] = distances[ln.x][ln.y] + 1;
					q.add(new Land(xx,yy));
				}
				
			}
		}
	}
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		try {
			n = stoi(br.readLine());
			maps = new int[n][n];
			edges = new boolean[n][n];
			distances = new int[n][n];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					maps[i][j] = stoi(st.nextToken());
				}
			}
			
			int landNum = 2;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(maps[i][j] == 1) {
						distinguishLand(i,j,landNum);
						++landNum;
					}
				}
			}

			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					bw.write(maps[j][k]+" ");
				}
				bw.write("\n");
			}
			ans = Integer.MAX_VALUE;
			for(int i=2; i<landNum; i++) {
				findPath(i);
			}
			
			bw.write(ans+"");
			
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

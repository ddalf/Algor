# 2146_다리 만들기

Algorithms: bfs

Date: 2021/03/10

Level: G3, ☆

Link: https://www.acmicpc.net/problem/2146

### 문제정리

- 한 섬과 다른 섬을 잇는 다리 하나만 만듬. 그 다리 가장 짧게 함

**[입력]**

- n : 지도의 크기
- n줄 n개의 숫자. 0은 바다, 1은 육지

**[출력]**

- 가장 짧은 다리의 길이

### 문제풀이

1. 각각의 섬들 구분(bfs) ⇒ O(n^2)
2. 각 섬에서 다른 섬으로 가는 최소거리(bfs) 구함

 

```java
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

public class Main {
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
		q.add(new Land(lx,ly));
		maps[lx][ly] = landNum;
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
	public static void main(String[] args) {
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
```
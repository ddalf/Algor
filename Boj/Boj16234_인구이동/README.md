# 16234_인구이동

Algorithms: bfs

Date: 2021/03/28

Level: G5, ○

Link: https://www.acmicpc.net/problem/16234

### 문제정리

- N*N크기 땅. 1*1개 칸으로 나누어짐
- 각 땅에는 나라가 하나씩 존재
- r행 c열에 있는 나라에서는 A[r][c]명 살고 있다.
- 인접한 나라 사이에는 국경선 존재
- 인구 이동
    - 국경선 공유하는 나라 인구 차 L이상 R 이하 ⇒ 국경선 열림
    - 국경선 모두 열렸다면 인구 이동 시작
    - 국경선 열려있어 인접한 칸만을 이요해 이동 가능, 그 나라를 오늘 하루 동안 연합이라고 한다
    - 연합 이루고 있는 각 칸의 인구수 = 연합 인구수 / 연합 이루고 있는 칸의 개수
    - 연합 해체 & 국경선 닫음

**[입력]**

- N : 1~50
- L, R : 1~100
- N개의 줄에 각 나라의 인구수
- 인구 이동 횟수 2000번 보다 작거나 같은 입력만 주어짐

**[출력]**

- 인구 이동이 몇 번 발생하는지

### 문제풀이

- BFS
    - A[r][c]에 대해 연합 이룰 수 있는 나라 모두 탐색 : findUnion()
        - 오늘 이미 연합 이루어진 나라 ⇒ 탐색 X

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Pair {
	int x;
	int y;
	
	Pair(){
		this.x = 0;
		this.y = 0;
	}
	
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
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

	
	static int movePopulation() {
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

	public static void main(String[] args) {
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
```
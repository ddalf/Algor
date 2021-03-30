# 1941_소문난 칠공주

Algorithms: bfs, dfs

Date: 2021/03/30

Level: G3, △

Link : https://www.acmicpc.net/problem/1941

### 문제정리

- 5*5 격자형태로 여학생 위치
- 칠공주 결성
    - 7명으로 구성
    - 자리 반드시 가로, 세로로 인접
    - '이다솜파' 적어도 4명 이상 포함

**[입력]**

- S : 이다솜파
- Y : 임도연파
- S,Y 값으로 5*5행렬 공백 없이 입력

**[출력]**

- 소문난 칠공주 결성할 수 있는 경우의 수

### 문제풀이

- DFS로 조합 구함 + BFS로 인접한지 확인

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
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

	
	public static void main(String[] args) {
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
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
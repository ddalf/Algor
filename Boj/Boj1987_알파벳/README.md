# 1987_알파벳

Algorithms: dfs

Date: 2021/03/27

Level: G4, ○

Link: https://www.acmicpc.net/problem/1987

### 문제정리

- R, C 보드
- 알파벳 적혀 있고, 좌측 상단에는 말 놓여 있다.
- 말 : 상하좌우 인접한 네 칸 중 한 칸으로 이동
- 새로 이동한 칸에 적혀 있는 알파벳 ⇒ 지금까지 지나온 모든 칸에 적혀 있는 알파벳과 달라야 함.
- 좌측 상단 시작 ⇒ 말이 최대 몇 칸을 지날 수 있는지 구하는 프로그램

**[입력]**

- R, C : 1~20
- R개줄에 걸쳐 보드에 적혀 있는 C개 문자

**[출력]**

- 말이 지날 수 있는 최대 칸 수

### 문제풀이

- DFS
    - 재귀로 backtracking 통해 지나온 길에 같은 알파벳 나왔는지 체크하면서 탐색

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int r,c;
	static char[][] board;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[] visitAlpha;
	static int ans = 0;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void findPath(int x, int y, int cnt) {
		ans = Math.max(ans,  cnt);
		for(int i=0; i<4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx < 0 || yy < 0 || xx >= r || yy >=c) continue;
			if(visitAlpha[board[xx][yy]-'A']) continue;
			visitAlpha[board[xx][yy]-'A'] = true;
			findPath(xx, yy, cnt+1);
			visitAlpha[board[xx][yy]-'A'] = false;
		}
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());//보드 행 크기
			c = stoi(st.nextToken());//보드 열 크기
			board = new char[r][c];
			visitAlpha = new boolean[26];//알파벳 체크
			for(int i=0; i<r; i++) {
				board[i] = br.readLine().toCharArray();
			}
			visitAlpha[board[0][0]-'A'] = true;
			findPath(0,0,1);
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
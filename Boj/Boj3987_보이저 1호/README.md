# 3987_보이저1호

Algorithms: simulation
Date: 2021/03/26
Level: S2
Link: https://www.acmicpc.net/problem/3987

### 문제정리

- 항성계 : N*M개 직사각형
- 행성, 블랙홀, 비어있을 수 있다.
- 인접한 4칸(위, 아래, 오른쪽, 왼쪽) 중 하나 골라서 시그널 보냄
- 시그널 항상 일직선으로 전파되어, 행성 만났을 경우 전파되는 방향 90도로 바뀌게 됨
- 탐사선 어느 방향으로 시그널 보내면, 시그널이 항성계 내부에 있는 시간이 최대가 되는지

**[입력]**

- N, M : 항상계 크기
- '/ ' , '\' : 행성
- 'C' : 블랙홀
- '.' : 빈 칸

**[출력]**

- 시그널 보내는 방향(U,R,D,L)
    - 방향 여러 가지 존재, U,R,D,L 순서 중 앞서는 것 출력
- 가장 긴 시간 출력.
    - 항성계 내에서 무한히 전파 ⇒ "Voyager" 출력

### 문제풀이

- 시뮬레이션
    - U, R, D, L 각각을 0,1,2,3 으로 정의. 순서대로 넣어서 출력 순서 따로 고려하지 않도록 함.
        - '\' 만났을 때 각 방향이 바뀌는 방향 sp1[i]에 저장
        - '/' 만났을 때 각 방향이 바뀌는 방향 sp2[i]에 저장
        - 각 방향에 대한 문자 dirChar에 저장
    - maps[n+2][m+2] : maps배열에 행성계 정보가 저장된다. 이때, 항성계 빠져나가는 경우, 블랙홀 만났을 경우 동일하게 경로 찾기가 종료되므로, maps를 'C'로 둘러싸준다.
    - 4가지 방향에 대해서 모두 탐색 - 각 방향에 대해 findPath()함수 호출
    - findPath(int startDir)함수 : 탐사선 시작 위치에서 startDir 방향 따라 경로 탐색
        - visit[r][c][dir] : 싸이클 생길 경우 고려.  (r,c)번째에 dir방향으로 들른적이 있다면 싸이클 존재하므로, 0을 return해준다

            ⇒ 방향 반드시 고려해 주어야  함 ★★★★★(틀렸던 부분!)

            아래 그림과 같이, 방문했던 곳이라도 다른 방향으로 방문할 경우 싸이클 존재 하지 않음

            ![image](https://user-images.githubusercontent.com/42609000/112584930-2ed7ab00-8e3c-11eb-8b2e-a2096d67a3dc.png)

        - 다음 방문하려는 곳이 행성인지, 블랙홀인지, 빈칸인지 따라 멈추거나 방향 바꾼다.

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

public class Main {
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
	
	public static void main(String[] args) {
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
				if(p == 0) {//싸이클 존재할 경우 for문 종료
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
				bw.write(dirChar[dir]+"\n"+ans);//싸이클 존재하지 않을 경우
			else 
				bw.write(dirChar[dir]+"\n"+"Voyager");//싸이클 존재할 경우

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
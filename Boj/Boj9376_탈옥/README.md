# 9376_탈옥

Algorithms: 다익스트라

Date: 2021/03/16

Level: P5, △

Link: https://www.acmicpc.net/problem/9376

### 문제정리

- 죄수 두 명 탈옥시켜야 함
- 평면도 - 벽, 문, 죄수 위치
- 죄수 두 명 탈옥시키기 위해 열어야 하는 문의 개수

**[입력]**

- 테스트 케이스 개수(~100)
- 평면도 높이 h, 너비 w(2~100)
- h개 줄 - 평면도 정보
- 빈 공간 : '.'
- 벽 : '*'
- 문:'#'
- 죄수 위치 : '$'

**[출력]**

- 각 테스트 케이스마다 두 죄수 탈옥시키기 위해서 열어야 하는 문의 최솟값

### 문제풀이

- 죄수 한명이이 탈출하기 까지의 최소 경로 ⇒ 다익스트라(연 문의 개수 작은 경로가 우선순위 높도록)로 구현 하였는데, 두명의 최소 경로를 어떻게 합쳐야 할지 고려해야 했다.

    ⇒ i,j 까지 도달하면서 연 문 개수의 다음 3가지 합

    1. 출구 밖에서 시작해서 i,j 도달하는데 연 문의 개수('.'으로 둘러싸주어야 함. 두 죄수의 탈출구 다를 경우 고려)
    2. 죄수 1이 i,j 도달하는데 연 문의 개수 
    3. 죄수 2이 i,j 도달하는데 연 문의 개수

    단, 문에서 만날 경우 동시에 문을 여는 것이므로 -2해줘야 함.

    ⇒ i,j 까지 도달하면서 연 문의 총 개수의 최솟값⇒ i,j 까지 도달하면서 연 문의 총 개수의 최솟값

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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

class Triple implements Comparable<Triple>{
	int x;
	int y;
	int v;
	
	Triple(){
		this.x = 0;
		this.y = 0;
		this.v = 0;
	}
	
	Triple(int x, int y, int v){
		this.x = x;
		this.y = y;
		this.v = v;
	}

	@Override
	public int compareTo(Triple t) {
		return this.v - t.v;
	}
}

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int w,h;
	public static char[][] maps;
	public static int[][] visits;
	public static int[][] visitSum;
	public static ArrayList<Pair> prisoners;
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,-1,0,1};
	public static int ans;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void findPath(Pair p) throws IOException{
		PriorityQueue<Triple> pq = new PriorityQueue<>();
		for(int i=0; i<=h+1; i++) { 
			Arrays.fill(visits[i], -1);
		}
		pq.add(new Triple(p.x,p.y,0));
		while(!pq.isEmpty()) {
			Triple cur = pq.poll();
			if(visits[cur.x][cur.y] >= 0) continue;
			visits[cur.x][cur.y] = cur.v;
			for(int i=0; i<4; i++) {
				int xx = cur.x + dx[i];
				int yy = cur.y + dy[i];
				if(xx < 0 || yy < 0 || xx >= h+2 || yy >= w+2) continue;
				if(visits[xx][yy] >= 0 || maps[xx][yy] == '*') continue;
				int value = cur.v;
				if(maps[xx][yy] == '#') {
					++value;
				}
				pq.add(new Triple(xx,yy,value));
			}
		}
		for(int i=0; i<=h+1; i++) {
			for(int j=0; j<=w+1; j++) {
				visitSum[i][j] += visits[i][j];
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int testCase = stoi(br.readLine());
			while(testCase-- > 0) {
				st = new StringTokenizer(br.readLine());
				h = stoi(st.nextToken());
				w = stoi(st.nextToken());
				maps = new char[h+2][w+2];
				visits = new int[h+2][w+2];
				visitSum = new int[h+2][w+2];
				prisoners = new ArrayList<>();
				ans = Integer.MAX_VALUE;
				prisoners.add(new Pair(0,0));
				for(int i=0; i<=h+1; i++) {
					for(int j=0; j<=w+1; j++) {
						maps[i][j] = '.';
					}
				}
				for(int i=1; i<=h; i++) {
					char[] mapTmp = br.readLine().toCharArray();
					for(int j=1; j<=w; j++) {
						maps[i][j] = mapTmp[j-1];
						if(maps[i][j] == '$') {
							prisoners.add(new Pair(i,j));
						}
					}
				}
				for(int i=0; i<3; i++) {
					findPath(prisoners.get(i));
				}
				ans = Integer.MAX_VALUE;
				for(int i=0; i<=h+1; i++) {
					for(int j=0; j<=w+1; j++) {
						if(maps[i][j] == '*'||visitSum[i][j] < 0) continue;
						if(maps[i][j] == '#') visitSum[i][j] -= 2;
						ans = Math.min(ans, visitSum[i][j]);
					}
				}
				bw.write(ans+"\n");
			}
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
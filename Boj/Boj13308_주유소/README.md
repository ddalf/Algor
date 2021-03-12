# 13308_주유소

Algorithms: dijkstra

Date: 2021/03/10

Level: G1, △

Link: https://www.acmicpc.net/problem/13308

### 문제정리

- N개의 도시. 1번부터 N번까지 번호 붙음
- 두 도시 양방향으로 연결하는 M개의 도로. 서로 길이(km) 다를 수 있음
- 1번 → N번 도시로 이동.
- 기름통 무제한
- 1km마다 1리터 기름 사용
- 각 도시마다 리터당 가격(원) 다름

**[입력]**

- N : 도시 수. 2~2500
- M : 도로 수. 1~4000
- 도시 주요소의 리터당 가격. 1~2500
- 하나의 도로에 대한 정보 : 도로가 연결하는 두 도시 번호, 도로의 길(1~2500)
- 한 쌍의 도시 연결하는 도로 최대 하나만 존재.
- 임의의 도시 → 다른 임의의 도시로 도로 이용하여 이동할 수 있는 방법 항상 존재

**[출력]**

- 1번 →> N번 도시로 가는 최소 비용

### 문제풀이

- 다익스트라
    - Priority queue 사용해서 비용이 가장 적게 드는 길 찾음
    - visit[현재도시][기름값] 으로 방문 체크해서, 현재 도시에 현재 기름값으로 방문한 적이 있는지 check
        - 똑같은 도시를 똑같은 최소 기름값을 가지고 방문하지 않도록 하여 여러번 방문하는 것 방지
    - 현재 위치에서 갈 수 있는 길들 중
        - 최소 기름 값 구함 : `지났던 도시들 중 최소 기름값` 과 `현재 도시의 기름값`을 비교
        - 다음 갈 도시에 최소 기름 값으로 방문한 적이 없다면
            - 다음 도시 가는 비용 ⇒ 최소 기름 값 * 다음 길까지의 거리
            - 다음 도시 번호, 최소 기름 값, 현재까지의 비용 + 다음도시가는 비용 을 구해서 Priority Queue에 넣어준다.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int num;
	int dis;
	Edge(){
		this.num = 0; 
		this.dis = 0;
	}
	
	Edge(int num, int dis){
		this.num = num;
		this.dis = dis;
	}
}

class Position implements Comparable<Position>{
	int num;
	int minLiter;
	long cost;
	
	Position(int num, int minLiter, long cost){
		this.num = num;
		this.minLiter = minLiter;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Position p) {
		return (int)(this.cost-p.cost);
	}
}

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;//도시 수
	static int m;//간선 수
	static int[] liters;
	static ArrayList<Edge>[] paths;
	static boolean[][] visit;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static long findPath(){
		PriorityQueue<Position> pq = new PriorityQueue<>(); 
		pq.add(new Position(1, liters[1], (long)0));
		while(!pq.isEmpty()) {
			Position curPos = pq.poll();
			if(curPos.num == n) {
				return curPos.cost;
			}
      if(visit[curPos.num][curPos.minLiter]) continue;
      visit[curPos.num][curPos.minLiter] = true;
			for(int i=0; i<paths[curPos.num].size(); i++) {
				Edge nextEdge = paths[curPos.num].get(i);
				int minL = Math.min(curPos.minLiter, liters[curPos.num]);
				if(visit[nextEdge.num][minL]) continue;
				long cost = curPos.cost + (long)nextEdge.dis * (long)minL;
				pq.add(new Position(nextEdge.num, minL, cost));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			liters = new int[n+1];
			paths = new ArrayList[n+1];
			visit = new boolean[n+1][2510];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) { //도시 주유소 리터당 가격
				liters[i] = stoi(st.nextToken());
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int dis = stoi(st.nextToken());
				if(paths[a] == null) paths[a] = new ArrayList();
				if(paths[b] == null) paths[b] = new ArrayList();
				paths[a].add(new Edge(b, dis));
				paths[b].add(new Edge(a, dis));
			}
			bw.write(findPath()+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

- 시간초과 나는 코드
    - visit 체크를 for문 안에서 함

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int num;
	int dis;
	Edge(){
		this.num = 0; 
		this.dis = 0;
	}
	
	Edge(int num, int dis){
		this.num = num;
		this.dis = dis;
	}
}

class Position implements Comparable<Position>{
	int num;
	int minLiter;
	long cost;
	
	Position(int num, int minLiter, long cost){
		this.num = num;
		this.minLiter = minLiter;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Position p) {
		return (int)(this.cost-p.cost);
	}
}

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;//도시 수
	static int m;//간선 수
	static int[] liters;
	static ArrayList<Edge>[] paths;
	static boolean[][] visit;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static long findPath(){
		PriorityQueue<Position> pq = new PriorityQueue<>(); 
		pq.add(new Position(1, liters[1], (long)0));
		while(!pq.isEmpty()) {
			Position curPos = pq.poll();
			if(curPos.num == n) {
				return curPos.cost;
			}
      if(visit[curPos.num][curPos.minLiter]) continue;
			for(int i=0; i<paths[curPos.num].size(); i++) {
				Edge nextEdge = paths[curPos.num].get(i);
				int minL = Math.min(curPos.minLiter, liters[curPos.num]);
				if(visit[nextEdge.num][minL]) continue;
				long cost = curPos.cost + (long)nextEdge.dis * (long)minL;
				visit[curPos.num][minL] = true;
				pq.add(new Position(nextEdge.num, minL, cost));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			liters = new int[n+1];
			paths = new ArrayList[n+1];
			visit = new boolean[n+1][2510];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) { //도시 주유소 리터당 가격
				liters[i] = stoi(st.nextToken());
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int dis = stoi(st.nextToken());
				if(paths[a] == null) paths[a] = new ArrayList();
				if(paths[b] == null) paths[b] = new ArrayList();
				paths[a].add(new Edge(b, dis));
				paths[b].add(new Edge(a, dis));
			}
			bw.write(findPath()+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
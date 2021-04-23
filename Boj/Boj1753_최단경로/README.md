# 1753_최단경로

Algorithms: dijkstra

Date: 2021/04/21

Level: G5, ○

Link: https://www.acmicpc.net/problem/1753

### 문제정리

- 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램
- 모든 간선의 가중치는 10 이하의 자연수

**[입력]**

- 정점의 개수 V와 간선의 개수 E(1≤V≤20,000, 1≤E≤300,000)
- 둘째 줄에는 시작 정점의 번호 K(1≤K≤V)
- 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
    - u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다.
    - 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음

**[출력]**

- 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력
- 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력

### 문제풀이

- 다익스트라 : O(VlogE)
    - 단일 정점사이의 최소 거리 구하기
    - `adjList` : 노드간 간선정보 저장하는 인접리스트
    - `ans[i]` : 1번 정점에서 i번 정점 도달하는 최소 거리 저장
        - INF값으로 초기화
    - `void dij(int stIdx)`
        - PriorityQueue사용 ⇒ 현재 최소거리로 오름차순 정렬
        - ans[xx] > ans[x] + adjList[x].get(i)[1]
            - 1번에서 xx정점 가는데 걸리는 최소거리 > 1번정점에서 x정점 가는데 걸리는 최소거리 + x에서 xx가는 거리
            - 이 경우에만 거리 갱신 시켜줌

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static List<int[]>[] adjList;
	static int[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static class Dis implements Comparable<Dis>{
		int x;
		int cost;
		Dis(int x, int cost){
			this.x = x;
			this.cost =cost;
		}
		@Override
		public int compareTo(Dis o) {
			return this.cost - o.cost;
		}
	}
	static void dij(int stIdx) throws IOException {
		PriorityQueue<Dis> pq = new PriorityQueue<>();
		pq.add(new Dis(stIdx, 0));
		ans[stIdx] = 0;
		while(!pq.isEmpty()) {
			int x = pq.peek().x;
			int cost = pq.peek().cost;
			pq.poll();
			for(int i=0; i<adjList[x].size(); i++) {
				int xx = adjList[x].get(i)[0];
				int cc = ans[x] + adjList[x].get(i)[1];
				if(ans[xx] > cc) {
					pq.add(new Dis(xx, cc));
					ans[xx] = cc;
				}
			}
		}
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			adjList = new ArrayList[v];
			ans = new int[v];
			Arrays.fill(ans, Integer.MAX_VALUE);
			for(int i=0; i<v; i++) {
				adjList[i] = new ArrayList<>();
			}
			int stIdx = stoi(br.readLine())-1;
			
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				int cost = stoi(st.nextToken());
				adjList[a].add(new int[] {b,cost});
			}
			
			dij(stIdx);
			
			for(int i=0; i<v; i++) {
				if(i == stIdx) bw.write("0\n");
				else if(ans[i] == Integer.MAX_VALUE) bw.write("INF\n");
				else bw.write(ans[i]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
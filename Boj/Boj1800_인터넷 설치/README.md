# 1800_인터넷 설치

Algorithms: binary search, dijkstra

Date: 2021/04/24

Level: G2, ☆

Link: https://www.acmicpc.net/problem/1800

### 문제정리

- 각각의 학생들의 번호가 1부터 N까지
- P(P<=10,000)개의 쌍만이 서로 이어 질수 있음. 서로 선을 연결하는데 가격이 다르다.
- 1번은 다행히 인터넷 서버와 바로 연결되어 있어 인터넷이 가능하다. 우리의 목표는 N번 컴퓨터가 인터넷에 연결하는 것이다.
- 나머지 컴퓨터는 연결 되어 있거나 연결 안되어 있어도 무방하다.
- K개의 인터넷 선에 대해서는 공짜로 연결
- 나머지 인터넷 선에 대해서는 남은 것 중 제일 가격이 비싼 것에 대해서만 가격을 받기로 하였다.

**[입력]**

- n : 학생 수. 1~1000
- p : 케이블 수. 1~10000
- k : 공짜로 제공하는 케이블 수. 1~n
- 다음 P개의 줄에는 케이블이 연결하는 두 컴퓨터 번호와 그 가격이 차례로 들어온다. 가격은 1 이상 1,000,000 이하

**[출력]**

- 원장선생님이 내게 되는 최소의 돈
- 1번과 N번 컴퓨터를 잇는 것이 불가능 하다면 -1을 출력

### 문제풀이

- 이분 탐색 + 다익스트라
    - 이분 탐색으로 최소의 비용 구함
        - `mid` : 현재 최소 비용
        - `isPossibleCost(minCost)`(

            : 다익스트라로 1부터 n까지 가면서 minCost 초과로 사용한 간선 개수 최소화 하면서 가는 길 찾음

            - `dis[i]` : 1부터 i까지 도달하는데 minCost 초과로 사용한 간선의 개수 저장
            - dis[n]  ≤ k일 경우
                - 1에서 n도달하는데 k개 이하로 사용했을 때 ⇒ minCost로 도달 가능한 경우
                - k개 초과로 사용 : 도달 불가능

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static List<int[]>[] adjList;
	static int n,k,p;
	static int[] dis;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static class Cable implements Comparable<Cable>{
		int x;
		int cost;
		Cable(int x, int cost){
			this.x = x;
			this.cost = cost;
		}
		public int compareTo(Cable c) {
			return this.cost - c.cost;
		}
	}
	
	static boolean isPossibleCost(int maxCost) {
		Arrays.fill(dis,  Integer.MAX_VALUE);		
		PriorityQueue<Cable> pq = new PriorityQueue<>();
		pq.add(new Cable(1, 0));
		dis[1] = 0;
		int kLeft = k;
		while(!pq.isEmpty()) {
			Cable cur = pq.poll();
			if(dis[cur.x] < cur.cost) continue;
			for(int[] edge : adjList[cur.x]) {
				int xx = edge[0];
				int nCost = edge[1] <= maxCost ? 0 : 1;//maxCost넘으면 가중치 1 아니면 0
				if(dis[xx] > cur.cost + nCost) {
					dis[xx] = cur.cost + nCost;
					pq.add(new Cable(xx, cur.cost + nCost));
				}
			}
		}
		return dis[n] <= k;
	}
	
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//학생 수
			p = stoi(st.nextToken());//케이블선 수
			k = stoi(st.nextToken());//공짜 제공 케이블 수
			adjList = new ArrayList[n+1];
            dis = new int[n+1];
			for(int i=1; i<=n; i++) {
				adjList[i] = new ArrayList<>();
			}
			for(int i=0; i<p; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				adjList[a].add(new int[] {b,cost});
				adjList[b].add(new int[] {a,cost});
			}
			int ans = -1;
			int left = 0, right = Integer.MAX_VALUE;
			while(left <= right) {
				int mid = (left + right) / 2;
				//간선 가중치 mid값 이하로 써서 N까지 도달할 수 있는지 탐색
				if(isPossibleCost(mid)) {//도달할 수 있는 경우 더 작은 가중치로 도달할 수 있는지 탐색 => mid값 감소
					ans = mid;
					right = mid - 1;
				}
				else left = mid + 1;//도달할 수 없는 경우 더 큰 가중치로는 도달할 수 있는지 탐색 => mid값 증가
			}
			
			// 원장선생님이 내게 되는 최소의 돈을 출력한다. 만약 1번과 N번 컴퓨터를 잇는 것이 불가능 하다면 -1을 출력
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
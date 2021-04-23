# 11657_타임머신

Algorithms: 벨만-포드

Date: 2021/04/23

Level: G4, ○

Link: https://www.acmicpc.net/problem/11657

### 문제정리

- N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다.
- 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다.
- 시간 C가 양수가 아닌 경우가 있다.
    - C = 0인 경우는 순간 이동을 하는 경우
    - C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우

**[입력]**

- 도시의 개수 N (1 ≤ N ≤ 500)
- 버스 노선의 개수 M (1 ≤ M ≤ 6,000)
- 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)

**[출력]**

- 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력
- 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력
- 해당 도시로 가는 경로가 없다면 대신 -1을 출력

### 문제풀이

- 벨만-포드 : O(VE)
    - `long costs[i]`: 1에서 i번째 노드 가는데 걸리는 최소 시간
        - INF값으로 초기화
    - `cycle` (edge-relaxation) v-1번 돌고, v번째에서 바뀌는 것 있다면 negative cycle
        - 각 사이클 마다 모든 경로 탐색
            - costs[i] == Long.MAX_VALUE일 경우 1에서 i번 가는 경로가 존재하지 않음
            - costs[curEdge] > costs[i]+cost일 경우 costs[curEdge]갱신
                - 1에서 curEdge도착하는 최소 거리 > 1에서 i도착하는 최소 거리 + i에서 curEdge 사이 거리

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());//도시 개수(노드 개수. 1~500)
			int e = stoi(st.nextToken());//버스 노선 개수(간선 개수. 1~6000)
			List<int[]>[] edges = new ArrayList[v+1];
			long costs[] = new long[v+1];//1에서 i번째 노드 가는데 걸리는 최소 시간
			for(int i=1; i<=v; i++) {
				edges[i] = new ArrayList<>();
			}
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				edges[a].add(new int[] {b,cost});
			}
			Arrays.fill(costs, Long.MAX_VALUE);
			costs[1] = 0;
			boolean flag = false;//negative cycle일 경우 true로 바뀜
			for(int cycle = 1; cycle <=v; cycle++) {//edge-relaxation v-1번 돌고, v번째에서 바뀌는 것 있다면 negative cycle
				for(int i=1; i<=v; i++) {
					if(flag) break;//negative cycle
					 for(int j=0; j<edges[i].size(); j++) {
						 int curEdge = edges[i].get(j)[0];
						 int cost = edges[i].get(j)[1];
						 if(costs[i] != Long.MAX_VALUE && costs[curEdge] > costs[i]+cost) {
							 if(cycle == v) flag = true;
							 costs[curEdge] = costs[i] + cost;
						 }
					 }
				}
			}
			if(flag) bw.write("-1");
			else {
				for(int i=2; i<=v; i++) {
					if(costs[i] == Long.MAX_VALUE) bw.write("-1\n");
					else bw.write(costs[i]+"\n");
				}
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
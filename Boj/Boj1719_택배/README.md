# 1719_택배

Algorithms: dijkstra

Date: 2021/04/21

Level: G4, ○

Link: https://www.acmicpc.net/problem/1719

### 문제정리

- 한 집하장에서 다른 집하장으로 최단경로로 화물을 이동시키기 위해 가장 먼저 거쳐야 하는 집하장

**[입력]**

- n : 집하장의 개수.  1~200
- m : 집하장간 경로의 개수. 1~10000
- 집하장간 경로
    - 두 집하장의 번호와 그 사이를 오가는데 필요한 시간
    - 집하장의 번호들과 경로의 소요시간 1~1000

**[출력]**

- 최단경로로 화물을 이동시키기 위해 가장 먼저 거쳐야 하는 집하장

### 문제풀이

- 다익스트라
    - `int[][] ans` : i번째 집하장에서 j번째 집하장 도착하기 위해 가장 먼저 거쳐야 하는 집하장
        - 다익스트라 돌면서 최단 경로가 갱신될 경우
            - 다른 노드 거치지 않고 바로 연결되는 경우(`ans[stIdx][curDis.x] == 0`) : stIdx번 집하장에서 xx번 집하장 가는데 가장 먼저 거쳐야 하는 집하장 = xx
                - `ans[stIdx][xx] = xx`
            - 다른 노드 거칠 경우 : stIdx번 집하장에서 xx번 집하장 가는데 가장 먼저 거쳐야 하는 집하장 =: stIdx번 집하장에서 x번 집하장 가는데 가장 먼저 거친 집하장
                - `ans[stIdx][xx] = ans[stIdx][curDis.x]`

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] ans;
	static List<Dis>[] adjList;
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
		@Override
		public String toString() {
			return this.x+" "+this.cost+"\n";
		}
	}
	static void dij(int stIdx, int n) throws IOException{
		PriorityQueue<Dis> pq = new PriorityQueue<>();
		int[] dis = new int[n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		pq.add(new Dis(stIdx,0));
		dis[stIdx] = 0;
		while(!pq.isEmpty()) {
			Dis curDis = pq.poll();
			for(int i=0; i<adjList[curDis.x].size(); i++) {
				int xx = adjList[curDis.x].get(i).x;
				int cc = adjList[curDis.x].get(i).cost + dis[curDis.x];
				if(dis[xx] > cc) {
					dis[xx] = cc;
					pq.add(new Dis(xx,cc));
					if(ans[stIdx][curDis.x] == 0) ans[stIdx][xx] = xx;
					else ans[stIdx][xx] = ans[stIdx][curDis.x];
				}
			}
		}
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());//집하장의 개수. 1~200
			int m = stoi(st.nextToken());//집하장간 경로의 개수. 1~10000
			ans = new int[n+1][n+1];
			adjList = new ArrayList[n+1];
			for(int i=0; i<=n; i++) {
				adjList[i] = new ArrayList<>();
			}
			//집하장 간 경로
			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				adjList[a].add(new Dis(b, cost));
				adjList[b].add(new Dis(a, cost));
			}
			for(int i=1; i<=n; i++) {
				dij(i, n);
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(ans[i][j] == 0)bw.write("- ");
					else bw.write(ans[i][j]+" ");
				}
				bw.newLine();
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
# 1197_최소 스패닝 트리

Algorithms: mst

Date: 2021/04/23

Level: G4, ○

Link: https://www.acmicpc.net/problem/1197

### 문제정리

- 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리

**[입력]**

- 정점의 개수 V(1 ≤ V ≤ 10,000)
- 간선의 개수 E(1 ≤ E ≤ 100,000)
- 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C
    - A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다
    - C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
- 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
- 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

**[출력]**

### 문제풀이

- 최소 스패닝 트리 : Kruskal. O(ElogE)
    - 간선의 가중치를 오름차순으로 정렬
    - 정렬된 간선을 탐색하면서 cycle만들지 않는 경우 tree에 추가

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] tree;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int cost;
		Edge(int a, int b, int cost){
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}
	
	static int findRoot(int x) {
		if(tree[x] == x) return x;
		return tree[x] = findRoot(tree[x]);
	}
	
	static boolean merge(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if(aRoot == bRoot) return false;
		tree[aRoot] = bRoot;
		return true;
	}
	
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			
			tree = new int[v+1];
			for(int i=0; i<=v; i++) {
				tree[i] = i;
			}
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				pq.add(new Edge(a,b,cost));
			}
			
			long ans = 0;
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(merge(cur.a, cur.b)) {
					ans += (long)cur.cost;
				}
			}
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
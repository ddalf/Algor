# 1939_중량제한

Algorithms: spanning tree

Date: 2021/05/15

Level: G4, ○

Link: https://www.acmicpc.net/problem/1939

### 문제정리

- 물품을 생산하다 보면 공장에서 다른 공장으로 생산 중이던 물품을 수송해야 할 일이 생기곤 한다. 그런데 각각의 다리마다 중량제한이 있기 때문에 무턱대고 물품을 옮길 순 없다. 만약 중량제한을 초과하는 양의 물품이 다리를 지나게 되면 다리가 무너지게 된다.
- 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램을 작성하시오.

**[입력]**

- 첫째 줄에 N, M(1≤M≤100,000)이 주어진다. 다음 M개의 줄에는 다리에 대한 정보를 나타내는 세 정수 A, B(1≤A, B≤N), C(1≤C≤1,000,000,000)가 주어진다. 이는 A번 섬과 B번 섬 사이에 중량제한이 C인 다리가 존재한다는 의미이다.
- 서로 같은 두 도시 사이에 여러 개의 다리가 있을 수도 있으며, 모든 다리는 양방향이다. 마지막 줄에는 공장이 위치해 있는 섬의 번호를 나타내는 서로 다른 두 정수가 주어진다. 공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다.

**[출력]**

- 물품들의 중량의 최댓값

### 문제풀이

- 스패닝 트리 - 크루스칼 알고리즘
    - 최대로 트리 잇다가 start와 end점 마지막에 이어질 때 = 최대로 이어질 수 있는 중량

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] trees;
	public static class Node implements Comparable<Node>{
		int v, u,cost;
		Node(int v, int u, int cost){
			this.v = v;
			this.u = u;
			this.cost = cost;
		}
		public int compareTo(Node n) {
			return n.cost - this.cost;
		}
	}
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static int find(int x) {
		if(trees[x] == x) return x;
		else return trees[x] = find(trees[x]);
	}
	public static void merge(int u, int v) {
		int a = find(u);
		int b = find(v);
		trees[a] = b;
	}
	
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			int m = stoi(st.nextToken());
			trees = new int[n+1];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int c = stoi(st.nextToken());
				pq.add(new Node(a,b,c));
			}
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken());
			int end = stoi(st.nextToken());
			
			for(int i=1; i<=n; i++) trees[i] = i;
			int answer = 0;
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				merge(cur.u, cur.v);
				if(find(start) == find(end)) {
					answer = cur.cost;
					break;
				}
			}
			bw.write(answer+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
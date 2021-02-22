# 1967_트리의 지름

Algorithms: bfs, tree

Date: 2021/02/22

Level: G4, ☆

Link: https://www.acmicpc.net/problem/1967

### 문제정리

- 트리 : 사이클 X 무방향 그래프. 경로 하나만 존재
- 트리의 지름 : 어떤 두 노드 선택해서 양쪽으로 당길 때 가장 길게 늘어나는 경우.
- 트리의 모든 노드 -> 두 노드 지름의 끝 점으로 하는 원 안에 들어감
- 루트가 있는 트리 가중치 있는 간선들로 줌 -> 트리의 지름 구해서 출력

**[입력]**

- n : 노드의 개수
- 간선 정보 : 세 개의 정수. 부모 노드 / 자식 노드 / 가중치
    - 부모 노드의 번호 작은 것 먼저 입력
    - 부모 노드 번호 같음 -> 자식 노드 번호 작은 것 먼저 입력
    - 루트 노드 : 항상 1.
    - 간선 가중치 : 1~100

**[출력]**

- 트리의 지름 출력

### 문제풀이

1. Root 에서 가장 먼 노드 찾음(End Node)
2. 1에서 찾은 노드에서 가장 먼 거리
- 오류 1. 트리 노드 개수 1개일 때 고려 X
- 오류 2. bfs함수(`getTreedia()`) 에서 맨 처음 시작 노드 방문 초기화 안해줌(`visited[stIdx] = true;`)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int num, weight;
	Node(){
		this.num = 0;
		this.weight = 0;
	}
	
	Node(int num, int weight){
		this.num = num;
		this.weight = weight;
	}
}
public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int nodeNum;
	static ArrayList<Node>[] tree;
	static boolean[] visited;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static Node getTreeDia(int stIdx) { 	
		Node maxDisNode = new Node(0,0);
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(stIdx, 0));
		visited[stIdx] = true;
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			for(int i=0; i<tree[curNode.num].size(); i++) {
				int nextIdx = tree[curNode.num].get(i).num;
				if(!visited[nextIdx]) {
					int nextWeight = curNode.weight + tree[curNode.num].get(i).weight;
					q.add(new Node(nextIdx, nextWeight));
					visited[nextIdx] = true;
					if(maxDisNode.weight < nextWeight) {
						maxDisNode.num = nextIdx;
						maxDisNode.weight = nextWeight;
					}
				}
			}
		}
		return maxDisNode;
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			nodeNum = stoi(br.readLine());
			if(nodeNum == 1) {
				bw.write("0");
			}
			else {
				tree= new ArrayList[nodeNum+1];
				visited = new boolean[nodeNum+1];
				for(int i=1; i<=nodeNum; i++) {
					tree[i] = new ArrayList<>();
				}
				for(int i=1; i<nodeNum; i++) {
					st = new StringTokenizer(br.readLine());
					int parent = stoi(st.nextToken());
					int child = stoi(st.nextToken());
					int cost = stoi(st.nextToken());
					tree[parent].add(new Node(child, cost));
					tree[child].add(new Node(parent, cost));
				}
				//1. 루트에서 가장 먼 거리에 있는 노드
				Arrays.fill(visited, false);
				int maxDisIdx = getTreeDia(1).num;
				//2. 1번에서 찾은 노드에서 가장 먼 거리에 있는 노드
				Arrays.fill(visited, false);
				int maxWeight = getTreeDia(maxDisIdx).weight;
				
				bw.write(maxWeight+"");
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
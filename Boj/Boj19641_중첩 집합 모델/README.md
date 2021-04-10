# 19641_중첩 집합 모델

Algorithms: dfs, graph, tree

Date: 2021/04/10

Level: G5, ○

Link: https://www.acmicpc.net/problem/19641

### 문제정리

- 중첩 집합 모델 : 구간의 포함관계를 이용해서 계층적인 구조를 나타내는 방식이며, 각각의 데이터는 서로 겹치지 않거나 한 데이터가 다른 데이터를 포함하는 관계를 가짐
- `각 노드의 left, right 필드에 트리의 방문 순서로 번호를 매겨서 left, right 필드가 구간을 나타내도록 한다.
- 이 때, 각 노드의 left 필드와 right 필드는 left < right임이 보장
- 중첩 집합 모델을 구성하는 A 노드와 B 노드가 있을 때, A.left < B.left이고 B.right < A.right이면 A는 B를 포함한다

**[입력]**

- N : 트리를 구성하는 정점의 개수  (2~10,000)
- N + 1번째 줄까지는 정점에 연결된 간선에 대한 정보
    - 각 줄의 처음에는 간선이 연결될 정점의 번호 Vi (1 ≤ Vi ≤ N)가 주어지며, -1을 입력받기 전까지는 해당 노드에서 연결된 모든 노드에 대한 정보가 주어진다.
- N + 2 번째 줄에는 루트 노드의 역할을 하게 될 정점의 번호 S
- 양방향 그래프의 입력임이 보장

**[출력]**

- S번 노드가 루트 노드일 때, 번호가 가장 낮은 노드부터 오름차순으로 방문해서 중첩 집합을 구성했을 때, 각 노드의 번호 left 필드와 right 필드를 출력

### 문제풀이

- DFS
    - List 타입 배열을 통해 각 노드에 연결되는 노드 `tree`배열에 저장
    - `int dfs(int idx, int num)` 함수
        - idx : 현재 노드 번호 / num : 필드 값
        - 현재 노드의 방문 체크 한후, `num`을 자신의 `left`값으로 가짐
        - 이어진 모든 노드 방문.
            - 방문된 노드의 `right`값을 받아 다음 이어진 노드의 `num`으로 넘겨줌
        - 이어진 모든 노드 방문이 끝난 후, 최종적으로 return된 `right`값에 + 1된 값을 자신의 `right`값으로 가짐

```java
import java.util.*;
import java.io.*;
class Ver{
	int left;
	int right;
	Ver(){
		this.left = 0;
		this.right = 0;
	}
	Ver(int left, int right){
		this.left = left;
		this.right = right;
	}
}
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;//트리 정점 개수 2~10,000
	static boolean[] visit;
	static List<Integer>[] tree;
	static Ver[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static int dfs(int idx, int num)throws IOException {//idx : 현재 노드 번호 / num : 필드 값
		visit[idx] = true;
		ans[idx] = new Ver();
		ans[idx].left = num;
		int right = num;
		for(int i=0; i< tree[idx].size(); i++) {//이어진 노드들 모두 방문(오름차순 순)
			int nextIdx = tree[idx].get(i);
			if(visit[nextIdx]) continue;
			right = dfs(nextIdx, right+1);
		}
		ans[idx].right = right+1;
		return ans[idx].right;
	}
	
	public static void main(String[] args) {
		try {
			n = stoi(br.readLine());
			tree = new ArrayList[n+1];
			ans = new Ver[n+1];
			visit = new boolean[n+1];
			
			StringTokenizer st;
			for(int i=1; i<=n; i++) {
				tree[i] = new ArrayList<>();
			}
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int node = stoi(st.nextToken());
				while(st.hasMoreTokens()) {
					int nNode = stoi(st.nextToken());
//					bw.write(nNode+"\n");
					if(nNode == -1) break;
					tree[node].add(nNode);
				}
			}
			for(int i=1; i<=n; i++) {
				Collections.sort(tree[i]);
			}
			
			int root = stoi(br.readLine());
			//S번 노드가 루트 노드일 때, 번호가 가장 낮은 노드부터 오름차순으로 방문해서 중첩 집합을 구성
			dfs(root, 1);
			for(int i=1; i<=n; i++) {
				if(ans[i] == null) bw.write(i+"\n");
				else bw.write(i+" "+ans[i].left+" "+ans[i].right+"\n");
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
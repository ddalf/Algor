# 11404_플로이드

Algorithms: floyd

Date: 2021/04/23

Level: G4

Link: https://www.acmicpc.net/problem/11404

### 문제정리

- n(2 ≤ n ≤ 100)개의 도시
- 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스
- 각 버스는 한 번 사용할 때 필요한 비용이 있음
- 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값

**[입력]**

- 도시의 개수 n
- 버스의 개수 m
- 셋째 줄부터 m+2줄까지 버스의 정보
    - 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c
    - 시작 도시와 도착 도시가 같은 경우는 없다.
    - 비용은 100,000보다 작거나 같은 자연수
- 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.

**[출력]**

- n개의 줄을 출력
    - i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용
    - i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력

### 문제풀이

- 벨만 포드 : O(v^3)

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
			int nodeNum = stoi(br.readLine());
			int edgeNum = stoi(br.readLine());
			int[][] costs = new int[nodeNum][nodeNum];
			for(int i=0; i<nodeNum; i++) {
				Arrays.fill(costs[i], 987654321);
				costs[i][i] = 0;
			}
			
			StringTokenizer st;
			for(int i=0; i<edgeNum; i++) {//간선 정보
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				int cost = stoi(st.nextToken());
				costs[a][b] = Math.min(costs[a][b],cost);
			}
			
			for(int k=0; k<nodeNum; k++) {
				for(int i=0; i<nodeNum; i++) {
					for(int j=0; j<nodeNum; j++) {
						costs[i][j] = Math.min(costs[i][j], costs[i][k]+costs[k][j]);
					}
				}
			}
			
			for(int i=0; i<nodeNum; i++) {
				for(int j=0; j<nodeNum; j++) {
					if(costs[i][j] == 987654321) bw.write("0 ");
					else bw.write(costs[i][j]+" ");
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
# 1613_역사

Algorithms: floyd

Date: 2021/04/23

Level: G3, △

Link: https://www.acmicpc.net/problem/1613

### 문제정리

- 일부 사건들의 전후 관계들이 주어질 때, 주어진 사건들의 전후 관계도 알 수 있을까?

**[입력]**

- 사건의 개수 n(400 이하의 자연수)
- 사건의 전후 관계의 개수 k(50,000 이하의 자연수)
- 다음 k줄에는 전후 관계를 알고 있는 두 사건의 번호
    - 앞에 있는 번호의 사건이 뒤에 있는 번호의 사건보다 먼저 일어났음을 의미
- 사건의 전후 관계를 알고 싶은 사건 쌍의 수 s(50,000 이하의 자연수)
- 다음 s줄에는 각각 서로 다른 두 사건의 번호
    - 사건의 번호는 1보다 크거나 같고, N보다 작거나 같은 자연수

**[출력]**

- s줄에 걸쳐 물음에 답한다.
    - 앞에 있는 번호의 사건이 먼저 일어났으면 -1
    - 뒤에 있는 번호의 사건이 먼저 일어났으면 1
    - 어떤지 모르면(유추할 수 없으면) 0을 출력한다.

### 문제풀이

- 플로이드 와샬
    - `int[i][j] costs` : i와 j사이의 전후 관계
        - i가 j보다 먼저 일어난 경우 : costs[i][j] = -1;
        - i가 j보다 나중에 일어난 경우: costs[i][j] = 1;
    - `costs[i][bw] == -1 && costs[bw][j] == -1`
        - i가 bw보다 먼저 일어나고 bw가 j보다 먼저 일어난 경우 ⇒ i는 j보다 무조건 먼저 발생함
    - `costs[i][bw] == 1 && costs[bw][j] == 1`
        - i가 bw보다 나중에 일어나고 bw가 j보다 나중에 일어난 경우 ⇒ i는 j보다 무조건 나중에 발생함

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
			int n = stoi(st.nextToken());//node 수
			int k = stoi(st.nextToken());//사건 전후 관계. edge 수
			int[][] costs = new int[n][n];
			for(int i=0; i<k; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				costs[a][b] = -1;
				costs[b][a] = 1;
			}
			
			for(int bw=0; bw<n; bw++) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(costs[i][bw] == -1 && costs[bw][j] == -1) costs[i][j] = -1;
						else if(costs[i][bw] == 1 && costs[bw][j] == 1) costs[i][j] = 1;
					}
				}
			}
			
			int s = stoi(br.readLine());
			for(int i=0; i<s; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				bw.write(costs[a][b]+"\n");
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
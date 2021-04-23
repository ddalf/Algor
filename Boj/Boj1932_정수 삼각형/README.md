# 1932_정수 삼각형

Algorithms: dp

Date: 2021/04/22

Level: S1, ○

Link: https://www.acmicpc.net/problem/1932

### 문제정리

- 정수 삼각형 맨 위층부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려옴
- 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램
- 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.

**[입력]**

- 삼각형의 크기 n(1 ≤ n ≤ 500)
- 둘째 줄부터 n+1번째 줄까지 정수 삼각형

**[출력]**

- 합이 최대가 되는 경로에 있는 수의 합

### 문제풀이

- 다이나믹 프로그래밍
    - `int dp[i][j]` : i번째 줄의 j번째 위치에서 가질 수 있는 최댓값
        - dp[i][j] = 현재 가진 정수값 + 위에 줄에서 왼쪽, 오른쪽 경로 중 최댓값
            - `dp[i][j] = tri[i].get(j) + Math.max(left, right)`

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
			int n = stoi(br.readLine()); //삼각형 크기
			List<Integer>[]tri = new ArrayList[n];
			int[][] dp = new int[n][n];
			StringTokenizer st;
			for(int i=0; i<n; i++) {
				st= new StringTokenizer(br.readLine());
				tri[i] = new ArrayList<>();
				for(int j=0; j<i+1; j++) {
					tri[i].add(stoi(st.nextToken()));
				}
			}
			//첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
			dp[0][0] = tri[0].get(0);
			for(int i=1; i<n; i++) {
				for(int j=0; j<tri[i].size(); j++) {
					int left = -1, right = -1;
					if(j-1 >=0 ) left = dp[i-1][j-1];
					if(j < tri[i-1].size()) right = dp[i-1][j];
					dp[i][j] = tri[i].get(j) + Math.max(left, right);
				}
			}
			int ans = -1;
			for(int i=0; i<tri[n-1].size(); i++) {
				ans = Math.max(ans, dp[n-1][i]);
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
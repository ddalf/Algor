# 2666_벽장문의 이동

Algorithms: dp
Date: 2021/01/19
Level: G5, ○
Link: https://www.acmicpc.net/problem/2666

### [문제정리]

- 벽장 : n개의 같은 크기 벽장 일렬로 붙어있음
- 벽장 문 : n-2개. 이웃 벽장 앞에 문 없으면 그 벽장 앞으로 이동 가능
- 사용할 벽장 순서 따라 벽장문 이동하는 순서 찾아야 함. 벽장문 이동횟수 최소로.
- 열려있는 벽장 개수 : 항상 2개

**입력**

- n : 벽장 개수(4~20)
- o(i) : 열려 잇는 두 개의 벽장
- l : 사용할 벽장 순서 길이
- orders : 사용할 벽장 번호

**출력**

- 벽장문의 최소 이동 횟수

### [문제풀이]

- DP : O(l*n*n)
    - dp[i][j][k] : i번째 순서에서 j, k번의 벽장문이 열리는 최소 이동횟수
    - k번호 문 닫히고 i번째 순서에서 열려야 하는 문 열림 : dp[i][j][order] = Min(dp[i][j][order], dp[i-1][j][k] + abs(order-k))
    - j번호 문 닫히고 i번째 순서에서 열려야 하는 문 열림 : dp[i][order][k] = Min(dp[i][order][k], dp[i-1][j][k] + abs(order-j))

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
		
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			int openC[] = new int[2];
			st = new StringTokenizer(br.readLine());
			openC[0] = stoi(st.nextToken());
			openC[1] = stoi(st.nextToken());
			int orderLen = stoi(br.readLine());
			int[] orders = new int[orderLen];
			
			int dp[][][] = new int[orderLen][n+1][n+1];
			
			for(int i=0; i<orderLen; i++) {
				orders[i] = stoi(br.readLine());
			}
			
			for(int i=0; i<orderLen; i++) {
				for(int j=1; j<=n; j++) {
					for(int k=1; k<=n; k++) {
						dp[i][j][k] = 987654321;
					}
				}
			}
			dp[0][orders[0]][openC[1]] = Math.abs(orders[0] - openC[0]);
			dp[0][openC[0]][orders[0]] = Math.abs(orders[0] - openC[1]);
			
			for(int i=1; i<orderLen; i++) {
				int order = orders[i];
				for(int j=1; j<=n; j++) {
					for(int k=1; k<=n; k++) {
						if(dp[i-1][j][k] >= 0) {
							int o1 = j;
							int o2 = k;
							dp[i][o1][order] = Math.min(dp[i][o1][order], dp[i-1][j][k]+Math.abs(order-o2));
							dp[i][order][o2] = Math.min(dp[i][order][o2], dp[i-1][j][k]+Math.abs(order-o1));
						}
					}
				}				
			}
			
			int rs = 987654321;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					rs = Math.min(rs, dp[orderLen-1][i][j]);
				}
			}
			bw.write(rs+"");
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
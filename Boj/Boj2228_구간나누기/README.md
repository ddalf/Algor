# 2228_구간나누기

Algorithms: dynamic

Date: 2021/02/18

Level: G5, △

Link: https://www.acmicpc.net/problem/2228

### 문제정리

- N개의 수로 이루어진 1차원 배열
- M개 구간 선택 -> 구간에 속한 수들의 합 최대가 되도록
- 조건
각 구간 한 개 이상의 연속된 수
서로 다른 두 구간 겹침 or 인접 X
정확히 M개의 구간. 미만이여서는 X

[입력]

- N : 1~100
- M : 1~N/2
- 배열 이루는 값 : -32768 ~ 32768

[출력]

- 구간에 속한 수들의 총 합의 최댓값

### 문제풀이

- DP[i][j] = i번째 수부터 j개의 구간으로 나눌 때 최댓값
- DP[i][j] 의 값
    1. dp[i-1][j]
    2. (i~i-k)구간 합 + dp[i-2-k][j-1] (0 ≤k≤i-2)

![image](https://user-images.githubusercontent.com/42609000/108316603-75643500-7200-11eb-8867-0a4340e18f3b.png)

- Bottom Up

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MINV = -2000000000;
	static int n;
	static int m;
	static int[] arr;
	static int[] sum;
	static int[][] dp;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			arr = new int[n+1];
			sum = new int[n+1];
			dp = new int[n+1][m+1];
			int ret = MINV;
			//마이너스 값 들어오므로 dp를 MINV로 초기화
			for(int i=0; i<=n; i++) {
				Arrays.fill(dp[i], MINV);
			}
			
			for(int i=1; i<=n; i++) {
				arr[i] = stoi(br.readLine());
				sum[i] = sum[i-1] + arr[i];
			}
			
			//dp[i][1] 값 먼저 넣어줌. dp를 MINV로 초기화해 주었기 때문에 
			//0 + sum[i]-sum[i-k-1]이 아닌 MINV + sum[i]-sum[i-k-1]가 되어 오류 발생
			for(int i=1; i<=n; i++) {
				dp[i][1] = dp[i-1][1];
				for(int j=0; j<i; j++) {
					dp[i][1] = Math.max(dp[i][1], sum[i]-sum[i-j-1]);
				}
			}		
			
			for(int i=2; i<=n; i++) {	
				for(int j=2; j<=m; j++) {
					dp[i][j] = dp[i-1][j];
					for(int k=0; k<=i-2; k++) {
						if(dp[i-2-k][j-1] == MINV) continue;
						dp[i][j] = Math.max(dp[i][j], dp[i-2-k][j-1]+sum[i]-sum[i-k-1]);
						int tmp = i-2-k;
						int tmp2 = j-1;
					}
				}
			}
			
			bw.write(dp[n][m]+"");
			
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

- Top Down(Java - 시간초과 남)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static final int MINV = -2000000000;
	static int n;
	static int m;
	static int[] arr;
	static int[] sum;
	static int[][] dp;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	//DP[i][j] = i번째 수부터 j개의 구간으로 나눌 때 최댓값
	public static int go(int idx, int section) {
		if(section == 0) return 0;
		if(idx > n || section < 0) return MINV;
		if(dp[idx][section] == MINV) {
			//1. i번째 값 선택하지 않을 경우
			dp[idx][section] = go(idx+1, section);
			//2. idx번째 ~ idx+i번째 까지 구간 선택할 경우
			//	= 	idx번째 ~ idx+i번째 구간의 합 			: sum[idx+i]-sum[idx-1]
			//      + (idx+i+2)번째 부터 구간 나눌 때 최댓값	: go(idx+2+i, section-1)
			for(int i=0; idx+i<=n; i++) {
				dp[idx][section] = Math.max(dp[idx][section], go(idx+2+i, section-1)+sum[idx+i]-sum[idx-1]);
			}
		}
		return dp[idx][section];
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			arr = new int[n+1];
			sum = new int[n+1];
			dp = new int[n+1][m+1];
	
			for(int i=1; i<=n; i++) {
				arr[i] = stoi(br.readLine());
				sum[i] = sum[i-1] + arr[i];
			}
			
			for(int i=0; i<=n; i++) {
				Arrays.fill(dp[i], MINV);
			}
			
			bw.write(go(1, m)+"");

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
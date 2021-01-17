# 5557_1학년

Algorithms: dp

Date: 2021/01/17

Level: G5, ☆

Link: https://www.acmicpc.net/problem/5557

### **[문제정리]**

- 줄지은 숫자.
- 마지막 두 숫자 사이 "=", 나머지 숫자 사이 "+" or "-"넣음
- 올바른 등식 만들고자 함.
- 음수 나오면 안됨.
- 중간 나오는 수 : 0~20 이여야 함.

**입력**

- N : 숫자의 개수. 3~100.

**출력**

- 가능한 올바른 등식의 개수. 2^63-1 이하(=long 써야 함)

### **[문제풀이]**

- DP 사용
    - dp[i][j] 정의 : i 번째 순서 일 때 j일 수 있는 경우의 수
    - dp[i-1][j] > 0 (이전에 0~20 중 가능한 경우의 수가 있으면) 일 때 
    j+arr[i] / j-arr[i]가 0~20 사이이면 
    dp[i][j] += dp[i-1][j]

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static boolean checkRange(int x) {
		return x >= 0 && x <= 20;
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			int[] arr = new int[n];
			long[][] dp = new long[n][21];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = stoi(st.nextToken());
			}
			
			dp[0][arr[0]] = 1;
			for(int i=1; i<n-1; i++) {
				for(int j=0; j<=20; j++) {
					if(dp[i-1][j] > 0) {//이전에 0~20 중 가능한 경우의 수가 있는 것
						int plusValue = j + arr[i];
						int minusValue = j - arr[i];
						if(checkRange(plusValue)) dp[i][plusValue] += dp[i-1][j];
						if(checkRange(minusValue)) dp[i][minusValue] += dp[i-1][j];
					}			
				}
			}
			bw.write(dp[n-2][arr[n-1]]+"");
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
# 15989_1,2,3 더하기 4

Algorithms: dp

Date: 2021/04/22

Level: S1, △

Link: https://www.acmicpc.net/problem/15989

### 문제정리

- 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 4가지가 있다.
- 합을 나타낼 때는 수를 1개 이상 사용해야 한다. 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다.
- 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수

**[입력]**

- 테스트 케이스의 개수 T
- 정수 n이 주어진다. n은 양수이며 10,000보다 작거나 같다.

**[출력]**

- 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력

### 문제풀이

- 다이나믹 프로그래밍
    - `int[i][j] dp`
        - dp[i][0] : i를 '1'로만 만드는 경우의 수
        - dp[i][1] : i를 '2' 적어도 1개 포함, '1','2'로 만드는 경우의 수
            - dp[i][1] = i-2를 적어도 1개 포함& 1,2로 만드는 경우의 수 +i-2를 1로만 만드는 경우의 수

                (2를 빼서 2는 적어도 1번은 포함되게 됨)

            - `dp[i][1] = dp[i-2][1] + 1;`
        - dp[i][2] : i를 '3' 적어도 1개 포함, '1','2','3'로 만드는 경우의 수
            - dp[i][2] = i-3를 3적어도 1개 포함& 1,2,3로 만드는 경우의 수 + i-3를 2 적어도 1개 포함& 1,2로 만드는 경우의 수 +i-3를 1로만 만드는 경우의 수

                (2를 빼서 2는 적어도 1번은 포함되게 됨)

            - `dp[i][2] = dp[i-3][2] + dp[i-3][1] + 1;`

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
			//정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
			int[][] dp = new int[10002][3];
			
			dp[1][0] = 1;
			dp[2][0] = 1;
			dp[2][1] = 1;
			dp[3][0] = 1;
			dp[3][1] = 1;
			dp[3][2] = 1;
			
			for(int i=4; i<=10000; i++) {
				dp[i][0] = 1;
				dp[i][1] = dp[i-2][1] + 1;
				dp[i][2] = dp[i-3][2] + dp[i-3][1] + 1;
			}
			
			int tc = stoi(br.readLine());
			while(--tc >= 0) {
				int n = stoi(br.readLine());
				bw.write(dp[n][0]+dp[n][1]+dp[n][2]+"\n");
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

- 다이나믹 프로그래밍
    - `int[] dp`
        - i만들 수 있는 경우의 수

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			//정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
			int[] dp = new int[10005];
			dp[0] =1;
			for(int i=1; i<=3; i++)//i 사용. 1사용 => 2사용 => 3사용. 중복 없이 구할 수 있다.
				for(int j=i; j<=10000; j++) {
					dp[j] += dp[j-i];
				}
			}
			int tc = stoi(br.readLine());
			while(--tc >= 0) {
				int n = stoi(br.readLine());
				bw.write(dp[n]+"\n");
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
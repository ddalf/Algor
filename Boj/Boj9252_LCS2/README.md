# 9252_LCS2

Algorithms: dp

Date: 2021/04/23

Level: G5, △

Link: https://www.acmicpc.net/problem/9252

### 문제정리

- LCS : 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제

**[입력]**

- 첫째 줄과 둘째 줄에 두 문자열이 주어진다.
    - 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

**[출력]**

- 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.
    - LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.

### 문제풀이

- DP +   반복문

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static String ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			ans = "";
			char[] x = br.readLine().toCharArray();
			char[] y = br.readLine().toCharArray();
			int xLen = x.length;
			int yLen = y.length;
			int dp[][] = new int[xLen+1][yLen+1];
			
			for(int i=1; i<=xLen; i++) {
				for(int j=1; j<=yLen; j++) {
					if(x[i-1] == y[j-1]) dp[i][j] = dp[i-1][j-1]+1;
					else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
			bw.write(dp[xLen][yLen]+"\n");
			StringBuffer sb = new StringBuffer("");
			while(xLen > 0 && yLen > 0) {
				if(dp[xLen][yLen] == dp[xLen-1][yLen]) {
					--xLen;
				}
				else if(dp[xLen][yLen] == dp[xLen][yLen-1]) {
					--yLen;
				}
				else {
					sb.append(x[xLen-1]);
					--xLen;
					--yLen;
				}
			}
			bw.write(sb.reverse().toString());
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
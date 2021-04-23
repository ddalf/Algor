# 9251_최장 길이 공통 부분 문자열

Algorithms: dp

Date: 2021/04/23

Level: G5, ☆

Link: https://www.acmicpc.net/problem/9251

### 문제정리

- LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제

**[입력]**

- 두 문자열
    - 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

**[출력]**

- 입력으로 주어진 두 문자열의 LCS의 길이를 출력

### 문제풀이

- LCS

    참고 : [https://doorbw.tistory.com/59](https://doorbw.tistory.com/59)

    ### 최장 공통 부분 수열의 길이 구하기

    ![Untitled](https://user-images.githubusercontent.com/42609000/115861761-25a72180-a46e-11eb-99b1-809882639af7.png)

    **1. i=0 or j=0**

    - 두 수열 중 하나의 수열의 길이가 0 이라면
    - 최장 공통 부분 수열의 길이도 .  서로 일치하는 문자가 전혀 존재할 수 없기 때문

    **2. i,j>0 and Xi = Yj**

    - 양수인 i,j에 대해서 **Xi와 Yj가 서로 일치**한다면 해당 문자는 최장 공통 부분 수열에 포함되는 문자
    - 따라서 해당 문자를 제외한 나머지 문자열에서 LCS를 탐색
    - 최종적으로 반환이 될때는 제외한 문자가 최장 공통 부분 수열에 포함되므로 +1

    **3. i,j>0 and Xi != Yj**

    - 양수인 i,j에 대해서 **Xi와 Yj가 서로 일치하지 않으면**, 각각의 수열에서 하나씩 제거해서 LCS를 탐색
    - Xi와 Yj가 서로 일치하지 않을 때는 각각의 수열에서 마지막 문자를 제거한, c[i,j-1] 또는 c[i-1,j] 둘 중 하나가 최장 공통 부분 수열을 포함

    ### 테이블 그리기

    ![Untitled 1](https://user-images.githubusercontent.com/42609000/115861751-2344c780-a46e-11eb-94d0-51de8248827c.png)

    - X수열은 <ABCBDAB>이며 Y수열은 <BDCABA>라고 가정
    1. **두 문자가 같을 때**

        좌측위에 있는 숫자에 1을 더한 값을 가지며 화살표는 좌측위를 가리킨다

    2. **두 문자가 다를 때**

        좌측이나 위에 있는 숫자중 큰 값을 가리키며 같은 값을 가집니다. 동일한 값일 때는 위의 값에 대해 행동합니다
    
        ![Untitled 2](https://user-images.githubusercontent.com/42609000/115861755-2475f480-a46e-11eb-9245-496098c9c06b.png)
    
        

    ![Untitled 3](https://user-images.githubusercontent.com/42609000/115861756-250e8b00-a46e-11eb-8eb9-0d15d6edbdc5.png)
    
    [https://www.youtube.com/watch?v=EAXDUxVYquY&t=663s](https://www.youtube.com/watch?v=EAXDUxVYquY&t=663s)

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
			char[] x = br.readLine().toCharArray();
			char[] y = br.readLine().toCharArray();
			int xLen = x.length;
			int yLen = y.length;
			int[][] dp = new int[xLen+1][yLen+1];
			for(int i=1; i<=xLen; i++) {
				for(int j=1; j<=yLen; j++) {
					if(x[i-1] != y[j-1]) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					else dp[i][j] = dp[i-1][j-1]+1;
				}
			}
			bw.write(dp[xLen][yLen]+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
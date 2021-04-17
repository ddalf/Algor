# 2447_별 찍기 - 10

Algorithms: 분할정복

Date: 2021/04/17

Level: S1, ☆

Link: https://www.acmicpc.net/problem/2447

### 문제정리

- N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양

```
***
* *
***
```

**[입력]**

- N : 3의 거듭제곱. 어떤 정수 k에 대해 N=3^k이며, 이때 1 ≤ k < 8이다.

**[출력]**

- 첫째 줄부터 N번째 줄까지 별을 출력한다.

### 문제풀이

- 분할정복
    - boolean isStar(int x, int y, int num)
        - 재귀함수. true일 경우 '*' false일 경우 ' '
        - ans[x][y] 의 값 = ans[x/3][y/3]의 값
        - 빈칸인 경우 : x%3, y%3 이 모두 1일 때

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static char[][] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static boolean isStar(int x, int y, int num) {
		if(ans[x][y] != 'x') return ans[x][y] == '*' ? true : false;
		if(x%3 == 1 && y%3 ==1) return false;
		if(num == 0) return true;
		return isStar(x/3, y/3, num/3);
	}
	
	public static void main(String[] args) {
		try {
			int n = stoi(br.readLine());
			ans = new char[n][n];
			for(int i=0; i<n; i++) {
				Arrays.fill(ans[i], 'x');
				for(int j=0; j<n; j++) {
					if(isStar(i,j,n)) ans[i][j] = '*';
					else ans[i][j] = ' ';
					bw.write(ans[i][j]);
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
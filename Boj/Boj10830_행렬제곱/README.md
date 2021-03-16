# 10830_행렬제곱

Algorithms: math, 거듭제곱근

Date: 2021/03/16

Level: G4, ○

Link: https://www.acmicpc.net/problem/10830

### 문제정리

- 행렬 : n*n
- a의 b제곱 구함
- a^b의 각 원소 1000으로 나눈 나머지

**[입력]**

- N : 행렬 크기(2~5)
- B : 제곱할 수(1~1000000000000)
- 각 원소 1000보다 작거나 같음

**[출력]**

- 첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B제곱한 결과를 출력

### 문제풀이

- 거듭제곱을 문제 풀이 방식과 비슷

    n^b에서 

    - b가 홀수일 경우: **n * n^(b-1)**
    - b가 짝수일 경우: **n^(b/2) * n^(b/2)**
- 행렬 곱하는 부분으로 바꿔주면 된다.

```java
import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static long b;
	static long[][] matrix;
	static long[][] ans;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static long stol(String s) {
		return Long.parseLong(s);
	}
	
	public static long[][] mulMatrix(long[][] x, long[][] y) {
		long[][] ret = new long[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					ret[i][j] = (ret[i][j] + x[i][k] * y[k][j]) % 1000;
				} 
			}
		}
		return ret;
	}
	
	public static void powMatrix() throws IOException {
		while(b > 0) {
			if(b % 2 == 1) {
				ans = mulMatrix(ans, matrix);
			}
			b/=2;
			matrix = mulMatrix(matrix, matrix);
		}
	}
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			n =stoi(st.nextToken());
			b = stol(st.nextToken());
			matrix = new long[n][n];
			ans = new long[n][n];

			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					matrix[i][j] = stoi(st.nextToken());
					if(i == j) ans[i][j] = 1;//단위행렬로 초기화
				}
			}
			
			powMatrix();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					bw.write(ans[i][j]+" ");
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
# 1780_종이의 개수

Algorithms: 분할정복

Date: 2021/04/17

Level: S2, ○

Link: https://www.acmicpc.net/problem/1780

### 문제정리

- N×N크기의 행렬로 표현되는 종이
- 종이의 각 칸에는 -1, 0, 1의 세 값 중 하나가 저장되어 있다.
    1. 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
    2. (1)이 아닌 경우에는 종이를 같은 크기의 9개의 종이로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.

**[입력]**

- N : 종이의 크기. N(1 ≤ N ≤ 3^7, N은 3^k 꼴)

**[출력]**

- 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력

### 문제풀이

- 분할정복
    - void cutPaper(int x, int y, int size)
        - 재귀함수.
        - x,y에서 size만큼 배열 탐색
            - 모두 같을 경우 ⇒ 현재 종이 값 papers 배열에 더해줌
            - 다를 경우 = > 9등분으로 분할해야함, 크기 / 3으로 줄어듬
                - 이중 for문으로 나눠진 배열의 첫번째 값 넣어줌

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] papers;
	static int[][] arr;
	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static void cutPaper(int x, int y, int size) throws IOException {
		boolean flag = true;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(arr[i][j] != arr[x][y]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			++papers[arr[x][y]];
		}
		else {
			size /= 3;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					cutPaper(x+i*size, y+j*size, size);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			int n = stoi(br.readLine());
			papers = new int[3];
			arr = new int[n][n];
			StringTokenizer st ;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = stoi(st.nextToken())+1;
				}
			}
			cutPaper(0,0,n);
			for(int i=0; i<3; i++) {
				bw.write(papers[i]+"\n");
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
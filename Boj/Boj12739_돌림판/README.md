# 12739_돌림판

Algorithms: simulation

Date: 2021/04/23

Level: G5, ○

Link: https://www.acmicpc.net/problem/12739

### 문제정리

1. 만약 P의 왼쪽 부분과 P의 오른쪽 부분과 P에 칠해진 색이 모두 같거나, 모두 다르다면, P에 칠해진 색을 파란색으로 바꾼다.
2. 그렇지 않다면, 1에서 고려했던 세 부분 중에서 X 색이 2개, Y 색이 1개 칠해진 상황일 것이다.
3. 다음 조건을 한 가지라도 만족한다면, P에 칠해진 색을 빨강색으로 바꾼다. 그렇지 않다면, 초록색으로 바꾼다.

    (X가 빨강색이고 Y가 초록색인 경우, X가 초록색이고 Y가 파랑색인 경우, X가 파랑색이고 Y가 빨강색인 경우)

**[입력]**

첫째 줄에 N과 K가 주어진다.

두 번째 줄에 길이가 N인 문자열이 주어지는데, 이는 돌림판의 각 구간에 칠해진 색을 시계방향으로 나타낸 것이다. (빨강색은 R, 초록색은 G, 파랑색은 B로 주어진다)

N, K의 제한은 다음과 같다.

1 ≤ N ≤ 1000, 1 ≤ K ≤ 1000

**[출력]**

빨강색으로 칠해진 구간의 수, 초록색으로 칠해진 구간의 수, 파랑색으로 칠해진 구간의 수를 차례대로 띄어쓰기로 구분해 출력한다.

### 문제풀이

- 시뮬레이션

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static Map<Character, Integer> colorMap;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static char[] changeColor(char[] board, int n) {
		char[] boardTmp = new char[n];
		for(int i=0; i<n; i++) {
			char part = board[i];
			char lChar = board[(i+n-1) % n];
			char rChar = board[(i+n+1) % n];
			int[] colors = new int[3];
			++colors[colorMap.get(part)];
			++colors[colorMap.get(lChar)];
			++colors[colorMap.get(rChar)];
			if(colors[0] == 3 || colors[1] == 3 || colors[2] == 3) boardTmp[i] = 'B';
			else if(colors[0] == 1 && colors[1] == 1 && colors[2] == 1) boardTmp[i] = 'B';
			else if((colors[0] == 2 && colors[1] == 1) 
					|| (colors[1] == 2 && colors[2] == 1) 
					|| (colors[2] == 2 && colors[0] == 1)) boardTmp[i] = 'R';
			else boardTmp[i] = 'G';
		}
		
		return boardTmp;
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());//돌림판 등분 개수
			int k = stoi(st.nextToken());//색 바꾼 횟수
			char[] board = br.readLine().toCharArray();
			colorMap = new HashMap<>();
			colorMap.put('R', 0);
			colorMap.put('G', 1);
			colorMap.put('B', 2);
			while(--k >= 0) {
				board = changeColor(board, n);
			}
			int[] ans = new int[3];
			for(int i=0; i<n; i++) {
				++ans[colorMap.get(board[i])];
			}
			
			for(int i=0; i<3; i++) {
				bw.write(ans[i]+" ");
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
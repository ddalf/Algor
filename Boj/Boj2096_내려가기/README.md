# 2096_내려가기

Algorithms: dp

Date: 2021/01/20

Level: G4, △

Link: https://www.acmicpc.net/problem/2096

### [문제정리]

- N줄 0~9이하 숫자 3개
- 첫 줄 시작 ~ 마지막 줄에서 끝나는 놀이
- 세 개의 숫자 중 하나 골라 시작 -> 다음줄 갈 때 제약 조건 有
- 숫자표 주어짐 -> 얻을 수 있는 최대 점수 & 최소 점수

**입력**

- N : n개의 줄(1~100,000)

**출력**

- 첫째 줄에 얻을 수 있는 최대 점수 / 최소 점수

### [문제풀이]

- 풀이 1. DP 이차원 배열 이용
    - dpMin / dpMax
    - dpMin[i][j] : i줄 j번째 위치에서 최소 점수
    - dpMax[i][j] : i줄 j번째 위치에서 최대 점수

```java
import java.util.*;
import java.io.*;

class Direction{
	int x, y;
	Direction(){this.x = 0; this.y = 0;}
	Direction(int x, int y){this.x = x; this.y = y;}
}
public class Main {
	private static int[][] board;
	private static int[][] dpMin;
	private static int[][] dpMax;
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static boolean checkRange(int x, int y) {
		return (x >= 0 && y >= 0 && x < 3 && y < 3);
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			board = new int[n][3];
			dpMin = new int[n][3];
			dpMax = new int[n][3];
			Direction[] dirArray = new Direction[3]; 
			dirArray[0] = new Direction(-1,-1);
			dirArray[1] = new Direction(-1,0);
			dirArray[2] = new Direction(-1,1);
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<3; j++) {
					board[i][j] = stoi(st.nextToken());
				}
			}
			for(int i=0;i<n;i++) {
				Arrays.fill(dpMin[i], Integer.MAX_VALUE);
				Arrays.fill(dpMax[i], Integer.MIN_VALUE);
			}
			for(int i=0; i<3; i++) {
				dpMin[0][i] = board[0][i];
				dpMax[0][i] = board[0][i];
			}
			
			for(int i=1; i<n; i++) {
				for(int j=0; j<3; j++) {
					for(int k=0; k<3; k++) {
						Direction dir = dirArray[k];
						if(checkRange(i+dir.x, j+dir.y)) {
							dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i+dir.x][j+dir.y]+board[i][j]);
							dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i+dir.x][j+dir.y]+board[i][j]);
						}
					}
				}
			}
			
			int rsMin = Integer.MAX_VALUE;
			int rsMax = Integer.MIN_VALUE;
			for(int i=0; i<3; i++) {
				rsMin = Math.min(rsMin, dpMin[n-1][i]);
				rsMax = Math.max(rsMax, dpMax[n-1][i]);
			}
			bw.write(rsMax+" "+rsMin);
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

- 풀이 2. DP + 슬라이딩 윈도우 알고리즘

```java
import java.util.*;
import java.io.*;

public class Main {
	private static int[][] board;
	private static int[] dpMin;
	private static int[] dpMax;
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			board = new int[n][3];
			dpMin = new int[3];
			dpMax = new int[3];
			int[] dirArr = {-1, 0, 1}; 
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<3; j++) {
					board[i][j] = stoi(st.nextToken());
				}
			}
			for(int i=0; i<3; i++) {
				dpMin[i] = board[0][i];
				dpMax[i] = board[0][i];
			}
			
			for(int i=1; i<n; i++) {	
				int[] dpMinTmp = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
				int[] dpMaxTmp = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
	
				for(int j=0; j<3; j++) {
					for(int k=0; k<3; k++) {
						int col = j + dirArr[k];
						if(col >= 0 && col <3) {
							dpMinTmp[j] = Math.min(dpMinTmp[j], dpMin[col] + board[i][j]);
							dpMaxTmp[j] = Math.max(dpMaxTmp[j], dpMax[col] + board[i][j]);
						}
					}
				}
				dpMin = dpMinTmp;
				dpMax = dpMaxTmp;
			}
			
			int rsMin = Integer.MAX_VALUE;
			int rsMax = Integer.MIN_VALUE;
			for(int i=0; i<3; i++) {
				rsMin = Math.min(rsMin, dpMin[i]);
				rsMax = Math.max(rsMax, dpMax[i]);
			}
			bw.write(rsMax+" "+rsMin);
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
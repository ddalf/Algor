# 18808_스티커붙이기

Algorithms: simulation

Date: 2021/04/27

Level: G3, ○

Link: https://www.acmicpc.net/problem/18808

### 문제정리

1. 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
2. 다른 스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다. 혜윤이는 노트북의 위쪽부터 스티커를 채워 나가려고 해서, 스티커를 붙일 수 있는 위치가 여러 곳 있다면 가장 위쪽의 위치를 선택한다. 가장 위쪽에 해당하는 위치도 여러 곳이 있다면 그중에서 가장 왼쪽의 위치를 선택한다.
3. 선택한 위치에 스티커를 붙인다. 만약 스티커를 붙일 수 있는 위치가 전혀 없어서 스티커를 붙이지 못했다면, 스티커를 시계 방향으로 90도 회전한 뒤 2번 과정을 반복한다.
4. 위의 과정을 네 번 반복해서 스티커를 0도, 90도, 180도, 270도 회전시켜 봤음에도 스티커를 붙이지 못했다면 해당 스티커를 붙이지 않고 버린다.

**[입력]**

- 노트북의 세로와 가로 길이를 나타내는 N(1 ≤ N ≤ 40)과 M(1 ≤ M ≤ 40), 그리고 스티커의 개수 K(1 ≤ K ≤ 100)
- K개의 스티커들에 대한 정보
    - 스티커가 인쇄된 모눈종이의 행의 개수와 열의 개수를 나타내는 Ri(1 ≤ Ri ≤ 10)와 Ci(1 ≤ Ci ≤ 10)
    - 다음 Ri개의 줄에는 각 줄마다 모눈종이의 각 행을 나타내는 Ci개의 정수가 한 개의 빈칸을 사이에 두고 주어진다

**[출력]**

- 첫째 줄에 주어진 스티커들을 차례대로 붙였을 때 노트북에서 스티커가 붙은 칸의 수를 출력한다.

### 문제풀이

- 시뮬레이션
    - `insertSticker(stickers.get(i));`
        - i번째 스티커 board에 넣으려 함
    - `turn(dir, sTmp);`
        - 오른쪽으로 스티커 회전한 배열 반환
        - dir=0일 땐 회전 안하므로 받은 값 그대로 반환
    - `boolean checkSticker(int sx, int sy, int[][] sticker)`
        - 스티커가 넣어질 경우 true, 넣어지지 않을 경우 false 반환

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] board;
	static int n,m;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static int[][] turn(int dir, int[][] sticker) throws IOException{
		if(dir == 0) return sticker;
		int sr = sticker.length;
		int sc = sticker[0].length;
		int[][] sTmp = new int[sc][sr];
		
		for(int i=0; i<sTmp.length; i++) {
			for(int j=0; j<sTmp[0].length; j++) {
				sTmp[i][j] = sticker[sr-j-1][i];
			}
		}
		return sTmp;
	}
	static boolean checkSticker(int sx, int sy, int[][] sticker)throws IOException {
		for(int i=sx; i<sx+sticker.length; i++) {
			for(int j=sy; j<sy+sticker[0].length; j++) {
				if(i<0 || j<0 || i>=n||j>=m) return false;
				if(sticker[i-sx][j-sy] == 1 && board[i][j] == 1) {
					return false;
				}
			}
		}
		for(int i=sx; i<sx+sticker.length; i++) {
			for(int j=sy; j<sy+sticker[0].length; j++) {
				board[i][j] += sticker[i-sx][j-sy];
			}
		}
		return true;
	}
	static void insertSticker(int[][] sticker)throws IOException {
		int dir = 0;
		boolean flag = false;
		int[][] sTmp = sticker;
		while(dir < 4) {
			//현재 방향에 따라 스티커 돌려줌 0:그대로/1:90도/2:180도/3:270도
			sTmp = turn(dir, sTmp);
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					//i,j 위치에서 스티커 들어가는지 확인
					if(checkSticker(i,j,sTmp)) {
						flag = true;//스티커가 들어가면
						break;
					}
				}
				if(flag) break;
			}
			if(flag) break;
			++dir;
		} 
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//행 길이
			m = stoi(st.nextToken());//열 길이
			int k = stoi(st.nextToken());//스티거 개수
			board = new int[n][m];
			List<int[][]> stickers = new ArrayList<>();
			for(int i=0; i<k; i++) {
				st= new StringTokenizer(br.readLine());
				int sr = stoi(st.nextToken());
				int sc = stoi(st.nextToken());
				int[][] sticker = new int[sr][sc];
				for(int j=0; j<sr; j++) {
					st= new StringTokenizer(br.readLine());
					for(int z=0; z<sc; z++) {
						sticker[j][z] = stoi(st.nextToken());
					}
				}
				stickers.add(sticker);
			}
			for(int i=0; i<k; i++) {
				insertSticker(stickers.get(i));
			}
			int ans = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans+=board[i][j];
				}
			}
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
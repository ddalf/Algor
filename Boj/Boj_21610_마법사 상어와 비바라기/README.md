# 마법사 상어와 비바라기

Algorithms: simulation

Date: 2021/05/22

Level: G5, ○

Link: https://www.acmicpc.net/problem/21610

### 문제정리

- N×N인 격자
- (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.
- 격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.
- N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.
- 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
- 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다.
- 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다.
1. 모든 구름이 d 방향으로 s칸 이동한다.
2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
3. 구름이 모두 사라진다.
4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
    - 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
    - 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.

**[입력]**

- 첫째 줄에 N, M이 주어진다.
- 둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.
- 다음 M개의 줄에는 이동의 정보 di, si가 순서대로 한 줄에 하나씩 주어진다.

**[출력]**

- M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.

### 문제풀이

- 시뮬레이션

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] dx = {0,-1,-1,-1,0,1,1,1};
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static boolean[][] lastCloud;
	static List<int[]> clouds;
	static int[][] arr;
	static int n,m;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static void moveCloud(int d, int s) throws IOException{
		for(int i=0; i<clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			cloud[0] = (cloud[0] + dx[d] * s + n) % n;
			cloud[1] = (cloud[1] + dy[d] * s + n) % n;
			++arr[cloud[0]][cloud[1]];
			lastCloud[cloud[0]][cloud[1]] = true;
			clouds.set(i, cloud);
		}
	}
	static void copyWater() {
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			int cnt = 0;
			for(int j=1; j<8; j+=2) {
				int x = cloud[0]+dx[j];
				int y = cloud[1]+dy[j];
				if(x<0 || y<0 || x>=n || y>=n) continue;
				if(arr[x][y] > 0) ++cnt;
			}
			if(cnt > 0) q.add(new int[] {cloud[0],cloud[1],cnt});
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			arr[cur[0]][cur[1]] += cur[2];
		}
	}
	static void setClouds() {
		clouds.clear();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!lastCloud[i][j] && arr[i][j] >= 2) {
					clouds.add(new int[] {i,j});
					arr[i][j]-=2;
				}
			}
		}
		for(int i=0; i<n; i++) {
			Arrays.fill(lastCloud[i], false);
		}
	}
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//arr의 크기
			m = stoi(st.nextToken());//구름 이동 횟수
			arr = new int[n][n];//저장된 물의 양
			lastCloud = new boolean[n][n];
			clouds = new ArrayList<>();
			clouds.add(new int[] {n-1,0});
			clouds.add(new int[] {n-1,1});
			clouds.add(new int[] {n-2,0});
			clouds.add(new int[] {n-2,1});
			
			for(int i=0; i<n; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = stoi(st.nextToken());
				}
			}

			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int d = stoi(st.nextToken())-1;//d방향
				int s = stoi(st.nextToken()) % n;//s칸 이동
				moveCloud(d,s);//구름 움직임, 물의 양 증가
				copyWater();//물이 증가한 칸에 물복사 마법 시작
				setClouds();//물의 양이 2이상인 모든 칸에 구름 생기고 물의 양 2 줄어듬.구름 생기는 칸은 이전에 사라진 구름있는 칸 제외 
			}
			int answer = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					answer += arr[i][j];
				}
			}
			bw.write(answer+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
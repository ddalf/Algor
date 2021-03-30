# 17245_서버실

Algorithms: binary search, simulation

Date: 2021/03/30

Level : △, S2

Link: https://www.acmicpc.net/problem/17245

### 문제정리

**[입력]**

- N*N으로 서버실 구분, 각 칸마다 컴퓨터 여러대 쌓음
- 컴퓨터 절반만 정상적 관리
- 냉방기에서 나온 차가운 공기
    - 서버실 아래쪽 부터 차오름.
    - 1분마다 컴퓨터 한 대의 높이만큼 채움
    - 컴퓨터는 차가운 공기 받아야만 정상 작동
- 서버실의 컴퓨터 중 절반 이상 켜지려면 몇 분 필요?

**[출력]**

- N : 1~1000. 서버실 크기
- N*N칸에 컴퓨터 몇 대 쌓여 있는지 = >10,000,000대까지 쌓음

### 문제풀이

- 시뮬레이션
    - height 배열 : 컴퓨터의 높이 개수 저장하는 배열.
    - 1초일 때 켜진 컴퓨터 수 = 전체 서버실 격자 개수(n*n) - 높이 0인 격자 개수(height[0])
    - 2초일 때 켜진 컴퓨터 수 = 전체 서버실 격자 개수(n*n) - 높이 0,1인 격자 개수(height[0]+height[1])

        ....

    - n초일 때 켜진 컴퓨터 수 = 전체 서버실 격자 개수(n*n) - 높이 0~n-1인 격자 개수
    - while문 돌면서 on변수에서 이전에 꺼진 컴퓨터 개수가 누적 됨 (on -= height[time++])

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static long sum;
	static int[][] room;
	static int[] height;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) {
		try {
			n = stoi(br.readLine());//서버실 크기
			room = new int[n][n];
			height = new int[10000001];
			sum = 1;
			StringTokenizer st;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					room[i][j] = stoi(st.nextToken());
					sum += room[i][j];
					++height[room[i][j]];
				}
			}
			
			sum /= 2;//절반 이상 컴퓨터 켜졌을 때
			long onSum = 0;//켜진 전체 컴퓨터 수
			int time=0, on=n*n;//on: 현재 켜진 컴퓨터 수
			while(onSum < sum) {//켜진 컴퓨터 수 < 전체의 반보다 커지면 반복문 종료
				on -= height[time++];//ex) 0일때 때 높이 0인것 제외하고 모두 켜짐 => 전체 - 높이[0]
				onSum += on;
			}
			
			bw.write(time+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

- 이분탐색
    - left = 0, right = 컴퓨터 쌓인 최대 높이

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static long sum;
	static int[][] room;
	static int[] height;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isNormalOP(int num) throws IOException {
		long cnt = 0;//켜진 컴퓨터 갯
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(room[i][j]< num) cnt += (long)room[i][j];
				else cnt += (long)num;
			}
		}
		return cnt >= sum;
	}
	
	public static void main(String[] args) {
		try {
			n = stoi(br.readLine());//서버실 크기
			room = new int[n][n];
			sum = 1;
			int maxCnt = 0;
			StringTokenizer st;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					room[i][j] = stoi(st.nextToken());
					sum += (long)room[i][j];
					maxCnt = Math.max(maxCnt, room[i][j]);
				}
			}
			sum /= 2;
			int left = 0, right = maxCnt;
			int ans = 0;
			while(left <= right) {
				int mid = (left+right)/2;
				if(isNormalOP(mid)) {//(켜진 컴퓨터 개수 >= 절반 이상 켜진 개수)보다 크거나 같으면 범위 줄임 
					ans = mid;//최소 컴퓨터 높이
					right = mid-1;
				}
				else left = mid+1;//켜진 컴퓨터 개수 < 절반 이상 켜진 개수 보다 작으면 범위 늘림
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
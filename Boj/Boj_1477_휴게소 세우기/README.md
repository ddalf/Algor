# 1477_휴게소 세우기

Algorithms: binary search

Date: 2021/02/23

Level: G5, ☆

Link: https://www.acmicpc.net/problem/1477

### 문제정리

- n개 휴개소.
- 휴게소 위치 : 휴게소 시작~얼만큼 떨어져 있는지.
- 휴게소 있는 곳에 또 휴게소 세울 수 X.
- 고속도로 끝 -> 휴게소 세울 수 X.
- 모든 휴게소 방문하려고 할 때 M개 더 지어서 휴게소 없는 구간의 길이 최댓값을 최소로 함.

**[입력]**

- n : 현재 휴게소 개수. 1~100
- m : 더 지으려고 하는 휴게소 개수. 1~100
- l : 고속도로 길이.100~1000
- 모든 휴게소 위치 중복 X. n+m < l.

**[출력]**

- m개의 휴게소 짓고 난 후 휴게소 없는 구간의 최댓값의 최솟값.

### 문제풀이

1. 이분탐색
거리로 접근해야 함.
    - left : 휴게소 최소 위치
    - right : 휴게소 최대 위치
    - mid : 중간 값. mid간격으로 휴게소 세웠을 때 mid 최솟값
2. (mid 간격으로 휴게소 세웠을 때 세운 휴게소의 개) newStoreCnt 와 m 비교
    - newStoreCnt <= m : 편의점 적게 생김. 간격 줄여야 함. right = mid - 1
    - newStoreCnt > m : 편의점 많이 생김. 간격 넓혀야 함. left = mid + 1

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n,m,l;
	static int[] storeDis;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isPossibleDis(int d) {
		int newStoreCnt = 0;
		for(int i=1; i<n+2; i++) {
			int dis = storeDis[i] - storeDis[i-1];
			newStoreCnt += (dis / d);
			if(dis % d == 0) --newStoreCnt;//나누어 떨어지는 경우 겹치는 편의점
		}
		
		return newStoreCnt <= m;
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st; 
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			l = stoi(st.nextToken());
			storeDis = new int[n+2];
			st = new StringTokenizer(br.readLine());

			for(int i=1; i<=n; i++) {
				storeDis[i] = stoi(st.nextToken());
			}
			storeDis[0] = 0;
			storeDis[n+1] = l;
			Arrays.sort(storeDis);
			
			int left = 1, right = l-1;
			while(left<=right) {
				int mid = (left + right) / 2;
				if(isPossibleDis(mid)) right = mid - 1;
				else left = mid + 1;
			}
			bw.write(left+"");
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
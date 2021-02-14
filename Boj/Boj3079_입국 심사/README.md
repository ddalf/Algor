# 3079_입국 심사

Algorithms: binary search

Date: 2021/02/14

Level: S1, △

Link: https://www.acmicpc.net/problem/3079

### 문제정리

- 상근이, 친구들 : M명
- 입국심사대 : N개. 맨 처음 모두 비어 있음.
    - 한 번에 한 명만 심사 가능.
    - 심사 하는데 걸리는 시간 모두 다름
- Tk : k번 심사대에 앉아있는 심사관이 한 명 심사 하는데 드는 시간
- 가장 앞에 있는 사람 -> 빈 심사대에서 심사 받을 수 O.
- 항상 이동하는 것은 X. 더 빠른 심사대 심사 끝나기 기다리고 거기서 심사 받을 수 o.

**[입력]**

- n : 입국심사대 개수. 1~100,000
- m : 입국심사 받을 명 수 : 1~1,000,000,000
- T(k) : 각 심사대에서 심사 하는데 걸리는 시간. 1~1,000,000,000

**[출력]**
심사 마치는데 걸리는 시간의 최솟값



### 문제풀이

1. 이분탐색 -> 가능한 시간 결정
left = 가능한 최소 시간 크기
right = 가능한 최대 시간 크기
mid = 중간값. mid 이하 시간으로 가능한 심사가능 인원 수 확인
2. mid 시간 내로 심사할 수 있는 인원 수
    - 심사 인원 m명 이상 : 가능한 최대 시간 줄임(right=mid-1) -> 심사할 수 있는 인원 수 줄임
    - 심사 인원 m명 이하 : 가능한 최소 시간 늘림 (left=mid+1) -> 심사할 수 있는 인원 수 늘림

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[] times;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}	
	
	public static boolean isPossibleTime(long t) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			sum += t / times[i];
		}
		return sum >= m;
	}
	
	public static void main(String[] args) {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			times = new int[n];
			
			for(int i=0; i<n; i++) {
				times[i] = stoi(br.readLine());
			}
			Arrays.sort(times);
			long left =1, right = (long)times[n-1]*m;
			while(left <= right) {
				long mid = (left+right)/2;
				if(isPossibleTime(mid)) right = mid-1;
				else left = mid+1;
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
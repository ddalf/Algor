# 16564_히오스 프로게이머

Algorithms: binary search

Date: 2021/02/16

Level: S1, △

Link: https://www.acmicpc.net/problem/16564

### 문제정리

- N개의 캐릭터, 캐릭터 각 레벨 X(i)
- 레벨의 총합 K만큼 올릴 수 O
- 팀 목표레벨 T = min(X(i)).
- 게임 끝날 때 까지 성권이가 달성할 수 있는 최대 팀 목표 레벨

**[입력]**

- N : 캐릭터 개수. 1~1,000,000
- K : 올릴 수 있는 레벨 총합. 1~1,000,000,000
- X(i) : 각 캐릭터 레벨. 1~1,000,000,000

**[출력]**

- 가능한 최대 팀 목표레벨 T

### 문제풀이

1. 이분탐색. 
    - left = 가능한 최소 목표 레벨
    - right = 가능한 최대 목표 레벨
    - mid = 중간값. k 내로 mid 도달 할 수 있는지 확인
2. mid값 도달했을 때 오른 레벨 총합 sum
    - sum > k : 도달하고자 하는 레벨이 사용가능한 레벨 총합 넘음.

        사용가능한 레벨 총합 부족 → mid 값 감소 시켜야 함 → right = mid-1

    - sum ≤ k : 
    사용가능한 레벨 총합 남음 → mid 값 증가 시켜서 레벨 더 사용 → left =mid+1

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
	static int k;
	static int[] xArr;
	static BufferedReader br;
	static BufferedWriter bw;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean isPossibleT(long t) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			if(t <= xArr[i]) break;
			sum += t - (long)xArr[i];
		}
		return sum > k;
	}
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			k = stoi(st.nextToken());
			xArr = new int[n];
			for(int i=0; i<n; i++) {
				xArr[i] = stoi(br.readLine());
			}
			Arrays.sort(xArr);
			long left =0, right = xArr[n-1] + (long)k;
			long ans = 0;
			while(left <= right) {
				long mid = (left + right) / 2;
				if(isPossibleT(mid)) right = mid - 1;
				else {
					ans = mid;
					left = mid + 1;
				}
			}
			bw.write(ans+"");
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
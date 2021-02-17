# 11687_팩토리얼 0의 개수

Algorithms: binary search

Date: 2021/02/17

Level: S1, ☆

Link: https://www.acmicpc.net/problem/11687

### 문제정리

- 가장 끝의 0의 개수가 M개이 N! 중에서 가장 작은 N 찾음

**[입력]**

- M : 100,000,000

**[출력]**

- 가장 작은 N 출력. 없는 경우 -1 출력

### 문제풀이

1. 이분탐색.
    - left = 최소 팩토리얼 값
    - right = 최대 팩토리얼 값
    - mid = 중간값. mid 값 5로 나누어지는 갯수 확인 (10의 배수  2*5 개수  → 5의 개수가 2보다 작으므로 5의 개수를 count한다)
2. cnt = mid가 5로 나누어 떨어지는 개수
    - cnt ≥ m : m보다 더 클 경우 → mid값 더 작게→ right = mid - 1
    - cnt < m : m보다 더 작을 경우 → mid값  더 크게 → left = mid + 1

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static int m;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static int isPossibleNum (int n) {
		int cnt = 0;
		for(int i=5; i<=n; i*=5) {
			cnt += n / i;
		}
		return cnt;
	}
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			m = stoi(br.readLine());
			int left = 1, right = 100000000, mid;
			boolean flag = false;
			while(left <= right) {
				mid = (left + right) / 2;
				int cnt = isPossibleNum(mid);
				if(cnt >= m) {
					if(cnt == m) flag = true;
					right = mid - 1;
				}
				else left = mid + 1;
			}
			if(!flag) bw.write("-1");
			else bw.write(left + "");
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
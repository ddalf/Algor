# 2457_용액

Algorithms: binary search

Date: 2021/02/19

Level: G5, ☆

Link: https://www.acmicpc.net/problem/2467

### 문제정리

- 용역 특성 나타내는 정수
- 산성 용액 : 1~1,000,000,000 양의 정수.
- 알칼리성 용액 : -1~-1,000,000,000 음의 정수
- 혼합 용액 특성값 = 혼합에 사용된 각 용액의 특성값 합
- 튻성값 0에 가까운 용액 만들려 함.
- EX. -99, -2, -1, 4, 98 -> -99 + 98 = -1 -> 가장 0 에 가까운 용액
- 두 종류 알칼리 or 두 종류 산성 만으로 0에 가깝게 만드는 경우 有
- 산성 용액 , 알칼리성 용액 특성값 정렬된 순서로 주어짐

**[입력]**

- N : 전체 용액의 수. 2~100,000
- 용액의 특성값 : -1,000,000,000 ~ + 1,000,000,000

**[출력]**

- 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값
- 출력 용액 특성값 오름차순 출력.
- 경우 두 개 이상일 경우 아무거나 하나 출력

### 문제풀이

- 이분탐색.

    1. i번째 선택 : O(N)

    2. i+1~n까지  i번째 값과 더해서 가장 작은 값 이분 탐색 : O(logN)

    ⇒ O(N * logN)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int n;
	public static int[] liquids;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			liquids = new int[n];
			for(int i=0; i<n; i++) {
				liquids[i] = stoi(st.nextToken());
			}

			int minMixV = 2000000001;
			int resultL = -1, resultR = -1;
			for(int i=0; i<n; i++) {//1.i번째 선택 : O(N)
				int left = i+1, right = n-1;
				while(left <= right) { //2. i+1~n까지 i번째 값과 더해서 가장 작은 값 이분 탐색 : O(logN)
					int mid = (left+right)/2;
					int mixV = liquids[i]+liquids[mid];
					if(minMixV > Math.abs(mixV)) {
						minMixV = Math.abs(mixV);
						resultL = liquids[i];
						resultR = liquids[mid];
					}
					if(mixV < 0) left = mid + 1;
					else right = mid - 1;
				}
			}
			bw.write(resultL + " " + resultR);			
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
```
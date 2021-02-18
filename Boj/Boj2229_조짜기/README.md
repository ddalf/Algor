# 2229_조짜기

Algorithms: dynamic

Date: 2021/02/17

Level: G5, ☆

Link: https://www.acmicpc.net/problem/2229

### 문제정리

- N명 캠프에 참여.
- 실력 차이 많이 나도록 조 편성
- 나이 순 정렬학생 적당히 나눔
- 조 개수 상관 X
- 각 조 잘 짜여진 정도 : 조의 점수 가장 높은 학생 - 점수 가장 낮은 학생
- 전체 조 잘 짜여진 정도 = 각 조 잘 짜여진 정도 합

입력

- N : 학생 수
- 각 학생 점수. 0~10,000

출력

- 조 잘 짜여진 정도의 최댓값

### 문제풀이

- dp[i] = i명으로 조를 짰을 때 조 잘 짜여진 정도의 최댓값
- dp[i] = j의 범위 0~i-1내에서 [**dp[j] + MAX-MIN**] 중 최댓값
    - MAX, MIN : j~i-1 범위 내 최댓값, 최소값

![image](https://user-images.githubusercontent.com/42609000/108169896-b135c680-713c-11eb-9ca8-2eda34d0ac5c.png)



- Bottom up

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static int[] scores;
	static int[] dp;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			scores = new int[n];
			dp = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				scores[i] = stoi(st.nextToken());
			}
			
			for(int i=1; i<=n; i++) {
				int maxScore = scores[i-1];
				int minScore = scores[i-1];
				for(int j=i-1; j>=0; j--) {
					maxScore = Math.max(maxScore, scores[j]);
					minScore = Math.min(minScore, scores[j]);
					dp[i] = Math.max(dp[i], dp[j] + maxScore - minScore);
				}
			}
			bw.write(dp[n]+"");
			
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



- Top down(java - 시간 초과 남)

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
	static int n;
	static int[] scores;
	static int[] dp;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static int go(int stdIdx) {
		if(stdIdx < 1) return 0;
		if(dp[stdIdx] > 0) return dp[stdIdx];
		int minScore = scores[stdIdx-1];
		int maxScore = scores[stdIdx-1];
		for(int j = stdIdx - 1; j>=0; j--) {
			minScore = Math.min(minScore, scores[j]);
			maxScore = Math.max(maxScore, scores[j]);
			dp[stdIdx] = Math.max(dp[stdIdx], go(j) + maxScore - minScore);
		}
		return dp[stdIdx];
	}

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			scores = new int[n];
			dp = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				scores[i] = stoi(st.nextToken());
			}
			go(n);
			bw.write(dp[n] + "");
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
```
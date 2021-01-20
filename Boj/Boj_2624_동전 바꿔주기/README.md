# 2624_동전 바꿔주기

Algorithms: dp

Date: 2021/01/19

Level: G5, ☆

Link: https://www.acmicpc.net/problem/2624

### [문제정리]

* T원 지폐 -> 동전으로 바꿈.

* 동전 교환 방법 여러가지 -> 지폐를 동전으로 교환하는 방법 가지 수

* 방법의 수 2^31-1 초과 X

**입력**

* T : 지폐 금액(1~10000)

* k : 동전 가지 수(1~100)

* p(i), n : 각 동전의 금액(0~1000), 각 동전 개수

**출력**

* 동전 교환 방법의 가지 수

* 방법 없음 -> 0 출력

### [문제풀이]

- DP

    ㅇdp[i] = i값을 만들 수 있는 방법의 가지 수

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Coin {
	int p,n;
	Coin(){this.p=0;this.n=0;}
	Coin(int p, int n){this.p = p; this.n = n;}
}

public class Main {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int t = stoi(br.readLine());
			int k = stoi(br.readLine());
			Coin[] coinInfo = new Coin[k];
			int[] dp = new int[t+1];
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int p = stoi(st.nextToken());
				int n = stoi(st.nextToken());
				coinInfo[i] = new Coin(p,n);
			}
			
			dp[0] = 1;
			for(int i=0; i<k; i++) {
				Coin c = coinInfo[i];//현재 동전 값 & 개수
				for(int j=t; j>=1; j--) {//t~1까지 가능한 모든 동전 값
					for(int n=1; n<=c.n; n++) {
						int cost = c.p * n;//동전 값 * n 
						if(j-cost >= 0 && dp[j-cost] > 0) dp[j] += dp[j-cost];//j-cost 값을 만들 수 있는 경우의 수가 존재할 때 dp[j] += dp[j-cost]
					}
				}
			}
			
			bw.write(dp[t]+"");
			
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
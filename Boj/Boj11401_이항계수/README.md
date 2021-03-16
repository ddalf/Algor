# 11401_이항계수

Algorithms: math, 거듭제곱근, 페르마의 소정리

Date: 2021/03/10

Level : ☆, G1

Link : https://www.acmicpc.net/problem/11401

### 문제정리

- 자연수 N과 정수 K 주어졌을 때 이항계수 (N K)를 1,000,000,007로 나눈 나머지 구함

**[입력]**

- N, K : 1~4000000

**[출력]**

- 이항계수 1,000,000,007로 나눈 나머지

### 문제풀이

- 페르마의 소정리

![Untitled (1)](https://user-images.githubusercontent.com/42609000/111267647-94bf7800-866f-11eb-8abe-7b66b148cbd0.png)

A = n!, B = k!(n-k)!, P = 1000000007이라고 하면, 우리가 구해야 하는 식은 AB^-1%P이다.

![Untitled (2)](https://user-images.githubusercontent.com/42609000/111267651-9721d200-866f-11eb-9325-76c3297f0850.png)

![Untitled (3)](https://user-images.githubusercontent.com/42609000/111267656-9852ff00-866f-11eb-8b12-c9326ae76093.png)

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
	static final int P = 1000000007;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static long pow(long b, long ex) {
		long ret = 1;
		while(ex > 0) {
			if(ex % 2 == 1) {
				ret = ret * b % P;
			}
			ex /= 2;
			b = (b*b) % P;
		}
		return ret;
	}
	
	public static long factorial(int num) {
		long ret = 1;
		for(int i=2; i<=num; i++) {
			ret = ret * i % P;
		}
		return ret;
	}
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			int k = stoi(st.nextToken());
			long a = factorial(n);
			long b = factorial(k) * factorial(n-k) % P;
			long ans = a * pow(b, P-2) % P;
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
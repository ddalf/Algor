# 투에-모스 문자열

Algorithms: 분할정복

Date: 2021/05/22

Level: S1, △

Link: https://www.acmicpc.net/problem/18222

### 문제정리

0과 1로 이루어진 길이가 무한한 문자열 *X*가 있다. 이 문자열은 다음과 같은 과정으로 만들어진다.

1. *X*는 맨 처음에 "0"으로 시작한다.
2. *X*에서 0을 1로, 1을 0으로 뒤바꾼 문자열 *X'*을 만든다.
3. *X*의 뒤에 *X'*를 붙인 문자열을 *X*로 다시 정의한다.
4. 2~3의 과정을 무한히 반복한다.

즉, *X*는 처음에 "0"으로 시작하여 "01"이 되고, "0110"이 되고, "01101001"이 되고, ⋯ 의 과정을 거쳐 다음과 같이 나타내어진다.

"011010011001011010010110011010011001011001101001⋯⋯"

자연수 *k*가 주어졌을 때 *X*의 *k*번째에는 무슨 문자가 오는지 구하여라.

**[입력]**

- 첫 번째 줄에 자연수 k (1 ≤ k ≤ 1018) 가 주어진다.

**[출력]**

- 첫 번째 줄에 k번째에 오는 문자를 출력하라.

### 문제풀이

- 분할 정복
    - f(i) = !f(i-(2^k))
        - 2^k = i보다 작은 2의 거듭제곱 값 중 가장 큰 값

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static long[] arr;
	public static long stol(String s) {
		 return Long.parseLong(s);
	}
	static long tMos(long x) {
		if(x == 1) return 0;
		long maxI = 0;
		for(int i=0; i<arr.length; i++) {
			if(x > arr[i]) maxI = arr[i];
			else break;
		}
		return 1-tMos(x-maxI);
	}
	public static void main(String[] args) {
		try {
			arr = new long[64];//10^18 (2^10)^6 = (10^3)^6
			long n = stol(br.readLine());
			for(int i=0; i<arr.length; i++) {
				arr[i] = (long)Math.pow(2, i);//i번째 2의 거듭제곱 값
			}
			bw.write(tMos(n)+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
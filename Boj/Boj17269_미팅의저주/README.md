# 17268_미팅의 저주

Algorithms: combination, dp, math
Date: Jan 15, 2021
Level: G3, ☆
Link: https://www.acmicpc.net/problem/17268

[**문제정리]**

- N명 동시에 2명씩 짝 지어 악수
- 미팅 실패
    1. 사람의 팔 교차 됨
    2. 한 사람이라도 악수 하지 X

**입력**

- N : 미팅에 참가하는 사람의 수. (1~10000. 짝수)

**출력**

- 미팅에 성공하는 경우의 수 % 987654321

    

[**문제풀이]**

- 카탈란 수. DP 사용.    

![image](https://user-images.githubusercontent.com/42609000/104812816-3715d780-5848-11eb-8fa9-7ff7ad43e73f.png)
<<<<<<< HEAD

=======
=======
>>>>>>> e4a948839892cd8a0703a724711d907c0cedefe4
```cpp
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			int n = stoi(br.readLine());
			n/=2;//n은 짝수값만 들어옴 -> 2나눠줘서 범위 줄여줌
			long[] arr = new long[n+1];
			arr[0] = 1;
			for(int i=1; i<=n; i++) {
				for(int j=0; j<=i-1; j++) {
					arr[i] += (arr[j] % 987654321) * (arr[i-1-j] % 987654321);
					arr[i] %= 987654321;
				}
			}
			bw.write(arr[n] +"");
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
# 17608_막대기

Algorithms: simulation

Date: 2021/04/08

Level: B2, ○

Link: https://www.acmicpc.net/problem/17608

### 문제정리

아래 그림처럼 높이만 다르고 (같은 높이의 막대기가 있을 수 있음) 모양이 같은 막대기를 일렬로 세운 후, 왼쪽부터 차례로 번호를 붙인다. 각 막대기의 높이는 그림에서 보인 것처럼 순서대로 6, 9, 7, 6, 4, 6 이다. 일렬로 세워진 막대기를 오른쪽에서 보면 보이는 막대기가 있고 보이지 않는 막대기가 있다. 즉, 지금 보이는 막대기보다 뒤에 있고 높이가 높은 것이 보이게 된다. 예를 들어, 그림과 같은 경우엔 3개(6번, 3번, 2번)의 막대기가 보인다.

N개의 막대기에 대한 높이 정보가 주어질 때, 오른쪽에서 보아서 몇 개가 보이는지를 알아내는 프로그램을 작성하려고 한다.

**[입력]**

- N : 막대기의 수. 2~100,000
- h : N개의 줄 각각에 대한 막대기 높이. 1~100,000

**[출력]**

오른쪽에서 N개의 막대기를 보았을 때, 보이는 막대기의 개수를 출력

### 문제풀이

- n번째 막대기 → 1번째 막대기 순으로 이전에서 가장 높은 막대기 보다 높은 경우 결과값을 1씩 증가시킨다.

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			int n = stoi(br.readLine());
			int[] stick = new int[n];
			for(int i=0; i<n; i++) {
				stick[i] = stoi(br.readLine());
			}
			int last = stick[n-1];
			int ans = 1;
			for(int i=n-1; i>=0; i--) {
				if(stick[i] > last) {
					++ans;
					last = stick[i];
				}
			}
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
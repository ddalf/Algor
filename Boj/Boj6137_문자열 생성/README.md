# 6137_문자열 생성

Algorithms: simulation, two pointer

Date: 2021/04/06

Level: G3, △

Link: https://www.acmicpc.net/problem/6137

### 문제정리

- N개의 문자로 이루어진 문자열 S
- 문자열 S로 문자열 T를 만드는 규칙
    - 문자열 S의 가장 앞의 문자 하나를 문자열 T의 마지막에 추가
    - 문자열 S의 가장 뒤의 문자 하나를 문자열 T의 마지막에 추가
- 위 규칙으로 만들어진 문자열 T들 중 사전순으로 가장 빠른 문자열을 출력하는 프로그램

**[입력]**

- N : 문자열 S의 길이. 1~2000
- N개의 줄에 S를 이루는 문자들

**[출력]**

- 만들어진 사전순으로 가장 빠른 문자열을 출력
- 80글자마다 새줄 문자를 출력

### 문제풀이

- 시뮬레이션
    - 앞의 문자가 우선순위 높은 경우(frontChar < backChar)
    - 뒤의 문자가 우선순위 높은 경우(backChar < frontChar)
    - 우선순위 같은 경우
        - 앞 문자의 다음 문자, 뒷 문자의 이전 문자로 계속 이동하며 우선순위 다를 때까지 반복 문 돔
            - 두 문자 가르키는 곳이 겹치거나 넘어갈 경우 or 앞 문자의 다음 문자가 우선순위 높은 경우
            - 뒷 문자의 이전 문자가 우선순위 높은 경우

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static List<Character> chars;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			n = stoi(br.readLine());
			chars = new LinkedList<>();
			for(int i=0; i<n; i++) {
				chars.add(br.readLine().toCharArray()[0]);
			}
			StringBuffer sf = new StringBuffer("");
			int front = 0, back = n-1, idx = 0;
			
			while(front <= back) {
				++idx;
				char frontChar = chars.get(front);
				char backChar = chars.get(back);
				
				if(backChar < frontChar) {
					sf.append(backChar);
					--back;
				}
				else if(frontChar < backChar) {
					sf.append(frontChar);
					++front;
				}
				else {//frontChar == backChar
					int nextFront = front + 1;
					int beforeBack = back - 1;
					while(true) {
						if(nextFront > beforeBack || chars.get(nextFront) < chars.get(beforeBack)) {
							sf.append(frontChar);
							++front;
							break;
						}
						else if(chars.get(beforeBack) < chars.get(nextFront)){
							sf.append(backChar);
							--back;
							break;
						}
						++nextFront;
						--beforeBack;
					}
				}
				if(idx % 80 == 0) sf.append("\n");
			}
			bw.write(sf.toString());
			//출력 : 만즐어진 사전순으로 가장 빠른 문자열열80글자마다 새줄 문자를 출력해야 한다.
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
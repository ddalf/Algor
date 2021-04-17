# 16472_고냥이

Algorithms: 투 포인터

Date: 2021/04/17

Level: G2, △

Link: https://www.acmicpc.net/problem/16472

### 문제정리

- 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다.

**[입력]**

- n : 인식할 수 있는 알파벳의 종류의 최대 개수. 1~26
- 문자열 : 1~100,000. 알파벳 소문자만 포함

**[출력]**

- 번역기가 인식할 수 있는 문자열의 최대길이를 출력

### 문제풀이

- 투 포인터
    - right가 문자열 길이와 같아지기 전까지 증가
    - 현재 right 위치 문자의 개수 증가시켜줌(visit배열 증가)
        - 처음 사용된 문자일 경우 cnt증가
    - cnt가 n보다 작거나 같음 ⇒ maxLen 값 비교
    - cnt가 n보다 커질 경우
        - 사용된 서로 다른 문자 개수 줄여주어야 함
        - cnt개수 줄어들 떼까지 left 증가

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
			//최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다
			//문자열이 주어졌을 때 이 번역기가 인식할 수 있는 최대 문자열의 길
			int n = stoi(br.readLine());//2~26
			char[] words = br.readLine().toCharArray();//1~100,000
			int[] visit = new int[27];//left~right사이의 각각의 문자 개수 저장
			
			int left = 0;
			int right = 0;
			int ans = -1;
			int maxLen = -1;//최대길이
			++visit[words[0]-'a'];
			int cnt = 1;//현재 사용된 서로 다른 문자 개수
			while(true) {
				++right;
				if(right == words.length) break;
				if(visit[words[right]-'a']++ == 0) {//현재 right가 가르키는 문자 개수 증가
					++cnt;//처음 방문한 문자일 경우 cnt 증가
				}
				if(cnt <= n)maxLen = Math.max(maxLen, right - left + 1);//cnt가 n보다 작을 경우
				while(cnt > n && left < right) {//cnt가 n보다 큰 경우  사용 문자 개수 줄여줘야 함 => left 이동
					if(--visit[words[left]-'a'] == 0) --cnt;
					++left;
				}
			}
			bw.write(maxLen+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
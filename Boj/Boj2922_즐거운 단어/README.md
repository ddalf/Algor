# 2922_즐거운 단어

Algorithms: recursive, string

Date: 2021/04/08

Level: G4, △

Link: https://www.acmicpc.net/problem/2922

### 문제정리

- 즐거운 단어란
    - 모음(A,E,I,O,U)이 연속해서 3번, 자음(모음을 제외한 나머지 알파벳)이 연속해서 3번 나오지 않아야 한다.
    - L을 반드시 포함해야 한다.
- 보기 싫은 알파벳을 지운 단어가 주어졌을 때, 즐거운 단어를 만들 수 있는 경우의 수를 세는 프로그램

**[입력]**

- 상근이가 공책에 적은 단어
- 단어의 길이는 최대 100이고, 알파벳 대문자와 밑 줄(_)로만 이루어져 있다. 단어에 포함된 밑 줄의 개수는 최대 10이다.

**[출력]**

- 첫째 줄에, 밑 줄을 알파벳으로 바꿔 즐거운 단어를 만들 수 있는 경우의 수를 출력

### 문제풀이

- 백트래킹
    - long isGoodWord(int idx, int conCnt, int vowCnt, boolean lFlag)
        - idx : 현재 알파벳 인덱스 값
        - conCnt : 모음 개수
        - vowCnt : 자음 개수
        - lFlag : 'L'이 한번이라도 나왔을 경우 true / 아니면 false
    - '_'일 때 : 자음, 모음이 들어갈 수 있는 경우 나누어 isGoodWord 재귀 호출
    - 모음일 때
    - 자음일 때
- 오류 1 : 시간초과

    '_'가 나왔을 때 각 알파벳에 대한 경우 26가지로  isGoodWord를 호출

    ⇒해결 : 모음, 자음('L'제외, 'L'일 경우) 에 대한 결과값이 동일하므로, 3가지 경우로 나눠서 isGoodWord 재귀로 돌리고, return된 값에 대해 각 경우의 수의 개수를 곱함

- 오류 2 : 자료형

    힌트 - 정답은 64비트 정수 범위 ⇒ 결과값은 long형이다.

```java
import java.util.*;
import java.io.*;
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final int BIN = 0;
	static final int CON = 1;//모음
	static final int VOWEL = 2;//자음
	static int[] alphaNum;
	static char[] words;
	static StringBuilder sb = new StringBuilder();
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static int wordDis(int w) {
		if(w == '_') {
			return BIN;
		}
		else if(w == 'A' || w == 'E' || w == 'I' || w == 'O' || w == 'U') {
			return CON;
		}
		else return VOWEL;		
	}
	static long isGoodWord(int idx, int conCnt, int vowCnt, boolean lFlag)throws IOException{
		if(vowCnt == 3) return 0;
		if(conCnt == 3) return 0;
		if(idx == words.length) {
			if(lFlag) return 1;
			return 0;
		}
		char curChar = words[idx];
		int dis = wordDis(curChar);
		long ret = 0;
		//_일 때
		if(dis == BIN) {
			ret += isGoodWord(idx+1, conCnt+1, 0, lFlag) * 5;
			ret += isGoodWord(idx+1, 0, vowCnt+1, lFlag) * 20;
			ret += isGoodWord(idx+1, 0, vowCnt+1, true);			
		}
		//모음일 때
		else if(dis == CON) {
			if(curChar == 'L')ret = isGoodWord(idx+1, conCnt+1, 0, true);	
			else ret = isGoodWord(idx+1, conCnt+1, 0, lFlag);	
		}
		//자음일 때
		else {
			if(curChar == 'L')ret = isGoodWord(idx+1, 0, vowCnt+1, true);	
			else ret = isGoodWord(idx+1, 0, vowCnt+1, lFlag);	
		}
		return ret;
	}
	public static void main(String[] args) {
		try {
			//즐거운 단어  : 모음 (A,E,I,O,U)가 연속 3번, 자음이 연석해서 3번 나오지 않아햐 함+ L을 반드시 포함
			words = br.readLine().toCharArray();
			bw.write(isGoodWord(0,0,0,false)+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
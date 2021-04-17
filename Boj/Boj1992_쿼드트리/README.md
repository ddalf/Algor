# 1992_쿼드트리

Algorithms: 분할정복

Date: 2021/04/17

Level: S1, ☆

Link: https://www.acmicpc.net/problem/1992

### 문제정리

- 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.
- 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다.
- 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축

**[입력]**

- n : 영상의 크기를 나타내는 숫자
    - 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다.
- 길이 N의 문자열이 N개
    - 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.

**[출력]**

- 영상을 압축한 결과를 출력

### 문제풀이

- 분할정복
    - static void quadTree(int x, int y, int size)
        - 재귀함수. x,y에서 size크기만큼 배열 탐색

            ⇒ 모두 같을 경우 : 해당 값 출력

            ⇒ 같지 않은 값 있을 경우 : 영역 4개로 분할해서 재귀함수 호출

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] nums;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void quadTree(int x, int y, int size) throws IOException {
		boolean flag = true;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(nums[i][j] != nums[x][y]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			bw.write(nums[x][y]+"");
		}
		else {
			size /= 2;
			bw.write("(");
			quadTree(x,y,size);
			quadTree(x,y+size,size);
			quadTree(x+size,y,size);
			quadTree(x+size, y+size, size);
			bw.write(")");
		}
	}
	
	public static void main(String[] args) {
		try {
			int n = stoi(br.readLine());
			nums = new int[n][n];
			for(int i=0; i<n; i++) {
				char[] chars = br.readLine().toCharArray();
				for(int j=0; j<n; j++) {
					nums[i][j] = chars[j]-'0';
				}
			}
			quadTree(0,0,n);
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
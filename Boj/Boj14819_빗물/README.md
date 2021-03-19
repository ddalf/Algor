# 14719_빗물

Algorithms: simulation

Date: 2021/03/10

Level: G5, △

Link: https://www.acmicpc.net/problem/14719

### 문제정리

- 블록 쌓여 있는 사이 빗물 고이는 총 량

**[입력]**

- h : 높이 / w : 너비 1~500

**[출력]**

- 한 칸의 용량 1. 고이는 빗물의 총량 출력
- 빗물 고이지 않는 경우 : 0 출력

### 문제풀이

- 현재 위치의 왼쪽에서 가장 높은 블록 높이, 오른쪽에서 가장 높은 블록 높이 구함
- 왼쪽과 오른쪽 모두 현재 블록 높이 보다 높으면 그 중 작은 높이 - 현재 높이 를 총량에 더해줌

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
	static int[] blocks;
	static int h,w;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int findLeftMax(int curIdx) {
		int maxV = -1, maxIdx = -1;
		for(int i=0; i<curIdx; i++) {
			if(blocks[i] > maxV) {
				maxIdx = i;
				maxV = blocks[i];
			}
		}
		return maxV;
	}
	
	static int findRightMax(int curIdx) {
		int maxV = -1, maxIdx = -1;
		for(int i=curIdx+1; i<w; i++) {
			if(blocks[i] > maxV) {
				maxIdx = i;
				maxV = blocks[i];
			}
		}
		return maxV;
	}
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			h = stoi(st.nextToken());
			w = stoi(st.nextToken());
			blocks = new int[w];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<w; i++) {
				blocks[i] = stoi(st.nextToken());
			}
			int ans = 0;
			for(int i=1; i<w-1; i++) {
				int leftMax = findLeftMax(i);
				int rightMax = findRightMax(i);
				if(leftMax > blocks[i] && rightMax > blocks[i]) {
					ans += Math.min(leftMax, rightMax) - blocks[i];
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
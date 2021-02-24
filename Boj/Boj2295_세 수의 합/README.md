# 2295_세 수의 합

Algorithms: binary search

Date: 2021/02/24

Level: G5, △

Link: https://www.acmicpc.net/problem/2295

### 문제정리

- 집합 U : N개의 자연수들로 이루어짐
- 적당히 세수 고름 -> 그 세 수의 합 d도 U에 포함되는 경우
- 이 경우들 중 가장 큰 d

**[입력]**

- N : 집합 U의 원소 개수
- U의 원소 : 1~200,000,000

**[출력]**

- 세 수의 합 d도 U에 포함되는 경우 d의 최댓값

### 문제풀이

- x + y + z = k (x <= y <= z < k)
-> x + y = k - z 같아지는 최댓값 구함
    1. 입력받은 값 정렬 → x + y의 합 unionSumList에 저장
    2. k-z 의 값과 x+y의 값 binary search로 비교해서 동일해 질 때의 k값 구함.

```java
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static int[] unionArr;
	static ArrayList<Integer> unionSumList;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean binarySearch(int findNum) {
		int left = 0, right = unionSumList.size()-1;
		boolean flag = false;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(unionSumList.get(mid) == findNum) {
				flag = true;
				break;
			}
			else if(unionSumList.get(mid) < findNum) left = mid + 1;
			else right = mid - 1;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			n = stoi(br.readLine());
			unionArr = new int[n];
			for(int i=0; i<n; i++) {
				unionArr[i] = stoi(br.readLine());
			}

//1. 입력받은 값 정렬 → x + y의 합 unionSumList에 저장
			Arrays.sort(unionArr);
			unionSumList = new ArrayList<>();
			// X + Y = K - Z 중 X + Y 구해서 unionSumList에 저장
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					unionSumList.add(unionArr[i]+unionArr[j]);
				}
			}
			Collections.sort(unionSumList);//
			
			int ans = -1;
//2. k-z 의 값과 x+y의 값 binary search로 비교해서 동일해 질 때의 k값 구함.
			//X + Y 와 값이 같아지는 K-Z 이분탐색으로 찾아 최댓값 구함.
			for(int k=n-1; k>=0; k--) {//unionArr[k] == K
				for(int z=k-1; z>=0; z--) {//unionArr[z] == Z
					if(binarySearch(unionArr[k]-unionArr[z])) {
						ans = unionArr[k];
						break;
					}
				}
				if(ans > 0) break;
			}
			bw.write(ans+"");
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
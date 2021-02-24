package boj;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Boj2295 {
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
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			n = stoi(br.readLine());
			unionArr = new int[n];
			for(int i=0; i<n; i++) {
				unionArr[i] = stoi(br.readLine());
			}
			Arrays.sort(unionArr);
			unionSumList = new ArrayList<>();
			// X + Y = K - Z �� X + Y ���ؼ� unionSumList�� ����
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					unionSumList.add(unionArr[i]+unionArr[j]);
				}
			}
			Collections.sort(unionSumList);//
			
			int ans = -1;
			//X + Y �� ���� �������� K-Z �� �ִ� binary search�� ����.
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

/*
��������
* ���� U : N���� �ڿ������ �̷����
* ������ ���� �� -> �� �� ���� �� d�� U�� ���ԵǴ� ���
* �� ���� �� ���� ū d
[�Է�]
* N : ���� U�� ���� ����
* U�� ���� : 1~200,000,000
[���]
*  �� ���� �� d�� U�� ���ԵǴ� ��� d�� �ִ�

����Ǯ��
* x + y + z = k (x <= y <= z < k)
 -> x + y = k - z;
*/
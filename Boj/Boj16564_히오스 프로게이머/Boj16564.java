package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16564 {
	static int n;
	static int k;
	static int[] xArr;
	static BufferedReader br;
	static BufferedWriter bw;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean isPossibleT(long t) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			if(t <= xArr[i]) break;
			sum += t - (long)xArr[i];
		}
		return k >= sum;
	}
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			k = stoi(st.nextToken());
			xArr = new int[n];
			for(int i=0; i<n; i++) {
				xArr[i] = stoi(br.readLine());
			}
			Arrays.sort(xArr);
			long left =0, right = xArr[n-1] + (long)k;
			while(left <= right) {
				long mid = (left + right) / 2;
				if(isPossibleT(mid)) left = mid + 1;
				else right = mid - 1;
			}
			bw.write(left+"");
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
* N���� ĳ����, ĳ���� �� ����  X(i)
* ������ ���� K��ŭ �ø� �� O
* �� ��ǥ���� T = min(X(i)).
* ���� ���� �� ���� �����̰� �޼��� �� �ִ� �ִ� �� ��ǥ ����
[�Է�]
* N : ĳ���� ����. 1~1,000,000
* K : �ø� �� �ִ� ���� ����. 1~1,000,000,000
* X(i) : �� ĳ���� ����. 1~1,000,000,000
[���]
* ������ �ִ� �� ��ǥ���� T

����Ǯ��
* 1. �̺�Ž��.
* 2. 
*/

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj1477 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n,m,l;
	static int[] storeDis;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isPossibleDis(int d) {
		int newStoreCnt = 0;
		for(int i=1; i<n+2; i++) {
			int dis = storeDis[i] - storeDis[i-1];
			newStoreCnt += (dis / d);
			if(dis % d == 0) --newStoreCnt;//������ �������� ��� ��ġ�� ������
		}
		
		return newStoreCnt <= m;
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st; 
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			l = stoi(st.nextToken());
			storeDis = new int[n+2];
			st = new StringTokenizer(br.readLine());

			for(int i=1; i<=n; i++) {
				storeDis[i] = stoi(st.nextToken());
			}
			storeDis[0] = 0;
			storeDis[n+1] = l;
			Arrays.sort(storeDis);
			
			int left = 1, right = l-1;
			while(left<=right) {
				int mid = (left + right) / 2;
				if(isPossibleDis(mid)) right = mid - 1;
				else left = mid + 1;
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
* n�� �ް���.
* �ްԼ� ��ġ : �ްԼ� ����~��ŭ ������ �ִ���.
* �ްԼ� �ִ� ���� �� �ްԼ� ���� �� X.
* ��ӵ��� �� -> �ްԼ� ���� �� X.
* ��� �ްԼ� �湮�Ϸ��� �� �� M�� �� ��� �ްԼ� ���� ������ ���� �ִ��� �ּҷ� ��.
[�Է�]
* n : ���� �ްԼ� ����. 1~100
* m : �� �������� �ϴ� �ްԼ� ����. 1~100
* l : ��ӵ��� ����.100~1000
* ��� �ްԼ� ��ġ �ߺ� X. n+m < l.

[���]
* m���� �ްԼ� ���� �� �� �ްԼ� ���� ������ �ִ��� �ּڰ�.

����Ǯ��
1. �̺�Ž��
	�Ÿ��� �����ؾ� ��. 
	* left : �ްԼ� �ּ� ��ġ
	* right : �ްԼ� �ִ� ��ġ
	* mid : �߰� ��. mid�������� �ްԼ� ������ �� mid �ּڰ�
2. (mid �������� �ްԼ� ������ �� ���� �ްԼ��� ��) newStoreCnt �� m ��
	* newStoreCnt <= m : ������ ���� ����. ���� �ٿ��� ��. right = mid - 1
	* newStoreCnt > m : ������ ���� ����. ���� ������ ��. left = mid + 1
*/
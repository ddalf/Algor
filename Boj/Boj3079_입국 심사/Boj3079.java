package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj3079 {
	static int n;
	static int m;
	static int[] times;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}	
	
	public static boolean isPossibleTime(long t) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			sum += t / times[i];
		}
		return sum >= m;
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			times = new int[n];
			
			for(int i=0; i<n; i++) {
				times[i] = stoi(br.readLine());
			}
			Arrays.sort(times);
			long left =1, right = (long)times[n-1]*m;
			while(left <= right) {
				long mid = (left+right)/2;
				if(isPossibleTime(mid)) right = mid-1;
				else left = mid+1;
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
�����, ģ���� : M��
�Ա��ɻ�� : N��. �� ó�� ��� ��� ����.
	�� ���� �� �� �ɻ� ����.
�Ա��ɻ�� : �ɻ� �ϴµ� �ɸ��� �ð� ��� �ٸ�
Tk : k�� �ɻ�뿡 �ɾ��ִ� �ɻ���� �� �� �ɻ� �ϴµ� ��� �ð�
���� �տ� �ִ� ��� -> �� �ɻ�뿡�� �ɻ� ���� �� O.
�׻� �̵��ϴ� ���� X. �� ���� �ɻ�� �ɻ� ������ ��ٸ��� �ű⼭ �ɻ� ���� �� o.

[�Է�]
* n : �Ա��ɻ�� ����. 1~100,000
* m : �Ա��ɻ� ���� �� �� : 1~1,000,000,000
* T(k) : �� �ɻ�뿡�� �ɻ� �ϴµ� �ɸ��� �ð�. 1~1,000,000,000

[���]
�ɻ� ��ġ�µ� �ɸ��� �ð��� �ּڰ�

����Ǯ��
1. �̺�Ž�� -> ������ �ð� ����
	left = ������ �ּ� �ð� ũ��
	right = ������ �ִ� �ð� ũ��
	mid = �߰���. mid ���� �ð����� ������ �ɻ簡�� �ο� �� Ȯ��
2. mid �ð� ���� �ɻ��� �� �ִ� �ο� ��
	* �ɻ� �ο� m�� �̻� : ������ �ִ� �ð� ����(right=mid-1) -> �ɻ��� �� �ִ� �ο� �� ����
	* �ɻ� �ο� m�� ���� : ������ �ּ� �ð� �ø� (left=mid+1) -> �ɻ��� �� �ִ� �ο� �� �ø�
*/
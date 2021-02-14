package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2110 {
	private static int n;
	private static int c;
	private static int[] housePos;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean isPossibleDist(int gap) {
		int cnt = 1;
		int cur = housePos[0];
		for(int i=1; i<n; i++) {//cur��ġ ~ i��° ��ġ�� ���̰� gap���� Ŀ���� i��° ��ġ�� �� ������ ��ġ
			if(housePos[i]-cur >= gap) {
				++cnt;//��ġ ������ �� ����
				cur = housePos[i];
			}
		}
		return cnt >= c;
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			c = stoi(st.nextToken());
			housePos = new int[n];
			for(int i=0; i<n; i++) {
				housePos[i] = stoi(br.readLine());
			}
			Arrays.sort(housePos);
			int left = 1, right = housePos[n-1];
			while(left <= right) {
				int mid = (left+right)/2;
				if(isPossibleDist(mid)) left = mid + 1;
				else right = mid - 1;
			}
			bw.write(right+"");
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
[��������]
������ �� : N��. ������ ���� ����. �� ��ǥ x1, ... xn. ���� ��ǥ ������ X
������ : C�� ��ġ. �ִ��� ���� ������ �������� ����Ϸ��� ��.
	�� ���� �ϳ��� ��ġ ����.
	���� ������ �� ������ ������ �Ÿ� ������ ũ��.

�Է�
N : �� ����. 2~200,000
C : ������ ����.
xi : 0~1,000,000,000

���
���� ������ �� ������ ������ �ִ� �Ÿ�

[����Ǯ��]
1. �̺� Ž�� �̿� -> ������ �������� �ּ� �Ÿ� ����
left = ������ �ּ� �Ÿ�
right = ������ �ִ� �Ÿ�
mid = �߰���. mid �̻��� �������� ������ 3�� ��ġ �� �� �ִ��� Ȯ��

2. mid �������� ��ġ���� ����
- ��ġ �� ���� c �̻� : ������ ������ �Ÿ� �ø� -> ��ġ �� �ٿ��� ��
- ��ġ �� ���� c �̸� : ������ ������ �Ÿ� ���� -> ��ġ �� �÷��� ��
*/
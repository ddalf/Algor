package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2343 {
	static int n;
	static int m;
	static int[] lessons;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isPossibleSize(int bSize) throws IOException {
		int sum = 0, cnt = 1;
		for(int i=0; i<n; i++) {
			if(lessons[i] > bSize) return false; //���� �ϳ��� �ִ� �����ð����� �� �� x.
			sum += lessons[i];
			if(sum > bSize) {
				sum = lessons[i];
				++cnt;
			}
		}
		return m >= cnt;
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			lessons = new int[n];
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i=0; i<n; i++) {
				lessons[i] = stoi(st.nextToken());
				sum += lessons[i];
			}
			int left = 1, right = sum, mid = 0;
			while(left <= right) {
				mid = (left+right) / 2;
				if(isPossibleSize(mid)) right = mid-1;//���Ե� ���� m�� ����
				else left = mid+1;//���Ե� ���� m�� �ʰ�
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
��緹�� : N���� ���� �� ����
��ȭ �� -> ��ȭ�� ���� �ٲ�� X.
i�� ����, j�� ���� ���� ��緹�̿� ��ȭ -> i�� j ���� ��� ������ ��緹�̿� ��ȭ
��ȭ ��緹�� �� : M�� -> ��緹�� ũ�� �ּ�. M�� ��緹�� ��� ���� ũ��
�� ������ ���� �� ���� -> ������ ��緹�� ũ�� �� �ּ�

�Է�
N : ������ ��. 1~100000
M : ��ȭ ��緹�� ��. 1~N
���� ���� : 1~10000

���
������ ��緹�� ũ�� �� �ּ� ���

����Ǯ��
1. �̺�Ž�� Ǯ�� -> ������ ��緹��ũ ũ�� ����
left = ������ �ּ� ��緹�� ũ��
right = ������ �ִ� ��緹�� ũ��
mid = �߰���. mid ���� ũ��� ������ ��緹�� ����  Ȯ��

2. mid ũ��� ������ �� �ִ� ���� ����
* ���Ե� ���� m�� ���� : ������ ��緹�� ũ�� ����(right = mid-1) -> �� ��緹�̿� ���Ե� ���� �� ����
* ���Ե� ���� m�� �ʰ� : ������ ��緹�� ũ�� �ø�(left = mid+1) -> �� ��緹�̿� ���Ե� ���� �� �ø�
*/
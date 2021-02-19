package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2467 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int n;
	public static int[] liquids;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			liquids = new int[n];
			for(int i=0; i<n; i++) {
				liquids[i] = stoi(st.nextToken());
			}

			int minMixV = 2000000001;
			int resultL = -1, resultR = -1;
			for(int i=0; i<n; i++) {
				int left = i+1, right = n-1;
				while(left <= right) {
					int mid = (left+right)/2;
					int mixV = liquids[i]+liquids[mid];
					if(minMixV > Math.abs(mixV)) {
						minMixV = Math.abs(mixV);
						resultL = liquids[i];
						resultR = liquids[mid];
					}
					if(mixV < 0) left = mid + 1;
					else right = mid - 1;
				}
			}
			bw.write(resultL + " " + resultR);			
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
/*
��������
* �뿪 Ư�� ��Ÿ���� ���� 
* �꼺 ��� : 1~1,000,000,000 ���� ����.
* ��Į���� �ˤ��� : -1~-1,000,000,000 ���� ����
* ȥ�� ��� Ư���� = ȥ�տ� ���� �� ����� Ư���� ��
* �k���� 0�� ����� ��� ����� ��.
* EX. -99, -2, -1, 4, 98 -> -99 + 98 = -1 -> ���� 0 �� ����� ���
* �� ���� ��Į�� or �� ���� �꼺 ������ 0�� ������ ����� ��� ��
* �꼺 ��� , ��Į���� ��� Ư���� ���ĵ� ������ �־���

[�Է�]
* N : ��ü ����� ��. 2~100,000
* ����� Ư���� : -1,000,000,000 ~ + 1,000,000,000

[���]
* 0�� ���� ����� ����� ������ �� ����� Ư����
* ��� ��� Ư���� �������� ���.
* ��� �� �� �̻��� ��� �ƹ��ų� �ϳ� ���


����Ǯ��

*/
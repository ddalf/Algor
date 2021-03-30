package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class Boj17245 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static long sum;
	static int[][] room;
	static int[] height;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			n = stoi(br.readLine());//������ ũ��
			room = new int[n][n];
			height = new int[10000001];
			sum = 1;
			StringTokenizer st;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					room[i][j] = stoi(st.nextToken());
					sum += room[i][j];
					++height[room[i][j]];
				}
			}
			
			sum /= 2;//���� �̻� ��ǻ�� ������ ��
			long onSum = 0;//���� ��ü ��ǻ�� ��
			int time=0, on=n*n;//on: ���� ���� ��ǻ�� ��
			while(onSum < sum) {//���� ��ǻ�� �� < ��ü�� �ݺ��� Ŀ���� �ݺ��� ����
				on -= height[time++];//ex) 0�϶� �� ���� 0�ΰ� �����ϰ� ��� ���� => ��ü - ����[0]
				onSum += on;
			}
			
			bw.write(time+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

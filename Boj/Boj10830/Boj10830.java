package boj;
import java.util.*;
import java.io.*;

public class Boj10830 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static long arr[][];
	public static long ans[][];
	public static int n;
	public void solve() throws Exception {
		n = Integer.parseInt(br.readLine());
		long b = Long.parseLong(br.readLine());
		arr = new long[n][n];
		ans = new long[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = Long.parseLong(br.readLine());
			}
		}	
	}
	
}

/*
��� : n*n
a�� b���� ���ϴ� ���α׷�
a^b�� �� ���� 1000���� ���� ������

n: 2~5;
b: 1,000,000,000,000
*/
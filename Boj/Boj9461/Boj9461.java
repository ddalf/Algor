package boj;
import java.util.*;

class Boj9461 {
	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		long[] arr= new long[105];
		arr[1] = 1; arr[2] = 1; arr[3] = 1; arr[4] = 2; arr[5] = 2;
		
		for(int i=6; i<=100; i++) {
			arr[i] = arr[i-1] + arr[i-5];
		}
		
		for(int i=0; i<n; i++) {
			int query = s.nextInt();
			System.out.println(arr[query]);
		}
		
	}
}
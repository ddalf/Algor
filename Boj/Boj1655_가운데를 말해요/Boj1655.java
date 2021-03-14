package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1655 {
	static BufferedReader br;
	static BufferedWriter bw;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			int n = stoi(br.readLine());
			int[] arr = new int[n];
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			
			for(int i=0; i<n; i++) {
				arr[i] = stoi(br.readLine());
				if(minHeap.size() < maxHeap.size()) {
					minHeap.add(arr[i]);
				}
				else {
					maxHeap.add(arr[i]);
				}

				if(minHeap.size() != 0 && maxHeap.peek() > minHeap.peek()) {
					int maxV = maxHeap.poll();
					int minV = minHeap.poll();
					maxHeap.add(minV);
					minHeap.add(maxV);
				}
				bw.write(maxHeap.peek()+"\n");
			}
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj19591 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static Deque<Long> numDq = new LinkedList<>();
	static Deque<Integer> opsDq = new LinkedList<>();
	static int[] priority = {1,1,2,2};
	public static long stol(String s) {
		 return Long.parseLong(s);
	}
	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static long calc(int ops, long a, long b) throws IOException{
		long rt = 0;
		switch(ops) {
		case 0 : rt = a + b; break;
		case 1 : rt = a - b; break;
		case 2 : rt = a * b; break;
		case 3 : {
			rt = Math.abs(a) / Math.abs(b); 
			if(a < 0 && b > 0 || a > 0 && b < 0) rt = -rt;
			break;
			}
		}
		return rt;
	}
	public static void check() throws IOException {
		while(!opsDq.isEmpty()) {
			int fOps = opsDq.peekFirst();
			int lastOps = opsDq.peekLast();
			if(opsDq.size()==1 || priority[fOps] > priority[lastOps]) {
				numDq.addFirst(calc(fOps, numDq.pollFirst(), numDq.pollFirst()));
				opsDq.pollFirst();
			}
			else if(priority[fOps] < priority[lastOps]) {
				long last = numDq.pollLast();
				numDq.addLast(calc(lastOps, numDq.pollLast(), last));//순서 주의
				opsDq.pollLast();
			}
			else {
				long frontNum = numDq.pollFirst();
				long backNum = numDq.pollLast();
				long frontRs = calc(fOps, frontNum, numDq.peekFirst());
				long lastRs = calc(lastOps, numDq.peekLast(), backNum);//순서 주의
				if(frontRs >= lastRs) {
					numDq.addLast(backNum);
					numDq.pollFirst();
					numDq.addFirst(frontRs);		
					opsDq.pollFirst();
				}
				else {
					numDq.addFirst(frontNum);
					numDq.pollLast();
					numDq.addLast(lastRs);	
					opsDq.pollLast();
				}
			}
		}
		
		long ans = numDq.pollFirst();
		bw.write(ans+"\n");
	}
	
	public static void solve() {
		try {
			numDq = new LinkedList<>();
			opsDq = new LinkedList<>();
			
			char[] equations = br.readLine().toCharArray();
			boolean minusFlag = false;
			int i = 0;
			if(equations[0] == '-') {
				minusFlag = true;
				++i;
			}
			for(; i<equations.length; i++) {
				char op = equations[i];
				if(op >= '0' && op <= '9') {
					long num = op -'0';				
						while(true) {
							if(i+1 == equations.length || equations[i+1] < '0' || equations[i+1] > '9') break;	
							op = equations[++i];									
							num = num * 10 + (op-'0');			
						}
					numDq.add(num);
				}
				else {
					switch(op) {
						case '+' : opsDq.add(0); break;
						case '-' : opsDq.add(1); break;
						case '*' : opsDq.add(2); break;
						case '/' : opsDq.add(3); break;
					}
				}
			}
			if(minusFlag) numDq.add(-numDq.pollFirst());
			check();
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

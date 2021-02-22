package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int num, weight;
	Node(){
		this.num = 0;
		this.weight = 0;
	}
	
	Node(int num, int weight){
		this.num = num;
		this.weight = weight;
	}
}
public class Boj1967 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int nodeNum;
	static ArrayList<Node>[] tree;
	static boolean[] visited;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static Node getTreeDia(int stIdx) { 	
		Node maxDisNode = new Node(0,0);
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(stIdx, 0));
		visited[stIdx] = true;
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			for(int i=0; i<tree[curNode.num].size(); i++) {
				int nextIdx = tree[curNode.num].get(i).num;
				if(!visited[nextIdx]) {
					int nextWeight = curNode.weight + tree[curNode.num].get(i).weight;
					q.add(new Node(nextIdx, nextWeight));
					visited[nextIdx] = true;
					if(maxDisNode.weight < nextWeight) {
						maxDisNode.num = nextIdx;
						maxDisNode.weight = nextWeight;
					}
				}
			}
		}
		return maxDisNode;
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			nodeNum = stoi(br.readLine());
			if(nodeNum == 1) {
				bw.write("0");
			}
			else {
				tree= new ArrayList[nodeNum+1];
				visited = new boolean[nodeNum+1];
				for(int i=1; i<=nodeNum; i++) {
					tree[i] = new ArrayList<>();
				}
				for(int i=1; i<nodeNum; i++) {
					st = new StringTokenizer(br.readLine());
					int parent = stoi(st.nextToken());
					int child = stoi(st.nextToken());
					int cost = stoi(st.nextToken());
					tree[parent].add(new Node(child, cost));
					tree[child].add(new Node(parent, cost));
				}
				//1. ��Ʈ���� ���� �� �Ÿ��� �ִ� ���
				Arrays.fill(visited, false);
				int maxDisIdx = getTreeDia(1).num;
				//2. 1������ ã�� ��忡�� ���� �� �Ÿ��� �ִ� ���
				Arrays.fill(visited, false);
				int maxWeight = getTreeDia(maxDisIdx).weight;
				
				bw.write(maxWeight+"");
			}
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
* Ʈ�� : ����Ŭ X ������ �׷���. ��� �ϳ��� ����
* Ʈ���� ���� : � �� ��� �����ؼ� �������� ��� �� ���� ��� �þ�� ���.
* 	Ʈ���� ��� ��� -> �� ��� ������ �� ������ �ϴ� �� �ȿ� ��
* ��Ʈ�� �ִ� Ʈ�� ����ġ �ִ� ������� �� -> Ʈ���� ���� ���ؼ� ���
[�Է�]
* n : ����� ����
* ���� ���� :  �� ���� ����. �θ� ��� / �ڽ� ��� / ����ġ
	* �θ� ����� ��ȣ ���� �� ���� �Է�
	* �θ� ��� ��ȣ ���� -> �ڽ� ��� ��ȣ ���� �� ���� �Է�
	* ��Ʈ ��� : �׻� 1.
	* ���� ����ġ : 1~100

[���]
Ʈ���� ���� ���

����Ǯ��

*/
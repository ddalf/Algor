package boj;

public class Triple implements Comparable<Triple>{
	int x;
	int y;
	int v;
	
	Triple(){
		this.x = 0;
		this.y = 0;
		this.v = 0;
	}
	
	Triple(int x, int y, int v){
		this.x = x;
		this.y = y;
		this.v = v;
	}

	@Override
	public int compareTo(Triple t) {
		return this.v - t.v;
	}
}

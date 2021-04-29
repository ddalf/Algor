# 야근지수

Do: Yes

Level: 3

분류: 연습문제

푼 날짜: 2021/04/29

- 가장 큰 값을 줄이는 방식으로 생각해서 품

    ⇒ Priority Queue 사용

```java
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work:works){
            pq.add(work);
        }
        while(n > 0){
            int w = pq.poll();
            if(w == 0) break;
            --w;
            --n;
            pq.add(w);
        }
        while(!pq.isEmpty()){
            int w = pq.poll();
            answer += (long)w * w;   
        }
        return answer;
    }
}
//1 2 3 4 5 7 
//3

//1 2 3 3 4 6 = 1 4 9 9 16 36
//1 2 3 4 5 4 = 1 4 9 16 25 16
```
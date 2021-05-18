# 컬러링북

Algorithms: bfs, simulation

Date: 2021/05/18

Level: Level2, ○

Link: https://programmers.co.kr/learn/courses/30/lessons/1829?language=java

### 문제정리

출판사의 편집자인 어피치는 네오에게 컬러링북에 들어갈 원화를 그려달라고 부탁하여 여러 장의 그림을 받았다. 여러 장의 그림을 난이도 순으로 컬러링북에 넣고 싶었던 어피치는 영역이 많으면 색칠하기가 까다로워 어려워진다는 사실을 발견하고 그림의 난이도를 영역의 수로 정의하였다. (영역이란 상하좌우로 연결된 같은 색상의 공간을 의미한다.)

그림에 몇 개의 영역이 있는지와 가장 큰 영역의 넓이는 얼마인지 계산하는 프로그램을 작성해보자.

**[입력]**

- `1 <= m, n <= 100`
- `picture`의 원소는 `0` 이상 `2^31 - 1` 이하의 임의의 값이다.
- `picture`의 원소 중 값이 `0`인 경우는 색칠하지 않는 영역을 뜻한다.

**[출력]**

리턴 타입은 원소가 두 개인 정수 배열이다. 그림에 몇 개의 영역이 있는지와 가장 큰 영역은 몇 칸으로 이루어져 있는지를 리턴한다.

### 문제풀이

- 번호가 주어진 배열의 모든 요소에 대해 bfs로 현재 영역의 번호 같은 붙어있는 영역을 모두 탐색하는 방식
- 이미 체크된 영역은 다시 검사하지 않는다.

```java
import java.util.*;
class Solution {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;
    int[][] visit;
    int row,col;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    void findArea(int sx, int sy, int[][] picture){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx,sy});
        visit[sx][sy] = ++numberOfArea;
        int cnt = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            ++cnt;
            for(int dir = 0; dir<4; dir++){
                int xx = cur[0] + dx[dir];
                int yy = cur[1] + dy[dir];
                if(xx < 0 || yy < 0 || xx >= row || yy >= col) continue;
                if(visit[xx][yy] != 0 || picture[xx][yy] != picture[cur[0]][cur[1]]) continue;
                q.add(new int[]{xx,yy});
                visit[xx][yy] = numberOfArea;
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
    }
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        row = m;
        col = n;
        
        visit = new int[row][col];
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(visit[i][j] == 0 && picture[i][j] > 0){
                    findArea(i,j,picture);
                }
            }
        }
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
```
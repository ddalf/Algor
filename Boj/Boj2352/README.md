


**문제정리**

- 반도체 설계. n개의 포트 - 다른 n개 포트와 연결
n개의 포트가 다른 n개의 포트와 어떻게 연결되어야 하는지 주어짐
-> 연결선이 서로 꼬이지 않도록 하면서 연결할 수 있는 최대의 개수

**문제풀이**

- Binary Search 이용 → 실제 원하는 값과는 다르게 나오지만 길이는 같음
- LIS 개념 사용. 가장 긴 증가하는 수열
1. 현재 port 값이 lis배열의 첫번째 값보다 작으면 첫번째 값을 현재 port 값으로 대체
2. lis 배열의 마지막 값보다 크면 lis 배열 맨 끝에 추가
3. 1,2 에 속하지 않을 경우(중간 값)

    Arrays의 binarySearch 사용. 

    (cf. lower bound.

    -(low + 1) 반환 = -(찾고자 하는 값 이상 나오는 위치 + 1) = -(배열 길이 + 1)

**다른 풀이**
- Segment Tree 사용하여 풀 수 있음


>**ref.**  [https://steady-coding.tistory.com/48](https://steady-coding.tistory.com/48)

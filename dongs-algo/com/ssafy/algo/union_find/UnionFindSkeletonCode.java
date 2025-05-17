package com.ssafy.algo.union_find;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnionFindSkeletonCode {
  static int[] parent;
  public static void main(String[] args) {
    int n = 8;
    init(n);

    System.out.println("초기 상태에서 각 노드의 부모 : ");
    printParents();

    union(1, 2);
    union(3, 4);
    union(5, 6);
    union(7, 8);
    printParents();


    union(1, 3);
    union(5, 7);
    printParents();

    union(1, 5);
    System.out.println("\n최종 union 연산 후 부모:");
    printParents();

    System.out.println("\n연결 확인 테스트:");
    System.out.println("노드 1과 8은 연결되어 있는가? " + connected(1, 8));
    System.out.println("노드 2와 5는 연결되어 있는가? " + connected(2, 5));
    System.out.println("노드 3과 7은 연결되어 있는가? " + connected(3, 7));
    System.out.println("노드 4과 6은 연결되어 있는가? " + connected(4, 6));
    System.out.println();
    printParents();
  }

  public static void printParents() {
    for (int i = 1; i < parent.length; i++) {
      System.out.print("노드 " + i + "의 부모 = " + parent[i] + " | ");
    }
    System.out.println();
  }

  public static void init(int n) {
    // 1 ~ n으로 가정
    parent = new int[n + 1];

    for(int i = 1; i <= n; i++) {
      parent[i] = i;
    }
  }

  public static void union(int x, int y) {
    x = find(x);
    y = find(y);

    // 이미 같은 집합일경우
    if(x == y) return;

    // 작은쪽이 부모 노드가 된다.
    if(x > y) {
      parent[x] = y;
    }else {
      parent[y] = x;
    }
  }

  public static int find(int x) {
    // 자기자신 == 부모라면 리턴
    if(parent[x] == x) {
      return x;
    }
    // 경로압축 -> 부모노드를 통일시킨다.
    return parent[x] = find(parent[x]);
  }

  // 같은 집합에 속해있는지 확인
  public static boolean connected(int x, int y) {
    return find(x) == find(y);
  }

}

package com.ssafy.algo.topological_sorting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortingSkeletonCode {

  static int[] D;
  static List<List<Integer>> graph;
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    int n = 4;
    graph = new ArrayList<>();
    D = new int[n+1];
    for(int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    graph.get(1).add(2);
    D[2]++;
    graph.get(1).add(3);
    D[3]++;
    graph.get(2).add(4);
    D[4]++;
    graph.get(3).add(4);
    D[4]++;

    Queue<Integer> q = new ArrayDeque<>();

    // 진입차수가 0인 노드들 삽입
    for(int i = 1; i <= n; i++) {
      if(D[i] == 0) {
        q.offer(i);
      }
    }

    while(!q.isEmpty()) {
      int curr = q.poll();
      sb.append(curr).append(" ");
      for(int next : graph.get(curr)) {
        D[next]--;
        if(D[next] == 0) {
          q.offer(next);
        }
      }
    }

    System.out.println(sb);
  }
}

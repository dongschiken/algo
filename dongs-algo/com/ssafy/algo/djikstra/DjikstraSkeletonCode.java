package com.ssafy.algo.djikstra;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DjikstraSkeletonCode {

  static int[] dist;
  static int[] prev;
  static List<List<Node>> graph;

  static class Node implements Comparable<Node> {
    int vertex;
    int distance;

    public Node(int vertex, int distance) {
      this.vertex = vertex;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
      return Integer.compare(this.distance, other.distance);
    }

  }

  public static void main(String[] args) {
    // 노드 개수 5개
    int N = 5;
    init(N);
    dist = new int[N + 1];
    prev = new int[N + 1];
    // 간선 추가
    addEdge(1, 2, 10);
    addEdge(1, 3, 10);
    addEdge(2, 4, 10);
    addEdge(3, 5, 10);
    addEdge(5, 3, 10);
    addEdge(4, 5, 10);

    Arrays.fill(dist, 100_000_000);
    Arrays.fill(prev, -1);
    // 1번 노드에서 시작한다고 가정
    dist[1] = 0;
    
    djikstra(1);
    // 5번을 도착 노드라고 가정
    System.out.println(dist[5]);
  }

  private static void addEdge(int from, int to, int weight) {
    graph.get(from).add(new Node(to, weight));
  }

  public static void init(int N) {
    graph = new ArrayList<>();
    for(int i = 1; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
  }
  public static void djikstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    while(!pq.isEmpty()) {
      Node curr = pq.poll();

      if(curr.distance > dist[curr.distance]) continue;

      for (Node next : graph.get(curr.vertex)) {
        // newDistance = A -> B -> C 까지의 거리
        int newDistance = curr.distance + next.distance;
        // dist[next.vertex] = A -> C 까지의 거리
        if(dist[next.vertex] > newDistance) {
          dist[next.vertex] = newDistance;
          // 최단 경로를 구해야할 경우
          prev[next.vertex] = curr.vertex;
          pq.offer(new Node(next.vertex, dist[next.vertex]));
        }
      }
    }
  }

  public static List<Integer> getPath(int start, int end) {
    List<Integer> path = new ArrayList<>();

    // 경로가 존재하지 않는 경우
    if (dist[end] == Integer.MAX_VALUE) {
      return path;
    }

    // 역추적으로 경로 구성
    for (int at = end; at != -1; at = prev[at]) {
      path.add(at);
    }

    // 시작점 -> 도착점 순서로 반환
    Collections.reverse(path);
    return path;
  }
}

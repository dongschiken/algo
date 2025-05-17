package com.ssafy.algo.implementation;

import java.io.*;
import java.util.*;

public class Baekjoon15644 {

  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M, min;
  static char[][] map;
  static boolean[][][][] visited;
  static class Node {
    int redR, redC, blueR, blueC, cnt;
    String path;

    public Node(int redR, int redC, int blueR, int blueC, int cnt, String path) {
      this.redR = redR;
      this.redC = redC;
      this.blueR = blueR;
      this.blueC = blueC;
      this.cnt = cnt;
      this.path = path;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new char[N][M];
    visited = new boolean[N][M][N][M];
    int[] s = new int[4];
    for(int r = 0; r < N; r++) {
      String str = br.readLine();
      for(int c = 0; c < M; c++) {
        map[r][c] = str.charAt(c);
        if(map[r][c] == 'R') {
          s[0] = r;
          s[1] = c;
        } else if(map[r][c] == 'B') {
          s[2] = r;
          s[3] = c;
        }
      }
    }

    Queue<Node> q = new ArrayDeque<>();
    q.offer(new Node(s[0], s[1], s[2], s[3], 0, ""));
    visited[s[0]][s[1]][s[2]][s[3]] = true;
    Node n = bfs(q);
    if(n == null) {
      System.out.println(-1);
    }else {
      System.out.println(n.cnt);
      System.out.println(n.path);
    }
  }
  public static Node bfs(Queue<Node> q) {
    while(!q.isEmpty()) {
      Node curr = q.poll();

      if(curr.cnt >= M) {
        continue;
      }

      for(int i = 0; i < 4; i++) {
        int nRedR = curr.redR;
        int nRedC = curr.redC;
        int nBlueR = curr.blueR;
        int nBlueC = curr.blueC;
        boolean redHole = false;
        boolean blueHole = false;
        // 레드
        while(map[nRedR + dr[i]][nRedC + dc[i]] != '#') {
          nRedR += dr[i];
          nRedC += dc[i];

          if(map[nRedR][nRedC] == 'O') {
            redHole = true;
            break;
          }
        }

        while(map[nBlueR + dr[i]][nBlueC + dc[i]] != '#') {
          nBlueR += dr[i];
          nBlueC += dc[i];

          if(map[nBlueR][nBlueC] == 'O') {
            blueHole = true;
            break;
          }
        }

        if(blueHole) continue;
        // 0 1 2 3
        String newPath = "";
        switch(i) {
          case 0:
            newPath = curr.path + "U";
            break;
          case 1:
            newPath = curr.path + "R";
            break;
          case 2:
            newPath = curr.path + "D";
            break;
          case 3:
            newPath = curr.path + "L";
            break;
        }

        if(redHole) {
          return new Node(0, 0, 0, 0, curr.cnt + 1, newPath);
        }

        if(nRedR == nBlueR && nRedC == nBlueC) {
          switch(i) {
            case 0:
              if(curr.redR > curr.blueR) {
                nRedR -= dr[i];
              }else {
                nBlueR -= dr[i];
              }
              break;
            case 1:
              if(curr.redC < curr.blueC) {
                nRedC -= dc[i];
              }else {
                nBlueC -= dc[i];
              }
              break;
            case 2:
              if(curr.redR < curr.blueR) {
                nRedR -= dr[i];
              }else {
                nBlueR -= dr[i];
              }
              break;
            case 3:
              if(curr.redC > curr.blueC) {
                nRedC -= dc[i];
              }else {
                nBlueC -= dc[i];
              }
              break;
          }
        }
        if(!visited[nRedR][nRedC][nBlueR][nBlueC]) {
          visited[nRedR][nRedC][nBlueR][nBlueC] = true;
          q.offer(new Node(nRedR, nRedC, nBlueR, nBlueC, curr.cnt + 1, newPath));
        }
      }
    }
    return null;
  }
}
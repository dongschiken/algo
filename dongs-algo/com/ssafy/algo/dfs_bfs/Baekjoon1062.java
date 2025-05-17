package com.ssafy.algo.dfs_bfs;

import com.sun.jdi.ArrayReference;
import java.io.*;
import java.util.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

class Baekjoon1062 {

  static int N, M;
  static List<String> list;
  static int max = -1;
  static boolean[] isAlpa;
  static boolean[] initAlpa;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    isAlpa = new boolean[26];
    initAlpa = new boolean[26];
    list = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      String tmpStr = br.readLine();
      tmpStr = tmpStr.substring(4, tmpStr.length() - 1);
      tmpStr = tmpStr.substring(0, tmpStr.length() - 3);
      list.add(tmpStr);
    }
    initAlpa[(int)'a' - 'a'] = true;
    initAlpa[(int)'n' - 'a'] = true;
    initAlpa[(int)'t' - 'a'] = true;
    initAlpa[(int)'i' - 'a'] = true;
    initAlpa[(int)'c' - 'a'] = true;
    if(M < 5) {
      System.out.println(0);
      return;
    }
    M = M - 5;
    dfs(0 , 0);
    System.out.println(max);
    //a, n, t, i, c
  }
  public static void dfs(int stage, int start) {
    if(stage == M) {
      int count = list.size();
      for(int i = 0; i < list.size(); i++) {
        for(int j = 0; j < list.get(i).length(); j++) {
          if(!isAlpa[list.get(i).charAt(j) - 'a'] && !initAlpa[list.get(i).charAt(j) - 'a']) {
            count--;
            break;
          }
        }
      }
      max = Math.max(count, max);
    }

    for(int i = start; i < 26; i++) {
      if(!isAlpa[i] && !initAlpa[i]) {
        isAlpa[i] = true;
        dfs(stage+1, i+1);
        isAlpa[i] = false;
      }
    }
  }
}
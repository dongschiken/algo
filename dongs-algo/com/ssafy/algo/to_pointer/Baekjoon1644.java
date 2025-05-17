package com.ssafy.algo.to_pointer;

import java.io.*;
import java.util.*;

public class Baekjoon1644 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    boolean[] primes = new boolean[N + 1];
    List<Integer> temp = new ArrayList<>();
    Arrays.fill(primes, true);
    for(int i = 2; i <= N; i++) {
      for(int j = i + i; j <= N; j += i) {
        if(!primes[i]) break;
        if(j % i == 0) {
          primes[j] = false;
        }
      }
    }
    for(int i = 2; i <= N; i++) {
      if(primes[i]) {
        temp.add(i);
      }
    }
    int[] sum = new int[temp.size() + 1];
    for(int i = 1; i <= temp.size(); i++) {
      sum[i] = sum[i - 1] + temp.get(i - 1);
    }

    int count = 0;
    int start = 0;
    int end = 0;
    while(end <= temp.size()) {
      if(sum[end] - sum[start] == N) {
        end++;
        count++;
      }else if(sum[end] - sum[start] < N) {
        end++;
      }else {
        start++;
      }
    }
    System.out.println(count);
  }
}
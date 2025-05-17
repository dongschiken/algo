package com.ssafy.algo.to_pointer;

import java.io.*;
import java.util.*;

public class Baekjoon20366 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				int sum1 = arr[i] + arr[j];
				int l = 0;
				int r = N - 1;
				while(l < r) {
					if(r == j || r == i) {
						r--;
						continue;
					}
					if(l == i || l == j) {
						l++;
						continue;
					}
					int sum2 = arr[l] + arr[r];
					int minus = sum1 - sum2;
					// min값 초기화
					if(min > Math.abs(minus)) {
						min = Math.abs(minus);
					}
					if(minus < 0) {
						r--;
					}else {
						l++;
					}
				}
			}
		}
		System.out.println(min);
	}
}

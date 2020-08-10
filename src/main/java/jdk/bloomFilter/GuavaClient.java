package jdk.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class GuavaClient {
	 private static int size = 10000;//预计要插入多少数据

	    private static double fpp = 0.01;//期望的误判率

	    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

	    public static void main(String[] args) {
	        //插入数据
	        for (int i = 0; i < 10000; i++) {
	            bloomFilter.put(i);
	        }
	        int count = 0;
	        for (int i = 10000; i < 20000; i++) {
	            if (bloomFilter.mightContain(i)) {
	                count++;
	                System.out.println(i + "误判了");
	            }
	        }
	        System.out.println("总共的误判数:" + count);
	    }
}

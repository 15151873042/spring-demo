package jdk.bloomFilter;

import java.nio.charset.Charset;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

public class RedisClient {

	static final int expectedInsertions = 10000;//要插入多少数据
    static final double fpp = 0.01;//期望的误判率

    //bit数组长度
    private static long numBits;

    //hash函数数量
    private static int numHashFunctions;

    static {
        numBits = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("root");
//        for (int i = 0; i < 10000; i++) {
//            long[] indexs = getIndexs(String.valueOf(i));
//            for (long index : indexs) {
//                jedis.setbit("codebear:bloom", index, true);
//            }
//        }
//        
//        int count = 0;
//        loop1:for (int i = 10000; i < 20000; i++) {
//            long[] indexs = getIndexs(String.valueOf(i));
//            for (long index : indexs) {
//                Boolean isContain = jedis.getbit("codebear:bloom", index);
//                if (!isContain) {
//                    System.out.println(i + "肯定不存在");
//                    continue loop1;
//                }
//            }
//            System.out.println(i + "可能存在");
//            count++;
//        }
//        System.out.println(count);
    	System.out.println(hash("1"));
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    //计算hash函数个数
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算bit数组长度
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }
}

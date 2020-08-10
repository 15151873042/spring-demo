package jdk.try_with_resource;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class Client {

	/**
	 *
	 * @Descripton 必须实现AutoCloseable接口，发生异常时，会自动调用close方法释放资源
	 * @author 胡鹏
	 * @date 2020年6月17日 下午2:13:59
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try (FileWriter fw = new FileWriter("d:\\test.txt", true)) {
			fw.write("test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void myCustomTest() {
		try (MyResource myResource = new MyResource()) {
			myResource.open();
			throw new Exception("异常");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static class MyResource implements AutoCloseable {
		
		public void open() {
			System.out.println("打开资源");
		}

		@Override
		public void close() throws Exception {
			System.out.println("释放资源");
		}
		
	}
}

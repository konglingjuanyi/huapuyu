package jvm;

import java.io.IOException;

import net.sourceforge.sizeof.SizeOf;


/**
 * 堆溢出 
 * VM参数：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -javaagent:D:/code/java/experiment/lib/SizeOf.jar
 * 限制Java堆的大小为20MB，不可扩展（将堆的最小值-Xms和最大值-Xmx设置为一样，即可避免堆自动扩展）
 * 同时通过-XX:+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照）
 * 
 * @author Anders Zhu
 * 
 */
public class Heap {

	private static char[] array = new char[512 * 1024* 10];

	public static void main(String[] args) throws InterruptedException, IllegalArgumentException, IllegalAccessException, IOException {
		// SizeOf.skipStaticField(true);
		// SizeOf.setMinSizeToLog(10);
		SizeOf.deepSizeOf(array);
		// SizeOf.sizeOf(array);
		// SizeOf.iterativeSizeOf(array);
		Thread.sleep(3000);
	}
}
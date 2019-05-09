package com.learningsession;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import com.learningsession.data_store_and_load.StringToArray;
import com.learningsession.find_optimal_path.LongestPath;


public class Application {
	
	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@Fork(value = 1, warmups = 1)
	public void longestPath() {
		LongestPath.test();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@Fork(value = 1, warmups = 1)
	public void StringToArray_store() {
		StringToArray.test_store();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@Fork(value = 1, warmups = 1)
	public void StringToArray_load() {
		StringToArray.test_load();
	}
}

package com.anders.spark.test1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * Logistic regression based classification.
 */
public final class JavaHdfsLR {

	private static final int D = 10; // Number of dimensions
	private static final Random rand = new Random(42);

	static class DataPoint implements Serializable {
		DataPoint(double[] x, double y) {
			this.x = x;
			this.y = y;
		}

		double[] x;
		double y;
	}

	static class ParsePoint implements Function<String, DataPoint> {
		private static final Pattern SPACE = Pattern.compile(" ");

		@Override
		public DataPoint call(String line) {
			System.out.println(line);
			String[] tok = SPACE.split(line);
			double y = Double.parseDouble(tok[0]);
			double[] x = new double[D];
			for (int i = 0; i < D; i++) {
				x[i] = Double.parseDouble(tok[i + 1]);
			}
			return new DataPoint(x, y);
		}
	}

	static class VectorSum implements Function2<double[], double[], double[]> {
		@Override
		public double[] call(double[] a, double[] b) {
			double[] result = new double[D];
			for (int j = 0; j < D; j++) {
				result[j] = a[j] + b[j];
			}
			return result;
		}
	}

	static class ComputeGradient implements Function<DataPoint, double[]> {
		private final double[] weights;

		ComputeGradient(double[] weights) {
			this.weights = weights;
		}

		@Override
		public double[] call(DataPoint p) {
			double[] gradient = new double[D];
			for (int i = 0; i < D; i++) {
				double dot = dot(weights, p.x);
				gradient[i] = (1 / (1 + Math.exp(-p.y * dot)) - 1) * p.y
						* p.x[i];
			}
			return gradient;
		}
	}

	public static double dot(double[] a, double[] b) {
		double x = 0;
		for (int i = 0; i < D; i++) {
			x += a[i] * b[i];
		}
		return x;
	}

	public static void printWeights(double[] a) {
		System.out.println(Arrays.toString(a));
	}

	public static void main(String[] args) {

		// if (args.length < 2) {
		// System.err.println("Usage: JavaHdfsLR <file> <iters>");
		// System.exit(1);
		// }

		// SparkConf sparkConf = new
		// SparkConf().setMaster("anders1").setAppName(
		// "JavaHdfsLR");

		System.setProperty("hadoop.home.dir",
				"C:\\Users\\Anders\\git\\hadoop-common-2.2.0-bin");

		SparkConf sparkConf = new SparkConf().setAppName("JavaHdfsLR")
				.setMaster("spark://anders1:7077").set("spark.ui.port", "4040")
				.setAppName("JavaHdfsLR");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		// JavaSparkContext ctx = new JavaSparkContext(args[0], "JavaWordCount",
		// System.getenv("SPARK_HOME"), System.getenv("SPARK_EXAMPLES_JAR"));

		JavaRDD<String> lines = sc
				.textFile("hdfs://anders1:9000/sqoop/part-m-00000");
		// JavaRDD<DataPoint> points = lines.map(new ParsePoint()).cache();
		// // int ITERATIONS = Integer.parseInt(args[1]);
		// int ITERATIONS = 10;
		//
		// // Initialize w to a random value
		// double[] w = new double[D];
		// for (int i = 0; i < D; i++) {
		// w[i] = 2 * rand.nextDouble() - 1;
		// }
		//
		// System.out.print("Initial w: ");
		// printWeights(w);
		//
		// for (int i = 1; i <= ITERATIONS; i++) {
		// System.out.println("On iteration " + i);
		//
		// double[] gradient = points.map(new ComputeGradient(w)).reduce(
		// new VectorSum());
		//
		// for (int j = 0; j < D; j++) {
		// w[j] -= gradient[j];
		// }
		//
		// }
		//
		// System.out.print("Final w: ");
		// printWeights(w);

		System.out.println(lines.map(new MyFunc()).count());

		sc.stop();
	}

	static class MyFunc implements Function<String, String> {
		@Override
		public String call(String line) {
			String[] values = line.split(",");
			System.out.println(line);
			return values[0];
		}
	}
}

package com.anders.experiment.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class LuceneUtil {
	public static void search内存(Directory directory) throws IOException, ParseException {
		String queryStr = "patent";

		QueryParser parser = new QueryParser(Version.LUCENE_35, "contents", new StandardAnalyzer(Version.LUCENE_35));
		Query query = parser.parse(queryStr);

		Directory dir = new RAMDirectory(directory);
		IndexReader indexReader = IndexReader.open(dir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		long start = System.currentTimeMillis();

		TopDocs topDocs = indexSearcher.search(query, 10);

		long end = System.currentTimeMillis();

		System.out.println("Found " + topDocs.totalHits + " documents (in " + (end - start) + " milliseconds) that matched query '" + queryStr + "'");

		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("fullpath"));
		}

		indexReader.close();
		indexSearcher.close();
	}

	public static Directory index内存() throws IOException {
		String dataDir = LuceneUtil.class.getResource("data").getPath();

		final FileFilter textFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".txt");
			}
		};

		Directory dir = new RAMDirectory();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
		int numIndexed;

		long start = System.currentTimeMillis();

		try {
			File[] files = new File(dataDir).listFiles();
			for (File file : files) {
				if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && (textFileFilter == null || textFileFilter.accept(file))) {
					System.out.println("Indexing " + file.getCanonicalPath());
					Document doc = new Document();
					doc.add(new Field("contents", new FileReader(file)));
					doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc.add(new Field("fullpath", file.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					indexWriter.addDocument(doc);
				}
			}
			numIndexed = indexWriter.numDocs();
		}
		finally {
			indexWriter.close();
		}

		long end = System.currentTimeMillis();

		System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");

		return dir;
	}

	public static void search文件() throws IOException, ParseException {
		String indexDir = LuceneUtil.class.getResource("index").getPath();
		String queryStr = "patent";

		QueryParser parser = new QueryParser(Version.LUCENE_35, "contents", new StandardAnalyzer(Version.LUCENE_35));
		Query query = parser.parse(queryStr);

		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader indexReader = IndexReader.open(dir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		long start = System.currentTimeMillis();

		TopDocs topDocs = indexSearcher.search(query, 10);

		long end = System.currentTimeMillis();

		System.out.println("Found " + topDocs.totalHits + " documents (in " + (end - start) + " milliseconds) that matched query '" + queryStr + "'");

		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("fullpath"));
		}

		indexReader.close();
		indexSearcher.close();
	}

	public static void index文件() throws IOException {
		String indexDir = LuceneUtil.class.getResource("index").getPath();
		String dataDir = LuceneUtil.class.getResource("data").getPath();

		final FileFilter textFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".txt");
			}
		};

		Directory dir = FSDirectory.open(new File(indexDir));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
		int numIndexed;

		long start = System.currentTimeMillis();

		try {
			File[] files = new File(dataDir).listFiles();
			for (File file : files) {
				if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && (textFileFilter == null || textFileFilter.accept(file))) {
					System.out.println("Indexing " + file.getCanonicalPath());
					Document doc = new Document();
					doc.add(new Field("contents", new FileReader(file)));
					doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc.add(new Field("fullpath", file.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					indexWriter.addDocument(doc);
				}
			}
			numIndexed = indexWriter.numDocs();
		}
		finally {
			indexWriter.close();
		}

		long end = System.currentTimeMillis();

		System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");
	}

	public static void 清空索引文件() throws IOException {
		String indexDir = LuceneUtil.class.getResource("index").getPath();

		Directory dir = FSDirectory.open(new File(indexDir));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
		indexWriter.deleteAll();
		indexWriter.close();
	}

	public static void search文件_自定义内容() throws IOException, ParseException {
		String indexDir = LuceneUtil.class.getResource("index1").getPath();
		String queryStr = "coffee";

		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader indexReader = IndexReader.open(dir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		TermQuery termQuery = new TermQuery(new Term("desc", queryStr));

		long start = System.currentTimeMillis();

		TopDocs topDocs = indexSearcher.search(termQuery, 10);

		long end = System.currentTimeMillis();

		System.out.println("Found " + topDocs.totalHits + " documents (in " + (end - start) + " milliseconds) that matched query '" + queryStr + "'");

		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("name"));
		}

		indexReader.close();
		indexSearcher.close();
	}

	public static Directory index文件_自定义内容() throws IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zhuzhen");
		map.put("desc", "i like coffee");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("name", "guolili");
		map.put("desc", "zhuzhen like coffee, me too");
		list.add(map);

		String indexDir = LuceneUtil.class.getResource("index1").getPath();

		Directory dir = FSDirectory.open(new File(indexDir));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
		int numIndexed;

		long start = System.currentTimeMillis();

		try {
			for (Map<String, String> mapTemp : list) {
				Document doc = new Document();
				doc.add(new Field("desc", mapTemp.get("desc"), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("name", mapTemp.get("name"), Field.Store.YES, Field.Index.NOT_ANALYZED));
				indexWriter.addDocument(doc);
			}
			numIndexed = indexWriter.numDocs();
		}
		finally {
			indexWriter.close();
		}

		long end = System.currentTimeMillis();

		System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");

		return dir;
	}
}
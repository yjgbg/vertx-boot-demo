package com.github.yjgbg.vertx.demo

import cps.{async, await}
import com.github.yjgbg.vertx.boot.syntax.all.{*, given}
import com.github.yjgbg.vertx.boot.api
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.{Document, Field, FieldType, LongPoint, TextField}
import org.apache.lucene.index.{Term,CodecReader, DirectoryReader, IndexOptions, IndexReader, IndexWriter, IndexWriterConfig}
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.{IndexSearcher, TermQuery}
import org.apache.lucene.store.{ByteBuffersDirectory, FSDirectory}
import org.apache.lucene.util.PagedBytes.Reader

import java.io.File

case class TheEntity(name: String, id: Long, description: String)
@main def elasticSearch: Unit = {
  val analyzer = StandardAnalyzer()
  val indexWriterConfig = IndexWriterConfig(analyzer)
  indexWriterConfig.setUseCompoundFile(false)
   val directory = ByteBuffersDirectory()
//  val directory = FSDirectory.open(new File("index").toPath)
  val writer = new IndexWriter(directory, indexWriterConfig)

  val theEntity = TheEntity("alice", 1, "a pretty girl")
  val doc0 = Document()
  doc0.add(TextField("id", theEntity.id.toString,Field.Store.YES))
  doc0.add(TextField("name", theEntity.name, Field.Store.YES))
  doc0.add(TextField("description", theEntity.description, Field.Store.YES))
  writer.addDocument(doc0)
  writer.close()

  val indexReader =DirectoryReader.open(directory)
  val indexSearcher = IndexSearcher(indexReader)

//  QueryParser("",StandardAnalyzer()).
  val term = Term("description","girl")
  val query = TermQuery(term)

  val topDocs = indexSearcher.search(query, 10)
  println(topDocs)
  println(doc0)
  println("-------------------")
  println(topDocs.scoreDocs)
  println(indexSearcher.doc(topDocs.scoreDocs(0).doc))
}

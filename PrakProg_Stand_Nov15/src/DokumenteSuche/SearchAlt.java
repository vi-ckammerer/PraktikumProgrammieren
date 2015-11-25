package DokumenteSuche;

import java.io.File;
import java.io.IOException;

import Index.IndexCreator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;
public class SearchAlt {
	 private String SearchSubject;
	 private  String FilesToIndex;

	 
	 
	 
		public SearchAlt( String filesToIndex, String searchSubject) {
		super();
		SearchSubject = searchSubject;
		FilesToIndex = filesToIndex;
	}




		public void find() throws ParseException, IOException{
			
			try{
			IndexCreator index = new IndexCreator(FilesToIndex, "/internOrdner");
			
			NIOFSDirectory indexDir = index.createIndex();
			    //  NIOFSDirectory indexDir = new NIOFSDirectory(new File(IndexDir));  // wird vom index �bergeben
			      DirectoryReader dr = DirectoryReader.open(indexDir); // �ffnen des Verzeichnises textIndexDir �ber die Variable indexDir
			   Analyzer analyzer2 = new StandardAnalyzer(Version.LUCENE_45); // Soll Query analysieren

		       QueryParser qp = new QueryParser(Version.LUCENE_45,"content",analyzer2);
		  
		      
		       IndexSearcher searcher = new IndexSearcher(dr); // Indexsuche
		
		       Query query = qp.parse(SearchSubject); //

		      

		       TopDocs td = searcher.search(query,10); // Die ersten 10 Dokumente mit h�chstem Ranking

		       ScoreDoc[] sd = td.scoreDocs; //

		      if(sd.length==0){
		    	  System.out.println("Keine Eintr�ge gefunden");
		      }else{

		       for (int i=0; i < sd.length; i++) {

		         Document doc = searcher.doc(sd[i].doc); // Dokument mit dem h�chsten "Ranking" wird hier "gefunden"
		         System.out.println("Dokument gefunden "+ doc.toString() );
		         IndexableField  iField[] =doc.getFields(SearchSubject);
		         System.out.println("Ifield " +  iField.length);
		         System.out.println(doc.get("title"));

		       }}
		      }
			catch(Exception e){
				System.out.println("Fehler!!!!");
				e.printStackTrace();
				System.out.println(e.getMessage() + "  "+
				e.getLocalizedMessage());
			}
		}
		
	}

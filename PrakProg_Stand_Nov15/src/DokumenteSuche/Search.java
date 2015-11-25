package DokumenteSuche;

import java.io.File;
import java.io.IOException;
import Index.IndexCreator;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

public class Search {
 private String SearchSubject;
 private String FILES_TO_INDEX_DIRECTORY ;
 private String IndexDir;
	/*Fehler bei der Indizierung*/
	
	


	public void find() throws ParseException, IOException{
	IndexCreator ind =  IndexCreator.getInstance(FILES_TO_INDEX_DIRECTORY,IndexDir );
		
		      NIOFSDirectory indexDir = ind.createIndex(); 
		      DirectoryReader dr = DirectoryReader.open(indexDir); // �ffnen des Verzeichnises textIndexDir �ber die Variable indexDir
		   Analyzer analyzer2 = new StandardAnalyzer(Version.LUCENE_45); // Soll Query analysieren
File path=new File("test.xt");
//indexDir.open(path);

	       QueryParser qp = new QueryParser(Version.LUCENE_45,"INHALT",analyzer2);
	  
	      
	       IndexSearcher searcher = new IndexSearcher(dr); // Indexsuche
	
	       Query query = qp.parse(SearchSubject); //

	      

	       TopDocs td = searcher.search(query,10); // Die ersten 10 Dokumente mit h�chstem Ranking

	       ScoreDoc[] sd = td.scoreDocs; //

	    		   
System.out.println("L�nge "+ sd.length);
	       for (int i=0; i < sd.length; i++) {

	         Document doc = searcher.doc(sd[i].doc); // Dokument mit dem h�chsten "Ranking" wird hier "gefunden"
	         
	         System.out.println(doc.toString());
	         System.out.println("l�uft");
	       }
	}
	public Search(String searchSubject, String fILES_TO_INDEX_DIRECTORY,
			String indexDir) {
		super();
		SearchSubject = searchSubject;
		FILES_TO_INDEX_DIRECTORY = fILES_TO_INDEX_DIRECTORY;
		IndexDir = indexDir;
	}
}

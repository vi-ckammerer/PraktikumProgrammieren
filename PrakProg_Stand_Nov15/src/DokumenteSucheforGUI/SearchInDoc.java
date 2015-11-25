package DokumenteSucheforGUI;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermToBytesRefAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.Version;

public class SearchInDoc {
	private List<IndexableField>   IField;
	private String FieldName;
	private TermsEnum Terme;
	private TokenStream TStream;  //verarbeitet einzelne Buchstaben
	private TokenFilter TFilter;  // evtl. unn�tig
	private List<String> tokenList;
	 
	
	
	private Reader reader = null;
	//https://lucene.apache.org/core/4_0_0/core/org/apache/lucene/index/DocsEnum.html
	

public SearchInDoc(List<IndexableField> iField, String fieldName) {
		// TODO Auto-generated constructor stub#
	IField = iField;
	FieldName = fieldName;
	Iterator iter= IField.iterator();
	while(iter.hasNext()){
		IndexableField currentField = (IndexableField) iter.next();
	//	System.out.println("?????????????"+ currentField.name() + "  "+ currentField.stringValue());
		if(currentField.name().equals(FieldName) ) {
			System.out.println("\n !!!!!!!Feld gefunden");
		//	reader=currentField.readerValue();
			//Analyzer analyzer = new Analyzer();
			
			
			try {
				   Analyzer analyzer2 = new StandardAnalyzer(Version.LUCENE_45); // Soll Query analysieren
//				   	   tokenArray = tokensFromAnalysis(analyzer2,currentField.toString() );
				
//				TStream= currentField.tokenStream(analyzer2 ); redundant
				   
				   TStream =analyzer2.tokenStream(FieldName, new StringReader(currentField.stringValue()));
		//	System.out.println("\n \n Stringvalue " + currentField.stringValue() +"\n \n toString " + currentField.toString());
			String text = currentField.stringValue()	;
			OffsetAttribute offsetAttribute = TStream.getAttribute(OffsetAttribute.class);
			CharTermAttribute termAttribute = TStream.getAttribute(CharTermAttribute.class);
			// siehe: http://stackoverflow.com/questions/2638200/how-to-get-a-token-from-a-lucene-tokenstreamc i.V.m. http://stackoverflow.com/questions/2638200/how-to-get-a-token-from-a-lucene-tokenstream
			//TermToBytesRefAttribute termAttribute = TStream.getAttribute(TermAttribute.class);
			TStream.reset();
			while (TStream.incrementToken()) {
			 //   int startOffset = offsetAttribute.startOffset();
			  //  int endOffset = offsetAttribute.endOffset();
			    String term = termAttribute.toString();
			  System.out.println(term);
			  try{  
			  tokenList.add(term);}
			  catch(Exception e){}  // hier muss noch Fleisch rein
			    
			}
			TStream.end();
			TStream.close();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
public String toString() throws NullPointerException{
	int SucheFeld=0;
	
return null;		

}
public void close(){
	if(reader != null){
	try {
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
}
	public int Anzahl(){
		
		int i=0;
	try{
		i=  tokenList.size();
	}catch(Exception e){
		
	}
	//	i = reader.
	//	i = iField.length;  Quatsch L�nge ist immer zwei, da zwei Felder
		
		
		return i;
		
	}

}
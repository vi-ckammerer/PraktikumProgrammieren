package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*; // Achtung die Liste von .util funktioniert nicht 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.filter.Filters;
import org.jdom2.input.DOMBuilder;
import org.jdom2.util.IteratorIterable;
import org.xml.sax.SAXException;

//&import org.xml.sax.SAXException; 

/* 
 * 
 */

public class Parser {
private org.jdom2.Document XMLDOC;

	private  List<String>  Ergebnis= new ArrayList<String>();
	private List<Element> KinderIntern =  new ArrayList<Element>();
	
	public List<String> getRetAr(int n) {
		List<String> retAr = new ArrayList<String>();	
		retAr = read(n);
		return retAr;

	}
	
	
	List <Element> eListe = new ArrayList();
	String XmlPfadString;

	String ElementName;

	int n = 0;
	public List<String> getKids(){
		
	Element r =	 XMLDOC.getRootElement();
//	while(r.getChildren()!=null){	}
    List<Element> Kinder =   r.getChildren();
    
  
		return KinderElemente( Kinder);
	}
	
	/*
	 * Achtung funktioniert nur wenn XML balancierter Baum bzw. erster Ast am tiefesten
	 * 
	 */
	private List <String> KinderElemente(    List<Element> Kinder){
		List <String> Ergebnis = new ArrayList <String>();
		  Iterator<Element> iter = Kinder.iterator();
		    
		    while(iter.hasNext()){
		    	Element kind = iter.next();
		    	KinderIntern.add(kind);
		    if(Ergebnis.indexOf(kind.getName())==-1){ // Ergebnis.indexOf(kind.getName()) ist g.d. -1 wenn kein gleichnamiges Element in der List vorhanden ist:
		    	Ergebnis.add(kind.getName());
		    	if(kind.getChildren()!= null){ 
		    		
		    		Ergebnis.addAll( KinderElemente(kind.getChildren()));
		    		}
		    	}
	}

		return Ergebnis;
	}
	
	public Parser(String xmlPfadString) {

		super();

		XmlPfadString = xmlPfadString;

	//	ElementName = elementName;
		try{
			org.jdom2.Document jDoc = null;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;

			builder = dbFactory.newDocumentBuilder();

			org.w3c.dom.Document doc = builder.parse(XmlPfadString); // hier xml

			DOMBuilder domB = new DOMBuilder();

			XMLDOC = domB.build(doc);// = new DOMBuilder();

		}catch(Exception e){
			XMLDOC = null;
		}

	}
	
	
	public String [] getXMLElements(){
		String  [] XMLElementArray = null;
		XMLDOC.getDescendants();
		return XMLElementArray;
		
	}
	
	
	/**private org.jdom2.Document XMLDOC() throws ParserConfigurationException, SAXException, IOException{
		org.jdom2.Document jDoc = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;

		builder = dbFactory.newDocumentBuilder();

		org.w3c.dom.Document doc = builder.parse(XmlPfadString); // hier xml

		DOMBuilder domB = new DOMBuilder();

			jDoc = domB.build(doc);// = new DOMBuilder();

		return jDoc;
			}                 Redundant XMLDOC wird mit aufruf des Konstruktors global erzeugt        
	 * @return **/
 
	@SuppressWarnings("finally")
	private List<String> read(int n) {
		List<String> retAr = new ArrayList<String>();	
		try {

			org.jdom2.Document jDoc =XMLDOC;
			

			org.jdom2.Element r = jDoc.getRootElement();

			/*
			 * for (Iterator it = r.getDescendants(Filters.element());
			 * 
			 * it.hasNext();) {
			 */
			
			
			Iterator it = r.getDescendants(Filters.element());
			while (it.hasNext()) {

				try {

					Element e = (org.jdom2.Element) it.next();

					if (e.getName() == KinderIntern.get(n).getName()) {
						

						String S = (String) e.getValue().toString();

						// System.out.println(e.getValue().toString());

						retAr.add(S);// Achtung nur ArrayList

						// n++; WTF, does it?

					}

				}

				catch (Exception e) {

					retAr.add("geht net" + e.getMessage());

				}

			}

		}

		catch (Exception ee) {

			retAr.add("io excption " + ee.getMessage());

		}finally{
			return retAr;
			
		}

	}

}


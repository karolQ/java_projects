import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String args[]) throws Exception{
    	
    	// get source files from input folder
        File folder = new File("./input");
        String[] fileList = folder.list();
        if(fileList == null || fileList.length == 0)
            System.out.println("No files.");
        else{
        	System.out.println(fileList.length);
            for(String s : fileList){
                File xmlFile = new File(s);
//                System.out.println(xmlFile);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                
                Document doc = (Document) dbFactory.newDocumentBuilder().parse("input/" + xmlFile);
                XPath xPath = XPathFactory.newInstance().newXPath();

                NodeList nodes = (NodeList) xPath.evaluate("//book", doc, XPathConstants.NODESET);
                int fileNumber = 0;
                
                Document newDoc = (Document) dbFactory.newDocumentBuilder().newDocument();
                
                // check if output directory is existed, if not, then create one
                Path outputPath = Paths.get("output/");
                if(Files.notExists(outputPath))
                	new File("output/").mkdir();
                
                File tempFile = new File("output/"+ fileNumber+ ".xml");
                Node rootNode;
                String newFileName = "";
                rootNode = newDoc.createElement("Book");
                for(int i = 1; i < nodes.getLength()+1; i++){
                	// iterate the child nodes to get the list name
                	NodeList childNodes = nodes.item(i-1).getChildNodes();
                	
                	for(int j = 0; j < childNodes.getLength(); j++) {
                		if(childNodes.item(j).getNodeName().equals("title")) {
                			newFileName = childNodes.item(j).getTextContent();
                		}
                	}
                	
                	Node imported = newDoc.importNode(nodes.item(i-1), true);
                    rootNode.appendChild(imported);
                    
                    File newFile = new File("output/" + newFileName +".xml");
                	tempFile.renameTo(newFile);

                    writeToFile(rootNode, newFile);
                    
                    rootNode = newDoc.createElement("Book");
                    tempFile = new File((++fileNumber)+".xml");
                    
                }
            }
        }
        System.out.print("Completed!");
    }
    
    private static void writeToFile(Node rootNode, File file) throws Exception {
    	Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(rootNode), new StreamResult(new FileWriter(file)));
    }
}




package aaa.bbb.FinalProject.utils;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaa.bbb.FinalProject.pages.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

public class ActionOnXMLFile {
	
	public static Document getDocument (String fileName) {

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			File file = new File (fileName);
			Document document = documentBuilder.parse(file);
			return document;	

		}catch (Exception e) {
			Reporter.reportError("The exception for getDocument function. "  + e.getMessage());
			return null;
		}
	}

	public static String getXMLValue (Document doc, String exp) {

		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String value = xpath.compile(exp).evaluate(doc);
			return value;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String [][] getXmlListChildren (String docName, String exp) {

		try {

			Document document = getDocument(docName);
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(exp).evaluate(document, XPathConstants.NODESET);

			String [][] tests = new String[nl.getLength()][5];
			Reporter.reportStep(ReporterStatus.INFO, "Inserting XML data into an array");

			for(int i = 0; i < nl.getLength(); i++) {
				Node currentItem = nl.item(i);
				NodeList children = currentItem.getChildNodes();  
				for (int j = 0; j < 5; j++)
					tests[i][j]  = children.item((j*2)+1).getTextContent();
			}
			return tests;
		}catch (Exception e) {
			Reporter.reportError("The exception for getXmlListChildren function. "  + e.getMessage());
			return null;
		}
	}
	
	public static String [][] getXmlList (String docName, String exp) {

		try {

			Document document = getDocument(docName);
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(exp).evaluate(document, XPathConstants.NODESET);
			String [][] tests = new String[nl.getLength()][2];
			Reporter.reportStep(ReporterStatus.INFO, "Inserting XML data into an array");


			for(int i = 0; i < nl.getLength(); i++) {
				Node currentItem = nl.item(i);
				tests[i][0]  = currentItem.getAttributes().getNamedItem("select").getTextContent();
				tests[i][1]  = currentItem.getAttributes().getNamedItem("item").getNodeValue();

			}
			return tests;

		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String [] runOnArrayChildren (String[][] array, WebDriver driver) {

		String [] listOfItem = new String[array.length]; 
		try {
			WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
			WomenPage womenPage = new WomenPage(driver);

			Reporter.reportStartTest("Starting add to cart XML file test");
			for(int i = 0; i < array.length; i++) {
				webStoreCommon.selectFootMenu(array[i][0]);
				womenPage.addToCart(array[i][1], array[i][2], array[i][3], array[i][4]);
				listOfItem[i] = array[i][1];
			}
		}catch(Exception e) {
			Reporter.reportError("The exception for runOnArray function. "  + e.getMessage());
			return null;
		}
		return listOfItem;
	}
	
	public static void runOnArrayAttribute (String[][] array, WebDriver driver) {

		try {
			WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
			WomenPage womenPage = new WomenPage(driver);
			//As this stage done once it not in the XML file
			
			Reporter.reportStartTest("Starting add to wishlist test");
			for(int i = 0; i < array.length; i++) {
				webStoreCommon.selectFootMenu(array[i][0]);
				womenPage.wishList(array[i][1]);
			}
			
			
		}catch(Exception e) {
			Reporter.reportError("The exception for runOnArray function. "  + e.getMessage());
		}
	}
}

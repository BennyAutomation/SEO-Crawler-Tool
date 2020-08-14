package main.java;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.NodeList;

public class Crawler {

	private String setUp() {
		Scanner sc = new Scanner(System.in);
    	System.out.println("Please enter full url:");
    	String url = sc.nextLine();
    	sc.close();
    	return url;
	}
	
	
    public static void main(String... args) throws IOException, GeneralSecurityException {
    	Crawler crawler = new Crawler();
    	String url = crawler.setUp();
    	SitemapCrawler sitemapCrawler = new SitemapCrawler();
		NodeList links = sitemapCrawler.getLinksFromXml(url);
		List<List<Object>> info = sitemapCrawler.crawlLinks(links);
    	
    	SheetHelper sheetHelper = new SheetHelper();
    	String spreadsheetId = sheetHelper.createSpreadsheet(url);
    	sheetHelper.writeToSheet(spreadsheetId, info);
    }	  	
    

}


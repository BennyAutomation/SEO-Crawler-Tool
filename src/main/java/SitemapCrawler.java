package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SitemapCrawler {
	private static final String SITEMAP_URL_XPATH = "/urlset/url/loc/text()";
	private static List<List<Object>> FINAL_LIST = new ArrayList<List<Object>>();
	
    public NodeList getLinksFromXml(String url) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		NodeList nl = null;
		try {
			builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(url);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile(SITEMAP_URL_XPATH);
			nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nl;
	}
	
	public List<List<Object>> crawlLinks(NodeList links) {
		for (int i = 0; i < links.getLength(); i++) {
			getPageInfo(links.item(i));
		}
		return FINAL_LIST;
	}
	
	private void getPageInfo(Node n) {
		List<Object> linkedInfo = new ArrayList<Object>();
		try {
			Document jdoc = Jsoup.connect(parseLink(n)).get();
			String title = jdoc.select("title").text().toString();
			String meta = jdoc.select("meta[name='description']").attr("content").toString();
			linkedInfo.add(parseLink(n));
			linkedInfo.add(title);
			linkedInfo.add(meta);
			FINAL_LIST.add(linkedInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String parseLink(Node n) {
		String href = n.toString().trim();
		return href.substring(8, href.length() - 1);
	}
}

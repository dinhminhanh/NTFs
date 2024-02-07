package application.service;


import application.crawl.crawler.AMyCrawler;

public class CrawlService {
	
	private AMyCrawler crawler ;
	
	public CrawlService(AMyCrawler crawler) {
		this.crawler = crawler ;
	}
	
	public boolean crawlData() {
		return crawler.crawl();
	}
	
}

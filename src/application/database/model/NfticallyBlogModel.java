package application.database.model;

import java.util.ArrayList;
import java.util.List;

public class NfticallyBlogModel extends APostModel {
	private String title ;
	private String describe ;

	public NfticallyBlogModel(String author, String date, String tags, String postUrl, String imageUrl, String title, String describe) {
		super(author, date, tags, postUrl, imageUrl);
		this.title 		= title ;
		this.describe 	= describe ;
	}

	public String getTitle() 	{ return title; }
	public String getDescribe() { return describe; }

	@Override
	public List<String> tagList() {
		List<String> list = new ArrayList<String>();
		String stringTag = super.getTags() ;
		String[] tags = stringTag.split("\\s+");
		for (String tag : tags) list.add(tag) ;
		return list;
	}

	@Override
	public String getDetailSearch() {
		return describe + super.getTags();
	}
	
	
}

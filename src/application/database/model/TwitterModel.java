package application.database.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterModel extends APostModel {

	private int views;
	private int likes;

	public TwitterModel(String author, String date, String tags, String postlUrl, String imageUrl, int views, int likes) {
		super(author,date,tags,postlUrl,imageUrl);
		this.views = views;
		this.likes = likes;
	}

	public int getViews() { return views; }
	public int getLikes() { return likes; }
	
	@Override
	public List<String> tagList() {
		List<String> list = new ArrayList<String>();
		String stringTag = super.getTags() ;
		String[] tags = stringTag.split("#");
		for (String tag : tags) list.add(tag) ;
		return list;
	}

	@Override
	public String getDetailSearch() {
		return super.getTags();
	}
	
}

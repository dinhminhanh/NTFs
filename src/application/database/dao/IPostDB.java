package application.database.dao;
import java.util.List;

import application.database.model.APostModel;

public interface IPostDB <T extends APostModel> {

	public  List<T> getAllPost()   ;
	public  List<T> getPostIn24H() ;
	public  List<T> getPostIn1W()  ;
	public  List<T> getPostIn1M()  ;
	public  List<T> getPostInDayAgo(int dayAgo) ;	
}

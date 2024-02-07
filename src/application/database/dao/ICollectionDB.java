package application.database.dao;

import java.util.List;

import application.database.model.ACollectionModel;


public interface ICollectionDB <T extends ACollectionModel> {
	
	public  List<T> getAllColectionInDayAgo (int dayAgo) ;
	public  List<T> getCollectionByNameInDayAgo (String name , int day);
	
}

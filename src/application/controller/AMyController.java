package application.controller;

import javafx.scene.layout.VBox;

public abstract class AMyController  {

	public abstract VBox getRoot()  ;
	public abstract void loadView() ;
	public abstract void loadData() ;
}

package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ModificationPane extends CustomAnchorPane {
	private int selectIndex = 0;
	
	public ModificationPane() {
		super();
	}
	
	@FXML protected void plus(ActionEvent event) {
		Context.getInstance().modifyTableRow(selectIndex, true);
	}
	
	@FXML protected void minus(ActionEvent event) {
		Context.getInstance().modifyTableRow(selectIndex, false);
	}
	
	@FXML protected void up(ActionEvent event) {
		selectIndex--;
		if (selectIndex < 0) {
			selectIndex = 0;
		}
		Context.getInstance().selectTableRow(selectIndex);
	}
	
	@FXML protected void down(ActionEvent event) {
		selectIndex++;
		int maxLength =  Context.getInstance().getProductListLength() - 1;
		if (selectIndex > maxLength) {
			selectIndex = maxLength;
		}
		Context.getInstance().selectTableRow(selectIndex);
	}
	
	@FXML protected void ok(ActionEvent event) {
		Context.getInstance().commitTransaction();
		Context.getInstance().getMainWindow().showEditBoxLeftPane();
		Context.getInstance().getMainWindow().showDetailPane();
	}
	
	@FXML protected void cancel(ActionEvent event) {
		Context.getInstance().getMainWindow().showEditBoxLeftPane();
		Context.getInstance().getMainWindow().showDetailPane();
	}
}

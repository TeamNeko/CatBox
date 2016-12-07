package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * The Class ModificationPane.
 */
public class ModificationPane extends CustomAnchorPane {
	
	/** The select index. */
	private int selectIndex = 0;
	
	/**
	 * Instantiates a new modification pane.
	 */
	public ModificationPane() {
		super();
	}
	
	/**
	 * Increment value in tablepane.
	 *
	 * @param event the event
	 */
	@FXML protected void plus(ActionEvent event) {
		Context.getInstance().modifyTableRow(selectIndex, true);
	}
	
	/**
	 * Decrement value in tablepane.
	 *
	 * @param event the event
	 */
	@FXML protected void minus(ActionEvent event) {
		Context.getInstance().modifyTableRow(selectIndex, false);
	}
	
	/**
	 * Select upper value in tablepane.
	 *
	 * @param event the event
	 */
	@FXML protected void up(ActionEvent event) {
		selectIndex--;
		if (selectIndex < 0) {
			selectIndex = 0;
		}
		Context.getInstance().selectTableRow(selectIndex);
	}
	
	/**
	 * Select lower value in tablepane.
	 *
	 * @param event the event
	 */
	@FXML protected void down(ActionEvent event) {
		selectIndex++;
		int maxLength =  Context.getInstance().getProductListLength() - 1;
		if (selectIndex > maxLength) {
			selectIndex = maxLength;
		}
		Context.getInstance().selectTableRow(selectIndex);
	}
	
	/**
	 * Save changes to database.
	 *
	 * @param event the event
	 */
	@FXML protected void ok(ActionEvent event) {
		Context.getInstance().commitTransaction();
		Context.getInstance().getMainWindow().showEditBoxLeftPane();
		Context.getInstance().getMainWindow().showDetailPane();
	}
	
	/**
	 * Cancel changes.
	 *
	 * @param event the event
	 */
	@FXML protected void cancel(ActionEvent event) {
		Context.getInstance().getMainWindow().showEditBoxLeftPane();
		Context.getInstance().getMainWindow().showDetailPane();
	}
}

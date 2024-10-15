package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Popup;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.Collections;


public class Main extends Application {
	@Override
	public void start(Stage windowMain) {
		Experience exp = new Experience();
		VBox layout = new VBox();
		layout.setPrefSize(500, 500);
		
		Button craftItems = new Button("Crafting");
		craftItems.setVisible(false);
		Label info = new Label("Reach level 10 to unlock a new crafting place!");
		BorderPane craftingSpace = new BorderPane();
		craftingSpace.setCenter(craftItems);
		craftingSpace.setCenter(info);
		
		Item wood = new Item("Wood", 10, 4,
				"file:/C:/Users/Panayiotis/Desktop/test/Item_9.png");
		Item sapling = new Item("Sapling", 5, 6,
				"file:/C:/Users/Panayiotis/Desktop/test/Item_1182.png");
		ArrayList<Item> items = new ArrayList<>();
		Collections.addAll(items, wood, sapling);

		ArrayList<Label> labels = new ArrayList<>();
		
		Label title = new Label("Clicker Gatherer (become you're favorite woodsman today!");
		Button clickerBtn = new Button("GATHER");
		ProgressBar expBar = new ProgressBar();
		expBar.setProgress(exp.getCurrentExp());
		
		Label displayLevel = new Label();
		displayLevel.setText("Level: 1");
		
		clickerBtn.setOnMouseClicked(e -> {
			exp.gainExp();
			if (sapling.getQuantity() >= 1) {
				expBar.setProgress(1.5 + exp.getCurrentExp() / exp.getMaxExp());
			}
			expBar.setProgress(exp.getCurrentExp() / exp.getMaxExp());
			for (Item item: items) {
				if (item.checkIfDrop(exp.getLevel())) {
					showPopup(windowMain, item.getImageView());
					item.increaseQuantity();
					for (int i = 0; i < labels.size(); i++) {
						labels.get(i).setText(items.get(i).getName() + ": " + items.get(i).getQuantity());
						if (items.get(i).getQuantity() > 0) {
							labels.get(i).setVisible(true);
						}
					}
				}
			}
			String level = String.valueOf(exp.getLevel());
			displayLevel.setText("Level: " + level);
		});
		
		layout.getChildren().addAll(title, clickerBtn, expBar,
				displayLevel, craftingSpace);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setSpacing(25);
		layout.setPadding(new Insets(25, 25, 25, 25));
		
		for (int i = 0; i < items.size(); i++) {
			String itemName = items.get(i).getName();
			String itemQuantity = String.valueOf(items.get(i).getQuantity());
			Label itemLabel = new Label(itemName + ": " + itemQuantity);
			if (items.get(i).getQuantity() == 0) {
				itemLabel.setVisible(false);
			}
			labels.add(itemLabel);
			layout.getChildren().add(labels.get(i));
		}
		
		Scene scene = new Scene(layout);
		windowMain.setScene(scene);
		windowMain.show();
	}
	
	private void showPopup(Stage stage, ImageView itemImageView) {
		Label popupLabel = new Label("You got an item!");
		popupLabel.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-color: black;");
		ImageView itemView = itemImageView;
		
		itemView.setPreserveRatio(true);
		
		VBox popupStack = new VBox();
		popupStack.getChildren().addAll(itemView, popupLabel);
		popupStack.setAlignment(Pos.CENTER);
		
		Popup popup = new Popup();
		popup.getContent().add(popupStack);
		popup.setAutoHide(true);
		
		popup.show(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

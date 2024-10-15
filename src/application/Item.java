package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Item {
	private String name;
	private int quantity;
	private int dropChance;
	private int levelReq;
	private Image itemPic;
	private ImageView imageView;
	
	public Item(String name, int dropChance, int levelReq, String imagePath) {
		this.name = name;
		this.quantity = 0;
		this.dropChance = dropChance;
		this.levelReq = levelReq;
		this.itemPic = new Image(imagePath);
		this.imageView = new ImageView(itemPic);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getDropChance() {
		return this.dropChance;
	}
	
	public int getLevelReq() {
		return this.levelReq;
	}
	
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public void increaseQuantity() {
		this.quantity++;
	}
	
	public boolean checkIfDrop(int playerLevel) {
		Random random = new Random();
		int randomValue = random.nextInt(101);
		return playerLevel >= levelReq && randomValue <= dropChance;
	}

}

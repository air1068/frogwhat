package frogwhat;

import java.util.ArrayList;

public class Equip {
	public String itemText;
	public ArrayList<String> qualities;

	protected Equip(String item) {
		this.itemText = item;
		this.qualities = ItemHandle.splitQualities(ItemHandle.getQualities(item));
	}
	
	public int matchQualities(ArrayList<String> targetlist) {
		//new Equip("longsword (Defender) [+5] {Fi,Ac,El}").matchQualities(ItemHandle.splitQualities("El,Fi,Fi,So")) == 2;
		int matches = 0;
		for (String quality : this.qualities) {
			if (targetlist.contains(quality)) {
				matches++;
			}
		}
		return matches;
	}
	
	public String getSlot() {
		return this.itemText.substring(1, 5);
	}
}
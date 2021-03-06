package comparator;

import java.util.Comparator;

import model.Player;

public class CompareToName implements Comparator<Player> {

	@Override
	public int compare(Player arg0, Player arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}

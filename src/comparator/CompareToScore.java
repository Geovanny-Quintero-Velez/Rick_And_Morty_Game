package comparator;

import java.util.Comparator;

import model.Player;

public class CompareToScore implements Comparator<Player> {

	@Override
	public int compare(Player arg0, Player arg1) {
		return arg0.getPoints()-arg1.getPoints();
	}

}

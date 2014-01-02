package in4rows.player;

import in4rows.game.GameObserver;
import in4rows.model.Player;

import java.util.Observer;

public interface ServerPlayer extends GameObserver, Observer, Player , ObservablePlayer {

}

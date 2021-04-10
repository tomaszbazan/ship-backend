package pl.btsoftware.ship.game.registration;

public class IncorrectPositionException extends RuntimeException {

    public IncorrectPositionException() {
        super("Coordinate x must me less than 8 and more than 0. Coordinate y must me less than 8 and more than 0.");
    }
}

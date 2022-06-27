package client;

public enum Komande {

    PRESS_MOUSE(-1),
    RELEASE_MOUSE(-2),
    PRESS_KEY(-3),
    RELEASE_KEY(-4),
    MOVE_MOUSE(-5);

    private final int sifra;

    Komande(int sifra) {
        this.sifra = sifra;
    }

    public int getSifra() {
        return this.sifra;
    }
}

package socket.server;

public class Sample {
    public String pm;

    public Sample() {
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    private String print(String str) {
        setPm(str.concat(" Done!"));
        return getPm();
    }
}

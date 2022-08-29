package animations;

public class ExitGame implements Task<Void>{
    @Override
    public Void run() {
        System.exit(1);
        return null;
    }
}

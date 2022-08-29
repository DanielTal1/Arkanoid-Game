package animations;

public class ShowTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation screenAnimation;
    public ShowTask(AnimationRunner runner, Animation screenAnimation) {
        this.runner = runner;
        this.screenAnimation = screenAnimation;
    }
    public Void run() {
        this.runner.run(this.screenAnimation);
        return null;
    }
}
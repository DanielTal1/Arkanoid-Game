package animations;

public class Triple<T> {
    private String key;
    private String message;
    private T returnVal;
    public Triple(String key, String message, T returnVal){
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    public String getKey(){
        return this.key;
    }

    public String getMessage(){
        return this.message;
    }

    public T getReturnVal(){
        return this.returnVal;
    }
}

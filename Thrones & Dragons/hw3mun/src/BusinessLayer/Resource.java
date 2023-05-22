package BusinessLayer;

public class Resource{
    private int max;
    private int curr;

    public Resource(int max, int curr) {
        this.max = max;
        this.curr = curr;
    }
    public void addToCurrent(double toAdd){
        if(curr + toAdd > max)
            curr = max;
        else curr += toAdd;
    }
    public void addToMax(int toAdd){
        if(toAdd > 0)
            max += toAdd;
    }
    public void reduceFromCurrent(int subtract) {
        if(curr - subtract < 0)
            curr = subtract;
        curr -= subtract;
    }
    public int getMax(){ return max;}
    public int getCurrent(){return curr;}
    public void setCurrent(int curr){
        if(curr > max)
            curr = max;
        this.curr = curr;
    }
}

import java.util.ArrayList;

public class Analyzer implements ODEUpdate {

    private ArrayList<Double> time = new ArrayList<>();
    private ArrayList<Double> xVal = new ArrayList<>();
    private ArrayList<Double> vVal = new ArrayList<>();


    public ArrayList<Double> getTime() {
        return time;
    }

    public ArrayList<Double> getxVal() {
        return xVal;
    }

    public ArrayList<Double> getvVal() {
        return vVal;
    }

    @Override
    public void update(double t, double x, double v) {
        time.add(t);
        xVal.add(x);
        vVal.add(v);
    }
}

import java.util.ArrayList;

public class ODE implements ODEUpdate {

    private ArrayList<Double> tValues = new ArrayList<>();
    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> vValues = new ArrayList<>();

    public ArrayList<Double> gettValues() {
        return tValues;
    }

    public ArrayList<Double> getxValues() {
        return xValues;
    }

    public ArrayList<Double> getvValues() {
        return vValues;
    }

    @Override
    public void update(double t, double x, double v) {
        tValues.add(t);
        xValues.add(x);
        vValues.add(v);

    }
}

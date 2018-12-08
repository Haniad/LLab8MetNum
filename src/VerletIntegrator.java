import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class VerletIntegrator {

    private double dt;
    private Analyzer analyzer;

    public VerletIntegrator(double dt) {
        this.dt = dt;
    }

    public void integrate(CalculateAcceleration calculateAcceleration, double ts, double te, double x0, double v0) {

        analyzer = new Analyzer();
        double t = ts;
        double x = x0;
        double v = v0;
        double a = calculateAcceleration.a(x);

        analyzer.update(t, x, v);

        for (t = ts; t < te; t += dt) {

            double v05 = v + dt * a / 2;
            double x1 = x + dt * v05;
            x = x1;
            a = calculateAcceleration.a(x1);
            double v1 = v05 + dt * a;
            v = v1;
            analyzer.update(t, x, v);
        }
    }

    public double getPeriod() {
        ArrayList<Double> xVal = analyzer.getxVal();
        ArrayList<Double> time = analyzer.getTime();
        ArrayList<Double> periods = new ArrayList<>();

        double xStart = xVal.get(0);
        double tStart = time.get(0);

        if (xStart > 0) {
            for (int i = 1; i < xVal.size() - 1; i++) {
                if ((xVal.get(i + 1) < xVal.get(i)) && (xVal.get(i - 1) < xVal.get(i)))
                    periods.add(time.get(i));

            }
        } else {
            for (int i = 1; i < xVal.size() - 1; i++) {
                if ((xVal.get(i + 1) > xVal.get(i)) && (xVal.get(i - 1) > xVal.get(i)))
                    periods.add(time.get(i));

            }
        }


        return periods.get(0) - tStart;
    }

    public void saveToTxt(String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            StringBuilder values = new StringBuilder();

            for (int i = 0; i < analyzer.getxVal().size(); i++) {
                String x = String.valueOf(analyzer.getxVal().get(i));
                String v = String.valueOf(analyzer.getvVal().get(i));
                String t = String.valueOf(analyzer.getTime().get(i));

                x = x.replace('.', ',');
                v = v.replace('.', ',');
                t = t.replace('.', ',');

                values.append(x + ";" + v + ";" + t + "\n");
            }


            printWriter.println(values.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


} //koniec klasy

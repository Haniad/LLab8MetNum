import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Verlet {

    private double dt;
    private ODE ode;

    public Verlet(double dt) {
        this.dt = dt;
    }

    public void integrate(CalculateAcceleration calculateAcceleration, double ts, double te, double x0, double v0) {

 ode = new ODE();
        double t = ts;
        double x = x0;
        double v = v0;
        double a = calculateAcceleration.a(x);

        ode.update(t, x, v);

        for (t = ts; t < te; t += dt) {

            double v05 = v + dt * a / 2;
            double x1 = x + dt * v05;
            x = x1;
            a = calculateAcceleration.a(x1);
            double v1 = v05 + dt * a;
            v = v1;
            ode.update(t, x, v);
        }
    }

    private ArrayList<Double> countEnergy() {
        ArrayList<Double> energy = new ArrayList<>();
        int l = ode.gettValues().size();

        for (int i = 0; i < l; i++)
            energy.add(0.5 * ode.getvValues().get(i) - Math.cos(ode.getxValues().get(i)));

        return energy;
    }

    public void saveToFile(String filename) {

        PrintWriter save = null;
        try {
            save = new PrintWriter(filename);

            String s="";
            int l = ode.gettValues().size();

            for (int i = 0; i < l; i++) {

                String t = ode.gettValues().get(i).toString().replace(".", ",");
                String x = ode.getxValues().get(i).toString().replace(".", ",");
                String v = ode.getvValues().get(i).toString().replace(".", ",");
                String e = countEnergy().get(i).toString().replace(".", ",");

                s=t + ";" + x + ";" + v + ";" + e;

                save.println(String.valueOf(s));
            }
            save.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Verlet verlet = new Verlet(0.01);
        Acceleration acceleration = new Acceleration();

        verlet.integrate(acceleration, 0, 10, 10, 0);
        verlet.saveToFile("dane.txt");


        String dupa ="dupa";

    }


} //koniec klasy

public class Pendulum implements CalculateAcceleration {
    @Override
    public double a(double x) {
        return -Math.sin(x);
    }

}

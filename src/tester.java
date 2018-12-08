public class tester {

    public static void main(String[] args) {

        VerletIntegrator verletIntegrator = new VerletIntegrator(0.5);
        Pendulum pendulum = new Pendulum();

        verletIntegrator.integrate(pendulum, 0, 20, 0.5, 0);
        verletIntegrator.saveToTxt("data.txt");

        System.out.println(verletIntegrator.getPeriod());
    }
}

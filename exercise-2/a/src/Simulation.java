public class Simulation {
  public static void main(String[] args) throws Exception {

    System.out.println("----------starting simulation----------");

    KitchenCounter kitchenCounter = new KitchenCounter(4);

    for (int i = 1; i <= 2; i++) {
      Thread t = new Thread(new Cook("Cook " + i, kitchenCounter));
      t.setDaemon(true);
      t.start();
    }

    for (int i = 1; i <= 8; i++) {
      Thread t = new Thread(new Student("Student " + i, kitchenCounter));
      t.setDaemon(true);
      t.start();
    }

    Thread.sleep(30000);
    System.out.println("----------closing simulation----------");
  }
}
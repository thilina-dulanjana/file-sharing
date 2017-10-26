import java.lang.Thread;
import java.util.Scanner;

class ClientInterface extends Thread {
    String commandRaw;
    Scanner scanner;
    boolean isFirstTime = true;

    @Override
    public void run() {
        System.out.println(scanner.next());
        if ((commandRaw = scanner.nextLine()) != "exit") {
            if (!isFirstTime) {
                String[] command = commandRaw.split(" ");
                if (command[0].equals("Search")) {
                    //Search Query Function calling goes here. Should return the results.
                    System.out.println("Search1");
                } else if (command[0].equals("Leave")) {
                    //Leave Query Function calling goes here.
                    System.out.println("Leave1");
                } else {
                    System.out.println("Invalid Command!");
                }
            }
            isFirstTime = false;
        }
    }
}
import java.lang.*;

class BusSeat {
    int total_seats = 10;

    public void book(int seats, String name) {
        System.out.println("Hello " + name);

        // Data corrupt na ho isliye synchronized use kiya hai
        synchronized (this) {
            if (total_seats >= seats) {
                System.out.println(seats + " seats booked for " + name);
                total_seats -= seats;
                System.out.println("Seats left: " + total_seats);
            } else {
                System.out.println("Failed for " + name + "! Only " + total_seats + " left");
            }
        }

        System.out.println("Bye " + name);
    }
}

class MyThread extends Thread {
    BusSeat b;
    int s;
    String n;

    MyThread(BusSeat b, int s, String n) {
        this.b = b;
        this.s = s;
        this.n = n;
    }

    // Run method implemented
    public void run() {
        b.book(s, n);
    }
}

public class BusReservationSystem {
    public static void main(String[] args) {
        BusSeat b = new BusSeat();

        MyThread t1 = new MyThread(b, 7, "User 1");
        MyThread t2 = new MyThread(b, 6, "User 2");
        t1.start();
        t2.start();
    }
}

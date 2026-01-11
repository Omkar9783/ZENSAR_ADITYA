class BusPrinting {
    int available = 10;

    void blockSync(int seat) {
        System.out.println("Hello:" + Thread.currentThread().getName());
        System.out.println("Hello:" + Thread.currentThread().getName());
        System.out.println("Hello:" + Thread.currentThread().getName());

        String name = Thread.currentThread().getName();
        if(available >= seat)
        {
            System.out.println("Seats booked:" + name);
            available = available - seat;
            System.out.println("Available seats:" + available);
        }

        else
        {
            System.out.println("Sorry:" + name + "Only" + available + "left");
        }

        synchronized(this)
        {
        }

        System.out.println("Bye:"+Thread.currentThread().getName());System.out.println("Bye:"+Thread.currentThread().getName());System.out.println("Bye:"+Thread.currentThread().getName());
    }
}

class BusPrintingThread extends Thread {
    int seat;

}

public class BusPrintingSystem {

    public static void main(String args[]) {

        Thread t1=new Thread();
        Thread t2=new Thread();


        t1.setName("Onkar");
        t2.setName("Rahul");

        System.out.println(t1.getName());
        System.out.println(t2.getName());

        // t1.seat=7; // NKO_103 logic not reachable here as t1 is Thread
        // t2.seat=6; 

        // Fixing logic to use NKO_103 if intended, but keeping original intent as much as possible
        // Actually the original code had t1.seat=7 which implies t1 should be NKO_103
        // But t1 is declared as Thread t1=new Thread();
        // Maybe it meant NKO_103 t1 = new NKO_103();
        // I will just fix the syntax of main for now.

        t1.start();
        t2.start();
    }

}

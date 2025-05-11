
class TicketBookingSystem {
    private int totalTickets;

    public TicketBookingSystem(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public synchronized boolean bookTicket(String customerName) {
        if (totalTickets > 0) {
            System.out.println(customerName + " booked ticket number " + totalTickets);
            totalTickets--;
            return true;
        } else {
            System.out.println(customerName + " could not book a ticket (Sold Out)");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private String customerName;
    private TicketBookingSystem system;

    public BookingThread(String customerName, TicketBookingSystem system) {
        this.customerName = customerName;
        this.system = system;
    }

    @Override
    public void run() {
        system.bookTicket(customerName);
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        BookingThread t1 = new BookingThread("Alice", system);
        BookingThread t2 = new BookingThread("Bob", system);
        BookingThread t3 = new BookingThread("Christine", system);
        BookingThread t4 = new BookingThread("David", system);
        BookingThread t5 = new BookingThread("Eve", system);
        BookingThread t6 = new BookingThread("Frank", system); // This one may not get a ticket

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

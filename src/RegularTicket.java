package tiketbioskop;

public class RegularTicket extends TicketType {
    @Override
    public int getPrice() {
        return 50000;
    }

    @Override
    public String getTypeName() {
        return "REGULER";
    }
}

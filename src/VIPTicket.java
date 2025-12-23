package tiketbioskop;

public class VIPTicket extends TicketType {
    @Override
    public int getPrice() {
        return 100000;
    }

    @Override
    public String getTypeName() {
        return "VIP";
    }
}

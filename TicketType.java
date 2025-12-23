package tiketbioskop;

public abstract class TicketType {
    public abstract int getPrice();
    public abstract String getTypeName();

    @Override
    public String toString() {
        return getTypeName();
    }
}
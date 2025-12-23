package tiketbioskop;

public class DataOrder {
    public String title;
    public String studio;
    public String day;
    public String showtime;
    public String seat;
    public int qty;
    public int price;

    public int getTotal() {
        return qty * price;
    }
}

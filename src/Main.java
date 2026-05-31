import view.MainView;

public class Main
{
    public static final int VERSION = 1;
    public static final String VERSION_NUMBER = "0.0.1";
    public static void main(String[] args)
    {
        System.out.println("VERSION "+VERSION);
        System.out.println("SUB VERSION "+VERSION_NUMBER);
        new MainView().init();
    }
}

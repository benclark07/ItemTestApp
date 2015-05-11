package bcdevops.me.itemtestapp.Models;

public class Region {
    private String name;
    private String host;
    private String locale;

    public Region(String name, String host, String locale){
        this.name = name;
        this.host = host;
        this.locale = locale;
    }

    public String getName(){
        return name;
    }
    public String getHost(){
        return host;
    }
    public String getLocale(){
        return locale;
    }
}

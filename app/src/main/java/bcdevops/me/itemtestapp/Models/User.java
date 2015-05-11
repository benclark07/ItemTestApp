package bcdevops.me.itemtestapp.Models;

public class User {
    private String regionName;
    private String host;
    private String locale;

    public User(Region region){
        regionName = region.getName();
        this.host = region.getHost();
        this.locale = region.getLocale();
    }

    public String getRegionName(){
        return regionName;
    }

    public String getHost(){
        return host;
    }

    public String getLocale(){
        return locale;
    }
}

package bcdevops.me.itemtestapp.Tools;

public class Enums {
    public enum Toggles{
        ITEM(0, "Item Toggle"),
        SET(1, "Item Set Toggle"),
        ALL(2, "All Tests Toggle");

        Toggles(int aStatus, String desc){
            this.status = aStatus;
            this.description = desc;
        }

        private final int status;
        private final String description;

        public int status(){
            return this.status;
        }
        public String description(){
            return this.description;
        }
    }
}
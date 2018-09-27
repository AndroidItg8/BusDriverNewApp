package itg8.com.busdriverapp.utils;

import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.Prefs;
import itg8.com.busdriverapp.common.USERTYPE;

public class UserType {
    private static USERTYPE type;

    public static void setType(USERTYPE type) {
        UserType.type = type;
    }

    public static boolean isDriver(){
        return type!=null && type==USERTYPE.BusDriver;
    }

    public static boolean isAdmin(){
        return type!=null && (type == USERTYPE.BusAdmin);
    }



    public static class Builder{
        USERTYPE type;

        public Builder setType(){
            if(Prefs.contains(CommonMethod.TYPE_DATA)){
                if(contains(Prefs.getString(CommonMethod.TYPE_DATA))) {
                    type = USERTYPE.valueOf(Prefs.getString(CommonMethod.TYPE_DATA));
                }
            }
            return this;
        }

        public static boolean contains(String value) {

            for (USERTYPE c : USERTYPE.values()) {
                if (c.name().equals(value)) {
                    return true;
                }
            }

            return false;
        }

        public void build() {
            UserType.setType(type);
        }
    }
}

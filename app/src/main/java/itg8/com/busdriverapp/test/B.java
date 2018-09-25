package itg8.com.busdriverapp.test;

public class B extends A{


    private final String child;

    public B(String childname, String surname) {
        super(surname);
        this.child=childname;
    }



    public String getFullName(){
        return child+surname;
    }
}

import forms.GreetingForm;

public class Main {
    public static void main(String[] args){
        GreetingForm ui = GreetingForm.getInstance();
        ui.setVisible(true);
    }
}
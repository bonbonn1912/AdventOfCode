package Day04;

public class trimtest {

    public static void main(String[] args) throws Exception {

        String test = "Hallo   hier sind  mehrere spoaces";
        String myText = test.trim().replaceAll("\\s+"," ");
        System.out.println(myText);
    }

    
}

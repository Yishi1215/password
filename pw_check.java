import java.util.ArrayList;

public class pw_check {

    public static void main(String[] args) {
        if (args.length != 0) {
            BadPassword bp = new BadPassword();
            bp.outputDic();
            GoodPassword gp = new GoodPassword();
            gp.outputPass();
        } else {
                  System.out.print("Enter password: ");
                  String input = System.console().readLine();
            DLB trie = new DLB();
            trie.buildTrie("good_passwords.txt");
            boolean valid = trie.search(input);
            if (valid) {
                          System.out.println("The password you just entered is valid.");
            } else {
                          System.out.println("The password you just entered is invalid. Here is 10 passwords you may consider:");
                          System.out.println(trie.recommendPassword(input));
            }
        }
    }
}

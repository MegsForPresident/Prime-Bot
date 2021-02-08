import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * "+ username +"
 */
public class Prime {
    private static Scanner a = new Scanner(System.in);
    private static String name = "Prime";
    private static boolean run = true;
    private static boolean reset = false;
    private static String username = "";

    private static void setUsername() throws IOException {
        File a = new File("C:\\Users");
        username = a.list()[1];
        if (a.list().length >= "abc".length()) {
            username = ask("Which folder userame:");
        }
        a = new File("username.txt");
        FileWriter g = new FileWriter(a);
        g.write(username);
        g.close();
    }

    private static String getUsername() throws FileNotFoundException {
        File a = new File("username.txt");
        Scanner scan = new Scanner(a);
        return scan.nextLine();
    }
    public static void main(String[] args) throws IOException, URISyntaxException {
        try{
            username = getUsername();
        }catch(Exception e){}
        if(username.isEmpty()){
            setUsername();
        }
        folders();
        if (password()) {
            if (!getName().isEmpty()) {
                name = getName();
            } else {
                setName(ask("Name for bot:"));
                name = getName();
            }
            while (run) {
                try {
                    brains();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (reset) {
                reset();
            }
        }
    }

    private static void lastPic() throws IOException {
        File a = new File("C:\\Users\\"+ username +"\\Pictures\\Camera Roll");
        String[] g = a.list();
        for (int c = g.length; c >= 0; c++) {
            a = new File("C:\\Users\\"+ username +"\\Pictures\\Camera Roll\\" + g[c]);
            if (a.isFile()) {
                Desktop.getDesktop().open(a);
                // run = false;
                break;
            }
        }

    }

    private static boolean password() throws IOException {
        File file2 = new File("C:\\Users\\"+ username +"\\PrimeFiles\\password.txt");
        if (checkPassword()) {
            file2.createNewFile();
            FileWriter g = new FileWriter(file2);
            g.write(getPassword("Create Password: "));
            g.close();
        } else {
            Scanner scan = new Scanner(file2);// history
            String g = "";
            while (scan.hasNextLine()) {
                g = scan.nextLine();
            }

            String pass = getPassword("Enter Password:  ");
            scan.close();
            if (!g.contentEquals(pass)) {
                return false;
            }
            
        }
        return true;
    }

    private static void brains() throws IOException, URISyntaxException {
        while (true) {
            String h = ask(name);
            boolean f = (h.contentEquals("exit"));
            if (f) {
                if (password()) {
                    run = false;
                    break;
                }
            } else if (h.isEmpty()) {
                continue;
            } else if (h.contentEquals("calling all autobots")) {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=nigxtH4XLMg"));
            } else if (h.contentEquals("show")) {
                lastPic();
            } else if (h.contains("browse")) {
                if (h.contains("browse") && h.length() > "abcdefg".length()) {
                    browse(h.replace("browse ", ""));
                } else {
                    browse();
                }
            } else if (h.contentEquals("random movies")) {
                File file = new File("D:\\");
                if (file.exists()) {
                    movie();
                } else {
                    System.out.println("You don't have the movie drive ready yet. Connect the Drive");
                }
            } else if (h.contains("change name")) {
                if (password()) {
                    if (h.contentEquals("change name")) {
                        name = ask(name + " Name");
                        setName(name);
                    } else {
                        name = h.replace("change name ", "").replace(Character.toString(h.charAt(0)),
                                Character.toString(h.charAt(0)).toUpperCase());
                        setName(name);
                    }
                }
            } else if (h.contains("open")) {
                File file = new File(h.replace("open ", ""));
                Desktop.getDesktop().open(file);
            } else if (h.contentEquals("reset")) {
                reset = true;
                run = false;
                break;
            } else if (h.contentEquals("change password")) {
                if (password()) {
                    File file2 = new File("C:\\Users\\"+ username +"\\PrimeFiles\\password.txt");
                    file2.createNewFile();
                    FileWriter g = new FileWriter(file2);
                    g.write(getPassword("Change Password: "));
                    g.close();

                }
                break;
            } else {
                System.out.println("\'" + h
                        + "\' is not recognized as an internal or external command,\noperable program or batch file for "
                        + name + ".\n");
            }
            if (!h.isEmpty()) {
                history(h);
            }
        }
        return;
    }

    private static void reset() {
        File a = new File("C:\\Users\\"+ username +"\\PrimeFiles\\");
        for (int c = 0; c < a.list().length; c++) {
            File f = new File("C:\\Users\\"+ username +"\\PrimeFiles\\" + a.list()[c]);
            System.out.println(f.delete());
        }
        System.out.println(a.delete());
    }

    private static void folders() {
        File file = new File("C:\\Users\\"+ username +"\\PrimeFiles");
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static boolean checkPassword() {
        File a = new File("C:\\Users\\"+ username +"\\PrimeFiles\\password.txt");
        if (a.exists()) {
            return false;
        }
        return true;
    }

    private static String getPassword(String prompt) {

        String password = "";
        ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        consoleEraser.start();
        try {
            password = in.readLine();
        } catch (IOException e) {
            System.out.println("Error trying to read your password!");
            System.exit(1);
        }

        consoleEraser.halt();
        System.out.print("\b");
        return password;
    }

    private static class ConsoleEraser extends Thread {
        private boolean running = true;

        public void run() {
            while (running) {
                System.out.print("\b ");
                try {
                    Thread.currentThread();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    run = false;
                    break;
                }
            }
        }

        public synchronized void halt() {
            running = false;
        }
    }

    private static void setName(String name) throws IOException {
        File a = new File("C:\\Users\\"+ username +"\\PrimeFiles\\Name.txt");
        FileWriter g = new FileWriter(a);
        g.write(name);
        g.close();
    }
    
    private static String getName() throws IOException {
        File a = new File("C:\\Users\\"+ username +"\\PrimeFiles\\Name.txt");
        if (!a.exists()) {
            a.createNewFile();
        }
        Scanner r = new Scanner(a);
        String name2 = "";
        while (r.hasNextLine()) {
            name2 = r.nextLine();
        }
        r.close();
        return name2;
    }

    private static void movie() throws IOException {
        LinkedList<String> g = new LinkedList<String>();
        File a = new File("D:\\Movie");
        for (int c = 0; c < a.list().length; c++) {
            File a2 = new File("D:\\Movie\\" + a.list()[c]);
            if (a2.isDirectory()) {
                for (int v = 0; v < a2.list().length; v++) {
                    File a3 = new File("D:\\Movie\\" + a.list()[c] + "\\" + a2.list()[v]);
                    if (a3.isDirectory()) {
                        for (int w = 0; w < a3.list().length; w++) {
                            if (a3.list()[w].endsWith(".mp4") || a3.list()[w].endsWith(".mp4")
                                    || a3.list()[w].endsWith(".mp4")) {
                                g.add("D:\\Movie\\" + a.list()[c] + "\\" + a2.list()[v] + "\\" + a3.list()[w]);
                            }
                        }
                    } else {
                        if (a2.list()[v].endsWith(".mp4") || a2.list()[v].endsWith(".mp4")
                                || a2.list()[v].endsWith(".mp4")) {
                            g.add("D:\\Movie\\" + a.list()[c] + "\\" + a2.list()[v]);
                        }
                    }
                }
            }
        }
        Random f = new Random();
        for(int c = 0;c < g.size();c++){
		System.out.println(g.get(c));
	}
        Desktop.getDesktop().open(new File(g.get(f.nextInt(g.size()))));
        return;
    }
    private static void history(String a) throws IOException {
        File f = new File("C:\\Users\\"+ username +"\\PrimeFiles\\history.txt");
        FileWriter b = new FileWriter(f,true);
        b.write(name + " was asked " + a + "\n");
        b.close();
    }
    private static void browse() throws IOException, URISyntaxException {
        while(true){
            String g = ask(name + " browse");
            if(g.contentEquals("exit")){
                run = false;
                break;
            }
            if(g.contains("https://")){
                Desktop.getDesktop().browse(new URI(g));
            }
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=NOPE&oq=NOPE&aqs=chrome..69i57j0i433j0l8.1041j0j7&sourceid=chrome&ie=UTF-8".replace("NOPE", g.replace(" ", "+"))));
        }
    }
    private static void browse(String g) throws IOException, URISyntaxException {
        if(g.contains("https://")){
            Desktop.getDesktop().browse(new URI(g));
        }
        else{
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=NOPE&oq=NOPE&aqs=chrome..69i57j0i433j0l8.1041j0j7&sourceid=chrome&ie=UTF-8".replace("NOPE", g.replace(" ", "+"))));
        }
    }
    private static String ask(String g) {
        System.out.print(g + ">");
        return a.nextLine();
    }
}
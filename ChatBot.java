package com.company;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ChatBot extends JFrame implements ActionListener {
    static JTextArea area = new JTextArea();

    JTextField field;

    JScrollPane sp;

    JButton send;

    LocalTime time = LocalTime.now();
    LocalDate date = LocalDate.now();

    Random random = new Random();

    public ChatBot(String title) {
        super(title);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setLayout(null);

        getContentPane().setBackground(Color.lightGray);

        field = new JTextField();

        send = new JButton("BOT");

        send.setFont(new Font("arial", Font.BOLD, 12));

        send.setBackground(Color.white);

        send.setBounds(735, 520, 60, 35);

        add(send);

        // For Text area
        area.setEditable(false);
        area.setBackground(Color.white);
        add(area);
        area.setFont(new Font("arial", Font.PLAIN, 16));

        // scrollbar
        sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setBounds(10, 20, 775, 470);
        add(sp);

        // For TextField
        field.setSize(725, 35);
        field.setLocation(10, 520);
        field.setForeground(Color.black);
        field.setFont(new Font("arial", Font.PLAIN, 16));
        add(field);

        send.addActionListener(this);
        getRootPane().setDefaultButton(send);

    }

    public void actionPerformed(ActionEvent e) {
        String message = field.getText().toLowerCase();

        message = message.replaceAll("\\s+", " ");
        message = message.toLowerCase();

        area.append("You :" + field.getText() + "\n");

        area.append(" ");

        field.setText(" ");

        Socket sock = new Socket();


        if (message.contains("how are you")) {

            int num = random.nextInt(3);

            if (num == 0) {

                bot("I'm fine !,What about you ? ");

            } else if (num == 1) {

                bot("I am good , thanks for asking !");

            }
            else {

                bot("I am great ,thanks for asking !");
            }

        } else if (message.contains("asalamualaikum")|| message.contains("salam")) {

            bot("Walaikum Salaam Warahmatuallahi Wabarakaatuh");

        } else if (message.contains("tell me ") &&message.contains("something about")) {

            bot("Ahh! I am Just a Robot and Mr. Faisal Bhat Team Created Me...");


        } else if (message.contains("suggest") && (message.contains("something") || message.contains("books"))) {

            bot("I will say you one thing that, educate yourself and make yourself feel high...");

        } else if (message.contains("what are you")) {

            bot("Hey I am a Robot !!");

        } else if (message.contains("you") && (message.contains("smart") || message.contains("good")) || message.contains("are intelligent")) {

            bot("Thank you !");

        } else if (message.contains("welcome")) {

            bot("You are so polite. How can i help you ?");
        }

        else if (message.contains("hi") && message.charAt(0)=='h'|| message.contains("hello") || message.contains("hey")) {

            int num = random.nextInt(3);

            if (num == 0) {

                bot("Hii");

            } else if (num == 1) {

                bot("Hello");

            }
            else {

                bot("Heyy");
            }
        } else if (message.contains("bye")) {

            bot("Byy,See you soon ..!");

        } else if (message.contains("i am good") || message.contains("i am great") || message.contains("i am ") && message.contains("fine")) {

            if(message.contains("not"))
            {

                bot("oh so sad ");
            }
            else {

                bot("Good to hear.");

            }
        } else if (message.contains("thank you") || message.contains("thanks")) {

            int num = random.nextInt(3);

            if (num == 0) {

                bot("Welcome..");

            } else if (num == 1) {

                bot("My pleasure");

            }
            else {

                bot("Happy to help");
            }
        }
        else if (message.contains("what") && message.contains("name")) {

            if (message.contains("your")) {

                bot("I'm Bot...");
            }
            if (message.contains("my")) {

                bot("i don't know");
            }

        } else if (message.contains("change")) {

            bot("Sorry,I can't change anything...");

        } else if (message.contains("time")) {

            String ctime = "";

            if (time.getHour() > 12) {

                int hour = time.getHour() - 12;

                ctime = ctime + hour + ":" + time.getMinute() + ":" + time.getSecond() + " PM";
            }

            else {

                ctime = ctime + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " AM";
            }
            bot(ctime);

        } else if (message.contains("date") || message.contains("month") || message.contains("year")) {

            String cdate = "";

            cdate = cdate + date.getDayOfWeek() + "," + date.getDayOfMonth() + " " + date.getMonth() + " "

                    + date.getYear();
            bot(cdate);

        }


        else if (message.contains("weather") || message.contains("todays weather")) {

            String loc = "anantnag";

            if (message.contains("in")) {

                loc = message.substring(message.indexOf("in") + 2);
                loc = loc.trim();
                bot(loc);
            }
            try {

                String inline = "";

                String url1 = "http://api.weatherapi.com/v1/current.json?key=8705caf55fbe46a590862902221405&q=" + loc
                        + "&aqi=no";

                URL url = new URL(url1);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");

                conn.connect();

                int responsecode = conn.getResponseCode();

                if (responsecode != 200) {

                }

                else {
                    Scanner sc = new Scanner(url.openStream());
                    while (sc.hasNext()) {
                        inline += sc.nextLine();
                    }
                    inline = inline.substring(inline.indexOf("\"text\""));
                    inline = inline.substring(8);
                    String abc = "";
                    abc = inline.substring(0, inline.indexOf("\""));

                    // System.out.println(“\nJSON data in string format”);
                    bot(abc);
                    inline = inline.substring(inline.indexOf("\"feelslike_c\""));
                    inline = inline.substring(14);
                    abc = inline.substring(0, inline.indexOf(","));
                    bot("feels_like:" + abc);
                    sc.close();
                }

            } catch (Exception e1) {
                System.out.println("Exception" + e1);
            }
        } else if (message.contains("good morning")) {

            bot("Good morning,Have a nice day !");

        } else if (message.contains("good night")) {

            bot("Good night,Have a nice dreams !");

        } else if (message.contains("good evening")) {

            bot("Good Evening ...!");

        } else if (message.contains("good") && message.contains("noon")) {

            bot("Good Afternoon ...!");

        }

        else if (message.contains("clear") && (message.contains("screen") || message.contains("chat"))) {

            bot("wait a few second...");

            area.setText("");

        } else if (message.contains("who developed you") || message.contains("build you") || message.contains("made you")) {

            bot("Faisal Bhatt and his TEAM ");

        } else if (message.contains("what can you do")|| message.contains("what is your job")) {

            bot("It depends on my features how i am developed");

            System.out.println("\n");

            bot("I hope in future i will be used as personal assistant");

        } else if (message.contains("did you know me")) {

            bot("let me guess");

            bot("Enter your first three letters as");

            bot("my name is ___");
        } else if (message.contains("who made") && !message.contains("you")) {

            bot("the almighty");

        } else if (message.contains("fai")) {

            if (message.contains("my")) {

                bot("hello faisal bhatt nice to meet you");

            } else

                bot("Faisal Bhatt my developer");

        } else if (message.contains("who are") && message.contains("you")) {

            bot("i am bot");

        } else if (message.contains("uzm")) {

            if (message.contains("my ")) {

                bot("heyy there uzma");

            }
            else {

                bot("uzma is Team member of this project");
            }
        } else if (message.contains("mah")) {

            if (message.contains("my name")) {

                bot("hi mehran ");

            }
            else {

                bot("Mehran  member of this project");
            }
        } else if ((message.isBlank())) {

            bot("sorry i didn't understand you");
        }

        else if (message.contains("faculty") && message.contains("computer")) {

            bot(" there are 4 faculty members in computer ");

            bot("abid sir, hilal sir, jan sir and snober mam");

        } else if (message.contains("civil")) {

            bot("i don't know much about them ");
        }

        else if (message.contains("who is abid")) {

            bot("Mr abid ahmad wani is permanent faculty of cse branch ");

            bot("abid sir  lives in srinagar");

        } else if (message.contains("qualification of abid")) {

            bot("MR abid ahmad have done b.tech in Iust");
        }

        else if (message.contains("abid") && message.contains("contact") || message.contains("number of abid")) {

            bot("Number.....");

        } else if (message.contains("abid sir is paid") || message.contains("salary of abid")) {

            bot("100000");

        } else if (message.contains("details of abid")) {

            bot("Mr abid ahmad wani is permanent faculty of cse branch ");

            bot("MR abid ahmad have done b.tech in Iust ");

            bot("contact number:.....");

            bot("abid sir is lives in srinagar");

        } else if (message.contains("who is jan") || message.contains("mohd jan")) {

            bot("Mr jan mohd is permanent faculty of cse branch ");

            bot("jan sir is lives in khanda Budgam");

        } else if (message.contains("qualification of jan") || message.contains("qualified of mohd jan")) {

            bot("MR jan ahmad have done b.tech in Iust");


        } else if (message.contains("jan") && message.contains("contact") || message.contains("number of jan")) {

            bot("Number:....");

        } else if (message.contains("jan sir is paid") || message.contains("salary of jan")) {

            bot("40000");

        } else if (message.contains("details of jan")) {


            bot("Mr jan ahmad is permanent faculty of cse branch ");

            bot("MR jan ahmad have done b.tech in Iust ");

            bot("contact number:.....");

            bot("salary: 40000");
        }

        else if (message.contains("who is hilal sir") || message.contains(" hilal ahmad bawani")) {

            bot("Hilal ahmad is a contractual faculty of cse branch");

            bot("Hilal ahmad is lives in Doru");
        }

        else if (message.contains("qualification of hilal")) {

            bot("MR hilal ahmad have done b.tech in Baba Ghulam shah Badshah");

        } else if (message.contains("contact number of hilal")) {

            bot(".......");
        }


        else if (message.contains(" hilal sir paid") || message.contains("salary of hilal")) {

            bot("50000");

        } else if (message.contains("who is snober ") || message.contains("bhat snober ali")) {

            bot("Ms snober ali is a contractual  faculty of cse");


            bot("Ms snober  lives in dialgam");

        } else if (message.contains("qualification of snober") || message.contains(" qualification bhat snober ali")) {

            bot("bhat snober ali have done b.tech in JAMMU university");

        } else if (message.contains(" snober is paid") || message.contains("salary of snober") || message.contains("snober mam is paid")) {

            bot("20000");

        } else if (message.contains("snober") && message.contains("contact") || message.contains("phone number of snober")) {

            bot(".....");

        } else if (message.contains("exit") || message.contains("close")) {

            System.exit(0);
        }
        //commands to open applications

        else if (message.contains("calculator") || message.contains("calc")) {

            Runtime run = Runtime.getRuntime();

            try
            {
                run.exec("calc");
            } catch (IOException ex) {
                System.out.println("Exception" + ex);
            }

        }
        else if (message.contains("notepad") || message.contains("notes")) {

            Runtime run = Runtime.getRuntime();
            try
            {

                run.exec("notepad");

            } catch (IOException ex) {

                System.out.println("Exception" + ex);
            }

        } else if (message.contains("paint") || message.contains("paints")) {

            Runtime run = Runtime.getRuntime();
            try
            {

                run.exec("mspaint.exe");

            } catch (IOException ex) {

                System.out.println("Exception" + ex);
            }

        }

        else if (message.contains("wordpad")) {

            Runtime run = Runtime.getRuntime();
            try {
                run.exec("wordpad");

            } catch (IOException ex) {

                System.out.println("Exception" + ex);
            }
        }

        else if (message.contains("shut pc") || message.contains("shutdown")) {

            Runtime run = Runtime.getRuntime();
            try {

                run.exec("shutdown -s -t 0");

            } catch (IOException ex) {

                System.out.println("Exception" + ex);
            }
        } else if (message.contains("restart")) {

            Runtime run = Runtime.getRuntime();

            try {

                run.exec("restart -r -t 0");

            } catch (IOException ex) {

                System.out.println("Exception" + ex);
            }
        }

        else if (message.contains("cmd") || message.contains("command prompt")) {

            try {
                Runtime run = Runtime.getRuntime();

                run.exec(new String[] { "cmd.exe", "/c", "start" });

            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }

        else if(message.contains("file") || message.contains("file explorer"))
        {
            Runtime runtime= Runtime.getRuntime();
            try {

                runtime.exec("explorer.exe");

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        else if(message.contains("excel"))
        {
            try {

                Runtime.getRuntime().exec("cmd /c start excel.exe");

            } catch (IOException ex) {

                ex.printStackTrace();
            }

        }
        else if(message.contains("word"))
        {
            try {
                Runtime.getRuntime().exec("cmd /c start winword.exe");

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        else if (message.contains("ppt") || message.contains("powerpoint"))
        {

            try {

                Runtime.getRuntime().exec("cmd /c start powerpnt.exe");

            }
            catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        else if (message.contains("government polytechnic college anantnag ") || message.contains("details about gpca") || message.contains("gpca"))
        {

            if (message.contains("located") || message.contains("situated")) {

                bot("it is present at larkipora");
            } else
            {
                bot("Gpca is polytechnic college where computer and civil courses are avail.");

                bot("Here is the link for college:https:\\www.govtpolyanantnag.org");
            }
        }
        else if (message.contains("female") || message.contains("male") || message.contains("gender")) {

            bot("i am just a  assistant");

        }
        else if (message.contains("yes"))
        {


            bot("i knew it ");

        }
        else if (message.contains("principal"))
        {

            bot("MR javid ahmad iqbal");

        }
        else if (message.contains("who is javid ahmad iqbal") || message.contains("javid iqbal"))
        {

            bot("Mr javid iqbal is principal of gpca");

        }
        else if (message.contains("hod"))
        {

            bot("MR paraviz ahmad ");

        }
        else if (message.contains("paraviz ahmad"))
        {

            bot("HOD OF GPCA ");

        }
        else if (message.contains("ok"))
        {

            bot("yes");
        }


        else if (message.contains("show my ip")) {
            try {
                bot("the ip:" + InetAddress.getLocalHost().getHostAddress());

            } catch (UnknownHostException ex) {

                ex.printStackTrace();
            }

        }
        else if (message.contains("www.")) {
            try {

                InetAddress address = InetAddress.getByName(message);

                bot("the ip is:"+address.getHostAddress());

            } catch (UnknownHostException ex) {

                ex.printStackTrace();
            }

        } else if (message.contains("2") || message.contains("3") || message.contains("1") || message.contains("4")) {
            try {
                InetAddress address = InetAddress.getByName(message);
                bot(address.getHostName());

            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        } else if (message.contains("who am i.")) {

            bot("My guess you are one who wrote my code");

        } else if (message.contains("who am i") || message.contains("who i am")) {

            bot("My guess is you are a human");

        } else if (message.contains("good"))

        {
            bot("well thanks");
        }

        else if (message.contains("who") && message.contains("your")) {

            bot("To me EVERYTHING is my developer");

            bot("faisal bhatt");

        } else if (message.contains("how much") && message.contains("are you")) {

            bot("well i am not but i pretend to be");

        }
        else if (message.contains("question"))
        {

            bot("why not but connect to internet i will answer to your any query");

        }
        else  if(message.contains("no") || message.contains("bad") || message.contains("not"))
        {

            bot("oh");

        }
        else
        {


            try {

                try {

                    URL url = new URL("https://google.co.in");

                    URLConnection connection = url.openConnection();

                    connection.connect();

                    bot("Here some results for you ...");

                    java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?hl=en&q=" + message.replace(" ", "+") + "&btnG=Google+Search"));

                } catch (Exception ee) {

                    bot("Connect with internet connection for get better results...");
                }

            } catch (Exception eee) {

                int num = random.nextInt(3);

                if (num == 0) {

                    bot("Sorry ,I can't understand you !");

                } else if (num == 1) {

                    bot("Sorry,I don't understand ");

                } else {

                    bot("My apologies...I don't understand ");
                }
            }
        }

    }

    public static void bot(String message) {
        area.append("Bot : " + message + "\n");
    }

    public static void main(String[] args) {
        ChatBot cb = new ChatBot("Virtual Assistant");
        cb.setSize(820, 605);
        cb.setLocation(50, 50);

    }

}

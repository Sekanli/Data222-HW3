public class TestClass4 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        int useridcount = 1 ,postidcount = 1;
        Account gizemsungu = new Account(useridcount++, "gizemsungu", "01.01.2001", "Adana");
        Account sibelgulmez = new Account(useridcount++, "sibelgulmez", "03.03.2003", "Kayseri");
        Account gokhankaya = new Account(useridcount++, "gokhankaya", "05.05.2005", "Zonguldak");
        Account acc4 = new Account(useridcount++, "acc4", "05.05.2005", "Chicago");
        Account acc5 = new Account(useridcount++, "acc5", "05.05.2005", "Chicago");
        Account acc6 = new Account(useridcount++, "acc6", "05.05.2005", "Chicago");
        Account acc7 = new Account(useridcount++, "acc7", "05.05.2005", "Chicago");
        Account acc8 = new Account(useridcount++, "acc8", "05.05.2005", "Chicago");
        Account acc9 = new Account(useridcount++, "acc9", "05.05.2005", "Chicago");
        Account acc10 = new Account(useridcount++, "acc10", "05.05.2005", "Chicago");
        sibelgulmez.login();
        Post p1 = new Post("Weather is great", postidcount++);
        Post p2 = new Post("Homework is annoying", postidcount++);
        sibelgulmez.addPost(p2);
        sibelgulmez.addPost(p1);
        sibelgulmez.follow(gokhankaya);
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.logout();

        gokhankaya.login();
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);
        System.out.println("asdasd");
        new Like (p1,gokhankaya);
        new Comment(p2, gokhankaya, "This is sparta.");
        gokhankaya.follow(sibelgulmez);
        gokhankaya.follow(gizemsungu);
        gokhankaya.sendMessage(gizemsungu,"Hello there.");
        gokhankaya.logout();

        gizemsungu.login();
        gizemsungu.checkInbox();
        gizemsungu.checkOutbox();
        gizemsungu.getInbox();
        gizemsungu.viewProfile(sibelgulmez);
        gizemsungu.viewPosts(sibelgulmez);
        gizemsungu.viewPostReactions(sibelgulmez);
        new Like(p1,gizemsungu);
        gizemsungu.viewPostReactions(sibelgulmez);


        /////////////Scenario 4/////////////
        System.out.println("Scenario 4");

        new Like(p2,gizemsungu);
        gizemsungu.follow(sibelgulmez);
        gizemsungu.follow(gokhankaya);
        gizemsungu.unfollow(sibelgulmez);
        new Comment(p2,gizemsungu, " Agreed! ");
        gizemsungu.follow(sibelgulmez);
        gizemsungu.showActionHistory();
        gizemsungu.logout();
        ///////////////////////////////////
        System.out.println("Scenario 4+10acc");

        acc4.login();
        Post post1 = new Post("Dont know dont care", postidcount++);
        acc4.addPost(post1);
        acc4.follow(acc10);
       
        acc4.follow(acc7);
        acc4.follow(acc9);
        acc4.follow(gokhankaya);

        acc4.block(acc10);
        acc4.unfollow(acc7);
        acc4.unfollow(acc10);

        new Like(p2, acc4);
        acc4.showActionHistory();
        acc4.unblock(acc10);
        acc4.follow(acc10);
        acc4.logout();

        acc5.login();
        new Like(post1,acc5);
        acc5.logout();

        acc6.login();
        new Like(post1,acc6);
        acc6.logout();

        acc7.login();
        new Like(post1,acc7);
        new Comment(post1,acc7,"Seems cool");
        new Comment(post1, acc7, "Hit me");
        acc7.logout();

        acc8.login();
        new Comment(post1, acc8, "Good job");
        acc8.logout();

        acc9.login();
        acc9.block(acc4);
        acc9.follow(acc10);
        acc9.logout();

        acc10.login();
        acc10.getFollowers();
        
        System.out.println("acc4 details \n");

        acc10.viewPosts(acc4);
        acc10.viewPostReactions(acc4);
        acc10.logout();

        acc4.getActionHistory();
   
        long end=System.currentTimeMillis();
        System.out.println("Total timex is : " + (end-start) + " seconds ");


    }
}


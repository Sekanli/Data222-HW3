import java.util.ArrayList;

/**
 * Account class implementation
 * 
 * @author Sekanli
 * 
 */
class Account {
    protected String birthday, username, location;
    protected int accountid;
    ArrayList<Account> followers = new ArrayList<Account>();
    ArrayList<Account> following =  new ArrayList<Account>();
    private ArrayList<Post> post;
    private ArrayList<Message> inbox;
    private ArrayList<Message> outbox;
    private boolean login_status = false;
    private ArrayList<Account> blockedid = new ArrayList<Account>();
    private ArrayList<Action> actionHistory = new ArrayList<Action>();

    /**
     * 
     * @param accountid
     * @param username
     * @param birthday
     * @param location
     */

    public Account(int accountid, String username, String birthday, String location) {
        this.accountid = accountid;
        this.username = username;
        this.birthday = birthday;
        this.location = location;
        this.inbox = new ArrayList<Message>();
        this.outbox = new ArrayList<Message>();
        this.post = new ArrayList<Post>();
    }
    /**
     * print the action history based on action type
     * 
     */
    public void showActionHistory()
    {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
      for( Action action : actionHistory)
      {
        if(action.getType() == "like")
        {
            System.out.println("You liked  " + action.getPostOwnerUserName() + "'s post id : " + action.getPostID());
        }
        if(action.getType() == "commment")
        {
            System.out.println("You commented to  " + action.getPostOwnerUserName() + "'s post id : "+ action.getPostID());
        }
        if(action.getType() == "post")
        {
            System.out.println("You shared a post with postID " +  action.getPostID());
        }
        if(action.getType() == "follow")
        {
            System.out.println("You followed " + action.getUsername());
        }
        if(action.getType() == "unlike")
        {
            System.out.println("You unliked a post from " + action.getPostOwnerUserName() + "'s post id : " + action.getPostID());
        }
        if(action.getType() == "uncomment")
        {
            System.out.println("You uncommented a post from" + action.getPostOwnerUserName() + "'s post id : " + action.getPostID());
        }
        if(action.getType() == "unfollow")
        {
            System.out.println("You unfollowed " + action.getUsername());
        }   
        if(action.getType() == "block")
        {
            System.out.println("You blocked " + action.getUsername());
        }   
        if(action.getType() == "unblock")
        {
            System.out.println("You unblocked " + action.getUsername());
        }   
        
      }
    }





    /**
     * Unlike a post
     * @param p
     */
    public void unlike(Post p)
    {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        boolean init = false;
        int index = 0;
        for(int i=0;i<p.getlikearray().size();i++)
        {
            if(p.getlikearray().get(i).getInteractionID() == this.accountid)
            {
                init=true;
                index=i;
                break;
            }
        }
        if(init==false)
        {
            System.out.println("You can not unlike a post you didnt like earlier");
            return;
        }
        p.getlikearray().remove(index);
        actionHistory.add(new Action ("unlike",p));
        }
        /**
         * Uncomment from a post
         * @param p
         * @param comment_number
         */
    public void uncomment(Post p,int comment_number)
    {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        boolean init = false;
        int index = 0;
        int count = 0;
        for(int i=0;i<p.getCommentarray().size();i++)
        {
            if(p.getCommentarray().get(i).getInteractionID() == this.accountid)
            {
                
                count++;
                if(count==comment_number)
                {
                    init=true;
                    index = i;
                }
            }
        }
        if(init==false)
        {
            System.out.println("Error on uncomment ...! ");
            return;
        }
        p.getCommentarray().remove(index);
        actionHistory.add(new Action ("uncomment",p));

    }
    /**
     * Unfollow a target
     * @param target
     */
    public void unfollow(Account target)
    {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        if(!isFollowing(target))
        {
            System.out.println("You cant unfollow an account you are not following");
            return;
        }
        target.followers.remove(this);
        this.following.remove(target);
        actionHistory.add(new Action ("unfollow",target));
      /*   for(int i=0;i<following.size();i++)
        {
            if(target.getUserID()==this.following.get(i).getUserID())
            {
                this.following.remove(i);
            }
            if(target.followers.get(i).getUserID() == this.getUserID())
            {
                target.followers.remove(i);
            }
        } */
    }
    /**
     * 
     * @param target
     * unblocking a target
     * 
     */
    public void unblock(Account target)
    {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        if(!isBlocked(target))
        {
            System.out.println("You cant unblock an account you didnt block earlier");
            return;
        }
        blockedid.remove(target);
        target.blockedid.remove(this);
        actionHistory.add(new Action ("unblock",target));
    }


    







    /**
     * block method allows user to block another user
     * 
     * @param target target account to block
     */
    public void block(Account target) {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        blockedid.add(target);
        followers.remove(target); // If this isnt following target,nothing will happen
        following.remove(target);
        target.followers.remove(this);
        target.following.remove(this);
        target.blockedid.add(this);
        actionHistory.add(new Action ("block",target));
    }

    /**
     * Checks if the target user is blocked
     * 
     * @param target target user for checking if this user blocked him/her
     * @return returns boolean based on the target is blocked or not
     */
    public boolean isBlocked(Account target) {
        for (int i = 0; i < blockedid.size(); i++) {
            if (blockedid.get(i).getUserID() == target.getUserID()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Allowing users to see how many messages they got in their inbox-outbox
     */
    public void checkInbox() {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        System.out.println("There is/are " + inbox.size() + "message(s) in the inbox.");
    }

    public void checkOutbox() {
        if(!getLoginStatus())
        {
            System.out.println("You did not login");
            return;
        }
        System.out.println("There is/are " + outbox.size() + "message(s) in the outbox.");
    }

    // Method for logging in to an account
    // User cant login twice
    public void login() {
        if (login_status == false)
            login_status = true;
        else {
            System.out.println("You already did login earlier");
        }
    }

    /*
     * Method for logging out from an account
     * User cant logout twice
     */
    public void logout() {
        if (login_status == true)
            login_status = false;
        else {
            System.out.println("You cant logout from an account you did not login earlier");
        }
    }

    /* Method to check if the user is logged in */
    public boolean getLoginStatus() {
        return login_status;
    }

    /**
     * Allowing user to share a new post
     * 
     * @param p parameter of the show that user wants to share
     */
    public void addPost(Post p) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
          post.add(p);
          p.SetOwner(this);
          actionHistory.add(new Action ("post",p));

    }

    /**
     * allowing user to view the reactions to the post if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the post reactions
     */
    public void viewPostReactions(Account target) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }

        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets posts ");
            return;
        }
        target.getPostReactions();
    }

    /**
     * printing the post reactions
     */
    public void getPostReactions() {
        System.out.println("Viewing " + getUsername() + "'s post interactions:\n -------------------- ");

        for (int i = 0; i < post.size(); i++) {

            System.out.println("(PostID : " + post.get(i).getPostID() + ") : " + post.get(i).getContent());
            if (post.get(i).getLikeCount() == 0) {
                System.out.println("This post has no likes.");
            } else {
                System.out.print("This post is liked by following accounts : ");

                for (int n = 0; n < post.get(i).getLikeCount(); n++) {
                    if (post.get(i).getlikearray().get(n) != null)
                        System.out.print(post.get(i).getlikearray().get(n).getInteractionName() + ",");
                }
                System.out.print("\n");
            }
            if (post.get(i).getCommentCount() == 0) {
                System.out.println("This post has no comments.");
            } else {
                System.out.print("This post is commented by following accounts : ");
                for (int n = 0; n < post.get(i).getCommentCount(); n++) {
                    if (post.get(i).getCommentarray().get(n) != null)
                        System.out.print(post.get(i).getCommentarray().get(n).getInteractionName() + ",");
                }
                System.out.println();
                for (int a1 = 0; a1 < post.get(i).getCommentCount(); a1++) {
                    if (post.get(i).getCommentarray().get(a1) != null)
                        System.out.println("Comment " + a1 + 1 + " :(" + 
                        post.get(i).getCommentarray().get(a1).getInteractionName()+ ") ==> " +
                        post.get(i).getCommentarray().get(a1).getContent());
                }
                System.out.println();

            }
        }
        System.out.println(" -------------------- ");
    }

    /**
     * allowing user to view the posts if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the posts
     */
    public void viewPosts(Account target) {
        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets posts ");
            return;
        }
        target.getPosts();
    }

    /**
     * * allowing user to view the profile if the user is logged in and
     * is not blocked by the target
     * 
     * @param target target account to see the post reactions
     */
    public void viewProfile(Account target) {

        if (target.isBlocked(this)) {
            System.out.println("You cant view this targets profile ");
            return;
        }
        target.getProfile();

    }

    /*
     * Printing the profile
     */
    public void getProfile() {
        System.out.println("Viewing " + getUsername() + "'s profile..."
                + "\nUser ID: " + accountid + "\nUsername: " + username
                + "\nLocation: " + location + "\nBirthday: " + birthday
                + "\n" + getUsername() + " is following " + following.size() + " account(s) and has "
                + followers.size() + " follower(s)");
        getFollowers();
        getFollowing();
        System.out.println(username + " has " + post.size() + " post(s)");

    }

    // Reach user inbox - outbox
    // Cant reach if user has not logged in
    public void getInbox() {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        System.out.println("Accessing the inbox of " + getUsername());
        for (int n = 0; n < inbox.size(); n++) {
            if (inbox.get(n) != null)
                System.out.println(n + 1 + ":\n" + inbox.get(n).getMessageContent() + "\n");
        }
    }

    public void getOutbox() {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        System.out.println("Accessing the outbox of " + getUsername());
        for (int n = 0; n < outbox.size(); n++) {
            if (outbox.get(n) != null)
                System.out.println(n + 1 + ":\n" + outbox.get(n).getMessageContent() + "\n");
        }
    }

    /**
     * 
     * @param account target account to follow
     */
    // Follow a target account => User following list updated,target follower list
    // updated
    // Cant follow if the user has not logged in
    public void follow(Account account) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        // Check if the account is already being followed
        for (int i = 0; i < following.size(); i++) {
            if (following.get(i) != null && following.get(i).equals(account)) {
                System.out.println("You are already following " + account.getUsername());
                return;
            }
        }
        this.following.add(account);
        account.followers.add(this);
        actionHistory.add(new Action ("follow",account));

    }
    public ArrayList<Action> getActionHistory()
    {
        return actionHistory;
    }
    // Check if the user is following the target account (for send messages)
    public boolean isFollowing(Account receiver) {
        boolean isFollowing = false;
        for (Account account : this.following) {
            if (account != null && account.getUserID() == receiver.getUserID()) {
                isFollowing = true;
                break;
            }
        }
        if (!isFollowing) {
            return false;
        }
        return true;
    }

    /**
     * Send message to another user with its content if the user is logged in,is not
     * blocked by the target user
     * and is following the target user
     * 
     * @param receiver        the user to take the message
     * @param message_content the content to deliver
     */
    public void sendMessage(Account receiver, String message_content) {
        if (!getLoginStatus()) {
            System.out.println("You did not login");
            return;
        }
        if (receiver.isBlocked(this)) {
            System.out.println("You cant send message to this target");
            return;
        }
        // Check the follow status
        if (!isFollowing(receiver)) {
            System.out.println("Cannot send message to " + receiver.getUsername() + ". You are not following them.");
            return;
        }
        // Send message , receiver receives the message to her/his inbox

        Message message = new Message(this, receiver, message_content);
        receiver.receiveMessage(message);
        addMessageToOutbox(message);
    }

    /**
     * A message was sent from another user,receive the message and add it to your
     * inbox
     * 
     * @param message
     */
    public void receiveMessage(Message message) {
        addMessageToInbox(message);
    }

  
    private void addMessageToInbox(Message message) {
        inbox.add(message);
    }

    /**
     * Add the sent message to your outbox
     * 
     * @param message
     */
    private void addMessageToOutbox(Message message) {
        outbox.add(message);
    }
    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return accountid;
    }

    public void getPosts() {

        System.out.println("Accessing the posts of " + getUsername());
        for (int n = 0; n < post.size(); n++) {
            if (post.get(n)!= null)
                System.out.println("(PostID : " +post.get(n).getPostID() + "):" +post.get(n).getContent());
        }

    }

    /**
     * If a user follows a target user,add the user as a follower to the target
     * account
     * 
     * @param follower target user
     */
    public void addFollower(Account follower) {
   
            followers.add(follower);////////////////////////////////

    }

    /*
     * print followers if it isnt 0
     */
    public void getFollowers() {
        if (followers.size()==0)
        {
            System.out.println("Noone is following " + username);
            return;
        }
        System.out.print(username + " is being followed by ");
        for (int n = 0; n < followers.size() - 1; n++) {
            System.out.print(followers.get(n).getUsername() + " , ");
        }
        System.out.println(followers.get(followers.size()-1).getUsername());
    }

    /**
     * if the user starts to follow target user,add the target user to the following
     * array of the user
     * 
     * @param following
     */
    public void addFollowing(Account following) {
            this.following.add(following);
         
    }

    /**
     * print
     */
    public void getFollowing() {
        if (following.size() == 0)
            return;

        System.out.print(username + " is following ");
        for (int n = 0; n < following.size() - 1; n++) {
            System.out.print(following.get(n).getUsername() + " , ");
        }
        System.out.println(following.get(following.size() - 1).getUsername());
    }
}

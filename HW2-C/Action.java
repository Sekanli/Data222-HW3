public class Action {
    private String type;
    private Post post;
    private Account acc;
    /**
     * Action class has 2 constructors
     * If an interaction happened to a post it uses this one
     * 
     * Main purpose is to keep the target post and use its owners name in action history
     * @param type
     * @param post
     */
    Action(String type,Post post)
    {
        this.type = type;
        this.post = post;
    }
    /**
     * In case of a follow,unfollow,block,unblock situtations,this constructor is used
     * Main purpose is to keep the target account and use its name in action history
     * @param type
     * @param acc
     */
    Action(String type,Account acc)
    {
        this.type = type;
        this.acc = acc;
    }

    public String getUsername()
    {
        return acc.getUsername();
    }

    public String getPostOwnerUserName()
    {
        return post.getOwner().getUsername();
    }

    public int getPostID()
    {
        return post.getPostID();
    }
    public String getType()
    {
        return type;
    }
}

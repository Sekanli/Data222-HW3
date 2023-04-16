

class Post {
    private String content;
    private int postID;
    private LDLinkedList<Like> likearray;
    private LDLinkedList<Comment> commentarray;
    private int likeCount, commentCount;
    private Account Owner;
/**
 * Constructor 
 * @param content contents of the post
 * @param postid  unique id of the post
 */
    public Post(String content, int postid) {
        this.content = content;
        likearray = new  LDLinkedList<Like>();
        commentarray = new LDLinkedList<Comment>();
        this.likeCount = 0;
        this.commentCount = 0;
        postID = postid;

    }
    public void SetOwner(Account acc)
    {
        Owner=acc;
    }
    public Account getOwner()
    {
        return Owner;
    }
    public int getLikeCount() {
        return likeCount;
    }
    /**
     * if the post is liked,add it to the likes array to keep track of who likes the post
     * @param obj like object with the user's id and name who liked the post
     */
    public void addtolikeArray(Like obj) {

        likearray.add(obj);
        likeCount++;
    }

    public int getCommentCount() {
        return commentCount;
    }
    /**
     * if the post is commented,add it to the likes array to keep track of who likes the post
     * @param obj like object with the user's id and name who commented the post
     */
    public void addtoCommentArray(Comment obj) {

        commentarray.add(obj);
        commentCount++;
    }

    public LDLinkedList<Like>  getlikearr() {
        return likearray;
    }

    public LDLinkedList<Comment> getCommentarr() {
        return commentarray;
    }
    

    public String getContent() {
        return content;
    }

    public int getPostID() {
        return postID;
    }
}

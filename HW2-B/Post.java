import java.util.LinkedList;

class Post {
    private String content;
    private int postID;
    private LinkedList<Like> likearray;
    private LinkedList<Comment> commentarray;
    private int likeCount, commentCount;
    private Account Owner;
/**
 * Constructor 
 * @param content contents of the post
 * @param postid  unique id of the post
 */
    public Post(String content, int postid) {
        this.content = content;
        likearray = new  LinkedList<Like>();
        commentarray = new LinkedList<Comment>();
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

    public LinkedList<Like>  getlikearray() {
        return likearray;
    }

    public LinkedList<Comment>  getCommentarray() {
        return commentarray;
    }

    public String getContent() {
        return content;
    }

    public int getPostID() {
        return postID;
    }
}

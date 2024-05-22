package CodeTest;

public class Content {

    public Content(int id, int pop){
        contentId = id;
        popularity = pop;
    }

    public int contentId;

    public int popularity;

    @Override
    public boolean equals(Object obj){
        Content content = (Content)obj;
        return this.contentId == content.contentId;
    }

}

package CodeTest;

public class Content implements Comparable<Content> {

    public Content(int id, int pop){
        contentId = id;
        popularity = pop;
    }

    public int contentId;

    public int popularity;

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        Content content = (Content)obj;
        return this.contentId == content.contentId;
    }

    @Override
	public int hashCode() {
		return this.hashCode();
	}

    @Override
    public int compareTo(Content o) {
        if(this.contentId == o.contentId){
            return 0;
        }
        if(this.popularity == o.popularity){
            return Integer.compare(o.contentId, this.contentId);
        }
        return Integer.compare(o.popularity, this.popularity);
    }

}

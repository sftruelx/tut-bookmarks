package bookmarks.bootstraptable;


public class PaginatorResult {


    public PaginatorResult(Object rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    private Object rows;
    private long total;

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

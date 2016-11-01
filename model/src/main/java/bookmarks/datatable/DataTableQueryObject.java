package bookmarks.datatable;

/**
 * Created by liaoxiang on 2016/11/1.
 * bSortable_0
 true
 bSortable_1
 true
 iColumns
 2
 iDisplayLength
 10
 iDisplayStart
 10
 iSortCol_0
 0
 iSortingCols
 1
 list
 mDataProp_0
 albumName
 mDataProp_1
 author
 sColumns
 ,
 sEcho
 2
 sSortDir_0
 asc
 */
public class DataTableQueryObject {

    private Integer iDisplayLength;
    private Integer iDisplayStart;
    private Integer sEcho;
    private String sSortDir;
    private String[] mDataProp;
    private Boolean[]  bSortable;

    public Integer getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public Integer getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public String getsSortDir() {
        return sSortDir;
    }

    public void setsSortDir(String sSortDir) {
        this.sSortDir = sSortDir;
    }

    public String[] getmDataProp() {
        return mDataProp;
    }

    public void setmDataProp(String[] mDataProp) {
        this.mDataProp = mDataProp;
    }

    public Boolean[] getbSortable() {
        return bSortable;
    }

    public void setbSortable(Boolean[] bSortable) {
        this.bSortable = bSortable;
    }
}

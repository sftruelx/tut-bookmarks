package bookmarks.workship.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyDataSet {

    private Long id;
    private String dataSetTable;
    private String dataSetNameField;
    private String dataSetValueField;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataSetTable() {
        return dataSetTable;
    }

    public void setDataSetTable(String dataSetTable) {
        this.dataSetTable = dataSetTable;
    }

    public String getDataSetNameField() {
        return dataSetNameField;
    }

    public void setDataSetNameField(String dataSetNameField) {
        this.dataSetNameField = dataSetNameField;
    }

    public String getDataSetValueField() {
        return dataSetValueField;
    }

    public void setDataSetValueField(String dataSetValueField) {
        this.dataSetValueField = dataSetValueField;
    }
}

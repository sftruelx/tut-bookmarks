package bookmarks.workship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Larry on 2016/10/22.
 *           System.out.println(rs.getString("Field") + "\t:\t" + rs.getString("Comment"));
 System.out.println(rs.getString("type"));
 System.out.println(rs.getString("key"));
 System.out.println(rs.getString("null"));
 */
@Entity
public class MyTableField {

    private Long id;
    private String field;
    private String fieldUpCase;
    private String fieldType;
    private String fieldKey;
    private String fieldNull;
    private String tableName;
    private String comment;

    private Boolean isList = Boolean.TRUE;
    private String fieldName;
    private String fieldControl;
    private String fieldClass;
    private Boolean ifSearch;
    private Set<MyValidate> validates = new HashSet<MyValidate>();
    private MyDataSet dataSet;
    private String dataSource;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldNull() {
        return fieldNull;
    }

    public void setFieldNull(String fieldNull) {
        this.fieldNull = fieldNull;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldUpCase() {
        return fieldUpCase;
    }

    public void setFieldUpCase(String fieldUpCase) {
        this.fieldUpCase = fieldUpCase;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldControl() {
        return fieldControl;
    }

    public void setFieldControl(String fieldControl) {
        this.fieldControl = fieldControl;
    }

    public String getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(String fieldClass) {
        this.fieldClass = fieldClass;
    }

    public Boolean getIfSearch() {
        return ifSearch;
    }

    public void setIfSearch(Boolean ifSearch) {
        this.ifSearch = ifSearch;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "my_field_validate",
            joinColumns = { @JoinColumn(name = "field_id") },
            inverseJoinColumns = @JoinColumn(name = "validate_id")
    )
    public Set<MyValidate> getValidates() {
        return validates;
    }

    public void setValidates(Set<MyValidate> validates) {
        this.validates = validates;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getList() {
        return isList;
    }

    public void setList(Boolean list) {
        isList = list;
    }
    @OneToOne
    @JoinColumn(name = "dataSet_id", insertable = true, unique = true)
    public MyDataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(MyDataSet dataSet) {
        this.dataSet = dataSet;
    }
}

package bookmarks.workship.entity;

import javax.persistence.*;

@Entity
public class MyTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tablesInTest;

    private String tableName;

    private String tableNameUpCase;

    private String module;

    public String getTablesInTest() {
        return tablesInTest;
    }

    public void setTablesInTest(String tablesInTest) {
        this.tablesInTest = tablesInTest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTableNameUpCase() {
        return tableNameUpCase;
    }

    public void setTableNameUpCase(String tableNameUpCase) {
        this.tableNameUpCase = tableNameUpCase;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

package bookmarks.workship.repository;

import bookmarks.workship.entity.MyTable;
import bookmarks.workship.entity.MyTableField;
import bookmarks.workship.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public  class MyTableDao{
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MyTableDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 获取所有的用户
     *
     * @return List
     */
    public List<MyTable> getAll() {
        String sql = "show tables";

        List<MyTable> tables = jdbcTemplate.query(sql, new RowMapper<MyTable>() {
            @Override
            public MyTable mapRow(ResultSet resultSet, int i) throws SQLException {
                MyTable t = new MyTable();

                t.setTablesInTest(resultSet.getString(1));
                t.setTableName(StringUtil.replaceUnderlineAndfirstToUpper(t.getTablesInTest()));
                t.setTableNameUpCase(StringUtil.captial(t.getTableName()));
                return t;
            }
        });

        return tables;
    }

    public List<MyTableField> getFields(String tableName) {
        String sql = "show full columns from " + tableName;

        List<MyTableField> fields = jdbcTemplate.query(sql, new RowMapper<MyTableField>() {
            @Override
            public MyTableField mapRow(ResultSet resultSet, int i) throws SQLException {
                MyTableField f = new MyTableField();

                f.setField(StringUtil.replaceUnderlineAndfirstToUpper(resultSet.getString("field")));
                f.setFieldUpCase(StringUtil.captial(f.getField()));
                f.setFieldKey(resultSet.getString("key"));
                f.setFieldType(StringUtil.sqlType2JavaType(resultSet.getString("type")));
                f.setFieldNull(resultSet.getString("null"));
                f.setComment(resultSet.getString("comment"));
                f.setTableName(tableName);
                return f;
            }
        });

        return fields;
    }


}
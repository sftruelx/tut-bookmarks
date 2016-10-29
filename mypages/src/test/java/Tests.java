import bookmarks.PageApp;
import bookmarks.workship.entity.MyTableField;
import bookmarks.workship.repository.MyTableFieldRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PageApp.class)
public class Tests {

    @Autowired
    MyTableFieldRepository tableFieldRepository;
    @Test
    public void test1(){
       MyTableField field =  tableFieldRepository.findOne(5L);
        System.out.println(field.toString());
    }
}

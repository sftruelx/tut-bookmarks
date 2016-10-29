package bookmarks.controller;

import bookmarks.workship.entity.MyTable;
import bookmarks.workship.entity.MyTableField;
import bookmarks.workship.repository.BizAlbumDao;
import bookmarks.workship.repository.MyTableDao;
import bookmarks.workship.repository.MyTableFieldRepository;
import bookmarks.workship.repository.MyTableRepository;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Controller
public class App3Controller {
    @Autowired
    BizAlbumDao bizAlbumDao;
    @Autowired
    MyTableDao myTableDao;
    @Autowired
    MyTableRepository myTableRepository;
    @Autowired
    MyTableFieldRepository myTableFieldRepository;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello3() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Iterable<MyTable> tables = myTableRepository.findAll();
        for(MyTable table : tables) {
            Template controllerTpt = ve.getTemplate("vm/ControllerTemplate.vm");
            Template entityTpt = ve.getTemplate("vm/EntityTemplate.vm");
            Template repositoryTpt = ve.getTemplate("vm/RepositoryTemplate.vm");
            Template addTpt = ve.getTemplate("vm/FormTemplate.vm");

            VelocityContext ctx = getVelocityContext(table);

//            StringWriter stringWriter = new StringWriter();
//            template.merge(ctx, stringWriter);
//            System.out.println(stringWriter.toString());
            String rootPath = "C:\\Users\\Larry\\Desktop\\demo\\multiboot3\\src\\main";
            File dir = new File("C:\\Users\\Larry\\Desktop\\demo\\multiboot3\\src\\main\\java\\com\\example\\velocity\\controller");
            if(!dir.exists()){
                    dir.mkdirs();
            }
            File dir1 = new File("C:\\Users\\Larry\\Desktop\\demo\\multiboot3\\src\\main\\java\\com\\example\\velocity\\entity");
            if(!dir1.exists()){
                dir1.mkdirs();
            }
            File dir2 = new File("C:\\Users\\Larry\\Desktop\\demo\\multiboot3\\src\\main\\java\\com\\example\\velocity\\repository");
            if(!dir2.exists()){
                dir2.mkdirs();
            }
            merge(controllerTpt,ctx,rootPath+"/java/com/example/velocity/controller/"+ table.getTableNameUpCase() +"Controller.java");
            merge(entityTpt,ctx,rootPath+"/java/com/example/velocity/entity/"+ table.getTableNameUpCase() +".java");
            merge(repositoryTpt,ctx,rootPath+"/java/com/example/velocity/repository/"+ table.getTableNameUpCase() +"Repository.java");
            merge(addTpt,ctx,rootPath+"/resources/templates3/"+ table.getTableNameUpCase()+".html");
            System.out.println("success...");
        }
        return "OK";
    }

    private static void merge(Template template, VelocityContext ctx, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
    private VelocityContext getVelocityContext(MyTable table) {

        List<MyTableField> fields = myTableFieldRepository.findByTableName(table.getTablesInTest());
        VelocityContext ctx = new VelocityContext();

        ctx.put("table", table);

        ctx.put("fields", fields);
        return ctx;
    }



    @RequestMapping("/hello2")
    public String hello2() {
        List<MyTable> list = myTableDao.getAll();
        List<MyTableField> fieldList = newArrayList();
        for (MyTable t1 : list) {
            System.out.println(t1.getTablesInTest());
            try {
                myTableRepository.save(t1);
                List<MyTableField> fields = myTableDao.getFields(t1.getTablesInTest());
                myTableFieldRepository.save(fields);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        System.out.println(list.size());

        return "hello";
    }
}

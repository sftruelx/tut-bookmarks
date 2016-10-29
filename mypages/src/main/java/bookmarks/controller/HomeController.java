package bookmarks.controller;

import bookmarks.workship.entity.MyTable;
import bookmarks.workship.repository.MyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    MyTableRepository tableRepository;

    @RequestMapping(value = "/test")
    public ModelAndView form1(Map<String,Object> model){
        Iterable<MyTable> tables = tableRepository.findAll();
        model.put("tables",tables);
        return new ModelAndView("faq",model);
    }

    @RequestMapping(value = "/form")
    public ModelAndView form2(Map<String,Object> model){
        Iterable<MyTable> tables = tableRepository.findAll();
        model.put("tables",tables);
        return new ModelAndView("form-elements",model);
    }

/*    @RequestMapping(value = "/websocket")
    public ModelAndView websocket(Map<String,Object> model){
        Iterable<MyTable> tables = tableRepository.findAll();
        model.put("tables",tables);
        return new ModelAndView("websocket",model);
    }*/
}

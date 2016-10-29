package bookmarks.controller;

import bookmarks.workship.entity.MyTable;
import bookmarks.workship.repository.MyTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tables")
public class TablesController {
    private static final Logger logger = LoggerFactory.getLogger(TablesController.class);

    private final MyTableRepository myTableRepository;

    public TablesController(MyTableRepository myTableRepository) {
        this.myTableRepository = myTableRepository;
    }

    @RequestMapping(value="/show")
    public ModelAndView show() {
        Iterable<MyTable> MyTables = this.myTableRepository.findAll();
        return new ModelAndView("MyTable/list", "tables", MyTables);
    }
    @ResponseBody
    @GetMapping
    public Iterable<MyTable> list() {
        Iterable<MyTable> myTables = this.myTableRepository.findAll();
        return myTables;
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") MyTable myTable) {
        logger.info(myTable.toString());
        return new ModelAndView("MyTables/view", "MyTable", myTable);
    }

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute MyTable myTable) {
        return "MyTables/form";
    }

    @PostMapping
    public ModelAndView create(@Valid MyTable myTable, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("MyTables/form", "formErrors", result.getAllErrors());
        }
        myTable = this.myTableRepository.save(myTable);
        redirect.addFlashAttribute("globalMyTable", "Successfully created a new MyTable");
        return new ModelAndView("redirect:/msg/{MyTable.id}", "MyTable.id", myTable.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.myTableRepository.delete(id);
        Iterable<MyTable> MyTables = this.myTableRepository.findAll();
        return new ModelAndView("MyTables/list", "MyTables", MyTables);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") MyTable MyTable) {
        return new ModelAndView("MyTables/form", "MyTable", MyTable);
    }

}

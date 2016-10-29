package bookmarks.controller;


import bookmarks.bootstraptable.Paginator;
import bookmarks.bootstraptable.PaginatorResult;
import bookmarks.workship.entity.MyTableField;
import bookmarks.workship.repository.MyTableFieldRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

@Controller
@RequestMapping("/fields")
public class TableFieldsController {
    private static final Logger logger = LoggerFactory.getLogger(TableFieldsController.class);

    private final MyTableFieldRepository myTableFieldRepository;

    public TableFieldsController(MyTableFieldRepository myTableFieldRepository) {
        this.myTableFieldRepository = myTableFieldRepository;
    }

    @RequestMapping(value="/show")
    public ModelAndView show() {
        Iterable<MyTableField> myTableField = this.myTableFieldRepository.findAll();
        return new ModelAndView("MyTableField/list", "myTableField", myTableField);
    }

    @ResponseBody
    @GetMapping
    public PaginatorResult list(Paginator paginator, String tableName ) {
        Pageable pageable = new PageRequest(paginator.getOffset()/paginator.getLimit(), paginator.getLimit());
        Page<MyTableField> fields = this.myTableFieldRepository.findAll(new Specification<MyTableField>() {
            @Override
            public Predicate toPredicate(Root<MyTableField> root,
                                         CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate name = cb.equal(root.get("tableName").as(String.class),tableName);

                return name;
            }
        },pageable);
        PaginatorResult result = new PaginatorResult(fields.getContent(),fields.getTotalElements());
        return result;
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") MyTableField myTableField) {
        logger.info(myTableField.toString());
        return new ModelAndView("MyTableFields/view", "MyTableField", myTableField);
    }

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute MyTableField myTableField) {
        return "MyTableFields/form";
    }

    @PostMapping
    public ModelAndView create(@Valid MyTableField myTableField, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("MyTableFields/form", "formErrors", result.getAllErrors());
        }
        myTableField = this.myTableFieldRepository.save(myTableField);
        redirect.addFlashAttribute("globalMyTableField", "Successfully created a new MyTableField");
        return new ModelAndView("redirect:/msg/{MyTableField.id}", "MyTableField.id", myTableField.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.myTableFieldRepository.delete(id);
        Iterable<MyTableField> myTableFields = this.myTableFieldRepository.findAll();
        return new ModelAndView("MyTableFields/list", "MyTableFields", myTableFields);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") MyTableField myTableField) {
        return new ModelAndView("MyTableFields/form", "MyTableField", myTableField);
    }

}

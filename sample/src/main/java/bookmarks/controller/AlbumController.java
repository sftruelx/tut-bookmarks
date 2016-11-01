package bookmarks.controller;


import bookmarks.datatable.DataTableQueryObject;
import bookmarks.datatable.DataTableReturnObject;
import bookmarks.bootstraptable.Paginator;
import bookmarks.bootstraptable.PaginatorResult;
import bookmarks.entity.Album;
import bookmarks.entity.Teammate;
import bookmarks.repository.AlbumRepository;
import bookmarks.repository.TeammateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    TeammateRepository teammateRepository;

    @RequestMapping(value = "show")
    public ModelAndView show() {
        Iterable<Album> albums = this.albumRepository.findAll();
        return new ModelAndView("album/list", "albums", albums);
    }


    private static final Logger logger = LoggerFactory
            .getLogger(AlbumController.class);

    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @ResponseBody
    @GetMapping
    public PaginatorResult list(Paginator paginator, Album album) {
//        if(paginator.getLimit() <=0) paginator.setLimit(10);
        Sort sort = null;
        if ("desc".equalsIgnoreCase(paginator.getOrder())) {
            sort = new Sort(Sort.Direction.DESC, new String[]{paginator.getSort()});
        } else {
            sort = new Sort(Sort.Direction.ASC, new String[]{paginator.getSort()});
        }
        Pageable pageable = new PageRequest(paginator.getOffset() /
                paginator.getLimit(), paginator.getLimit(), sort);
//        Page<Album> fields = this.albumRepository.findAll(pageable);
        Page<Album> fields = this.albumRepository.findAll(new Specification<Album>() {
            @Override
            public Predicate toPredicate(Root<Album> root,
                                         CriteriaQuery<?> q, CriteriaBuilder cb) {
                List<Predicate> list = newArrayList();
                Predicate albumName = null;
                if (!StringUtils.isEmpty(album.getAlbumName())) {
                    albumName = cb.like(root.get("albumName").as(String.class), "%" + album.getAlbumName() + "%");
                    list.add(albumName);
                }
                Predicate enabled = cb.equal(root.get("enabled").as(Integer.class), 1);
                list.add(enabled);
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        }, pageable);
        PaginatorResult result = new PaginatorResult(fields.getContent(), fields.getTotalElements());
        return result;
    }

    @GetMapping(params = "list")
    @ResponseBody
    public DataTableReturnObject list2(DataTableQueryObject dtquery) {
        DataTableReturnObject dt = new DataTableReturnObject();

        Pageable pageable = new PageRequest(dtquery.getiDisplayStart()/dtquery.getiDisplayLength(), dtquery.getiDisplayLength(), new Sort(Sort.Direction.ASC, new String[]{"albumName"}));
        Page<Album> fields = this.albumRepository.findAll(pageable);
        dt.setAaData(fields.getContent());
        dt.setiTotalDisplayRecords(fields.getTotalElements());
        dt.setiTotalRecords(dtquery.getiDisplayLength());
        return dt;
    }


/*    @ResponseBody
    @GetMapping("{id}")
    public Album view(@PathVariable("id") Album album) {
        logger.info(album.toString());
        return album;
    }*/

    @GetMapping("{id}")
    public String view(@PathVariable Long id, Model model) {
        Album album = albumRepository.findOne(id);
        model.addAttribute("album", album);
        Iterable<Teammate> teammates = teammateRepository.findAll();
        model.addAttribute("teammates", teammates);
        return "album/one";
    }

    @GetMapping(params = "show")
    public ModelAndView createForm(Model model) {
        Iterable<Album> albums = this.albumRepository.findAll();
        model.addAttribute("model", albums);
        Iterable<Teammate> teammates = teammateRepository.findAll();
        model.addAttribute("teammates", teammates);
        return new ModelAndView("album/list");
    }

    @ResponseBody
    @PostMapping
    public Album create(@Valid Album album, BindingResult result,
                        RedirectAttributes redirect) {
      /*  if (result.hasErrors()) {
            return new ModelAndView("albums/form", "formErrors", result.getAllErrors());
        }*/
        album = this.albumRepository.save(album);
        return album;
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }


    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.albumRepository.delete(id);
        Iterable<Album> albums = this.albumRepository.findAll();
        return new ModelAndView("albums/list", "albums", albums);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Album album) {
        return new ModelAndView("albums/form", "album", album);
    }
}

package bookmarks.controller;


import bookmarks.bootstraptable.Paginator;
import bookmarks.bootstraptable.PaginatorResult;
import bookmarks.datatable.DataTableQueryObject;
import bookmarks.datatable.DataTableReturnObject;
import bookmarks.sampleentity.Album;
import bookmarks.sampleentity.ReturnMessage;
import bookmarks.sampleentity.Teammate;
import bookmarks.sampleentity.dto.AlbumDto;
import bookmarks.repository.AlbumRepository;
import bookmarks.repository.TeammateRepository;
import bookmarks.repository.specs.AlbumSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    TeammateRepository teammateRepository;

    private AlbumRepository albumRepository;

    @Autowired
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
        Page<Album> fields = albumRepository.findAll(AlbumSpecs.AlbumFormCondition(album), pageable);
        PaginatorResult result = new PaginatorResult(fields.getContent(), fields.getTotalElements());
        return result;
    }

    @GetMapping(params = "list")
    @ResponseBody
    public DataTableReturnObject list2(DataTableQueryObject dtquery) {
        DataTableReturnObject dt = new DataTableReturnObject();

        Pageable pageable = new PageRequest(dtquery.getiDisplayStart() / dtquery.getiDisplayLength(), dtquery.getiDisplayLength(), new Sort(Sort.Direction.ASC, new String[]{"albumName"}));
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
    public String editForm(@PathVariable Long id, Model model) {
        Album album = albumRepository.findOne(id);
        model.addAttribute("album", album);
        Iterable<Teammate> teammates = teammateRepository.findAll();
        model.addAttribute("teammates", teammates);
        return "album/one";
    }

    @GetMapping(params = "add")
    public String addForm(Model model) {
        Album album = new Album();
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
    public Album create(Album album) {
        if(StringUtils.isEmpty(album.getId()) ) {
            album = this.albumRepository.save(album);
        }else{
            Album oldAlbum = albumRepository.findOne(album.getId());
            oldAlbum.setAlbumName(album.getAlbumName());
            oldAlbum.setAuthor(album.getAuthor());
            oldAlbum.setDescripe(album.getDescripe());
            oldAlbum.setTeammate(album.getTeammate());
            oldAlbum = this.albumRepository.save(oldAlbum);
        }
        return album;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ReturnMessage delete(@RequestBody AlbumDto dto) {
        Album[] albums = dto.getAlbums();
        List<Album> list = newArrayList();
        for(Album a : albums){
            a.setEnabled(0);
            list.add(a);
        }
        this.albumRepository.save(list);
        return new ReturnMessage(1,"OK");
    }


}

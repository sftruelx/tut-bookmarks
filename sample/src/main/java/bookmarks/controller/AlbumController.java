package bookmarks.controller;


import bookmarks.entity.Album;
import bookmarks.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @RequestMapping(value = "show")
    public ModelAndView show(){
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
    public Iterable<Album> list() {
        Iterable<Album> albums = this.albumRepository.findAll();
        return albums;
    }

    @ResponseBody
    @GetMapping("{id}")
    public Album view(@PathVariable("id") Album album) {
        logger.info(album.toString());
        return album;
    }

    @GetMapping(params = "show")
    public ModelAndView createForm(@ModelAttribute Album album) {
        Iterable<Album> albums = this.albumRepository.findAll();
        return new ModelAndView("album/list", "albums", albums);
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

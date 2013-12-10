package com.kadet.handbook.server.controller;

import com.kadet.handbook.server.entity.Chapter;
import com.kadet.handbook.server.entity.ChapterWrapper;
import com.kadet.handbook.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/8/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value = "/publisher")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * GET method
     * Get one chapter by id
     *
     * @param id id of chapter
     * @return xml of chapter
     */
    @RequestMapping(value = "/chapter/{id}", produces = {MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public
    @ResponseBody
    Chapter getChapterById (@PathVariable Long id) {
        return chapterService.findById(id);
    }

    /**
     * POST Method
     * Create new Chapter
     *
     * @param chapter new chapter
     */
    @RequestMapping(value = "/chapter",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Transactional(readOnly = false)
    public void saveBook(@RequestBody Chapter chapter) {
        chapterService.saveOrUpdate(chapter);
    }

    /**
     * DELETE Method
     * Delete chapter by id
     *
     * @param id
     */
    @RequestMapping(value = "/chapter/{id}",
            method = RequestMethod.DELETE)
    @Transactional(readOnly = false)
    public void removeBook(@PathVariable Long id) {
        chapterService.delete(id);
    }

    /**
     * GET METHOD
     * Get all books
     *
     * @return wrapper-xml wrapped all books as list
     */
    @RequestMapping(value = "/chapters", produces = {MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ChapterWrapper allBooks() {

        List<Chapter> chapterList = chapterService.findAll();
        return new ChapterWrapper(chapterList);
    }

    /**
     * PUT Method
     * Update book by id
     *
     * @param chapter
     */
    @RequestMapping(value = "/chapter",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Transactional(readOnly = false)
    public void updateBook(@RequestBody Chapter chapter) {
        chapterService.saveOrUpdate(chapter);
    }
}
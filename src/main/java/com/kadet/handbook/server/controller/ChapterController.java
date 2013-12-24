package com.kadet.handbook.server.controller;

import com.kadet.handbook.entity.*;
import com.kadet.handbook.server.service.ChapterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/8/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller("chapterController")
@RequestMapping(value = "/publisher")
public class ChapterController {

    protected static Logger logger
            = Logger.getLogger(ChapterController.class.getName());

    @Autowired
    private ChapterService chapterService;

    /**
     * GET method
     * Get one chapter by id
     *
     * @param id id of chapter
     * @return xml of chapter
     */
    @RequestMapping(value = "/chapter/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Chapter getChapterById (@PathVariable Integer id) {

        logger.debug("Find Chapter by id = " + id);
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
    public void saveChapter(@RequestBody Chapter chapter) {

        boolean result = chapterService.save(chapter);
        logger.debug("Save new chapter: " + chapter + ", save success: " + result);

    }

    /**
     * DELETE Method
     * Delete chapter by id
     *
     * @param id
     */
    @RequestMapping(value = "/chapter/{id}",
            method = RequestMethod.DELETE)
    public void removeChapter(@PathVariable Integer id) {

        boolean result = chapterService.delete(id);
        logger.debug("Remove chapter with id = " + id + ", remove success: " + result);

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
    ChapterWrapper allChapters() {

        List<Chapter> chapterList
                = chapterService.findAll();
        logger.debug("Get chapters. Chapters number: " + chapterList.size());
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
    public void updateChapter(@RequestBody Chapter chapter) {

        boolean result = chapterService.update(chapter);
        logger.debug("Update chapter: " + chapter + ", update success: " + result);

    }
}
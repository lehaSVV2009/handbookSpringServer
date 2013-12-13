package com.kadet.handbook.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/11/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ChapterWrapper implements Serializable {

    private List<Chapter> chapters;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public ChapterWrapper(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public ChapterWrapper() {
    }
}

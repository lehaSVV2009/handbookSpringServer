package com.kadet.handbook.entity;

/**
 * Date: 14.12.13
 * Time: 18:12
 *
 * @author Кадет
 */
public interface Entity<I> {

    public void setId (I id);
    public I getId ();

}

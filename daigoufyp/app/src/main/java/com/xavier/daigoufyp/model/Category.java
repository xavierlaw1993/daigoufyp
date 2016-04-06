package com.xavier.daigoufyp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 6/4/16.
 */
public class Category extends Model {
    @Expose
    @Column(name = "category_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int category_id;

    @Expose
    @Column(name = "category_name")
    public String category_name;
}

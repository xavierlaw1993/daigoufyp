package com.xavier.daigoufyp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 6/4/16.
 */
public class Country extends Model {
    @Expose
    @Column(name = "country_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int country_id;

    @Expose
    @Column(name = "country_name")
    public String country_name;
}

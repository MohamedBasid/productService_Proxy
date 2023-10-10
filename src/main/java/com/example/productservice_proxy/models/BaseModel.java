package com.example.productservice_proxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private long id;
    private Date createdOn;
    private Date lastUpdatedOn;
    private boolean isDeleted;
}

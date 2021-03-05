package com.bitirme.dataset.model;

import com.bitirme.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "QUIREC", name = "BOOK")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Book extends BaseModel {
}

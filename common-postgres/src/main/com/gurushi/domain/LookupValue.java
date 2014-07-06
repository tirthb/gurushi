package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="lookup_value")
public class LookupValue extends AbstractEntityAssigned {

    private static final long serialVersionUID = 1L;

	public LookupValue(){
	}

	public LookupValue(Long id){
		this.id = id;
	}

	@Column(name="name")
	private String name;

	@Column(name="sort_order")
	private Integer sortOrder;

	@Column(name="is_active")
	private Boolean is_active;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="lookup_type_id")
	private LookupType lookupType;

	public LookupType getLookupType() {
		return lookupType;
	}

	public void setLookupType(LookupType lookupType) {
		this.lookupType = lookupType;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "LookupValue [id=" + id + ", name=" + name + ", lookupType="
				+ lookupType + "]";
	}
}

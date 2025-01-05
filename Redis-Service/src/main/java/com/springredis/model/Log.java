package com.springredis.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7271469396100112671L;
	private Integer id;
	private String logData;

}

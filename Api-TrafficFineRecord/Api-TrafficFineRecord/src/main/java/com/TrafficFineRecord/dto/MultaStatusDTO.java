package com.TrafficFineRecord.dto;

import java.io.Serializable;

import com.TrafficFineRecord.enums.MultaStatus;

import lombok.Data;

@Data
public class MultaStatusDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private MultaStatus status;

	public MultaStatusDTO() {
	}

}

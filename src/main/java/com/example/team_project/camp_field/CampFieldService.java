package com.example.team_project.camp_field;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project._core.erroes.exception.Exception404;
import com.example.team_project.camp_field._dto.CampFieldReqDTO;
import com.example.team_project.camp_field._dto.CampFieldRespDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CampFieldService {

    private final CampFieldJPARepository campFieldJPARepository;

    //캠프장 아이디를 받아 캠프 구역 목록 조회
	public CampFieldRespDTO.CampFieldListDTO  campFieldList(CampFieldReqDTO.CampFieldListDTO requestDTO) {
		List<CampField> campfields = campFieldJPARepository.findAllByCampId(requestDTO.getCampId());
		if(campfields == null)throw new Exception404("잘못된 캠프장 명입니다.");
		return new CampFieldRespDTO.CampFieldListDTO(campfields, requestDTO.getCheckInDate(),requestDTO.getCheckOutDate());
	}

}

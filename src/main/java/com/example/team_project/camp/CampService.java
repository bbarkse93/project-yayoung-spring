package com.example.team_project.camp;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_project.camp._dto.CampRespDTO.CampListDTO;


@Transactional
@RequiredArgsConstructor
@Service
public class CampService {

    private final CampJPARepository campJPARepository;

    public List<CampListDTO> getAllCamps() {
        List<Camp> camps = campJPARepository.findAll();
        return camps.stream().map(this::convertToCampRespDto).collect(Collectors.toList());

    }

    private CampListDTO convertToCampRespDto(Camp camp) {
        // Camp 엔티티 객체를 받아서 CampRespDTO 객체로 변환하는 로직
        // 필요한 필드를 매핑하고 DTO 객체를 반환
        return new CampListDTO(
                camp.getId(),
                camp.getCampName(),
                camp.getCampAddress(),
                camp.getCampCallNumber(),
                camp.getCampWebsite(),
                camp.getCampRefundPolicy(),
                camp.isCampWater(),
                camp.isCampGarbageBag(),
                camp.getHoliday(),
                camp.getCampCheckIn(),
                camp.getCampCheckOut(),
                camp.getCampFieldImage());
    }
}

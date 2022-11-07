package com.tgshelterbot.repository;

import com.tgshelterbot.model.AnimalReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;

@Repository
public interface AnimalReportTypeRepository extends JpaRepository<AnimalReportType, Long> {
    @Query(value = "select * from animal_report_type where id in (select arsrt.report_type_id from animal_report_setup_report_type arsrt inner join animal_report_setup ars on ars.id=arsrt.setup_id inner join animal_type at on at.animal_report_setup_id=ars.id where at.id=? order by priority desc) and shelter_id=? and language_id=?;", nativeQuery = true)
    LinkedHashSet<AnimalReportType> getReportSetByAnimalType(Long typeId, Long shelter_id, Long language_id);
    @Query(value = "select * from animal_report_type art inner join animal_report_data ard on ard.report_type_id=art.id where art.id in (select arsrt.report_type_id from animal_report_setup_report_type arsrt inner join animal_report_setup ars on ars.id=arsrt.setup_id inner join animal_type at on at.animal_report_setup_id=ars.id where at.id=? order by priority desc) and art.shelter_id=? and art.language_id=? and ard.state='CREATED' and ard.animal_report_id=?;", nativeQuery = true)
    LinkedHashSet<AnimalReportType> findCreatedUserReport(Long typeId, Long shelter_id, Long language_id, Long animal_report_id);

    Optional<AnimalReportType> findFirstByTagCallback(String tag);
}
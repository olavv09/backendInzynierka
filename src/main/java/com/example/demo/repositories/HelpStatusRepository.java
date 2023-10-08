package com.example.demo.repositories;
import com.example.demo.entities.HelpStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpStatusRepository extends CrudRepository<HelpStatus, Integer> {
}

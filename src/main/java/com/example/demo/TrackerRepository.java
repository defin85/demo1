package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackerRepository extends CrudRepository<Tracker, Long> {
    List<Tracker> findByTitle(String title);
    List<Tracker> findByDesc(String desc);

    Tracker findById(long id);
}
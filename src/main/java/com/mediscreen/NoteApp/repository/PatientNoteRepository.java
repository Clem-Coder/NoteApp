package com.mediscreen.NoteApp.repository;

import com.mediscreen.NoteApp.domain.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientNoteRepository extends MongoRepository<PatientNote, Integer> {

    public List<PatientNote> findPatientNoteByPatientId(Integer id);
}

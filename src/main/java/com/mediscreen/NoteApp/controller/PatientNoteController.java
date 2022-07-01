package com.mediscreen.NoteApp.controller;

import com.mediscreen.NoteApp.domain.PatientNote;
import com.mediscreen.NoteApp.repository.PatientNoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientNoteController {

    @Autowired
    PatientNoteRepository patientNoteRepository;

    private static final Logger logger = LogManager.getLogger("PatientNoteController");


    /**
     * Get all patient's notes
     *
     * @param id The id of the Patient
     *
     * @return a list with all patient's notes
     */

    @Operation(summary = "Get a list of all patient's notes", description = "return a list of all patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All patient's notes returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "No patient's notes found", content = @Content)
    })
    @GetMapping("/patHistory/list/{id}")
    public List<PatientNote> getPatientNotesById(@PathVariable Integer id){
        logger.info("New request: get patient's notes for patient id " + id);
        return patientNoteRepository.findPatientNoteByPatientId(id);
    }

    /**
     * Get all patient's notes
     *
     * @param id The id of the Patient
     *
     * @return a list with all patient's notes
     */

    @Operation(summary = "Get a list of all patient's notes (only notes without date and practitioner)", description = "return a list of all patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All patient's notes returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "No patient's notes found", content = @Content)
    })
    @GetMapping("/patHistory/notes/get/{id}")
    public List<String> getNotesById(@PathVariable Integer id)
    {
        logger.info("New request: get list of all patient's notes (only notes without date and practitioner)");
        List<String> notes = new ArrayList<>();
        List<PatientNote> patientNotes = patientNoteRepository.findPatientNoteByPatientId(id);
        for (PatientNote patientNote : patientNotes){
            notes.add(patientNote.getNote());
        }
        return notes;
    }


    /**
     * Add patient's notes
     *
     * @param id The id of the Patient
     *
     */

    @Operation(summary = "Add new patient's note", description = "add new patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully added" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "Error during note adding", content = @Content)
    })
    @PostMapping("/patHistory/add/{id}")
    public void addPatientNote(@PathVariable Integer id, @RequestBody PatientNote patientNote){
        logger.info("New request: add note for patient id " + id);
        System.out.println(patientNote.getNoteId());
        patientNote.setPatientId(id);
        patientNoteRepository.save(patientNote);
    }







}

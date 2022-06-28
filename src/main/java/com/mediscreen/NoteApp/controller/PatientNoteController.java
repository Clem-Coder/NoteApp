package com.mediscreen.NoteApp.controller;

import com.mediscreen.NoteApp.domain.PatientNote;
import com.mediscreen.NoteApp.repository.PatientNoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientNoteController {

    @Autowired
    PatientNoteRepository patientNoteRepository;


    @Operation(summary = "Get a list of all patient's notes", description = "return a list of all patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All patient's notes returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "No patient's notes found", content = @Content)
    })
    @GetMapping("/patHistory/list/{id}")
    public List<PatientNote> getPatientNotesById(@PathVariable Integer id){
        return patientNoteRepository.findPatientNoteByPatientId(id);
    }



    @Operation(summary = "Get a list of all patient's notes (only notes without date and practitioner)", description = "return a list of all patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All patient's notes returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "No patient's notes found", content = @Content)
    })
    @GetMapping("/patHistory/notes/get/{id}")
    public List<String> getNotesById(@PathVariable Integer id)
    {
        List<String> notes = new ArrayList<>();
        List<PatientNote> patientNotes = patientNoteRepository.findPatientNoteByPatientId(id);
        for (PatientNote patientNote : patientNotes){
            notes.add(patientNote.getNote());
        }
        return notes;
    }


    @Operation(summary = "Add new patient's note", description = "add new patient's notes in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully added" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  PatientNote.class))}),
            @ApiResponse (responseCode = "404", description = "Error during note adding", content = @Content)
    })
    @PostMapping("/patHistory/add/{id}")
    public void addPatientNote(@PathVariable Integer id, @RequestBody PatientNote patientNote){

        System.out.println(patientNote.getNoteId());
        patientNote.setPatientId(id);
        patientNoteRepository.save(patientNote);
    }







}

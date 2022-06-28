package com.mediscreen.NoteApp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Getter
@Setter
@Document(collection = "patient_note")
public class PatientNote {

    @Id
    private String noteId;

    @NotBlank(message = "Patient id is mandatory")
    private Integer patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of note must be in the past")
    @NotNull(message = "Date of note is mandatory")
    private LocalDate date;
    @NotBlank(message = "Practitioner identity is mandatory")
    private String practitionerName;

    @NotBlank(message = "Practitioner's note is mandatory")
    private String note;

    public PatientNote( Integer patientId, LocalDate date, String practitionerName, String note) {
        this.patientId = patientId;
        this.date = date;
        this.practitionerName = practitionerName;
        this.note = note;
    }

    public PatientNote(Integer patientId, LocalDate date, String note) {

        this.patientId = patientId;
        this.date = date;
        this.note      = note;
    }

    public PatientNote() {

    }
}

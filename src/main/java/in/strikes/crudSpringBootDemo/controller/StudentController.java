package in.strikes.crudSpringBootDemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import in.strikes.crudSpringBootDemo.dto.CreateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.CreateStudentResponseDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentResponseDto;
import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(
        name = "Student Management",
        description = "APIs for managing student records"
)
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Operation(
            summary = "Create Student",
            description = "Creates a new student record"
    )
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentReq){

        CreateStudentResponseDto createStudent =
                studentService.createStudent(studentReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);

    }

    // Read one student

    @Operation(
            summary = "Get Student",
            description = "Get a student record by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentResp = studentService.getStudentById(id);

        return  ResponseEntity.ok(studentResp);

    }

    // Read All student
    @Operation(
            summary = "Get Students",
            description = "Get all students record"
    )
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent(){
        List<CreateStudentResponseDto> studentResp = studentService.getAllStudent();

        return  ResponseEntity.ok(studentResp);

    }

    // Update Student
    @Operation(
            summary = "Update Student",
            description = "update a existing student record"
    )
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDto> updateStudentById(@RequestParam Long id,
                                                     @RequestBody UpdateStudentRequestDto studentReq){
        UpdateStudentResponseDto studentResp =
                studentService.updateStudent(id,studentReq );

        return ResponseEntity.ok(studentResp);
    }

    @Operation(
            summary = "Delete Student",
            description = "Delete a student form database"
    )
    @DeleteMapping
    public ResponseEntity<String> deleteStudentByID(@RequestParam Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Soft Delete Student",
            description = "Marks a student record as deleted without permanently removing it from the database."
    )
    @PatchMapping("/softdelete")
    public ResponseEntity<String> softDeletedStudent(@RequestParam Long id){
        studentService.deleteStudentSoftly(id);

        return ResponseEntity.noContent().build();
    }
}

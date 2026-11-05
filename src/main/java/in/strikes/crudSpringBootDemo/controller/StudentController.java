package in.strikes.crudSpringBootDemo.controller;


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
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

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
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentResp = studentService.getStudentById(id);

        return  ResponseEntity.ok(studentResp);

    }

    // Read All student
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent(){
        List<CreateStudentResponseDto> studentResp = studentService.getAllStudent();

        return  ResponseEntity.ok(studentResp);

    }

    // Update Student

    @PutMapping
    public ResponseEntity<UpdateStudentResponseDto> updateStudentById(@RequestParam Long id,
                                                     @RequestBody UpdateStudentRequestDto studentReq){
        UpdateStudentResponseDto studentResp =
                studentService.updateStudent(id,studentReq );

        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentByID(@RequestParam Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/softdelete")
    public ResponseEntity<String> softDeletedStudent(@RequestParam Long id){
        studentService.deleteStudentSoftly(id);

        return ResponseEntity.noContent().build();
    }
}

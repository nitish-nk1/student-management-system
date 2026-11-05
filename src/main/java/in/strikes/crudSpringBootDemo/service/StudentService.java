package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.dto.CreateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.CreateStudentResponseDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentResponseDto;
import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.exception.DuplicateResourceException;
import in.strikes.crudSpringBootDemo.exception.ResourceNotFoundException;
import in.strikes.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // save to db
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto){

      Student student = mapToEnitity(studentReqDto);

      if(emailExists(student)){
            throw new DuplicateResourceException(
                    "Student Email "+student.getEmail()+" already exits");
      }

      Student studentRes = studentRepository.save(student);
      return mapToDto(studentRes);
    }

    // Get one student
    public CreateStudentResponseDto getStudentById(Long id){
        Student studentResp = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id: "+id+" not found"));

        return mapToDto(studentResp);
    }

    //Read All Student
    public List<CreateStudentResponseDto> getAllStudent(){
        List<Student> studentResp= studentRepository.findByDeletedIsFalse();

        return studentResp.stream()
                .map(this::mapToDto)
                .toList();
    }

    // update Student by Id
    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq){
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException ("Student with id:"+id+" not found"));


        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student saveStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(saveStudent);
    }

    public void deleteStudent(Long id){
      Student studentToBeDeleted = studentRepository
              .findById(id)
              .orElseThrow(()-> new ResourceNotFoundException(("Student with id:"+id+" not found")));

        studentRepository.delete(studentToBeDeleted);
    }

    // softDelete
        // get
        // delete = 1
        // save
    public boolean deleteStudentSoftly(Long id){
        Student studentToBeDeleted = studentRepository
              .findByIdAndDeletedIsFalse(id)
              .orElseThrow(()->
                      new ResourceNotFoundException("Student with id:"+id+" not found"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted );
        return true;
    }

    private Student mapToEnitity(CreateStudentRequestDto createStudentRequestDto){
        Student student = new Student();

        student.setName(createStudentRequestDto.getName());
        student.setAge(createStudentRequestDto.getAge());
        student.setEmail(createStudentRequestDto.getEmail());
        student.setRollNo(createStudentRequestDto.getRollNo());
        student.setSubject(createStudentRequestDto.getSubject());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());


        student.setDeleted(false);
        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto responseDto =new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student is create successfully");

        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();


        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student is updated successfully");

        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;

    }
    private Boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }
}

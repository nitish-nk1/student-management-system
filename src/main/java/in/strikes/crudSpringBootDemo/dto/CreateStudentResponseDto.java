package in.strikes.crudSpringBootDemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateStudentResponseDto {
    private Long id;
    private String name;
    private int age;
    private String email;
    private int rollNo;
    private String subject;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

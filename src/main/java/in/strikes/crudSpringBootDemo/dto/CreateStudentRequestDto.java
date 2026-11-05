package in.strikes.crudSpringBootDemo.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequestDto {

    @NotBlank(message = "Name can not be null ")
    @Size(min=2,max = 50,message = "Student name must be 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is require")
    @Min(value = 18, message = "Student must be 18 years old")
    private Integer age;

    @Email(message = "student email must be valid")
    @NotBlank(message = "Student email can not be blank")
    private String email;

    @NotNull(message = "Roll no is required")
    private Integer rollNo;

    @NotBlank(message = "Subject not blak")
    private String subject;
}

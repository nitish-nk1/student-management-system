package in.strikes.crudSpringBootDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequestDto {
    private String name;
    private int age;
    private int rollNo;
    private String subject;
}

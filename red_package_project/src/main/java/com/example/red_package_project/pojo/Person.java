package com.example.red_package_project.pojo;

import com.example.red_package_project.anno.Gender;
import com.example.red_package_project.anno.MustIn;
import com.example.red_package_project.anno.MustOut;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @Date 13/3/2023 0013 下午 3:17
 * @Description 测试 validation 验证
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
public class Person {

    private Integer id;

    @DecimalMin(value = "1.0")
    @DecimalMax(value = "10.0")
    private BigDecimal money;

    @NotBlank
    private String name;

    @Length(max = 10)
    @NotEmpty
    private String address;

    @Pattern(regexp = "1\\d{10}")
    private String phone;

    private String email;

    @Gender
    private String gender;

    @MustIn(scopeList = {"A", "B", "C", "D"})
    private String choose;

    @MustOut(scopeList = {"A", "B", "C", "D"})
    private String notChoose;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", money=" + money +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

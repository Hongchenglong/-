package com.oeong.controller.api;

import com.oeong.entity.Student;
import com.oeong.service.StudentService;
import com.oeong.util.ResultVOUtil;
import com.oeong.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    @ResponseBody // 将Java对象转为Json格式的数据。
    public ResultVO login(Integer id, String password) {
        Student student = studentService.findById(id);
        if (student != null) {
            String pwd = student.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes()); // md5加密
            if (pwd.equals(password)) {
                System.out.println("登录成功");
                return ResultVOUtil.success(student);
            }  else {
                return ResultVOUtil.error("您输入的账号或密码不正确");
            }
        } else {
            return ResultVOUtil.error("您输入的账号或密码不正确");
        }
    }
}

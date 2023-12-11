package com.myproject.main.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedWriter;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.main.request.ExecuteRequest;

@RestController
public class CodeExecutionController {

    @PostMapping("/execute")
    public String executeCode(@RequestBody ExecuteRequest request) {
        try {
            // Thực thi code
            String result = executeJavaCode(request);
            return result;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String executeJavaCode(ExecuteRequest request) throws Exception {
        String fileName = "TempCodeUtils.java";
        String className = "TempCodeUtils";
        String mainFunc = addFunctionMain(request);

        // Tạo một thư mục làm việc riêng biệt cho mỗi phiên biên dịch và thực thi
        String workingDirectory = System.getProperty("user.dir") + "/temp";
        Files.createDirectories(Paths.get(workingDirectory));

        try (FileWriter fileWriter = new FileWriter(workingDirectory + "/" + fileName)) {
            // Ghi dữ liệu vào file
            Files.write(Paths.get(workingDirectory, fileName), (request.getCode().substring(0, request.getCode().length() - 1)+addFunctionMain(request)).getBytes());
        } catch (Exception e) {
            // Xử lý lỗi khi không thể tạo file Java tạm thời
            return ("Error creating temporary Java file: " + e.getMessage());
        }

        ProcessBuilder processBuilder = new ProcessBuilder("javac", fileName);
        processBuilder.directory(new File(workingDirectory));
        Process process = processBuilder.start();
        process.waitFor();  // Chờ quá trình biên dịch kết thúc

        // Kiểm tra lỗi biên dịch
        if (process.exitValue() != 0) {
            Scanner errorScanner = new Scanner(process.getErrorStream());
            StringBuilder errorOutput = new StringBuilder();
            while (errorScanner.hasNext()) {
                errorOutput.append(errorScanner.nextLine()).append("\n");
            }
            // Xử lý lỗi biên dịch
            System.err.println("Compile Error:\n" + errorOutput.toString()+mainFunc);
            throw new IllegalStateException("Compile Error:\n" + errorOutput.toString());
        }

        // Thêm hàm thực thi cụ thể của hàm findMaxValue và kiểm tra kết quả
        processBuilder = new ProcessBuilder("java", className);
        processBuilder.directory(new File(workingDirectory));
        process = processBuilder.start();


        // Đọc kết quả từ đầu ra của quá trình thực thi
        Scanner scanner = new Scanner(process.getInputStream());
        StringBuilder output = new StringBuilder();
        while (scanner.hasNext()) {
            output.append(scanner.nextLine()).append("\n");
        }


        // Đọc lỗi từ đầu ra của quá trình thực thi
        Scanner errorScanner = new Scanner(process.getErrorStream());
        StringBuilder errorOutput = new StringBuilder();
        while (errorScanner.hasNext()) {
            errorOutput.append(errorScanner.nextLine()).append("\n");
        }

        // Xóa file Java tạm thời
        Files.deleteIfExists(Paths.get(workingDirectory, fileName));

        // Kiểm tra kết quả và trả về chuỗi kết quả
        if (errorOutput.length() > 0) {
            return "Compile Error:\n" + errorOutput.toString();
        } else {
            // Kiểm tra kết quả của hàm findMaxValue với giá trị kỳ vọng
            if (output.toString().trim().equals(request.getOutput().trim())) {
                return "Accepted\n"+ output;
            } else {
                return "Wrong Answer";
            }
        }
    }
    public String addFunctionMain(ExecuteRequest request) {
        List<Object> parametersRequest = request.getParameters();
        
        Object[] parameters = parametersRequest.toArray();

        StringBuilder paramStringBuilder = new StringBuilder();
       
        for (int i = 0; i < parameters.length; i++) {
            if (i > 0) {
                paramStringBuilder.append(", ");
            }
            if (parameters[i] instanceof List) {
                // Sử dụng StringBuilder để xây dựng chuỗi thay vì sử dụng chuỗi
                StringBuilder subArrayStringBuilder = new StringBuilder("new int[]");
                subArrayStringBuilder.append(parameters[i].toString().replaceAll("\\[", "\\{").replaceAll("\\]", "\\}"));
                paramStringBuilder.append(subArrayStringBuilder);
            } else if (parameters[i] instanceof Integer) {
                paramStringBuilder.append(parameters[i]);
            }
        }        
        
        return "public static void main(String[] args) {" +
                "int result = " + request.getFunctionName() + "(" + paramStringBuilder.toString() + ");" +
                "System.out.println(result);" +
                "}}";
    }


}

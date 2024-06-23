package com.myproject.main.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.Console;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.main.request.ExecuteRequest;
import com.myproject.main.response.ExecuteResponse;

@RestController
public class CodeExecutionController {

    @PostMapping("/api/executes")
    public ExecuteResponse executeCode(@RequestBody ExecuteRequest request) {
        try {
            // Thực thi code
            ExecuteResponse result = executeJavaCode(request);
            return result;
        } catch (Exception e) {
        	ExecuteResponse result = new ExecuteResponse();
        	result.setResponseString(e.getMessage());
        	result.setResponseResult(false);
            return result;
        }
    }

    private ExecuteResponse executeJavaCode(ExecuteRequest request) throws Exception {
        String fileName = "Solutions.java";
        String className = "Solutions";

        ExecuteResponse response = new ExecuteResponse();

        // Tạo một thư mục làm việc riêng biệt cho mỗi phiên biên dịch và thực thi
        String workingDirectory = System.getProperty("user.dir") + "/temp";
        Files.createDirectories(Paths.get(workingDirectory));

        try (FileWriter fileWriter = new FileWriter(workingDirectory + "/" + fileName)) {
            // Ghi dữ liệu vào file
        	String codeWithImport = "import java.util.*;" + request.getCode().trim().substring(0, request.getCode().length() - 1) + addFunctionMain(request);
            Files.write(Paths.get(workingDirectory, fileName), codeWithImport.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
        	response.setResponseString(("Error creating temporary Java file: " + e.getMessage()));
        	response.setResponseResult(false);
            // Xử lý lỗi khi không thể tạo file Java tạm thời
            return response;
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
            System.err.println("COMPILE ERROR\n\n" + errorOutput.toString()+"import java.util.*;" + request.getCode().trim().substring(0, request.getCode().length() - 1) + addFunctionMain(request));
            throw new IllegalStateException("COMPILE ERROR\n\n" + errorOutput.toString());
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
        	response.setResponseString(("COMPILE ERROR\n\n" + errorOutput.toString()));
        	response.setResponseResult(false);
            return response;
        } else {
            // Kiểm tra kết quả của hàm findMaxValue với giá trị kỳ vọng
            if (output.toString().trim().equals(request.getOutput().trim())) {
            	response.setResponseString("ACCEPTED\n\n"+ output);
            	response.setResponseResult(true);
            	return response;
            } else {
            	response.setResponseString("WRONG ANSWER\n\n"+ output);
            	response.setResponseResult(false);
                return response;
            }
        }
    }
    public String printOutpt(ExecuteRequest request) {
		
    	String result = "";
    	
    	if(request.getReturnType().equals("int[]"))
    		result = "System.out.println(Arrays.toString(result));";
    	else
    		result = "System.out.println(result);";
  	
    	return result;
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
                StringBuilder subArrayStringBuilder = new StringBuilder("new int[]");
                subArrayStringBuilder.append(parameters[i].toString().replaceAll("\\[", "\\{").replaceAll("\\]", "\\}"));
                paramStringBuilder.append(subArrayStringBuilder);
            } else if (parameters[i] instanceof Integer) {
                paramStringBuilder.append(parameters[i]);
            }
            System.out.println(paramStringBuilder.toString()); 
        }        
        
        return "public static void main(String[] args) {Solutions s = new Solutions();" +
                request.getReturnType()+" result = s." + request.getFunctionName() + "(" + paramStringBuilder.toString() + ");" +
                printOutpt(request) +
                "}}";
    }


}

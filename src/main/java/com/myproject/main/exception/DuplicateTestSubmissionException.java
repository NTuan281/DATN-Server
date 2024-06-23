package com.myproject.main.exception;

import java.sql.SQLException;

public class DuplicateTestSubmissionException extends SQLException {

    public DuplicateTestSubmissionException(String message) {
        super(message);
    }
}

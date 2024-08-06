package org.oreochex.file.exceptions;

import org.oreochex.global.exceptions.script.AlertBackException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends AlertBackException {
    public FileNotFoundException() {
        super("NOTFound.file", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}

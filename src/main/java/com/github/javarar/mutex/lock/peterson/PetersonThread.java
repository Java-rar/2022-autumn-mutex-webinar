package com.github.javarar.mutex.lock.peterson;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PetersonThread extends Thread {

    // идентификатор потока, может быть равен 0 или 1
    private final int petersonID;
}

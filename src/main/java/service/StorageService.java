package service;

import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    Resource loadAsResource(String fileName);
}

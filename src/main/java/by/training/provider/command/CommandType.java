package by.training.provider.command;

import java.net.http.HttpRequest;

public interface CommandType {
    String execute(HttpRequest request);
}

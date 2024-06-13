package com.amigoscode;

@RestController
public class PingPongController {

    record PingPong(String result){}

    @GetMapping("/ping")
    public PingPong getPingPong() {
        return new PingPong("Pong");
    }
}
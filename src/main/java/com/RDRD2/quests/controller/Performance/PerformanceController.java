package com.RDRD2.quests.controller.Performance;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/performance")
public class PerformanceController {
  
  @GetMapping("/heavy-hashing")
  public Map<String, String> getMethodName(@RequestParam int rounds, @RequestParam int hashes) {
    int strength = rounds;
    int hashesToGenerate = hashes;
    String password = "";  

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
    
    long start = System.currentTimeMillis();

    IntStream.range(0, hashesToGenerate)
      .parallel()
      .forEach(i -> encoder.encode(password));

    long durationMs = System.currentTimeMillis() - start;
    
    return Map.of("duration", durationMs + "ms");
  }
  
}

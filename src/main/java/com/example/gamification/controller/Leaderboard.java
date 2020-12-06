package com.example.gamification.controller;


import com.example.gamification.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Leaderboard {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/leaderboard")
    public List<UserScore> getDetails(){
        List<UserScore> userScores=new ArrayList<>();
        //get users
//        UserList userResponse = restTemplate.getForObject(
//                "http://localhost:8080/api/users",
//                UserList.class);
//        List<User> users= userResponse.getUsers();


        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange("http://localhost:8080/api/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users= userResponse.getBody();

        //get attempts
//        MultiplicationResultAttemptList attemptResponse = restTemplate.getForObject(
//                "http://localhost:8080/show/attempt",
//                MultiplicationResultAttemptList.class);
//        List<MultiplicationResultAttempt> attempts= attemptResponse.getAttempts();


        ResponseEntity<List<MultiplicationResultAttempt>> attemptResponse =
                restTemplate.exchange("http://localhost:8080/show/attempt",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<MultiplicationResultAttempt>>() {
                        });
        List<MultiplicationResultAttempt> attempts= attemptResponse.getBody();



       for(User user:users){
           int count=0;
           for(MultiplicationResultAttempt attempt:attempts){
               if(attempt.isCorrect()==true && attempt.getUser().getId()== user.getId())
               count++;
           }
           userScores.add(new UserScore(user.getId(),user.getAlias(),count));
       }

        return userScores.stream()
                .sorted(Comparator.comparing(UserScore::getScore).reversed())
                .collect(Collectors.toList());
    }


}

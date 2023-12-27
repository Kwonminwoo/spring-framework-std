package com.example.redis_test.controller;

import com.example.redis_test.service.RankingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 게임 리더 보드를 만든다.
 * 리더 보드는 조회가 많고 업데이트도 함께 많은 기능이다.
 * 랭킹을 함께 조회하기 때문에 조회시 리소스가 많이 들게 된다.
 * 따라서 DB로 직접 조회가 아닌 redis를 활용하여 효율을 높일 수 있다.
 * 
 */

@RestController
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/setScore")
    public Boolean setScore(@RequestParam String userId, @RequestParam int score){
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/getRank")
    public Long getUserRank(@RequestParam String userId){
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/getTopRanks")
    public List<String> getTopRanks(){
        return rankingService.getTopRank(3);
    }

}

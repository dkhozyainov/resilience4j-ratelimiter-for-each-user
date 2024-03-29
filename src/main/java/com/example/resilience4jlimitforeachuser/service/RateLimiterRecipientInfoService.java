package com.example.resilience4jlimitforeachuser.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimiterRecipientInfoService {

    private final RateLimiterManager rateLimiterManager;
    private final TargetService targetService;
    private final UserService userService;

    public String getTestInfo(Integer length) {
        Function<String, String> rateLimiterDecorator = RateLimiter.decorateFunction(
                rateLimiterManager.getLimiter(userService.getRandomUserId(), "testRateLimiter"),
                targetService::getSomethingUsefulResult);
        return rateLimiterDecorator.apply(String.valueOf(length));
    }
}

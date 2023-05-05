package com.ian.bskyapp.controller;

import com.ian.bskyapp.entity.*;
import com.ian.bskyapp.entity.dto.*;
import com.ian.bskyapp.service.BlueSkyApiService;
import com.ian.bskyapp.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static com.ian.bskyapp.util.ATProtocolUtil.getAtpUri;
import static com.ian.bskyapp.util.HttpUtil.parseHeader;

@RestController
public class BlueSkyRestController {

    private final BlueSkyApiService blueSkyApiService;
    private final UserService userService;

    public BlueSkyRestController(BlueSkyApiService blueSkyApiService, UserService userService) {
        this.blueSkyApiService = blueSkyApiService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Session getLogin(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/post")
    public void createPost(@RequestHeader HttpHeaders headers, @RequestBody CreatePostDto dto) {
        blueSkyApiService.createPost(parseHeader(headers), dto.text());
    }

    @PostMapping("/repost")
    public void repost(@RequestHeader HttpHeaders headers, @RequestBody StrongRef strongRef) {
        blueSkyApiService.repost(parseHeader(headers), strongRef);
    }

    @PostMapping("/like")
    public void like(@RequestHeader HttpHeaders headers, @RequestBody StrongRef strongRef) {
        blueSkyApiService.like(parseHeader(headers), strongRef);
    }

    @PostMapping("/follow")
    public void follow(@RequestHeader HttpHeaders headers, @RequestBody FollowDto dto) {
        blueSkyApiService.follow(parseHeader(headers), dto.did());
    }

    @GetMapping("/followers")
    public Followers getFollowers(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getFollowers(parseHeader(headers));
    }

    @GetMapping("/follows")
    public Follows getFollows(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getFollows(parseHeader(headers));
    }

    @GetMapping("/author-feed")
    public Feeds getAuthorFeed(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getAuthorFeed(parseHeader(headers));
    }

    @GetMapping("/timeline")
    public Feeds getTimeline(@RequestHeader HttpHeaders headers) {
        return blueSkyApiService.getTimeLine(parseHeader(headers));
    }

    @GetMapping("/did/{did}/post/{post}/likes")
    public Likes getLikes(@RequestHeader HttpHeaders headers, @PathVariable String did, @PathVariable String post) {
        return blueSkyApiService.getLikes(parseHeader(headers), getAtpUri(did, "app.bsky.feed.post", post));
    }

}

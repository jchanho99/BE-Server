package com.example.writeagain_be.Service;

import com.example.writeagain_be.domain.BlogPost;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private UserService userService;

    public List<BlogPost> fetchRecentPosts(Long userId) {
        String username = userService.getUsernameById(userId);
        String rssUrl = "https://v2.velog.io/rss/" + username;

        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(rssUrl)));

            return feed.getEntries().stream()
                    .limit(5)
                    .map(entry -> new BlogPost(
                            entry.getTitle(),
                            entry.getLink(),
                            extractContent(entry),
                            entry.getPublishedDate()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Velog RSS 가져오기 실패", e);
        }
    }

    private String extractContent(SyndEntry entry) {
        String content = "내용 없음"; // 기본 값

        // 1️ `content:encoded` 필드 확인 (Velog에서 본문 제공 가능성 있음)
        List<SyndContent> contents = entry.getContents();
        if (!contents.isEmpty()) {
            content = contents.get(0).getValue();
        }
        // 2️ `description` 태그 사용 (대체 가능)
        else if (entry.getDescription() != null) {
            content = entry.getDescription().getValue();
        }

        // 3️ Jsoup으로 HTML 태그 유지한 채 정리
        Document doc = Jsoup.parse(content);
        Element body = doc.body();
        return body != null ? body.html() : "내용 없음";
    }
}

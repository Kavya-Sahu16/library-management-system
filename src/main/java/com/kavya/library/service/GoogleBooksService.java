package com.kavya.library.service;

import com.kavya.library.dto.GoogleBookDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoogleBooksService {

    public List<GoogleBookDTO> searchBooks(String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);

        List<GoogleBookDTO> books = new ArrayList<>();

        List items = (List) response.get("items");

        if (items == null) return books;

        for (Object obj : items) {

            Map item = (Map) obj;
            Map volumeInfo = (Map) item.get("volumeInfo");

            String bookTitle = (String) volumeInfo.get("title");

            List authors = (List) volumeInfo.get("authors");
            String author = authors != null ? authors.get(0).toString() : "Unknown";

            String isbn = "N/A";

            List identifiers = (List) volumeInfo.get("industryIdentifiers");
            if (identifiers != null) {
                Map id = (Map) identifiers.get(0);
                isbn = id.get("identifier").toString();
            }

            books.add(new GoogleBookDTO(bookTitle, author, isbn));
        }

        return books;
    }
}
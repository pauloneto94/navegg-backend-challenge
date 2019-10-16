package com.navegg.challenge.CRUDApi.config;

import com.navegg.challenge.CRUDApi.model.Category;
import com.navegg.challenge.CRUDApi.model.Item;
import com.navegg.challenge.CRUDApi.model.Url;
import com.navegg.challenge.CRUDApi.repository.ItemRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class MyConfiguration {

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void populateDB() throws IOException {

        String urlString = "https://raw.githubusercontent.com/Navegg/navegg-backend-challenge/master/sites.csv";

        // create the url
        URL url = new URL(urlString);

        // open the url stream, wrap it an a few "readers"
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        String[] row;

        while ((row = csvReader.readNext()) != null) {

                Set<Url> urls = new HashSet<>();
                Set<Category> categories = new HashSet<>();

                for(String ur : row[1].split(";")){
                    urls.add(new Url(ur));
                }

                for(String category : row[2].split(";")){
                    categories.add(new Category(category));
                }

                Item item = new Item(row[0], urls, categories, row[3]);

                item.getUrlList().forEach(url1 -> url1.setItem(item));

                item.getCategoryList().forEach(category1 -> category1.setItem(item));

                itemRepository.save(item);

            }

        reader.close();

    }

}

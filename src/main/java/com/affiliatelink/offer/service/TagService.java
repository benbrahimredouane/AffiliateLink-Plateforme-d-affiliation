package com.affiliatelink.offer.service;

import com.affiliatelink.offer.model.Tag;
import com.affiliatelink.offer.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }
    @Transactional
    public Set<Tag> resolve(Set<String> names){
        Set<String> normalnames = new HashSet<>();
        for (String raw : names){
            normalnames.add(raw.strip().toLowerCase(Locale.ROOT));
        }
        Set<Tag> tags = new HashSet<>();
        for (String name : normalnames){
            tagRepository.insertIfNotExists(name);
            tags.add(tagRepository.findByName(name).orElseThrow());
        }
        return tags;
    }
}
